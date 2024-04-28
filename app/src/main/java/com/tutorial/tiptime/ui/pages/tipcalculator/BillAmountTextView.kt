package com.tutorial.tiptime.ui.pages.tipcalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tutorial.tiptime.R

@Composable
fun BillAmountTextView(
    headerText: String = stringResource(id = R.string.calculate_tip),
    label: String = stringResource(id = R.string.bill_amount),
    errorText: String = stringResource(id = R.string.error),
    isError: Boolean,
    onValueChange: (String) -> Unit,
    keyboardActionScope: KeyboardActionScope.() -> Unit,
    value: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = headerText)
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = { onValueChange(it) },
            label = { Text(text = label) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = keyboardActionScope)
        )
        if (isError) {
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

    BillAmountTextView(
        headerText = "Calculate Tip",
        errorText = "Value must be a number",
        isError = true,
        value = "",
        onValueChange = {},
        keyboardActionScope = {}
    )
}