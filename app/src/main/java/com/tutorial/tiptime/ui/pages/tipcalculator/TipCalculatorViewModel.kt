package com.tutorial.tiptime.ui.pages.tipcalculator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.tutorial.tiptime.R
import java.util.Locale

interface TipCalculatorViewModel {
    fun getBillAmount(): MutableState<String>
    fun getTipAmount(): MutableState<String>
    fun calculateTip()
    fun getErrorValue(): MutableState<Boolean>
    fun onBillAmountChange(onValueChange: String)
    fun onTipPercentChange(onValueChange: String)
    fun getTipPercentValue(): MutableState<String>
    fun onSwitchToggled(toggleValue: Boolean)
    fun getSwitchToggleValue(): MutableState<Boolean>
}

class TipCalculatorViewModelImplementation: TipCalculatorViewModel, ViewModel() {

    private var billAmount = mutableStateOf("")
    private var tipAmount = mutableStateOf("")
    private var isError = mutableStateOf(false)
    private var tipPercentage = mutableStateOf("${R.string.tip_percentage}")
    private var switchToggleValue = mutableStateOf(false)

    override fun getBillAmount(): MutableState<String> {
        return billAmount
    }

    override fun getTipAmount(): MutableState<String> {
        return tipAmount
    }

    override fun calculateTip() {
        val tipRate = 0.20f
        if (!billAmount.value.isDigitsOnly() || billAmount.value.isEmpty()) {
            billAmount.value = ""
            isError.value = true
        } else {
            isError.value = false
            tipAmount.value = String.format(
                locale = Locale.ENGLISH,
                format = "%.2f",
                (tipRate * billAmount.value.toInt())
            )
        }
    }

    override fun getErrorValue(): MutableState<Boolean> {
        return isError
    }

    override fun onBillAmountChange(onValueChange: String) {
        billAmount.value = onValueChange
    }

    override fun onTipPercentChange(onValueChange: String) {
        tipPercentage.value = onValueChange
    }

    override fun getTipPercentValue(): MutableState<String> {
        return tipPercentage
    }

    override fun onSwitchToggled(toggleValue: Boolean) {
        switchToggleValue.value = toggleValue
    }

    override fun getSwitchToggleValue(): MutableState<Boolean> {
        return switchToggleValue
    }
}