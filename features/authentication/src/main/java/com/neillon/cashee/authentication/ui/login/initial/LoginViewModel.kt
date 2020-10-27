package com.neillon.cashee.authentication.ui.login.initial

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.neillon.cashee.authentication.domain.User
import com.neillon.cashee.authentication.usecase.login.LoginWithGoogleUseCase
import com.neillon.cashee.authentication.usecase.UseCase
import com.neillon.cashee.authentication.util.makeSimpleSnackBarWithMessage
import com.neillon.cashee.ui.AuthenticationActivity
import com.neillon.cashee.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authenticationUseCase: UseCase<LoginWithGoogleUseCase.Params, User>
) : ViewModel() {

    val handleLoginResult = SingleLiveEvent<User>()
    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun loginWithGoogle(completedTask: Task<GoogleSignInAccount>) = viewModelScope.launch {
        try {
            if (completedTask.isSuccessful) {
                val googleAccount = completedTask.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(googleAccount!!.idToken!!, null)
                val user =
                    authenticationUseCase.execute(LoginWithGoogleUseCase.Params(credential = credential))
                handleLoginResult.value = user
                _error.value = null
            } else {
                _error.value = completedTask.exception?.localizedMessage
                Log.e(TAG, "handleSignInResult: ${completedTask.exception}")
            }

        } catch (e: ApiException) {
            Log.w(AuthenticationActivity.TAG, "handleSignInResult: failed code -> " + e.statusCode)
        }
    }

    companion object {
        private const val TAG = "LoginViewModel"
    }
}