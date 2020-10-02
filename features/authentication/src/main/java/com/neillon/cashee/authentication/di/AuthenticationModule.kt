package com.neillon.cashee.authentication.di

import com.neillon.cashee.authentication.repository.AuthRepository
import com.neillon.cashee.authentication.repository.AuthenticationRepository
import com.neillon.cashee.authentication.ui.login.initial.LoginViewModel
import com.neillon.cashee.authentication.usecase.LoginWithGoogleUseCase
import com.neillon.cashee.authentication.usecase.UseCase
import com.neillon.cashee.authentication.util.FirebaseConfiguration
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

object AuthenticationModule {
    val dependencies = module {
        // Firebase
        single { FirebaseConfiguration(firebaseContext = get()) }
        single { FirebaseConfiguration(firebaseContext = get()).Authentication().firebaseAuth }

        // Repository
        factory { AuthenticationRepository(firebaseAuth = get()) } bind AuthRepository::class

        // Use Case
        factory { LoginWithGoogleUseCase(repository = get()) } bind UseCase::class

        viewModel { LoginViewModel(authenticationUseCase = get()) }
    }
}