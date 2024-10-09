package com.lhfp.studifydemo.domain.util

sealed class NoteOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): NoteOrder(orderType)
    class CreatedDate(orderType: OrderType): NoteOrder(orderType)
    class UpdatedDate(orderType: OrderType): NoteOrder(orderType)
}