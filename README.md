# ¿Quién es el Impostor? - Android Game

Una aplicación de juego local para Android donde los jugadores deben descubrir quién es el impostor entre ellos.

## Características

- **Mínimo 3 jugadores**: El juego requiere al menos 3 jugadores para comenzar
- **Juego local**: Todos los jugadores comparten el mismo dispositivo
- **Roles aleatorios**: Un jugador será el impostor y los demás tendrán el mismo personaje famoso
- **Interfaz intuitiva**: Cartas con animaciones suaves y diseño minimalista
- **Sin conexión requerida**: Funciona completamente offline

## Cómo jugar

### 1. Configuración inicial
- Abre la aplicación
- Agrega jugadores escribiendo sus nicknames
- Mínimo 3 jugadores requeridos
- Puedes eliminar jugadores tocando el ícono de eliminar

### 2. Iniciar el juego
- Toca "Iniciar Juego" cuando tengas todos los jugadores
- La app asignará automáticamente:
  - Un impostor aleatorio
  - El mismo personaje famoso a todos los demás jugadores

### 3. Revelar roles
- Cada jugador ve su carta por turno
- Toca la carta para revelar tu rol
- Si eres el impostor: ¡Memoriza tu rol secreto!
- Si tienes un personaje: Recuerda qué personaje te tocó
- Toca "Siguiente Jugador" para continuar

### 4. Jugar
- Una vez que todos conocen sus roles, ¡comienza el juego real!
- Los jugadores deben actuar como su personaje asignado
- El impostor debe fingir ser el mismo personaje que los demás
- El objetivo es descubrir quién es el impostor

## Estructura del proyecto

```
app/src/main/java/com/example/impostor/
├── MainActivity.kt                    # Actividad principal y navegación
├── manager/
│   └── GameManager.kt                # Lógica del juego
├── model/
│   └── GameModels.kt                 # Modelos de datos (Player, GameState, etc.)
├── ui/
│   ├── screen/
│   │   ├── PlayerSetupScreen.kt      # Pantalla de configuración de jugadores
│   │   └── GameScreen.kt             # Pantalla principal del juego
│   └── theme/
│       ├── Color.kt                  # Colores personalizados
│       ├── Theme.kt                  # Tema de la aplicación
│       └── Type.kt                   # Tipografía
```

## Tecnologías utilizadas

- **Kotlin**: Lenguaje principal
- **Jetpack Compose**: UI moderna y declarativa
- **Material Design 3**: Componentes de diseño
- **Android Gradle Plugin**: Build system

## Requisitos

- Android 7.0 (API 24) o superior
- Android Studio para desarrollo
- Dispositivo Android o emulador

## Instalación para desarrollo

1. Clona o descarga el proyecto
2. Abre en Android Studio
3. Sincroniza el proyecto con Gradle
4. Ejecuta en un dispositivo o emulador

## Compilar la app

```bash
# En el directorio del proyecto
./gradlew assembleDebug

# Para instalar en un dispositivo conectado
./gradlew installDebug
```

## Personalización

### Agregar más personajes famosos
Edita el archivo `model/GameModels.kt` y agrega más nombres al objeto `FamousCharacters`.

### Cambiar colores
Modifica los colores en `ui/theme/Color.kt` para personalizar la apariencia.

### Modificar animaciones
Las animaciones de las cartas están en `ui/screen/GameScreen.kt` en la función `GamePlayScreen`.

## Posibles mejoras futuras

- Modo multijugador online
- Diferentes categorías de personajes
- Temporizador para las rondas
- Sistema de puntuación
- Modo noche/día
- Sonidos y efectos
- Soporte para más idiomas

¡Disfruta del juego! 🎮