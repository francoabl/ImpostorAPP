package com.example.impostor.manager

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.impostor.data.repository.CharacterRepository
import com.example.impostor.model.GameState
import com.example.impostor.model.Player
import com.example.impostor.model.PlayerRole
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class GameManager(private val characterRepository: CharacterRepository) {
    private val _gameState = mutableStateOf(GameState())
    val gameState: State<GameState> = _gameState
    
    private val scope = CoroutineScope(Dispatchers.Main)
    
    fun addPlayer(nickname: String, profilePhotoUri: Uri? = null) {
        if (nickname.isBlank()) return
        
        val currentPlayers = _gameState.value.players
        if (currentPlayers.any { it.nickname.equals(nickname, ignoreCase = true) }) {
            return // No agregar jugadores con el mismo nickname
        }
        
        val newPlayer = Player(
            id = UUID.randomUUID().toString(),
            nickname = nickname.trim(),
            profilePhotoUri = profilePhotoUri
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
    
    fun setSelectedCategory(categoryId: Int?) {
        _gameState.value = _gameState.value.copy(selectedCategoryId = categoryId)
    }
    
    fun startGame() {
        if (!canStartGame()) return
        
        scope.launch {
            try {
                val players = _gameState.value.players
                val selectedCategoryId = _gameState.value.selectedCategoryId
                
                // Obtener un personaje aleatorio de la base de datos
                val randomCharacterEntity = if (selectedCategoryId == null || selectedCategoryId == 1) {
                    // Categoría "Todas" o ninguna seleccionada - obtener cualquier personaje
                    characterRepository.getRandomCharacter()
                } else {
                    // Obtener personaje de la categoría específica
                    characterRepository.getRandomCharacterFromCategory(selectedCategoryId)
                }
                
                val randomCharacter = randomCharacterEntity?.name ?: "Personaje Desconocido"
                
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
            } catch (e: Exception) {
                // En caso de error, usar un personaje por defecto
                e.printStackTrace()
                startGameWithDefaultCharacter()
            }
        }
    }
    
    private fun startGameWithDefaultCharacter() {
        val players = _gameState.value.players
        val randomCharacter = "Personaje Misterioso"
        
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
            // Todos han revelado sus cartas, ir a la fase de votación
            _gameState.value = currentState.copy(
                votingPhase = true
            )
        } else {
            _gameState.value = currentState.copy(
                currentPlayerIndex = nextIndex
            )
        }
    }
    
    fun startVotingPhase() {
        _gameState.value = _gameState.value.copy(votingPhase = true)
    }
    
    fun endVotingPhase() {
        _gameState.value = _gameState.value.copy(
            votingPhase = false,
            gameFinished = true
        )
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