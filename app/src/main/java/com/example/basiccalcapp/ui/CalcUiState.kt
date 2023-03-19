package com.example.basiccalcapp.ui

data class CalcUiState(
    val expressionsList: MutableList<String> = mutableListOf(),
    val currentAnswer: String = ""
)