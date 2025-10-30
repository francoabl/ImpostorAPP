package com.example.impostor.model

import android.net.Uri

data class Player(
    val id: String = "",
    val nickname: String = "",
    val profilePhotoUri: Uri? = null,
    val role: PlayerRole = PlayerRole.NOT_ASSIGNED,
    val character: String = "",
    val isRevealed: Boolean = false
)

enum class PlayerRole {
    NOT_ASSIGNED,
    IMPOSTOR,
    CHARACTER
}

data class GameState(
    val players: List<Player> = emptyList(),
    val currentPlayerIndex: Int = 0,
    val gameStarted: Boolean = false,
    val gameFinished: Boolean = false,
    val votingPhase: Boolean = false,
    val assignedCharacter: String = "",
    val selectedCategoryId: Int? = null // null = todas las categorías
)