package com.example.impostor.ui.screen

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.impostor.manager.GameManager
import com.example.impostor.model.PlayerRole

@Composable
fun GameScreen(
    gameManager: GameManager,
    onBackToSetup: () -> Unit
) {
    val gameState by gameManager.gameState
    val currentPlayer = gameManager.getCurrentPlayer()
    
    if (gameState.gameFinished) {
        GameFinishedScreen(
            gameManager = gameManager,
            onBackToSetup = onBackToSetup
        )
    } else {
        currentPlayer?.let { player ->
            GamePlayScreen(
                player = player,
                gameManager = gameManager,
                onBackToSetup = onBackToSetup
            )
        }
    }
}

@Composable
fun GamePlayScreen(
    player: com.example.impostor.model.Player,
    gameManager: GameManager,
    onBackToSetup: () -> Unit
) {
    val gameState by gameManager.gameState
    var isCardFlipped by remember(player.id) { mutableStateOf(player.isRevealed) }
    
    // Animation for card flip
    val rotationY by animateFloatAsState(
        targetValue = if (isCardFlipped) 180f else 0f,
        animationSpec = tween(
            durationMillis = 600,
            easing = FastOutSlowInEasing
        ),
        label = "cardFlip"
    )
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top bar with back button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackToSetup) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Volver al inicio",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            
            Text(
                text = "Jugador ${gameState.currentPlayerIndex + 1} de ${gameState.players.size}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
            
            // Placeholder for symmetry
            Spacer(modifier = Modifier.size(48.dp))
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Instructions
        Text(
            text = if (!isCardFlipped) "Toca la carta para revelar tu rol" else "¡Ya conoces tu rol!",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        // Card
        Box(
            modifier = Modifier
                .size(300.dp, 400.dp)
                .graphicsLayer {
                    this.rotationY = rotationY
                    cameraDistance = 12f * density
                },
            contentAlignment = Alignment.Center
        ) {
            if (rotationY <= 90f) {
                // Front of card (back side showing nickname)
                CardBack(
                    nickname = player.nickname,
                    onClick = {
                        isCardFlipped = true
                        gameManager.revealCurrentPlayer()
                    }
                )
            } else {
                // Back of card (front side showing role)
                CardFront(
                    player = player,
                    modifier = Modifier.graphicsLayer {
                        this.rotationY = 180f
                    }
                )
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Next player button
        AnimatedVisibility(
            visible = isCardFlipped,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            Button(
                onClick = { gameManager.nextPlayer() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Siguiente jugador"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Siguiente Jugador",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun CardBack(
    nickname: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primaryContainer,
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "?",
                    fontSize = 120.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = nickname,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "Toca para revelar",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun CardFront(
    player: com.example.impostor.model.Player,
    modifier: Modifier = Modifier
) {
    val isImpostor = player.role == PlayerRole.IMPOSTOR
    val backgroundColor = if (isImpostor) {
        MaterialTheme.colorScheme.errorContainer
    } else {
        MaterialTheme.colorScheme.tertiaryContainer
    }
    
    val textColor = if (isImpostor) {
        MaterialTheme.colorScheme.onErrorContainer
    } else {
        MaterialTheme.colorScheme.onTertiaryContainer
    }
    
    Card(
        modifier = modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            backgroundColor,
                            if (isImpostor) MaterialTheme.colorScheme.error.copy(alpha = 0.3f)
                            else MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isImpostor) "💀" else "⭐",
                    fontSize = 80.sp
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = if (isImpostor) "IMPOSTOR" else "PERSONAJE",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Text(
                    text = if (isImpostor) "¡Eres el impostor!" else player.character,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = textColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = player.nickname,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun GameFinishedScreen(
    gameManager: GameManager,
    onBackToSetup: () -> Unit
) {
    val gameState by gameManager.gameState
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "🎉 ¡Juego Terminado! 🎉",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "Todos los jugadores han visto sus roles",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Resumen del juego
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Resumen del juego:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                
                gameState.players.forEach { player ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = player.nickname,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = if (player.role == PlayerRole.IMPOSTOR) "IMPOSTOR 💀" else player.character,
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (player.role == PlayerRole.IMPOSTOR) 
                                MaterialTheme.colorScheme.error 
                            else 
                                MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Botones de acción
        Button(
            onClick = {
                gameManager.restartGameWithSamePlayers()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Jugar otra vez"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Jugar Otra Vez",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        OutlinedButton(
            onClick = {
                gameManager.resetGame()
                onBackToSetup()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Volver al inicio"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Nuevo Juego",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}