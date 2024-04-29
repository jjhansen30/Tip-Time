package com.tutorial.tiptime.ui.pages.tipcalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tutorial.tiptime.R

@Composable
fun CustomTip(
    keyboardActionScope: KeyboardActionScope.() -> Unit,
    onTipChange: (String) -> Unit,
    tipPercentage: MutableState<String>,
    onSwitchToggled: (Boolean) -> Unit,
    isSwitchToggled: MutableState<Boolean>
) {
    Column {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = tipPercentage.value,
            onValueChange = { onTipChange(it) },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.LightGray
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = keyboardActionScope)
        )
        Spacer(modifier = Modifier.height(28.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(id = R.string.round_up_tip_question))
            Switch(
                checked = isSwitchToggled.value,
                onCheckedChange = { onSwitchToggled(it) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomTip() {
    CustomTip(
        tipPercentage = remember { mutableStateOf("Tip Percentage") },
        onTipChange = {},
        isSwitchToggled = remember { mutableStateOf(false) },
        onSwitchToggled = {},
        keyboardActionScope = {}
    )
}