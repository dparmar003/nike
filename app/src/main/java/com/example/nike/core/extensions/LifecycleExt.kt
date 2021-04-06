package com.example.nike.core.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.nike.core.exceptions.Result

fun <T: Any, L: LiveData<T>>LifecycleOwner.observe(liveData: L, block: (T?) -> Unit) {
    return liveData.observe(this, Observer(block))
}

fun <L: LiveData<Result.Failure>>LifecycleOwner.failure(liveData: L, block: (Result.Failure?) -> Unit){
    return liveData.observe(this, Observer(block))
}