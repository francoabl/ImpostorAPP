package com.example.impostor.data.repository

import com.example.impostor.data.dao.CategoryDao
import com.example.impostor.data.dao.CharacterDao
import com.example.impostor.data.entity.CategoryEntity
import com.example.impostor.data.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

class CharacterRepository(
    private val characterDao: CharacterDao,
    private val categoryDao: CategoryDao
) {
    // Character operations
    fun getAllCharacters(): Flow<List<CharacterEntity>> = characterDao.getAllCharacters()
    
    fun getCharactersByCategory(categoryId: Int): Flow<List<CharacterEntity>> = 
        characterDao.getCharactersByCategory(categoryId)
    
    suspend fun getCharacterById(characterId: Int): CharacterEntity? = 
        characterDao.getCharacterById(characterId)
    
    fun searchCharacters(query: String): Flow<List<CharacterEntity>> = 
        characterDao.searchCharacters(query)
    
    suspend fun getRandomCharacter(): CharacterEntity? = 
        characterDao.getRandomCharacter()
    
    suspend fun getRandomCharacterFromCategory(categoryId: Int): CharacterEntity? = 
        characterDao.getRandomCharacterFromCategory(categoryId)
    
    suspend fun insertCharacter(character: CharacterEntity): Long = 
        characterDao.insertCharacter(character)
    
    suspend fun updateCharacter(character: CharacterEntity) = 
        characterDao.updateCharacter(character)
    
    suspend fun deleteCharacter(character: CharacterEntity) = 
        characterDao.deleteCharacter(character)
    
    suspend fun getCharacterCount(): Int = 
        characterDao.getCharacterCount()
    
    // Category operations
    fun getAllCategories(): Flow<List<CategoryEntity>> = 
        categoryDao.getAllCategories()
    
    suspend fun getCategoryById(categoryId: Int): CategoryEntity? = 
        categoryDao.getCategoryById(categoryId)
    
    suspend fun insertCategory(category: CategoryEntity): Long = 
        categoryDao.insertCategory(category)
    
    suspend fun updateCategory(category: CategoryEntity) = 
        categoryDao.updateCategory(category)
    
    suspend fun deleteCategory(category: CategoryEntity) = 
        categoryDao.deleteCategory(category)
}
