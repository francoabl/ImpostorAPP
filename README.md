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

