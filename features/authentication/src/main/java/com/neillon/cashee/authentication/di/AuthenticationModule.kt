package com.neillon.cashee.authentication.di

import com.neillon.cashee.authentication.repository.AuthRepository
import com.neillon.cashee.authentication.repository.AuthenticationRepository
import com.neillon.cashee.authentication.ui.login.initial.LoginViewModel
import com.neillon.cashee.authentication.ui.login.with_email.password.LoginPasswordViewModel
import com.neillon.cashee.authentication.ui.register.password.RegisterPasswordViewModel
import com.neillon.cashee.authentication.usecase.login.LoginWithEmailAndPasswordUseCase
import com.neillon.cashee.authentication.usecase.login.LoginWithGoogleUseCase
import com.neillon.cashee.authentication.usecase.UseCase
import com.neillon.cashee.authentication.usecase.register.RegisterUseCase
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
        factory { LoginWithEmailAndPasswordUseCase(repository = get()) } bind UseCase::class
        factory { RegisterUseCase(authenticationRepository = get()) } bind UseCase::class

        // ViewModel
        viewModel { LoginViewModel(authenticationUseCase = get()) }
        viewModel { LoginPasswordViewModel(loginWithEmailAndPasswordUseCase = get()) }
        viewModel { RegisterPasswordViewModel(registerUseCase = get()) }
    }
}