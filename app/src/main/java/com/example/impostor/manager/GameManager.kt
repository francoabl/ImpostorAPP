package com.example.impostor.manager

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.impostor.model.FamousCharacters
import com.example.impostor.model.GameState
import com.example.impostor.model.Player
import com.example.impostor.model.PlayerRole
import java.util.UUID

class GameManager {
    private val _gameState = mutableStateOf(GameState())
    val gameState: State<GameState> = _gameState
    
    fun addPlayer(nickname: String) {
        if (nickname.isBlank()) return
        
        val currentPlayers = _gameState.value.players
        if (currentPlayers.any { it.nickname.equals(nickname, ignoreCase = true) }) {
            return // No agregar jugadores con el mismo nickname
        }
        
        val newPlayer = Player(
            id = UUID.randomUUID().toString(),
            nickname = nickname.trim()
        )
        
        _gameState.value = _gameState.value.copy(
            players = currentPlayers + newPlayer
        )
    }
    
    fun removePlayer(playerId: String) {
        val currentPlayers = _gameState.value.players
        _gameState.value = _gameState.value.copy(
            players = currentPlayers.filter { it.id != playerId }
        )
    }
    
    fun canStartGame(): Boolean {
        return _gameState.value.players.size >= 3
    }
    
    fun startGame() {
        if (!canStartGame()) return
        
        val players = _gameState.value.players
        val randomCharacter = FamousCharacters.characters.random()
        
        // Seleccionar aleatoriamente un impostor
        val impostorIndex = players.indices.random()
        
        // Asignar roles
        val updatedPlayers = players.mapIndexed { index, player ->
            if (index == impostorIndex) {
                player.copy(
                    role = PlayerRole.IMPOSTOR,
                    character = "IMPOSTOR"
                )
            } else {
                player.copy(
                    role = PlayerRole.CHARACTER,
                    character = randomCharacter
                )
            }
        }
        
        _gameState.value = _gameState.value.copy(
            players = updatedPlayers,
            gameStarted = true,
            currentPlayerIndex = 0,
            assignedCharacter = randomCharacter
        )
    }
    
    fun revealCurrentPlayer() {
        val currentState = _gameState.value
        val currentPlayerIndex = currentState.currentPlayerIndex
        
        if (currentPlayerIndex < currentState.players.size) {
            val updatedPlayers = currentState.players.mapIndexed { index, player ->
                if (index == currentPlayerIndex) {
                    player.copy(isRevealed = true)
                } else {
                    player
                }
            }
            
            _gameState.value = currentState.copy(
                players = updatedPlayers
            )
        }
    }
    
    fun nextPlayer() {
        val currentState = _gameState.value
        val nextIndex = currentState.currentPlayerIndex + 1
        
        if (nextIndex >= currentState.players.size) {
            // Juego terminado
            _gameState.value = currentState.copy(
                gameFinished = true
            )
        } else {
            _gameState.value = currentState.copy(
                currentPlayerIndex = nextIndex
            )
        }
    }
    
    fun getCurrentPlayer(): Player? {
        val currentState = _gameState.value
        return if (currentState.currentPlayerIndex < currentState.players.size) {
            currentState.players[currentState.currentPlayerIndex]
        } else {
            null
        }
    }
    
    fun resetGame() {
        _gameState.value = GameState()
    }
    
    fun restartGameWithSamePlayers() {
        val currentPlayers = _gameState.value.players.map { player ->
            player.copy(
                role = PlayerRole.NOT_ASSIGNED,
                character = "",
                isRevealed = false
            )
        }
        
        _gameState.value = GameState(
            players = currentPlayers
        )
    }
}