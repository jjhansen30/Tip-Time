package com.tutorial.tiptime.ui.pages.tipcalculator

import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color

interface State {
    fun onTextFieldValueChange(newValue: String)
    fun getMutableTextFieldValue(): String
    fun isUserInputValid(onFocusStateChanged: FocusState)
    fun getIsInputValid(): Boolean
    fun inCreaseFocusCount()
    fun getSwitchValue(): Boolean
    fun onSwitchValueChange(switchValue: Boolean)
    fun onFocusStateChange(focusState: FocusState)
    fun getTextFieldFontcolor(): Color

    companion object {
        const val FOCUS_COUNT_LIMIT = 2
        const val ZERO = 0
        const val BLANK_STRING = ""
    }
}