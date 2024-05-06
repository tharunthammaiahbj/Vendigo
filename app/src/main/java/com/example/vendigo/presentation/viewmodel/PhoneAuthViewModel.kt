package com.example.vendigo.presentation.viewmodel

import android.app.Activity
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.vendigo.domain.use_case.IsButtonEnableUseCase
import com.example.vendigo.firebase.repository.AuthRepo
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhoneAuthViewModel @Inject constructor(

    private val repo:AuthRepo

):ViewModel() {

    fun buttonEnable(phoneNumber:MutableState<String>):Boolean {

        return IsButtonEnableUseCase().isButtonEnable(phoneNumber)
    }

    fun createUserWithPhoneNumber(
        phoneNumber:String,
        activity:Activity
    ) = repo.createUserWithPhoneNumber(phoneNumber, activity)


    fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential
    ) = repo.signInWithPhoneAuthCredential(credential)


    fun resendVerificationCode(
        phoneNumber:String,
        token: PhoneAuthProvider.ForceResendingToken,
        activity: Activity
    )= repo.resendVerificationCode(phoneNumber, token, activity)



}