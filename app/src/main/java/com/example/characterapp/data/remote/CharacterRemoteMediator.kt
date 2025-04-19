package com.example.characterapp.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.characterapp.data.local.CharacterDatabase
import com.example.characterapp.data.local.CharacterEntity
import com.example.characterapp.data.mappers.toCharacterEntity
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val api: CharacterApi,
    private val db: CharacterDatabase
) : RemoteMediator<Int, CharacterEntity>() {

    private val characterDao = db.dao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) 1 else (state.pages.size + 1)
                }
            }

            val response = api.getCharacters(loadKey)
            val characters = response.results.map { it.toCharacterEntity() }

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    characterDao.clearAll()
                }
                characterDao.upsertAll(characters)
            }

            MediatorResult.Success(
                endOfPaginationReached = response.info.next == null
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
