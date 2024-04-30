package com.example.vendigo.presentation.viewmodel

import android.app.Activity
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.vendigo.domain.use_case.IsButtonEnableUseCase
import com.example.vendigo.firebase.repository.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhoneAuthViewModel @Inject constructor(

    private val repo : AuthRepo

):ViewModel() {

    fun createUserWithPhone(
        phoneNumber:String,
        activity:Activity
    ) = repo.createUserWithPhone(phoneNumber, activity)

    fun signInWithCredential(
        code:String
    ) = repo.signWithCredential(code)

    fun buttonEnable(phoneNumber:MutableState<String>):Boolean {

        return IsButtonEnableUseCase().isButtonEnable(phoneNumber)
    }

    val finalPhoneNumber: MutableState<String> = mutableStateOf("")

}