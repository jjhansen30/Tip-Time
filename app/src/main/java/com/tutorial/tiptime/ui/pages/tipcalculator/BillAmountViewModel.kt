package com.tutorial.tiptime.ui.pages.tipcalculator

import android.util.Log
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel

class BillAmountViewModel : State, ViewModel() {

    private var focusCount: MutableState<Int> = mutableIntStateOf(State.ZERO)
    private var billAmount: MutableState<String> = mutableStateOf(BILL_AMOUNT)
    private var isInputValid: MutableState<Boolean> = mutableStateOf(true)
    private var isFocused: MutableState<Boolean> = mutableStateOf(false)
    private var unFocussedFontColor: MutableState<Color> = mutableStateOf(Color.LightGray)

    override fun onTextFieldValueChange(newValue: String) {
        billAmount.value = newValue
    }

    override fun getTextFieldValue(): String {
        return billAmount.value
    }

    override fun isUserInputValid(onFocusStateChanged: FocusState) {
        if (!onFocusStateChanged.isFocused && focusCount.value >= State.FOCUS_COUNT_LIMIT) {
            if (!billAmount.value.isDigitsOnly() || billAmount.value.isEmpty()) {
                billAmount.value = State.BLANK_STRING
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
        //Do nothing!
    }

    override fun onFocusStateChange(focusState: FocusState) {
        if (focusState.isFocused && focusCount.value <= 2) {
            billAmount.value = State.BLANK_STRING
            unFocussedFontColor.value = Color.Black
        }
        isFocused.value = focusState.isFocused
    }

    override fun onKeyboardButtonPress() {
        try {
            billAmount.value.toFloat()
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