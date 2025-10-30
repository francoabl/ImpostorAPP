package com.example.impostor.data.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.impostor.data.dao.CategoryDao
import com.example.impostor.data.dao.CharacterDao
import com.example.impostor.data.entity.CategoryEntity
import com.example.impostor.data.entity.CharacterEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Database(
    entities = [CategoryEntity::class, CharacterEntity::class],
    version = 2,
    exportSchema = false
)
abstract class ImpostorDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun characterDao(): CharacterDao
    
    companion object {
        @Volatile
        private var INSTANCE: ImpostorDatabase? = null
        
        fun getDatabase(context: Context): ImpostorDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ImpostorDatabase::class.java,
                    "impostor_database"
                )
                    .addCallback(ImpostorDatabaseCallback())
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class ImpostorDatabaseCallback : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        populateDatabase(database.categoryDao(), database.characterDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(categoryDao: CategoryDao, characterDao: CharacterDao) {
            // Verificar si ya hay datos para evitar la duplicación
            if (characterDao.getCharacterCount() > 0) {
                Log.d("ImpostorDatabase", "⚠️ La base de datos ya está poblada. No se realizarán inserciones.")
                return
            }

            Log.d("ImpostorDatabase", "🔄 Poblando la base de datos por primera vez...")

            val categories = listOf(
                CategoryEntity(1, "Todas", "Todos los personajes disponibles", "grid_view"),
                CategoryEntity(2, "Fútbol", "Jugadores de fútbol profesional", "sports_soccer"),
                CategoryEntity(3, "Música", "Cantantes y músicos famosos", "music_note"),
                CategoryEntity(4, "Cine y TV", "Actores y actrices reconocidos", "movie"),
                CategoryEntity(5, "Deportes Varios", "Atletas de diferentes disciplinas", "sports"),
                CategoryEntity(6, "Streamers y YouTubers", "Creadores de contenido digital", "videocam"),
                CategoryEntity(7, "Famosos Chilenos", "Personalidades destacadas de Chile", "flag"),
                CategoryEntity(8, "Otros Famosos", "Celebridades y personajes públicos", "star")
            )
            categoryDao.insertCategories(categories)

            // Insertar personajes por categoría
            val characters = mutableListOf<CharacterEntity>()

            // Futbolistas (Categoría 2)
            val footballPlayers = listOf(
                "Lionel Messi", "Cristiano Ronaldo", "Neymar Jr", "Kylian Mbappé",
                "Erling Haaland", "Karim Benzema", "Luka Modrić", "Kevin De Bruyne",
                "Mohamed Salah", "Virgil van Dijk", "Harry Kane", "Robert Lewandowski",
                "Vinicius Jr", "Jude Bellingham", "Rodri", "Ángel Di María",
                "Sergio Agüero", "Zlatan Ibrahimović", "Sergio Ramos", "Gerard Piqué",
                "Andrés Iniesta", "Xavi Hernández", "Paul Pogba", "Toni Kroos",
                "Manuel Neuer", "Gianluigi Buffon", "Iker Casillas"
            )
            footballPlayers.forEach { name ->
                characters.add(CharacterEntity(name = name, categoryId = 2, description = "Futbolista"))
            }
            Log.d("ImpostorDatabase", "✅ Agregados ${footballPlayers.size} futbolistas")
            
            // Músicos (Categoría 3)
            val musicians = listOf(
                "Taylor Swift", "Ed Sheeran", "Ariana Grande", "Drake",
                "Billie Eilish", "The Weeknd", "Dua Lipa", "Post Malone",
                "Olivia Rodrigo", "Harry Styles", "Bad Bunny", "Shakira",
                "J Balvin", "Karol G", "Peso Pluma", "Bizarrap",
                "Duki", "Emilia Mernes", "María Becerra", "Tini Stoessel",
                "Paulo Londra", "Nicki Nicole", "Trueno", "Lit Killah",
                "Coldplay", "The Beatles", "Queen", "Michael Jackson",
                "Madonna", "Beyoncé", "Rihanna", "Lady Gaga"
            )
            musicians.forEach { name ->
                characters.add(CharacterEntity(name = name, categoryId = 3, description = "Músico/Cantante"))
            }
            Log.d("ImpostorDatabase", "✅ Agregados ${musicians.size} músicos")
            
            // Actores (Categoría 4)
            val actors = listOf(
                "Leonardo DiCaprio", "Brad Pitt", "Scarlett Johansson", "Robert Downey Jr.",
                "Emma Stone", "Ryan Reynolds", "Jennifer Lawrence", "Chris Hemsworth",
                "Margot Robbie", "Tom Holland", "Zendaya", "Timothée Chalamet",
                "Anya Taylor-Joy", "Florence Pugh", "Tom Cruise", "Keanu Reeves",
                "Will Smith", "Dwayne Johnson", "Johnny Depp", "Morgan Freeman",
                "Denzel Washington", "Meryl Streep", "Natalie Portman", "Emma Watson",
                "Daniel Radcliffe", "Jennifer Aniston", "Sandra Bullock"
            )
            actors.forEach { name ->
                characters.add(CharacterEntity(name = name, categoryId = 4, description = "Actor/Actriz"))
            }
            Log.d("ImpostorDatabase", "✅ Agregados ${actors.size} actores")
            
            // Deportes Varios (Categoría 5)
            val athletes = listOf(
                "LeBron James", "Stephen Curry", "Michael Jordan", "Kobe Bryant",
                "Serena Williams", "Rafael Nadal", "Roger Federer", "Novak Djokovic",
                "Usain Bolt", "Simone Biles", "Michael Phelps", "Lewis Hamilton",
                "Max Verstappen", "Tiger Woods", "Conor McGregor", "Floyd Mayweather",
                "Mike Tyson", "Muhammad Ali", "Pelé", "Maradona",
                "Valentino Rossi", "Tony Hawk", "Neymar", "Manu Ginóbili"
            )
            athletes.forEach { name ->
                characters.add(CharacterEntity(name = name, categoryId = 5, description = "Atleta"))
            }
            Log.d("ImpostorDatabase", "✅ Agregados ${athletes.size} atletas")
            
            // Streamers y YouTubers (Categoría 6)
            val creators = listOf(
                "MrBeast", "PewDiePie", "ElRubius", "AuronPlay", "Ibai Llanos",
                "TheGrefg", "Ninja", "xQc", "Pokimane", "Valkyrae",
                "Ludwig", "HasanAbi", "Amouranth", "Tfue", "shroud",
                "Coscu", "Spreen", "Germán Garmendia", "JuanSGuarnizo",
                "Luisito Comunica", "DrossRotzank", "Vegetta777"
            )
            creators.forEach { name ->
                characters.add(CharacterEntity(name = name, categoryId = 6, description = "Streamer/YouTuber"))
            }
            Log.d("ImpostorDatabase", "✅ Agregados ${creators.size} streamers/youtubers")
            
            // Famosos Chilenos (Categoría 7)
            val chileanCelebrities = listOf(
                "Alexis Sánchez", "Arturo Vidal", "Gary Medel", "Claudio Bravo",
                "Ben Brereton", "Eduardo Vargas", "Marcelo Salas", "Iván Zamorano",
                "Mon Laferte", "Paloma Mami", "Francisca Valenzuela", "Camila Moreno",
                "Los Bunkers", "Chancho en Piedra", "La Ley", "Los Prisioneros",
                "Violeta Parra", "Víctor Jara", "Gabriela Mistral", "Pablo Neruda",
                "Pedro Pascal", "Daniela Vega", "Alfredo Castro", "Paulina García",
                "Nicolás Massú", "Fernando González", "Tomás González", "Bárbara Riveros",
                "Stefan Kramer", "Fabrizio Copano", "Natalia Valdebenito", "Paul Vásquez (Niño Poeta)",
                "Américo", "Buddy Richard", "Myriam Hernández", "Alberto Plaza",
                "Cecilia Bolocco", "Rafael Araneda", "Martín Cárcamo", "Diana Bolocco"
            )
            chileanCelebrities.forEach { name ->
                characters.add(CharacterEntity(name = name, categoryId = 7, description = "Famoso chileno"))
            }
            Log.d("ImpostorDatabase", "✅ Agregados ${chileanCelebrities.size} famosos chilenos")
            
            // Otros Famosos (Categoría 8)
            val others = listOf(
                "Elon Musk", "Jeff Bezos", "Bill Gates", "Mark Zuckerberg",
                "Oprah Winfrey", "Gordon Ramsay", "Jamie Oliver", "Anthony Bourdain",
                "Kim Kardashian", "Kylie Jenner", "Kendall Jenner", "Paris Hilton",
                "Donald Trump", "Barack Obama", "Joe Biden", "Vladimir Putin",
                "Papa Francisco", "Dalai Lama", "Greta Thunberg", "Malala Yousafzai",
                "Steve Jobs", "Albert Einstein", "Stephen Hawking", "Neil deGrasse Tyson"
            )
            others.forEach { name ->
                characters.add(CharacterEntity(name = name, categoryId = 8, description = "Personalidad famosa"))
            }
            Log.d("ImpostorDatabase", "✅ Agregados ${others.size} otros famosos")
            
            // Insertar todos los personajes
            Log.d("ImpostorDatabase", "📦 Total de personajes a insertar: ${characters.size}")
            
            try {
                characterDao.insertCharacters(characters)
                Log.d("ImpostorDatabase", "✅ Insertados ${characters.size} personajes en la base de datos")
                
                // Verificar que se insertaron correctamente
                val finalCount = characterDao.getCharacterCount()
                Log.d("ImpostorDatabase", "✅ Verificación: ${finalCount} personajes en la BD")
            } catch (e: Exception) {
                Log.e("ImpostorDatabase", "❌ Error al insertar personajes: ${e.message}", e)
            }
        }
    } // Fin companion object
}
