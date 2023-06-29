package edu.phystech.pf.zubtilin.androidmipt.screens.login.signIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

sealed class SignInEvent {
    data class ChangePassword(val newPassword: String) : SignInEvent()
    data class ChangeEmail(val newEmail: String) : SignInEvent()
    object SignIn : SignInEvent()
}

data class SignInState(
    var email: String = "",
    var password: String = "",
)

class SignInScreenViewModel : ViewModel() {
    private val _viewState: MutableLiveData<SignInState> = MutableLiveData(SignInState())
    val viewState: LiveData<SignInState> = _viewState

    fun obtainEvent(event: SignInEvent, navController: NavController) {
        when (event) {
            is SignInEvent.ChangeEmail -> {
                _viewState.postValue(_viewState.value?.copy(email = event.newEmail))
            }
            is SignInEvent.ChangePassword -> {
                _viewState.postValue(_viewState.value?.copy(password = event.newPassword))
            }
            SignInEvent.SignIn -> {
                navController.navigate("restaurants")
            }
        }
    }
}