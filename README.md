# 🎭 ImpostorAPP# 🎭 ImpostorAPP# ¿Quién es el Impostor? - Android Game



Una aplicación móvil de juego social inspirada en Among Us, donde los jugadores deben descubrir quién es el impostor a través de pistas sobre personajes famosos.



## 📱 DescripciónUna aplicación móvil de juego social inspirada en Among Us, donde los jugadores deben descubrir quién es el impostor a través de pistas sobre personajes famosos.Una aplicación de juego local para Android donde los jugadores deben descubrir quién es el impostor entre ellos.



ImpostorAPP es un juego multijugador presencial desarrollado en Android con Kotlin y Jetpack Compose. Los jugadores reciben información sobre un personaje famoso, excepto uno (el impostor) que debe intentar pasar desapercibido mientras los demás dan pistas. El objetivo es identificar al impostor antes de que él descubra el personaje secreto.



## 👥 Integrantes## 📱 Descripción## Características



- Franco Alarcón

- Cristobal Nuñez

ImpostorAPP es un juego multijugador presencial desarrollado en Android con Kotlin y Jetpack Compose. Los jugadores reciben información sobre un personaje famoso, excepto uno (el impostor) que debe intentar pasar desapercibido mientras los demás dan pistas. El objetivo es identificar al impostor antes de que él descubra el personaje secreto.- **Mínimo 3 jugadores**: El juego requiere al menos 3 jugadores para comenzar

## ✨ Funcionalidades

- **Juego local**: Todos los jugadores comparten el mismo dispositivo

### 🎮 Sistema de Juego

- **Selección de Jugadores**: Configura entre 3 y 10 jugadores con nombres personalizados## 👥 Integrantes- **Roles aleatorios**: Un jugador será el impostor y los demás tendrán el mismo personaje famoso

- **Asignación Aleatoria de Roles**: El impostor se asigna automáticamente de forma aleatoria

- **Selección de Categorías**: Elige entre 8 categorías diferentes de personajes:- **Interfaz intuitiva**: Cartas con animaciones suaves y diseño minimalista

  - 🌍 Todas (196 personajes)

  - ⚽ Fútbol (27 futbolistas)- Franco Abusleme- **Sin conexión requerida**: Funciona completamente offline

  - 🎵 Música (32 músicos)

  - 🎬 Cine y TV (32 actores y personajes)

  - 🏀 Deportes (25 deportistas)

  - 📺 Streamers (20 creadores de contenido)## ✨ Funcionalidades## Cómo jugar

  - 🇨🇱 Famosos Chilenos (40 personalidades chilenas)

  - 🎭 Otros (20 personajes variados)



### 🖼️ Personalización### 🎮 Sistema de Juego### 1. Configuración inicial

- **Fotos de Perfil**: Opción de agregar foto desde galería o cámara para cada jugador

- **Avatar por Defecto**: Sistema de avatares automático si no se selecciona foto- **Selección de Jugadores**: Configura entre 3 y 10 jugadores con nombres personalizados- Abre la aplicación

- **Interfaz Adaptativa**: UI optimizada para diferentes tamaños de pantalla, incluyendo dispositivos con "isla de cámara"

- **Asignación Aleatoria de Roles**: El impostor se asigna automáticamente de forma aleatoria- Agrega jugadores escribiendo sus nicknames

### 💾 Base de Datos

- **Room + SQLite**: Persistencia local de 196+ personajes famosos- **Selección de Categorías**: Elige entre 8 categorías diferentes de personajes:- Mínimo 3 jugadores requeridos

- **Relaciones de Datos**: Sistema de categorías con foreign keys

- **Pre-población Automática**: Base de datos lista para usar desde el primer inicio  - 🌍 Todas (196 personajes)- Puedes eliminar jugadores tocando el ícono de eliminar



### 🎨 Diseño  - ⚽ Fútbol (27 futbolistas)

- **Material Design 3**: Interfaz moderna con Material You

- **Jetpack Compose**: UI declarativa y reactiva  - 🎵 Música (32 músicos)### 2. Iniciar el juego

