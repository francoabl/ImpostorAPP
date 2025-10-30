package com.example.impostor.data.dao

import androidx.room.*
import com.example.impostor.data.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters ORDER BY name ASC")
    fun getAllCharacters(): Flow<List<CharacterEntity>>
    
    @Query("SELECT * FROM characters WHERE categoryId = :categoryId ORDER BY name ASC")
    fun getCharactersByCategory(categoryId: Int): Flow<List<CharacterEntity>>
    
    @Query("SELECT * FROM characters WHERE id = :characterId")
    suspend fun getCharacterById(characterId: Int): CharacterEntity?
    
    @Query("SELECT * FROM characters WHERE name LIKE '%' || :query || '%' ORDER BY name ASC")
    fun searchCharacters(query: String): Flow<List<CharacterEntity>>
    
    @Query("SELECT * FROM characters ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomCharacter(): CharacterEntity?
    
    @Query("SELECT * FROM characters WHERE categoryId = :categoryId ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomCharacterFromCategory(categoryId: Int): CharacterEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterEntity): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)
    
    @Update
    suspend fun updateCharacter(character: CharacterEntity)
    
    @Delete
    suspend fun deleteCharacter(character: CharacterEntity)
    
    @Query("DELETE FROM characters")
    suspend fun deleteAllCharacters()
    
    @Query("SELECT COUNT(*) FROM characters")
    suspend fun getCharacterCount(): Int
}
