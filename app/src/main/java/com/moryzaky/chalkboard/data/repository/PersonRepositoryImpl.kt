package com.moryzaky.chalkboard.data.repository

import androidx.paging.*
import com.moryzaky.chalkboard.data.model.toDomainModel
import com.moryzaky.chalkboard.data.paging.PersonsRemoteMediator
import com.moryzaky.chalkboard.data.source.local.ChalkBoardDatabase
import com.moryzaky.chalkboard.data.source.remote.ApiServices
import com.moryzaky.chalkboard.domain.model.PersonDomainModel
import com.moryzaky.chalkboard.domain.repository.PersonRepository
import com.moryzaky.chalkboard.utils.Constants.PERSON_INITIAL_PAGE
import com.moryzaky.chalkboard.utils.Constants.PERSON_PER_PAGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

class PersonRepositoryImpl(
    private val apiServices: ApiServices,
    private val database: ChalkBoardDatabase
) : PersonRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPersons(): Flow<PagingData<PersonDomainModel>> {
        return Pager(
            config = PagingConfig(pageSize = PERSON_PER_PAGE),
            remoteMediator = PersonsRemoteMediator(
                PERSON_INITIAL_PAGE,
                apiServices,
                database
            )
        ) {
            database.personDao.pagingSource()
        }.flow.map { pagingData ->
            pagingData.map {
                it.toDomainModel()

            }
        }
    }

    override suspend fun getPersonDetails(id: String): PersonDomainModel? {
        return database.personDao.getPersonDetails(id)?.toDomainModel()
    }
}