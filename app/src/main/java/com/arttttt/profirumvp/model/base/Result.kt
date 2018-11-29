package com.arttttt.profirumvp.model.base

sealed class Result {
    data class Success<T>(val result: T): Result()
    data class Error(val ex: Throwable): Result()
}