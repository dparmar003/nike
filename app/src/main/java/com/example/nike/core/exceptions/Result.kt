package com.example.nike.core.exceptions


sealed class Result<out R>{
    data class Success<out T> (val data: T): Result<T>()
    data class Exception(val e: kotlin.Exception): Result<Nothing>()
    data class Failure(val msg: String): Result<Nothing>()


}

val Result<*>.succeed get() = this is Result.Success && data != null
