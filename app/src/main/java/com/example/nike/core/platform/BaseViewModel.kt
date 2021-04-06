package com.example.nike.core.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nike.core.exceptions.Result

abstract class BaseViewModel: ViewModel() {
    // can do something
    var failure = MutableLiveData<Result.Failure>()

    protected fun handleFailure(failure: Result.Failure) {

        this.failure.value = failure
    }
}