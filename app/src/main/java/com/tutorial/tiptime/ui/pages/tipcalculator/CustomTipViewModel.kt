package com.tutorial.tiptime.ui.pages.tipcalculator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusState
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel

class CustomTipViewModel : State, ViewModel() {

    private var tipPercent: MutableState<String> = mutableStateOf(State.BLANK_STRING)
    private var focusCount: MutableState<Int> = mutableIntStateOf(State.ZERO)
    private var isTipPercentError: MutableState<Boolean> = mutableStateOf(false)
    private var isRoundUpTip: MutableState<Boolean> = mutableStateOf(false)

    override fun onTextFieldValueChange(updatedValue: String) {
        tipPercent.value = updatedValue
    }

    override fun getMutableTextFieldValue(): String {
        return tipPercent.value
    }

    override fun isUserInputValid(onFocusStateChanged: FocusState) {
        if (!onFocusStateChanged.isFocused && focusCount.value >= State.FOCUS_COUNT_LIMIT) {
            if (!tipPercent.value.isDigitsOnly() || tipPercent.value.isEmpty()) {
                tipPercent.value = State.BLANK_STRING
                isTipPercentError.value = true
            } else {
                isTipPercentError.value = false
            }
        }
    }

    override fun getErrorValue(): Boolean {
        return isTipPercentError.value
    }

    override fun inCreaseFocusCount() {
        focusCount.value += 1
    }

    override fun getSwitchValue(): Boolean {
        return isRoundUpTip.value
    }

    override fun onSwitchValueChange(switchValue: Boolean) {
        isRoundUpTip.value = switchValue
    }
}