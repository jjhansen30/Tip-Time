package com.tutorial.tiptime.ui.pages.tipcalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutorial.tiptime.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipCalculator(
    viewModel: TipCalculatorViewModel,
    topAppBarTitle: String = stringResource(id = R.string.app_name),
    tipAmountText: String = stringResource(id = R.string.tip_amount),
    tipAmountFontSize: TextUnit = 32.sp
) {

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = topAppBarTitle) }) },
        modifier = Modifier.padding()
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 32.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BillAmountTextView(
                value = viewModel.getBillAmount(),
                onValueChange = viewModel::onBillAmountChange,
                isError = viewModel.getErrorValue(),
                keyboardActionScope = {viewModel.calculateTip()}
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$tipAmountText: ",
                    fontSize = tipAmountFontSize,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$"+viewModel.getTipAmount(),
                    fontSize = tipAmountFontSize,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewCalculator() {
    val viewModel = object : TipCalculatorViewModel {
        override fun getBillAmount(): String = "10.00"

        override fun getTipAmount(): String = "0.01"

        override fun calculateTip() {}
        override fun getErrorValue(): Boolean = false

        override fun onBillAmountChange(value: String) {}
    }
    TipCalculator(viewModel = viewModel)
}