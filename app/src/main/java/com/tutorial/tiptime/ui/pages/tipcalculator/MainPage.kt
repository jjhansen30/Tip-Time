package com.tutorial.tiptime.ui.pages.tipcalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tutorial.tiptime.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    mainPageViewModel: MainPageViewModel,
    billAmountViewModel: State,
    customTipViewModel: State
) {
    val focusRequester = remember { FocusRequester }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }) },
        modifier = Modifier
            .padding()
            .verticalScroll(rememberScrollState())
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 32.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BillAmount(
                viewModel = billAmountViewModel,
                focusRequester = focusRequester.Default
            )
            Spacer(modifier = Modifier.height(24.dp))
            CustomTip(
                viewModel = customTipViewModel,
                keyboardActionScope = {
                    customTipViewModel.onKeyboardButtonPress()
                    if (customTipViewModel.getIsInputValid()) {
                        mainPageViewModel.calculateTip(
                            billAmount = billAmountViewModel.getTextFieldValue(),
                            tipPercent = customTipViewModel.getTextFieldValue(),
                            isTipRoundedUp = customTipViewModel.getSwitchValue()
                        )
                        this.defaultKeyboardAction(imeAction = ImeAction.Done)
                    }
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            TipAmount(tipTotal = mainPageViewModel.getTipAmount())
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewCalculator() {
    val viewModel = object : MainPageViewModel {
        override fun roundUpTip(isTrue: Boolean) {}

        override fun calculateTip(
            billAmount: String,
            tipPercent: String,
            isTipRoundedUp: Boolean
        ) {
        }

        override fun getTipAmount(): String = "2.34"
    }
    val state = object : State {
        override fun onTextFieldValueChange(newValue: String) {}

        override fun getTextFieldValue(): String = ""

        override fun isUserInputValid(onFocusStateChanged: FocusState) {}

        override fun getIsInputValid(): Boolean = false

        override fun inCreaseFocusCount() {}

        override fun getSwitchValue(): Boolean = false

        override fun getTextFieldFontColor(): Color = Color.LightGray

        override fun onSwitchValueChange(switchValue: Boolean) {}

        override fun onFocusStateChange(focusState: FocusState) {}

        override fun onKeyboardButtonPress() {}

    }
    MainPage(
        mainPageViewModel = viewModel,
        billAmountViewModel = state,
        customTipViewModel = state
    )
}