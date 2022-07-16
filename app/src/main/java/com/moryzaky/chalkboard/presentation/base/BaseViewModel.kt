package com.moryzaky.chalkboard.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moryzaky.chalkboard.exception.Failure

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

abstract class BaseViewModel : ViewModel() {
    val showProgress = MutableLiveData(false)
    val failure: MutableLiveData<Failure> = MutableLiveData()

}