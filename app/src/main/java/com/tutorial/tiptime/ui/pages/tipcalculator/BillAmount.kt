package com.tutorial.tiptime.ui.pages.tipcalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.tutorial.tiptime.R

@Composable
fun BillAmount(
    inputErrorText: String = stringResource(id = R.string.error),
    viewModel: State,
    focusRequester: FocusRequester
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = stringResource(id = R.string.calculate_tip))
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    viewModel.inCreaseFocusCount()
                    viewModel.onFocusStateChange(it)
                }
                .focusRequester(focusRequester),
            value = viewModel.getTextFieldValue(),
            onValueChange = { viewModel.onTextFieldValueChange(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                viewModel.onKeyboardButtonPress()
                if (viewModel.getIsInputValid()) {
                    this.defaultKeyboardAction(imeAction = ImeAction.Next)
                }
            }),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = viewModel.getTextFieldFontColor()
            )
        )
        if (!viewModel.getIsInputValid()) {
            Text(
                modifier = Modifier,
                text = inputErrorText,
                color = Color.Red
            )
        }
    }
}
