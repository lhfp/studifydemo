package com.lhfp.studifydemo.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}