- **Animaciones Fluidas**: Transiciones suaves entre pantallas

  - 🎬 Cine y TV (32 actores y personajes)- Toca "Iniciar Juego" cuando tengas todos los jugadores

## 📋 Reglas del Juego

  - 🏀 Deportes (25 deportistas)- La app asignará automáticamente:

### 🎯 Objetivo

- **No Impostores**: Descubrir quién es el impostor mediante votación  - 📺 Streamers (20 creadores de contenido)  - Un impostor aleatorio

- **Impostor**: Pasar desapercibido y deducir cuál es el personaje secreto

  - 🇨🇱 Famosos Chilenos (40 personalidades chilenas)  - El mismo personaje famoso a todos los demás jugadores

### 🎲 Desarrollo del Juego

  - 🎭 Otros (20 personajes variados)

1. **Inicio de Partida**

   - Todos los jugadores reciben el nombre del personaje famoso### 3. Revelar roles

   - El impostor recibe un mensaje indicando su rol especial (sin conocer el personaje)

### 🖼️ Personalización- Cada jugador ve su carta por turno

2. **Fase de Pistas**

   - Cada jugador debe decir una característica del personaje por turnos- **Fotos de Perfil**: Opción de agregar foto desde galería o cámara para cada jugador- Toca la carta para revelar tu rol

   - El impostor debe intentar dar pistas genéricas basándose en lo que escucha

   - Los no impostores intentan dar pistas que solo el verdadero conocedor entendería- **Avatar por Defecto**: Sistema de avatares automático si no se selecciona foto- Si eres el impostor: ¡Memoriza tu rol secreto!



3. **Votación**- **Interfaz Adaptativa**: UI optimizada para diferentes tamaños de pantalla, incluyendo dispositivos con "isla de cámara"- Si tienes un personaje: Recuerda qué personaje te tocó

   - Cada jugador puede votar **una sola vez por ronda**

   - La votación se realiza simultáneamente- Toca "Siguiente Jugador" para continuar

   - El jugador con más votos es eliminado o revelado

### 💾 Base de Datos

4. **Condiciones de Victoria**

   - ✅ **Ganan los No Impostores**: Si descubren al impostor mediante votación- **Room + SQLite**: Persistencia local de 196+ personajes famosos### 4. Jugar

   - ✅ **Gana el Impostor**: Si logra pasar desapercibido y no es descubierto

- **Relaciones de Datos**: Sistema de categorías con foreign keys- Una vez que todos conocen sus roles, ¡comienza el juego real!

### ⚠️ Reglas Importantes

- No mostrar la pantalla a otros jugadores durante la asignación de roles- **Pre-población Automática**: Base de datos lista para usar desde el primer inicio- Los jugadores deben actuar como su personaje asignado

- Ser honesto al dar las pistas (no hacer trampa)

- Respetar el límite de un voto por jugador- El impostor debe fingir ser el mismo personaje que los demás

- El impostor no puede revelar directamente que no conoce el personaje

### 🎨 Diseño- El objetivo es descubrir quién es el impostor

## 🚀 Pasos para Ejecutar

- **Material Design 3**: Interfaz moderna con Material You

### Prerrequisitos

- Android Studio Hedgehog (2023.1.1) o superior- **Jetpack Compose**: UI declarativa y reactiva## Estructura del proyecto

- JDK 17 o superior

