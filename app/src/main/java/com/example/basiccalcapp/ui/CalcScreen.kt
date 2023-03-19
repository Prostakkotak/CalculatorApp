package com.example.basiccalcapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CalculatorApp(calcViewModel: CalculatorViewModel = viewModel()) {
    val inputState by calcViewModel.inputState.observeAsState()
    val uiState by calcViewModel.uiState.observeAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Calculator") }) },
        content = {padding ->
            val ButtonsRowModifier: Modifier = Modifier.fillMaxWidth()

            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom,
            ) {

                ScrollableList(listItems = uiState!!.expressionsList)

                Text(
                    text = inputState.toString(),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(text = uiState!!.currentAnswer, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Gray)

                Row(
                    modifier = ButtonsRowModifier,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CalculatorButton(text = "C", onClick = {
                        calcViewModel.clearInput()
                        calcViewModel.clearAnswer()
                    })
                    CalculatorButton(text = "(", onClick = { calcViewModel.appendToInput("(") })
                    CalculatorButton(text = ")", onClick = { calcViewModel.appendToInput(")") })
                    CalculatorButton(text = "=", onClick = { calcViewModel.evaluateInput() })
                }

                Row(
                    modifier = ButtonsRowModifier,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CalculatorButton(text = "1", onClick = { calcViewModel.appendToInput("1") })
                    CalculatorButton(text = "2", onClick = { calcViewModel.appendToInput("2") })
                    CalculatorButton(text = "3", onClick = { calcViewModel.appendToInput("3") })
                    CalculatorButton(text = "+", onClick = { calcViewModel.appendToInput("+") })
                }

                Row(
                    modifier = ButtonsRowModifier,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CalculatorButton(text = "4", onClick = { calcViewModel.appendToInput("4") })
                    CalculatorButton(text = "5", onClick = { calcViewModel.appendToInput("5") })
                    CalculatorButton(text = "6", onClick = { calcViewModel.appendToInput("6") })
                    CalculatorButton(text = "-", onClick = { calcViewModel.appendToInput("-") })
                }

                Row(
                    modifier = ButtonsRowModifier,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CalculatorButton(text = "7", onClick = { calcViewModel.appendToInput("7") })
                    CalculatorButton(text = "8", onClick = { calcViewModel.appendToInput("8") })
                    CalculatorButton(text = "9", onClick = { calcViewModel.appendToInput("9") })
                    CalculatorButton(text = "*", onClick = { calcViewModel.appendToInput("*") })
                }

                Row(
                    modifier = ButtonsRowModifier,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CalculatorButton(text = "%", onClick = { calcViewModel.appendToInput("%") })
                    CalculatorButton(text = "0", onClick = { calcViewModel.appendToInput("0") })
                    CalculatorButton(text = ".", onClick = { calcViewModel.appendToInput(".") })
                    CalculatorButton(text = "/", onClick = { calcViewModel.appendToInput("/") })
                }
            }
        }
    )
}

@Composable
fun ScrollableList(listItems: MutableList<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(bottom = 24.dp),
        horizontalAlignment = Alignment.End
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            for (item in listItems) {
                Text(
                    text = item,
                    modifier = Modifier.padding(2.dp).fillMaxWidth(),
                    style = MaterialTheme.typography.body1,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Right,
                )
            }
        }
    }
}

@Composable
fun CalculatorButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(82.dp, 82.dp)
            .padding(4.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        elevation = null,
    ) {
        Text(text = text, fontSize = 24.sp)
    }
}