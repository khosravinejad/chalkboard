package com.moryzaky.chalkboard.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moryzaky.chalkboard.data.repository.PersonRepositoryImpl
import com.moryzaky.chalkboard.data.source.local.ChalkBoardDatabase
import com.moryzaky.chalkboard.data.source.remote.ApiServices
import com.moryzaky.chalkboard.domain.model.PersonDomainModel
import com.moryzaky.chalkboard.domain.usecase.GetPersonDetailsUseCase
import com.moryzaky.chalkboard.presentation.details.PersonDetailsViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

class PersonDetailsViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var apiServices: ApiServices

    @MockK
    lateinit var database: ChalkBoardDatabase

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var personDetailsViewModel: PersonDetailsViewModel
    private lateinit var personRepositoryImpl: PersonRepositoryImpl
    private lateinit var getPersonDetailsUseCase: GetPersonDetailsUseCase


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        personRepositoryImpl = PersonRepositoryImpl(apiServices, database)
        getPersonDetailsUseCase = GetPersonDetailsUseCase(personRepositoryImpl)
        personDetailsViewModel = PersonDetailsViewModel(
            getPersonDetailsUseCase
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun person_details_live_data_should_update_the_returned_value() = runBlocking {
        coEvery { getPersonDetailsUseCase.invoke(any()) } returns makeFakePersonDomainModel()
        Dispatchers.setMain(testDispatcher)
        personDetailsViewModel.loadDetails("validId")
        var actual: PersonDomainModel? = null
        personDetailsViewModel.person.observeForever {
            actual = it
        }
        val expected = personDetailsViewModel.person.value
        Assert.assertEquals(expected, actual)
    }

    private fun makeFakePersonDomainModel() = PersonDomainModel(
        id = "validId",
        title = "Mr",
        firstName = "Morteza",
        lastName = "Khosravinejad",
        birthday = Date("11/09/1990"),
        age = 32
    )
}