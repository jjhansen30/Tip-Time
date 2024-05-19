package com.tutorial.tiptime.ui.pages.tipcalculator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel

class CustomTipViewModel : State, ViewModel() {

    private var focusCount: MutableState<Int> = mutableIntStateOf(State.ZERO)
    private var tipPercentage: MutableState<String> = mutableStateOf(BILL_AMOUNT)
    private var isInputValid: MutableState<Boolean> = mutableStateOf(true)
    private var isFocused: MutableState<Boolean> = mutableStateOf(false)
    private var unFocussedFontColor: MutableState<Color> = mutableStateOf(Color.LightGray)
		private var isRoundedUp: MutableState<Boolean> = mutableStateOf(false)

    override fun onTextFieldValueChange(newValue: String) {
        tipPercentage.value = newValue
    }

    override fun getTextFieldValue(): String {
        return tipPercentage.value
    }

    override fun isUserInputValid(onFocusStateChanged: FocusState) {
        if (!onFocusStateChanged.isFocused && focusCount.value >= State.FOCUS_COUNT_LIMIT) {
            if (!tipPercentage.value.isDigitsOnly() || billAmount.value.isEmpty()) {
                tipPercentage.value = State.BLANK_STRING
                isInputValid.value = true
            } else {
                isInputValid.value = false
            }
        }
    }

    override fun getIsInputValid(): Boolean {
        return isInputValid.value
    }

    override fun inCreaseFocusCount() {
        focusCount.value += 1
    }

    override fun getSwitchValue(): Boolean = false

    override fun onSwitchValueChange(switchValue: Boolean) {
        isRoundedUp.value = !isRoundedUp.value
    }

    override fun onFocusStateChange(focusState: FocusState) {
        if (focusState.isFocused && focusCount.value <= 2) {
            tipPercentage.value = State.BLANK_STRING
            unFocussedFontColor.value = Color.Black
        }
        isFocused.value = focusState.isFocused
    }

    override fun onKeyboardButtonPress() {
        try {
            tipPercentage.value.toFloat()
            isInputValid.value = true
        } catch (e: Exception) {
            Log.e(TAG, "${e.message}")
            isInputValid.value = false
        }
    }

    override fun getTextFieldFontColor(): Color {
        return unFocussedFontColor.value
    }

    companion object {
        const val TAG = "BillAmountViewModel"
        const val BILL_AMOUNT = "Bill Amount"
    }
}