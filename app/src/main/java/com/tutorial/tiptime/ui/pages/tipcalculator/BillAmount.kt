package com.tutorial.tiptime.ui.pages.tipcalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tutorial.tiptime.R

@Composable
fun BillAmount(
    errorText: String = stringResource(id = R.string.error),
    isError: MutableState<Boolean>,
    onValueChange: (String) -> Unit,
    value: MutableState<String>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = stringResource(id = R.string.calculate_tip))
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value.value,
            onValueChange = { onValueChange(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.LightGray
            )
        )
        if (isError.value) {
            Text(
                modifier = Modifier,
                text = errorText,
                color = Color.Red
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun PreviewTextField() {

    BillAmount(
        errorText = "Value must be a number",
        isError = remember { mutableStateOf(false) },
        value = remember { mutableStateOf("Bill Amount") },
        onValueChange = {}
    )
}