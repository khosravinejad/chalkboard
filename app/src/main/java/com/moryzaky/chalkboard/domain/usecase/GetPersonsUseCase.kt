package com.moryzaky.chalkboard.domain.usecase

import com.moryzaky.chalkboard.domain.repository.PersonRepository
import javax.inject.Inject

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

class GetPersonsUseCase @Inject constructor(
    private val personRepository: PersonRepository
) {
    operator fun invoke() = personRepository.getPersons()
}