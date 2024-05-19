package com.tutorial.tiptime.ui.pages.tipcalculator

import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color

interface State {
    fun onTextFieldValueChange(newValue: String)
    fun onSwitchValueChange(switchValue: Boolean)
    fun onFocusStateChange(focusState: FocusState)
    fun onKeyboardButtonPress()
    fun getTextFieldValue(): String
    fun getSwitchValue(): Boolean
    fun getTextFieldFontColor(): Color
    fun getIsInputValid(): Boolean
    fun isUserInputValid(onFocusStateChanged: FocusState)
    fun inCreaseFocusCount()

    companion object {
        const val FOCUS_COUNT_LIMIT = 2
        const val ZERO = 0
        const val BLANK_STRING = ""
    }
}