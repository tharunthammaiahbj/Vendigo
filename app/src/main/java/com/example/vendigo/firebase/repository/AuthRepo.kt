package com.example.vendigo.firebase.repository

import android.app.Activity
import com.example.vendigo.common.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepo {


    fun createUserWithPhone(
        phoneNumber:String,
        activity:Activity
    ): Flow<ResultState<String>>

    fun signWithCredential(
        otp:String
    ): Flow<ResultState<String>>

}