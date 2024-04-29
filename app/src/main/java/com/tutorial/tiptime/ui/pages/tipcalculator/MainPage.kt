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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tutorial.tiptime.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    viewModel: TipCalculatorViewModel
) {

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }) },
        modifier = Modifier
            .padding()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 32.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BillAmount(
                value = viewModel.getBillAmount(),
                onValueChange = viewModel::onBillAmountChange,
                isError = viewModel.getErrorValue()
            )
            Spacer(modifier = Modifier.height(24.dp))
            CustomTip(
                onTipChange = viewModel::onTipPercentChange,
                tipPercentage = viewModel.getTipPercentValue(),
                onSwitchToggled = viewModel::onSwitchToggled,
                isSwitchToggled = viewModel.getSwitchToggleValue(),
                keyboardActionScope = { viewModel.calculateTip() }
            )
            Spacer(modifier = Modifier.height(12.dp))
            TipAmount(tipTotal = viewModel.getTipAmount())
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewCalculator() {
    val viewModel = object : TipCalculatorViewModel {
        override fun getBillAmount(): MutableState<String> = mutableStateOf("Bill Amount")

        override fun getTipAmount(): MutableState<String> = mutableStateOf("0.34")

        override fun calculateTip() {}
        override fun getErrorValue(): MutableState<Boolean> = mutableStateOf(false)

        override fun onBillAmountChange(onValueChange: String) {}
        override fun onTipPercentChange(onValueChange: String) {}

        override fun getTipPercentValue(): MutableState<String> = mutableStateOf("0%")

        override fun onSwitchToggled(toggleValue: Boolean) {}

        override fun getSwitchToggleValue(): MutableState<Boolean> = mutableStateOf(false)
    }
    MainPage(viewModel = viewModel)
}