package com.tutorial.tiptime.ui.pages.tipcalculator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusState
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel

class BillAmountViewModel : State, ViewModel() {

    private var focusCount: MutableState<Int> = mutableIntStateOf(State.ZERO)
    private var billAmount: MutableState<String> = mutableStateOf(State.BLANK_STRING)
    private var isBillError: MutableState<Boolean> = mutableStateOf(false)

    override fun onTextFieldValueChange(updatedValue: String) {
        billAmount.value = updatedValue
    }

    override fun getMutableTextFieldValue(): String {
        return billAmount.value
    }

    override fun isUserInputValid(onFocusStateChanged: FocusState) {
        if (!onFocusStateChanged.isFocused && focusCount.value >= State.FOCUS_COUNT_LIMIT) {
            if (!billAmount.value.isDigitsOnly() || billAmount.value.isEmpty()) {
                billAmount.value = State.BLANK_STRING
                isBillError.value = true
            } else {
                isBillError.value = false
            }
        }
    }

    override fun getErrorValue(): Boolean {
        return isBillError.value
    }

    override fun inCreaseFocusCount() {
        focusCount.value += 1
    }

    override fun getSwitchValue(): Boolean = false

    override fun onSwitchValueChange(switchValue: Boolean) {
        //Do nothing!
    }
}