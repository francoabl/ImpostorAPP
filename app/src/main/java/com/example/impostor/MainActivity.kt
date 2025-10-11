package com.example.impostor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.impostor.manager.GameManager
import com.example.impostor.ui.screen.GameScreen
import com.example.impostor.ui.screen.PlayerSetupScreen
import com.example.impostor.ui.theme.ImpostorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImpostorTheme {
                ImpostorApp()
            }
        }
    }
}

@Composable
fun ImpostorApp() {
    val gameManager = remember { GameManager() }
    val gameState by gameManager.gameState
    
    // Determine which screen to show
    val currentScreen = when {
        !gameState.gameStarted -> Screen.PLAYER_SETUP
        gameState.gameStarted -> Screen.GAME
        else -> Screen.PLAYER_SETUP
    }
    
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        when (currentScreen) {
            Screen.PLAYER_SETUP -> {
                PlayerSetupScreen(
                    gameManager = gameManager,
                    onStartGame = { /* Navigation handled by gameState */ }
                )
            }
            Screen.GAME -> {
                GameScreen(
                    gameManager = gameManager,
                    onBackToSetup = { 
                        gameManager.resetGame()
                    }
                )
            }
        }
    }
}

enum class Screen {
    PLAYER_SETUP,
    GAME
}