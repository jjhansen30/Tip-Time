package com.tutorial.tiptime.ui.pages.tipcalculator

import androidx.compose.runtime.mutableStateOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import java.util.Locale

interface TipCalculatorViewModel {
    fun getBillAmount(): String
    fun getTipAmount(): String
    fun calculateTip()
    fun getErrorValue(): Boolean
    fun onBillAmountChange(value: String)
}

class TipCalculatorViewModelImplementation: TipCalculatorViewModel, ViewModel() {

    private var billAmount = mutableStateOf("")
    private var tipAmount = mutableStateOf("")
    private var isError = mutableStateOf(false)

    override fun getBillAmount(): String {
        return billAmount.value
    }

    override fun getTipAmount(): String {
        return tipAmount.value
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

    override fun getErrorValue(): Boolean {
        return isError.value
    }

    override fun onBillAmountChange(value: String) {
        billAmount.value = value
    }
}