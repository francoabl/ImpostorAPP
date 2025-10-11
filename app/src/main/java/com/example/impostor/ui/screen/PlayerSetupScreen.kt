package com.example.impostor.ui.screen

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.impostor.manager.GameManager
import com.example.impostor.model.Player
import com.example.impostor.ui.components.CircularProfileImage
import com.example.impostor.ui.components.PhotoPickerDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerSetupScreen(
    gameManager: GameManager,
    onStartGame: () -> Unit
) {
    val gameState by gameManager.gameState
    var newPlayerName by remember { mutableStateOf("") }
    var selectedPhotoUri by remember { mutableStateOf<Uri?>(null) }
    var showPhotoDialog by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "¿Quién es el Impostor?",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Text(
            text = "Agregar jugadores para comenzar",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        // Campo de entrada para nuevo jugador
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Sección de foto y datos del jugador
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Foto de perfil centrada
                    Box(
                        modifier = Modifier.width(80.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProfileImage(
                                imageUri = selectedPhotoUri,
                                size = 60.dp
                            )
                            
                            TextButton(
                                onClick = { showPhotoDialog = true },
                                modifier = Modifier.padding(0.dp),
                                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.PhotoCamera,
                                    contentDescription = "Agregar foto",
                                    modifier = Modifier.size(12.dp)
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    text = "Foto",
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.width(16.dp))
                    
                    // Campo de texto expandido y centrado verticalmente
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        OutlinedTextField(
                            value = newPlayerName,
                            onValueChange = { newPlayerName = it },
                            label = { Text("Nickname del jugador") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Jugador"
                                )
                            },
                            trailingIcon = {
                                if (newPlayerName.isNotBlank()) {
                                    IconButton(
                                        onClick = {
                                            gameManager.addPlayer(newPlayerName, selectedPhotoUri)
                                            newPlayerName = ""
                                            selectedPhotoUri = null
                                            keyboardController?.hide()
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Add,
                                            contentDescription = "Agregar jugador"
                                        )
                                    }
                                }
                            },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    if (newPlayerName.isNotBlank()) {
                                        gameManager.addPlayer(newPlayerName, selectedPhotoUri)
                                        newPlayerName = ""
                                        selectedPhotoUri = null
                                        keyboardController?.hide()
                                    }
                                }
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }
                }
                
                if (gameState.players.size < 3) {
                    Text(
                        text = "Mínimo 3 jugadores requeridos",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
        
        // Lista de jugadores
        Card(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Jugadores (${gameState.players.size})",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                
                if (gameState.players.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No hay jugadores agregados",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(gameState.players, key = { it.id }) { player ->
                            AnimatedVisibility(
                                visible = true,
                                enter = slideInVertically() + fadeIn(),
                                exit = slideOutVertically() + fadeOut()
                            ) {
                                PlayerItem(
                                    player = player,
                                    onRemovePlayer = { gameManager.removePlayer(player.id) }
                                )
                            }
                        }
                    }
                }
            }
        }
        
        // Botón para iniciar juego
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = {
                gameManager.startGame()
                onStartGame()
            },
            enabled = gameManager.canStartGame(),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Iniciar juego",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Iniciar Juego",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
    
    // Diálogo para seleccionar foto
    if (showPhotoDialog) {
        PhotoPickerDialog(
            onImageSelected = { uri ->
                selectedPhotoUri = uri
            },
            onDismiss = { showPhotoDialog = false }
        )
    }
}

@Composable
fun PlayerItem(
    player: Player,
    onRemovePlayer: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Mostrar foto de perfil o ícono por defecto
                CircularProfileImage(
                    imageUri = player.profilePhotoUri,
                    size = 40.dp,
                    borderWidth = 1.dp,
                    borderColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
                
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = player.nickname,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            
            IconButton(onClick = onRemovePlayer) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar jugador",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}