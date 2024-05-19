package com.tutorial.tiptime.ui.pages.tipcalculator

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tutorial.tiptime.R
import java.util.Locale
import kotlin.math.ceil

interface MainPageViewModel {
    fun roundUpTip(isTrue: Boolean)
    fun calculateTip(
        billAmount: String,
        tipPercent: String,
        isTipRoundedUp: Boolean
    )

    fun getTipAmount(): String
}

class MainPageViewModelImplementation : MainPageViewModel, ViewModel() {

    private var tip = mutableStateOf("")

    override fun calculateTip(
        billAmount: String,
        tipPercent: String,
        isTipRoundedUp: Boolean
    ) {

        tip.value = String.format(
            locale = Locale.ENGLISH,
            format = PERCENT_FORMAT,
            (tipPercent.toFloat() * billAmount.toFloat())
        )
        roundUpTip(isTipRoundedUp)
    }

    override fun getTipAmount(): String {
        return tip.value
    }

    override fun roundUpTip(isTrue: Boolean) {
        if (isTrue) {
            val roundUpValue = ceil(tip.value.toFloat())
            tip.value = "${roundUpValue}0"
        }
    }

    companion object {
        const val PERCENT_FORMAT = "%.2f"
    }
}