- SDK de Android:- **Animaciones Fluidas**: Transiciones suaves entre pantallas

  - Compile SDK: 36

  - Min SDK: 24 (Android 7.0)```

  - Target SDK: 36

## 📋 Reglas del Juegoapp/src/main/java/com/example/impostor/

### Instalación

├── MainActivity.kt                    # Actividad principal y navegación

1. **Clonar el Repositorio**

   ```bash### 🎯 Objetivo├── manager/

   git clone https://github.com/francoabl/ImpostorAPP.git

   cd ImpostorAPP- **No Impostores**: Descubrir quién es el impostor mediante votación│   └── GameManager.kt                # Lógica del juego

   ```

- **Impostor**: Pasar desapercibido y deducir cuál es el personaje secreto├── model/

2. **Abrir en Android Studio**

   - Abre Android Studio│   └── GameModels.kt                 # Modelos de datos (Player, GameState, etc.)

   - Selecciona `File > Open`

   - Navega hasta la carpeta del proyecto y ábrela### 🎲 Desarrollo del Juego├── ui/

   - Espera a que Gradle sincronice las dependencias

│   ├── screen/

3. **Configurar Dispositivo**

   - **Opción A - Emulador**: Crea un AVD (Android Virtual Device) desde AVD Manager1. **Inicio de Partida**│   │   ├── PlayerSetupScreen.kt      # Pantalla de configuración de jugadores

   - **Opción B - Dispositivo Físico**: 

     - Habilita "Opciones de Desarrollador" en tu dispositivo Android   - Todos los jugadores reciben el nombre del personaje famoso│   │   └── GameScreen.kt             # Pantalla principal del juego

     - Activa "Depuración USB"

     - Conecta el dispositivo por USB   - El impostor recibe un mensaje indicando su rol especial (sin conocer el personaje)│   └── theme/



4. **Ejecutar la Aplicación**│       ├── Color.kt                  # Colores personalizados

   - Haz clic en el botón `Run` (▶️) en Android Studio

   - Selecciona tu dispositivo/emulador2. **Fase de Pistas**│       ├── Theme.kt                  # Tema de la aplicación

   - Espera a que la app se compile e instale

   - Cada jugador debe decir una característica del personaje por turnos│       └── Type.kt                   # Tipografía

### Build desde Terminal

   - El impostor debe intentar dar pistas genéricas basándose en lo que escucha```

```bash

# En Windows PowerShell:   - Los no impostores intentan dar pistas que solo el verdadero conocedor entendería

.\gradlew.bat assembleDebug



# El APK se generará en:3. **Votación**

# app\build\outputs\apk\debug\app-debug.apk   - Cada jugador puede votar **una sola vez por ronda**

```   - La votación se realiza simultáneamente

   - El jugador con más votos es eliminado o revelado

### Instalación Directa del APK

4. **Condiciones de Victoria**

```bash   - ✅ **Ganan los No Impostores**: Si descubren al impostor mediante votación

# Conecta tu dispositivo Android y ejecuta:   - ✅ **Gana el Impostor**: Si logra pasar desapercibido y no es descubierto

adb install app\build\outputs\apk\debug\app-debug.apk

```### ⚠️ Reglas Importantes

- No mostrar la pantalla a otros jugadores durante la asignación de roles

## 🛠️ Tecnologías Utilizadas- Ser honesto al dar las pistas (no hacer trampa)

- Respetar el límite de un voto por jugador

- **Lenguaje**: Kotlin 2.0.21- El impostor no puede revelar directamente que no conoce el personaje

- **UI Framework**: Jetpack Compose (BOM 2024.09.00)

- **Base de Datos**: Room 2.6.1 + SQLite## 🚀 Pasos para Ejecutar

- **Procesamiento de Anotaciones**: KSP 2.0.21-1.0.25

- **Asincronía**: Kotlin Coroutines 1.8.0### Prerrequisitos

- **Carga de Imágenes**: Coil 2.5.0- Android Studio Hedgehog (2023.1.1) o superior

- **Cámara**: CameraX 1.3.0- JDK 17 o superior

- **Build System**: Gradle 8.13.0- SDK de Android:

- **Arquitectura**: MVVM parcial + Repository Pattern  - Compile SDK: 36

  - Min SDK: 24 (Android 7.0)

## 📁 Estructura del Proyecto  - Target SDK: 36



```### Instalación

app/src/main/java/com/example/impostor/

├── data/1. **Clonar el Repositorio**

│   ├── dao/              # Data Access Objects (Room)   ```bash

│   ├── database/         # Configuración de Room Database   git clone https://github.com/francoabl/ImpostorAPP.git

│   ├── entity/           # Entidades de la base de datos   cd ImpostorAPP

