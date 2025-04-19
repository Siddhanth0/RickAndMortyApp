package com.example.characterapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Upsert
import androidx.room.Query

@Dao
interface CharacterDao {

    @Upsert
    suspend fun upsertAll(beers: List<CharacterEntity>)

    @Query("SELECT * FROM characterentity")
    fun pagingSource(): PagingSource<Int, CharacterEntity>

    @Query("DELETE FROM characterentity")
    suspend fun clearAll()
}