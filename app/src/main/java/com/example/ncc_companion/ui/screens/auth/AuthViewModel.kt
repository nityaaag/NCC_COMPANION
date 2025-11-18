package com.example.ncc_companion.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.SignedOut())
    val uiState = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        val currentState = _uiState.value
        if (currentState is AuthUiState.SignedOut) {
            _uiState.value = currentState.copy(email = email)
        }
    }

    fun onPasswordChange(password: String) {
        val currentState = _uiState.value
        if (currentState is AuthUiState.SignedOut) {
            _uiState.value = currentState.copy(password = password)
        }
    }

    fun signInWithEmailAndPassword() {
        val currentState = _uiState.value
        if (currentState is AuthUiState.SignedOut) {
            viewModelScope.launch {
                _uiState.value = AuthUiState.Loading
                try {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(currentState.email, currentState.password).await()
                    _uiState.value = AuthUiState.Success
                } catch (e: Exception) {
                    _uiState.value = AuthUiState.SignedOut(error = e.message)
                }
            }
        }
    }

    private fun Task<AuthResult>.await() {
        TODO("Not yet implemented")
    }

    fun onGoogleSignInResult(credential: SignInCredential) {
        if (credential != null) {
            viewModelScope.launch {
                _uiState.value = AuthUiState.Loading
                try {
                    FirebaseAuth.getInstance().signInWithCredential(credential).await()
                    _uiState.value = AuthUiState.Success
                } catch (e: Exception) {
                    _uiState.value = AuthUiState.SignedOut(error = e.message)
                }
            }
        }
    }

    private fun Unit.await() {
        TODO("Not yet implemented")
    }

    private fun FirebaseAuth.signInWithCredential(p0: SignInCredential) {
        TODO("Not yet implemented")
    }
}

sealed class AuthUiState {
    data object Loading : AuthUiState()
    data object Success : AuthUiState()
    data class SignedOut(
        val email: String = "",
        val password: String = "",
        val error: String? = null
    ) : AuthUiState()
}
