package com.tutorial.tiptime.ui.pages.tipcalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
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
    viewModel: State
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = stringResource(id = R.string.calculate_tip))
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    viewModel.inCreaseFocusCount()
                    viewModel.isUserInputValid(focusState)
                },
            value = viewModel.getMutableTextFieldValue(),
            onValueChange = { viewModel.onTextFieldValueChange(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.LightGray
            )
        )
        if (viewModel.getErrorValue()) {
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
        viewModel = BillAmountViewModel(),
        errorText = "Value must be a number"
    )
}