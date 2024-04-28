package com.tutorial.tiptime.ui.pages.tipcalculator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import java.util.Locale

interface TipCalculatorViewModel {
    fun getBillAmount(): MutableState<String>
    fun getTipAmount(): MutableState<String>
    fun calculateTip()
    fun getErrorValue(): MutableState<Boolean>
    fun onBillAmountChange(onValueChange: String)
    fun onTipAmountChange(onValueChange: String)
    fun getTipPercentValue(): MutableState<String>
    fun onSwitchToggled(toggleValue: Boolean)
    fun getSwitchToggleValue(): MutableState<Boolean>
}

class TipCalculatorViewModelImplementation: TipCalculatorViewModel, ViewModel() {

    private var billAmount = mutableStateOf("")
    private var tipAmount = mutableStateOf("")
    private var isError = mutableStateOf(false)

    override fun getBillAmount(): MutableState<String> {
        return billAmount
    }

    override fun getTipAmount(): MutableState<String> {
        return tipAmount
    }

    override fun calculateTip() {
        val tipRate = 0.20f
        if (billAmount.value.isDigitsOnly()) {
            isError.value = false
            tipAmount.value = String.format(
                locale = Locale.ENGLISH,
                format = "%.2f",
                (tipRate * billAmount.value.toInt())
            )
        } else {
            billAmount.value = ""
            isError.value = true
        }
    }

    override fun getErrorValue(): MutableState<Boolean> {
        return isError
    }

    override fun onBillAmountChange(onValueChange: String) {
        billAmount.value = onValueChange
    }

    override fun onTipAmountChange(onValueChange: String) {
        TODO("Not yet implemented")
    }

    override fun getTipPercentValue(): MutableState<String> {
        TODO("Not yet implemented")
    }

    override fun onSwitchToggled(toggleValue: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getSwitchToggleValue(): MutableState<Boolean> {
        TODO("Not yet implemented")
    }
}