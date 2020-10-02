package com.neillon.cashee.authentication.usecase

interface UseCase<in T, out S> {
    suspend fun execute(param: T): S
}
