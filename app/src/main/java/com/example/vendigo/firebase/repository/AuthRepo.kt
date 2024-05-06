package com.example.vendigo.firebase.repository

import android.app.Activity
import com.example.vendigo.common.ResultState
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.flow.Flow


interface AuthRepo{

    suspend fun createUserWithPhoneNumber(
        phoneNumber:String,
        activity: Activity
    ): Flow<ResultState<String>>

    suspend fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential
    ): Flow<ResultState<String>>

    suspend fun resendVerificationCode(
        phoneNumber:String,
        token: PhoneAuthProvider.ForceResendingToken,
        activity: Activity
    ): Flow<ResultState<String>>

    suspend fun verifyPhoneNumberWithCode(
        code: String
    ): PhoneAuthCredential

}