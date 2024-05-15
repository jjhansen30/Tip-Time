package com.tutorial.tiptime.ui.pages.tipcalculator

import androidx.compose.ui.focus.FocusState

interface State {
    fun onTextFieldValueChange(updatedValue: String)
    fun getMutableTextFieldValue(): String
    fun isUserInputValid(onFocusStateChanged: FocusState)
    fun getErrorValue(): Boolean
    fun inCreaseFocusCount()
    fun getSwitchValue(): Boolean
    fun onSwitchValueChange(switchValue: Boolean)

    companion object {
        const val FOCUS_COUNT_LIMIT = 2
        const val ZERO = 0
        const val BLANK_STRING = ""
    }
}