package com.anestevez.gigirestaurants

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.anestevez.gigirestaurants.ui.GigiApp
import com.anestevez.gigirestaurants.ui.theme.GigirestaurantsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GigirestaurantsTheme(dynamicColor = false) {
              GigiApp()
            }
        }
    }

}