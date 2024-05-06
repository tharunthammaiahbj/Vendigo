package com.example.vendigo.firebase.repository

import android.app.Activity
import com.example.vendigo.common.ResultState
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.flow.Flow


interface AuthRepo{

    fun createUserWithPhoneNumber(
        phoneNumber:String,
        activity: Activity
    ): Flow<ResultState<String>>

    fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential
    ): Flow<ResultState<String>>

    fun resendVerificationCode(
        phoneNumber:String,
        token: PhoneAuthProvider.ForceResendingToken,
        activity: Activity
    ): Flow<ResultState<String>>


}