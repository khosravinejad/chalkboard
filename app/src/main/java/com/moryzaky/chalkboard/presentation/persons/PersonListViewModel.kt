package com.moryzaky.chalkboard.presentation.persons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.moryzaky.chalkboard.domain.model.PersonDomainModel
import com.moryzaky.chalkboard.domain.usecase.ClearAllPersonsLocalUseCase
import com.moryzaky.chalkboard.domain.usecase.GetPersonsUseCase
import com.moryzaky.chalkboard.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

@HiltViewModel
class PersonListViewModel @Inject constructor(
    private val getPersonsUseCase: GetPersonsUseCase,
    private val clearAllPersonsLocalUseCase: ClearAllPersonsLocalUseCase
) : BaseViewModel() {

    private val _persons: MutableLiveData<PagingData<PersonDomainModel>> = MutableLiveData()
    val persons: MutableLiveData<PagingData<PersonDomainModel>> = _persons

    init {
        clearDatabase()
    }

    fun loadPersons() {
        viewModelScope.launch {
            getPersonsUseCase.invoke().collect {
                _persons.value = it
            }
        }
    }

    private fun clearDatabase() {
        viewModelScope.launch {
            clearAllPersonsLocalUseCase.invoke()
        }
    }

}