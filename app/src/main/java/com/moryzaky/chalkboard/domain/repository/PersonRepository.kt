package com.moryzaky.chalkboard.domain.repository

import androidx.paging.PagingData
import com.moryzaky.chalkboard.domain.model.PersonDomainModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

interface PersonRepository {
    fun getPersons(): Flow<PagingData<PersonDomainModel>>
    suspend fun getPersonDetails(id: String): PersonDomainModel?
}