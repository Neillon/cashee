package com.neillon.cashee.authentication.di

import com.neillon.cashee.authentication.domain.User
import com.neillon.cashee.authentication.repository.auth.AuthRepository
import com.neillon.cashee.authentication.repository.auth.AuthenticationRepository
import com.neillon.cashee.authentication.repository.user.UserFirebaseRepository
import com.neillon.cashee.authentication.repository.user.UserRepository
import com.neillon.cashee.authentication.ui.login.initial.LoginViewModel
import com.neillon.cashee.authentication.ui.login.with_email.password.LoginPasswordViewModel
import com.neillon.cashee.authentication.ui.register.password.RegisterPasswordViewModel
import com.neillon.cashee.authentication.usecase.UseCase
import com.neillon.cashee.authentication.usecase.login.LoginWithEmailAndPasswordUseCase
import com.neillon.cashee.authentication.usecase.login.LoginWithGoogleUseCase
import com.neillon.cashee.authentication.usecase.register.RegisterUseCase
import com.neillon.cashee.authentication.usecase.user.GetUserByEmailUseCase
import com.neillon.network.firebase.FirebaseConfiguration
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

object AuthenticationModule {
    val dependencies = module {
        // Firebase
        single { FirebaseConfiguration(firebaseContext = get()) }
        single { FirebaseConfiguration(firebaseContext = get()).firebaseAuth }
        single { FirebaseConfiguration(firebaseContext = get()).firestore }

        // Repository
        factory {
            AuthenticationRepository(
                firebaseAuth = get(),
                networkManager = get()
            )
        } bind AuthRepository::class
        factory { UserRepository(firestore = get()) } bind UserFirebaseRepository::class

        // Use Case
        factory<UseCase<LoginWithGoogleUseCase.Params, User>>(named("loginWithGoogle")) {
            LoginWithGoogleUseCase(
                repository = get(),
                userRepository = get()
            )
        }
        factory<UseCase<LoginWithEmailAndPasswordUseCase.Params, User>>(named("loginWithEmailAndPassword")) {
            LoginWithEmailAndPasswordUseCase(
                repository = get(),
                userRepository = get()
            )
        }
        factory<UseCase<RegisterUseCase.Params, User>>(named("register")) {
            RegisterUseCase(
                authenticationRepository = get(),
                userRepository = get()
            )
        }
        factory<UseCase<GetUserByEmailUseCase.Params, User?>>(named("getUserByEmail")) {
            GetUserByEmailUseCase(
                repository = get()
            )
        }

        // ViewModel
        viewModel { LoginViewModel(authenticationUseCase = get(qualifier = StringQualifier("loginWithGoogle"))) }
        viewModel {
            LoginPasswordViewModel(
                loginWithEmailAndPasswordUseCase = get(
                    qualifier = StringQualifier(
                        "loginWithEmailAndPassword"
                    )
                )
            )
        }
        viewModel { RegisterPasswordViewModel(registerUseCase = get(qualifier = StringQualifier("register"))) }
    }
}