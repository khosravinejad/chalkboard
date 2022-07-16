package com.moryzaky.chalkboard.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.moryzaky.chalkboard.data.model.PersonDataModel
import com.moryzaky.chalkboard.data.model.PersonRemoteKey
import com.moryzaky.chalkboard.data.source.local.ChalkBoardDatabase
import com.moryzaky.chalkboard.data.source.remote.ApiServices
import com.moryzaky.chalkboard.utils.Constants.PERSON_INITIAL_PAGE
import com.moryzaky.chalkboard.utils.Constants.PERSON_MAX_PAGE
import com.moryzaky.chalkboard.utils.Constants.PERSON_PER_PAGE

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

@OptIn(ExperimentalPagingApi::class)
class PersonsRemoteMediator(
    private val initialPage: Int = PERSON_INITIAL_PAGE,
    private val apiServices: ApiServices,
    private val database: ChalkBoardDatabase
) : RemoteMediator<Int, PersonDataModel>() {
    private val personDao = database.personDao
    private val remoteKeyDao = database.personRemoteKeyDao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PersonDataModel>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: initialPage
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    remoteKeys?.nextKey
                        ?: return MediatorResult.Success(remoteKeys != null)
                }
            }

            val response = apiServices.getBirthdayList(PERSON_PER_PAGE, page).body()
            val persons = response?.results

            val nextKey = if (response?.info?.pageNumber == PERSON_MAX_PAGE) {
                null
            } else {
                page + (state.config.pageSize / PERSON_PER_PAGE)
            }

            val keys = persons?.map {
                PersonRemoteKey(it.id, null, nextKey)
            }

            if (!keys.isNullOrEmpty()) {
                remoteKeyDao.insertAll(keys)
            }
            if (!persons.isNullOrEmpty()) {
                personDao.insertAll(persons)
            }

            MediatorResult.Success(
                response?.results.isNullOrEmpty()
            )
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, PersonDataModel>): PersonRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeyDao.personRemoteKeysByPokeId(id)
            }
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PersonDataModel>): PersonRemoteKey? {
        return state.lastItemOrNull()?.let { poke ->
            remoteKeyDao.personRemoteKeysByPokeId(poke.id)
        }
    }
}