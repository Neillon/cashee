package com.neillon.cashee.authentication.usecase.user

import com.neillon.cashee.authentication.domain.User
import com.neillon.cashee.authentication.repository.user.UserFirebaseRepository
import com.neillon.cashee.common.exceptions.ExceptionManager
import com.neillon.cashee.authentication.usecase.UseCase

class GetUserByEmailUseCase(
    private val repository: UserFirebaseRepository
): UseCase<GetUserByEmailUseCase.Params, User?> {
    inner class Params(var email: String)

    override suspend fun execute(param: Params): User? =
        ExceptionManager.doAndTreatExceptions<User?> {
            repository.getByEmail(param.email)
        }
}