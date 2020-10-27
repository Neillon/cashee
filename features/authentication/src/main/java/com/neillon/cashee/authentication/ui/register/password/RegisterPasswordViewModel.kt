package com.neillon.cashee.authentication.ui.register.password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neillon.cashee.authentication.domain.User
import com.neillon.cashee.authentication.usecase.UseCase
import com.neillon.cashee.authentication.usecase.register.RegisterUseCase
import kotlinx.coroutines.launch

class RegisterPasswordViewModel(
    private val registerUseCase: UseCase<RegisterUseCase.Params, User>
) : ViewModel() {

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun register(email: String, name: String, password: String) = viewModelScope.launch {
        try {
            val params = RegisterUseCase.Params(email, name, password)
            registerUseCase.execute(params)
            _error.value = null
        } catch (e: Exception) {
            _error.value = e.localizedMessage
        }
    }

}