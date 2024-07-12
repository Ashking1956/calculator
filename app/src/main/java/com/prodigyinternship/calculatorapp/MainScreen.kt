package com.prodigyinternship.calculatorapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prodigyinternship.calculatorapp.components.CalculatorButton

val buttonList = listOf(
    "C", "±", "%", "/",
    "7", "8", "9", "x",
    "4", "5", "6", "-",
    "1", "2", "3", "+",
    ".", "0", "<-", "="
)

@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    val result by mainViewModel.result.collectAsState()
    val currentInput by mainViewModel.currentInput.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display Stored value
        Text(
            text = if (mainViewModel._storedValue?.toString().isNullOrEmpty()) "" else mainViewModel._storedValue.toString(),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraLight,
            textAlign = TextAlign.Right,
            color = Color.Black
        )
//        Display Result
        Text(
            text = currentInput.ifEmpty { result.toString() },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Right,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Buttons
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(buttonList.size) { index ->
                val buttonText = buttonList[index]
                CalculatorButton(
                    text = buttonText,
                    onClick = {
                        when (buttonText) {
                            "C" -> mainViewModel.onClearClick()
                            "±" -> mainViewModel.onToggleSignClick()
                            "%" -> mainViewModel.onOperatorClick("%")
                            "/" -> mainViewModel.onOperatorClick("/")
                            "x" -> mainViewModel.onOperatorClick("*")
                            "-" -> mainViewModel.onOperatorClick("-")
                            "+" -> mainViewModel.onOperatorClick("+")
                            "=" -> mainViewModel.onEqualClick()
                            "<-" -> mainViewModel.onDeleteClick()
                            "." -> mainViewModel.onDecimalClick()
                            else -> mainViewModel.onNumberClick(buttonText)
                        }
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(MainViewModel())
}
