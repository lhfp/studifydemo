package com.lhfp.studifydemo.domain.util

sealed class SubjectOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): SubjectOrder(orderType)
    class Date(orderType: OrderType): SubjectOrder(orderType)
    class Color(orderType: OrderType): SubjectOrder(orderType)
}