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
    val assignedCharacter: String = ""
)

// Lista de personajes famosos para el juego
object FamousCharacters {
    val characters = listOf(
        // Futbolistas
        "Lionel Messi",
        "Cristiano Ronaldo", 
        "Neymar Jr",
        "Kylian Mbappé",
        "Erling Haaland",
        "Karim Benzema",
        "Luka Modrić",
        "Kevin De Bruyne",
        "Mohamed Salah",
        "Virgil van Dijk",
        
        // Músicos
        "Taylor Swift",
        "Ed Sheeran",
        "Ariana Grande",
        "Drake",
        "Billie Eilish",
        "The Weeknd",
        "Dua Lipa",
        "Post Malone",
        "Olivia Rodrigo",
        "Harry Styles",
        
        // Actores
        "Leonardo DiCaprio",
        "Brad Pitt",
        "Scarlett Johansson",
        "Robert Downey Jr.",
        "Emma Stone",
        "Ryan Reynolds",
        "Jennifer Lawrence",
        "Chris Hemsworth",
        "Margot Robbie",
        "Tom Holland",
        
        // Otros famosos
        "Elon Musk",
        "Oprah Winfrey",
        "Gordon Ramsay",
        "MrBeast",
        "PewDiePie"
    )
}