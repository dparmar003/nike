package com.example.nike.core.interactors

import com.example.nike.core.exceptions.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class UseCase<in Params, out Type> where Type: Any{

    abstract suspend fun run(params: Params): Result<Type>

    //here custom coroutine scope can be used to run every use case in background thread by convention
    suspend operator fun invoke(params: Params) = withContext(Dispatchers.IO){
        return@withContext run(params)
    }

}