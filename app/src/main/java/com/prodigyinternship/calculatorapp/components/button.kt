package com.prodigyinternship.calculatorapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(
    text: String,
    onClick: () -> Unit,
    shape: androidx.compose.foundation.shape.CornerBasedShape = RoundedCornerShape(10.dp),
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.Black,
    size: Dp = 100.dp
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(size) // Adjust size for the button
            .padding(6.dp)
            .background(backgroundColor, shape = shape)
            .shadow(elevation = 4.dp, shape = shape),
        contentPadding = PaddingValues(0.dp), // Ensure content is centered
        shape = shape
    ) {
        Text(
            text = text,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = contentColor
        )

    }
}


@Composable
fun NumberButton(value: String, onClick: () -> Unit) {
    CalculatorButton(
        text = value,
        onClick = onClick,
        shape = CircleShape,
        backgroundColor = Color.LightGray,
        contentColor = Color.Black,
        size = 80.dp
    )
}

@Composable
fun IconButton(icon: ImageVector, name: String, onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .size(80.dp)  // Adjusted size for calculator button
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(10.dp)),
        contentPadding = PaddingValues(0.dp), // Ensure icon is centered
        colors = ButtonDefaults.buttonColors(),
        shape = RoundedCornerShape(5.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = name,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp) // Padding to ensure icon fits well
        )
    }
}
