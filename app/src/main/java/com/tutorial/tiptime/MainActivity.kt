package com.tutorial.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tutorial.tiptime.ui.pages.tipcalculator.BillAmountViewModel
import com.tutorial.tiptime.ui.pages.tipcalculator.CustomTipViewModel
import com.tutorial.tiptime.ui.pages.tipcalculator.MainPageViewModelImplementation
import com.tutorial.tiptime.ui.pages.tipcalculator.MainPage
import com.tutorial.tiptime.ui.theme.TipTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainPageViewModel: MainPageViewModelImplementation = viewModel()
            val billAmountViewModel: BillAmountViewModel = viewModel()
            val customTipViewModel: CustomTipViewModel = viewModel()
            TipTimeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainPage(
                        mainPageViewModel = mainPageViewModel,
                        billAmountViewModel = billAmountViewModel,
                        customTipViewModel = customTipViewModel
                    )
                }
            }
        }
    }
}