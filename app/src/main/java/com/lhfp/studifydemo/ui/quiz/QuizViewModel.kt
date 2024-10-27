package com.lhfp.studifydemo.ui.quiz

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.lhfp.studifydemo.domain.model.QuizWithQuestions
import com.lhfp.studifydemo.domain.model.SubjectWithNotes
import com.lhfp.studifydemo.domain.usecases.quiz.QuizUseCases
import com.lhfp.studifydemo.ui.quiz.quiz_list.QuizListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor (private val quizUseCases: QuizUseCases) : ViewModel() {

    private val _quizState = mutableStateOf(QuizState())
    val quizState: State<QuizState> = _quizState

    private val _quizListState = mutableStateOf(QuizListState())
    val quizListState: State<QuizListState> = _quizListState

    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-pro-latest",
        apiKey = "AIzaSyBrixujouD9UWWOvf52S9x5d7v-cCSrQpI"
    )

    init {
        getQuizzes()
    }

    private var getQuizzesJob: Job? = null

    private fun getQuizzes() {
        getQuizzesJob?.cancel()
        quizUseCases.getAllQuizzesWithQuestions().onEach {
            _quizListState.value = quizListState.value.copy(quizzes = it)
        }.launchIn(viewModelScope)
    }

    fun generateQuiz(subjectWithNotes: SubjectWithNotes, subjectId: Int) {
        Log.i("QUIZ GENERATOR::", "Generating quiz...")
        _quizListState.value = _quizListState.value.copy(uiState = UIState.Loading)
        var subjectContent = ""
        runCatching {
            subjectContent = getSubjectContent(subjectWithNotes)
        }.onFailure {
            _quizListState.value = _quizListState.value.copy(uiState = UIState.Error(it.message))
        }
        if (subjectContent.isEmpty()) return
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = generativeModel.generateContent(content {
                    text(
                        PROMPT_PREFIX + subjectContent + PROMPT_SUFFIX
                    )
                })

                response.text?.let {
                    val responseString = it.replace("```json", "").replace("```", "")
                    Log.i("QUIZ GENERATOR::", responseString)
                    insertGeneratedQuiz(responseString, subjectId)
                }
            } catch (e: Exception) {
                _quizListState.value = quizListState.value.copy(uiState = UIState.Error("Something went wrong"))
                Log.i("QUIZ GENERATOR::", e.message.toString())
            }
        }
    }

    private fun insertGeneratedQuiz(generativeResponse: String, subjectId: Int) {
        viewModelScope.launch {
            quizUseCases.createQuiz(generativeResponse, subjectId).let {
                _quizListState.value = _quizListState.value.copy(uiState = UIState.OnQuizReady(it.toInt()))
            }
        }
    }

    fun getSubjectContent(subjectWithNotes: SubjectWithNotes): String {
        val subjectContent = StringBuilder()
        subjectWithNotes.notes.forEach { note ->
            subjectContent.append(note.title)
            subjectContent.append("\n")
            subjectContent.append(note.content)
            subjectContent.append("\n")
        }
        if (subjectContent.length < 300) throw SubjectContentTooShortException()

        return subjectContent.toString()
    }

    fun onUIEvent(uiState: UIState) {
        _quizListState.value = _quizListState.value.copy(uiState = uiState)
    }

    fun finishQuiz(quiz: QuizWithQuestions): Job {
        val newQuiz = quiz.quiz.copy(isCompleted = true, completedAt = System.currentTimeMillis())
        _quizState.value = _quizState.value.copy(uiState = UIState.Loading)
        return viewModelScope.launch {
            quizUseCases.updateQuiz(newQuiz)
        }
    }

    companion object {
        private const val PROMPT_PREFIX = "given the following text:"
        private const val PROMPT_SUFFIX = "using only this text above, please create a quiz with 10 questions about these points made. also don't reference or acknowledge the text's existence as part of the questions or in any answers.\n" +
                "\n" +
                "\n" +
                "\n" +
                "answer this question with just JSON text response given the following objects:\n" +
                "\n" +
                "\n" +
                "\n" +
                "data class Quiz(\n" +
                "\n" +
                "val name: String,\n" +
                "\n" +
                "val questions: List<QuestionWithOptions>\n" +
                "\n" +
                ")\n" +
                "\n" +
                "PS.: 'name' field -> please generate a name for this based on the theme of the text you read\n" +
                "\n" +
                "\n" +
                "\n" +
                "data class QuestionWithOptions(\n" +
                "\n" +
                "val question: Question,\n" +
                "\n" +
                "val options: List<Option>\n" +
                "\n" +
                ")\n" +
                "\n" +
                "\n" +
                "\n" +
                "data class Question(\n" +
                "\n" +
                "val text: String\n" +
                "\n" +
                ")\n" +
                "\n" +
                "PS.: 'text' field represents the question's wording\n" +
                "\n" +
                "\n" +
                "\n" +
                "data class Option(\n" +
                "\n" +
                "val answer: String,\n" +
                "\n" +
                "val isCorrect: Boolean\n" +
                "\n" +
                ")\n" +
                "\n" +
                "PS.: \n" +
                "\n" +
                "- 'answer' field represents the option's wording\n" +
                "\n" +
                "- 'isCorrect' determines if this option would be correct for that question or no (only one from the list could be correct)."
    }
}

class SubjectContentTooShortException() : Throwable() {
    override val message: String
        get() = "The content for this subject is too short to generate a quiz. Please add more notes to use this function"
}
