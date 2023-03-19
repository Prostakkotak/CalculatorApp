package com.example.basiccalcapp.ui

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.notkamui.keval.Keval
import com.notkamui.keval.KevalInvalidExpressionException

class CalculatorViewModel: ViewModel() {

    val inputState: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }

    val uiState: MutableLiveData<CalcUiState> by lazy {
        MutableLiveData<CalcUiState>(CalcUiState())
    }

    fun appendToInput(text: String) {
        inputState.value += text

            val answer = try {

                if (!inputState.value.toString().isDigitsOnly()) {
                    Keval.eval(inputState.value.toString())
                } else {
                    ""
                }
            } catch (e: KevalInvalidExpressionException) {
                ""
            }

            uiState.value = CalcUiState(expressionsList = uiState.value!!.expressionsList, currentAnswer = answer.toString())
    }

    fun clearInput() {
        inputState.value = ""
    }

    fun clearAnswer() {
        uiState.value = CalcUiState(expressionsList = uiState.value!!.expressionsList, currentAnswer = "")
    }

    fun evaluateInput() {
        try {
            val result = Keval.eval(inputState.value.toString())

            val listCopy = uiState.value!!.expressionsList
            listCopy.add("${inputState.value} = $result")

            inputState.value = result.toString()

            uiState.value = CalcUiState(expressionsList = listCopy, currentAnswer = result.toString())
        } catch (e: KevalInvalidExpressionException) {

        }
    }
}