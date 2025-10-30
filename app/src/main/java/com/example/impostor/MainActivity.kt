package com.example.impostor

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.impostor.data.database.ImpostorDatabase
import com.example.impostor.data.repository.CharacterRepository
import com.example.impostor.manager.GameManager
import com.example.impostor.ui.screen.GameScreen
import com.example.impostor.ui.screen.FinalResultsScreen
import com.example.impostor.ui.screen.PlayerSetupScreen
import com.example.impostor.ui.screen.VotingScreen
import com.example.impostor.ui.theme.ImpostorTheme

class MainActivity : ComponentActivity() {
    private lateinit var characterRepository: CharacterRepository
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        Log.d("ImpostorApp", "🚀 Inicializando base de datos...")
        
        // Inicializar la base de datos y el repository
        val database = ImpostorDatabase.getDatabase(applicationContext)
        characterRepository = CharacterRepository(
            database.characterDao(),
            database.categoryDao()
        )
        
        Log.d("ImpostorApp", "✅ Base de datos inicializada correctamente")
        
        enableEdgeToEdge()
        setContent {
            ImpostorTheme {
                ImpostorApp(characterRepository)
            }
        }
    }
}

@Composable
fun ImpostorApp(characterRepository: CharacterRepository) {
    val gameManager = remember { GameManager(characterRepository) }
    val gameState by gameManager.gameState
    
    // Determine which screen to show
    val currentScreen = when {
        !gameState.gameStarted -> Screen.PLAYER_SETUP
        gameState.votingPhase -> Screen.VOTING
        gameState.gameFinished -> Screen.FINAL_RESULTS
        gameState.gameStarted -> Screen.GAME
        else -> Screen.PLAYER_SETUP
    }
    
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        when (currentScreen) {
            Screen.PLAYER_SETUP -> {
                PlayerSetupScreen(
                    gameManager = gameManager,
                    characterRepository = characterRepository,
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
            Screen.VOTING -> {
                VotingScreen(
                    gameManager = gameManager,
                    onRevealPlayers = {
                        gameManager.endVotingPhase()
                    }
                )
            }
            Screen.FINAL_RESULTS -> {
                FinalResultsScreen(
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
    GAME,
    VOTING,
    FINAL_RESULTS
}