package com.jefferson.bestfdjtest.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jefferson.bestfdjtest.sportsleagues.presentation.ui.SportsLeaguesScreen
import com.jefferson.bestfdjtest.style.theme.FdjTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BestFdjActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FdjTestTheme {
                SportsLeaguesScreen(
                    splashScreen = installSplashScreen(),
                )
            }
        }
    }
}
