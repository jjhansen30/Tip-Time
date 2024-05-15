package com.tutorial.tiptime.ui.pages.tipcalculator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusState
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.tutorial.tiptime.R
import java.util.Locale
import kotlin.math.ceil

interface TipCalculatorViewModel {
    fun getBillAmount(): MutableState<String>
    fun getTipAmount(): MutableState<String>
    fun calculateTip()
    fun getBillError(): MutableState<Boolean>
    fun onBillAmountChange(onValueChange: String)
    fun onTipPercentChange(onValueChange: String)
    fun getTipPercentValue(): MutableState<String>
    fun onSwitchToggled(toggleValue: Boolean)
    fun getSwitchToggleValue(): MutableState<Boolean>
    fun isBillAmountValid(onFocusChange: FocusState, focusCount: Int)
    fun isTipAmountValid(onFocusChange: FocusState)
}

class MainPageViewModelImplementation: TipCalculatorViewModel, ViewModel() {

    private var billAmount = mutableStateOf(BLANK_STRING)
    private var tipAmount = mutableStateOf(BLANK_STRING)
    private var isBillError = mutableStateOf(false)
    private var isTipPercentError = mutableStateOf(false)
    private var tipPercentage = mutableStateOf("${R.string.tip_percentage}")
    private var isRoundUpTip = mutableStateOf(false)

    override fun getBillAmount(): MutableState<String> {
        return billAmount
    }

    override fun getTipAmount(): MutableState<String> {
        return tipAmount
    }

    override fun calculateTip() {
        isBillError.value = false
        tipAmount.value = String.format(
            locale = Locale.ENGLISH,
            format = PERCENT_FORMAT,
            (tipPercentage.value.toFloat() * billAmount.value.toInt())
        )
        roundUpTip()
    }

    override fun getBillError(): MutableState<Boolean> {
        return isBillError
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
        isRoundUpTip.value = toggleValue
    }

    override fun getSwitchToggleValue(): MutableState<Boolean> {
        return isRoundUpTip
    }

    private fun roundUpTip() {
        if (isRoundUpTip.value) {
            val roundUpValue = ceil(tipAmount.value.toFloat())
            tipAmount.value = roundUpValue.toString()
        }
    }

    override fun isBillAmountValid(onFocusChange: FocusState, focusCount: Int) {

    }

    override fun isTipAmountValid(onFocusChange: FocusState) {
        if (!onFocusChange.isFocused) {
            if (!tipAmount.value.isDigitsOnly() || tipAmount.value.isEmpty()) {
                tipAmount.value = ""
                isTipPercentError.value = true
            } else { isTipPercentError.value = false }
        }
    }
    companion object {

        const val PERCENT_FORMAT = "%.2f"
    }
}