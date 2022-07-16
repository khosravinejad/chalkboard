package com.moryzaky.chalkboard.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.moryzaky.chalkboard.domain.model.PersonDomainModel
import com.moryzaky.chalkboard.domain.usecase.GetPersonDetailsUseCase
import com.moryzaky.chalkboard.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    private val getPersonDetailsUseCase: GetPersonDetailsUseCase
) : BaseViewModel() {
    private val _person: MutableLiveData<PersonDomainModel?> = MutableLiveData()
    val person: MutableLiveData<PersonDomainModel?> = _person

    fun loadDetails(id: String) {
        viewModelScope.launch {
            _person.value = getPersonDetailsUseCase.invoke(id)
        }
    }
}