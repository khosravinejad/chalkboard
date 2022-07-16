package com.moryzaky.chalkboard.domain.usecase

import com.moryzaky.chalkboard.domain.model.PersonDomainModel
import com.moryzaky.chalkboard.domain.repository.PersonRepository
import javax.inject.Inject

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

class GetPersonDetailsUseCase @Inject constructor(
    private val personRepository: PersonRepository
) {
    suspend operator fun invoke(id: String): PersonDomainModel? =
        personRepository.getPersonDetails(id)
}