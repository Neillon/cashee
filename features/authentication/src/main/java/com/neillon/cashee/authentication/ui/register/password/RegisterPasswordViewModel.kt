package com.neillon.cashee.authentication.ui.register.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neillon.cashee.authentication.usecase.register.RegisterUseCase
import kotlinx.coroutines.launch

class RegisterPasswordViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {


    fun register(email: String, name: String, password: String) = viewModelScope.launch {
        val params = RegisterUseCase.Params(email, name, password)
        registerUseCase.execute(params)
    }

}