│   └── repository/       # Capa de abstracción de datos   ```

├── manager/              # Lógica de negocio del juego

├── model/                # Modelos de datos del juego2. **Abrir en Android Studio**

└── ui/   - Abre Android Studio

    ├── components/       # Componentes reutilizables   - Selecciona `File > Open`

    ├── screen/           # Pantallas principales   - Navega hasta la carpeta del proyecto y ábrela

    └── theme/            # Temas y estilos   - Espera a que Gradle sincronice las dependencias

```

3. **Configurar Dispositivo**

## 🎮 Capturas de Pantalla   - **Opción A - Emulador**: Crea un AVD (Android Virtual Device) desde AVD Manager

   - **Opción B - Dispositivo Físico**: 

_(Espacio para agregar capturas en el futuro)_     - Habilita "Opciones de Desarrollador" en tu dispositivo Android

     - Activa "Depuración USB"

## 🔮 Futuras Mejoras     - Conecta el dispositivo por USB



- [ ] Sistema de votación interactivo en la app4. **Ejecutar la Aplicación**

- [ ] Estadísticas de partidas jugadas   - Haz clic en el botón `Run` (▶️) en Android Studio

- [ ] Creación de personajes personalizados   - Selecciona tu dispositivo/emulador

- [ ] Modo multijugador online   - Espera a que la app se compile e instale

- [ ] Soporte multiidioma

- [ ] Temporizador de turnos### Build desde Terminal

- [ ] Sistema de logros

```bash

## 📄 Licencia# En Windows PowerShell:

.\gradlew.bat assembleDebug

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

# El APK se generará en:

## 📧 Contacto# app\build\outputs\apk\debug\app-debug.apk

```

- **GitHub**: [@francoabl](https://github.com/francoabl)

- **Repositorio**: [ImpostorAPP](https://github.com/francoabl/ImpostorAPP)### Instalación Directa del APK



---```bash

# Conecta tu dispositivo Android y ejecuta:

Desarrollado con ❤️ usando Kotlin y Jetpack Composeadb install app\build\outputs\apk\debug\app-debug.apk

```

## 🛠️ Tecnologías Utilizadas

- **Lenguaje**: Kotlin 2.0.21
- **UI Framework**: Jetpack Compose (BOM 2024.09.00)
- **Base de Datos**: Room 2.6.1 + SQLite
- **Procesamiento de Anotaciones**: KSP 2.0.21-1.0.25
- **Asincronía**: Kotlin Coroutines 1.8.0
- **Carga de Imágenes**: Coil 2.5.0
- **Cámara**: CameraX 1.3.0
- **Build System**: Gradle 8.13.0
- **Arquitectura**: MVVM parcial + Repository Pattern

## 📁 Estructura del Proyecto

```
app/src/main/java/com/example/impostor/
├── data/
│   ├── dao/              # Data Access Objects (Room)
│   ├── database/         # Configuración de Room Database
│   ├── entity/           # Entidades de la base de datos
│   └── repository/       # Capa de abstracción de datos
├── manager/              # Lógica de negocio del juego
├── model/                # Modelos de datos del juego
└── ui/
    ├── components/       # Componentes reutilizables
    ├── screen/           # Pantallas principales
    └── theme/            # Temas y estilos
```

## 🎮 Capturas de Pantalla

_(Espacio para agregar capturas en el futuro)_

## 🔮 Futuras Mejoras

- [ ] Sistema de votación interactivo en la app
- [ ] Estadísticas de partidas jugadas
- [ ] Creación de personajes personalizados
- [ ] Modo multijugador online
- [ ] Soporte multiidioma
- [ ] Temporizador de turnos
- [ ] Sistema de logros

## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

## 📧 Contacto

- **GitHub**: [@francoabl](https://github.com/francoabl)
- **GitHub**: [@Cris7o](https://github.com/Cris7o)
- **Repositorio**: [ImpostorAPP](https://github.com/francoabl/ImpostorAPP)

---

Desarrollado con ❤️ usando Kotlin y Jetpack Compose
