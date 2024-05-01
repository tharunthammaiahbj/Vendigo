package com.example.vendigo.firebase.repository

import android.app.Activity
import com.example.vendigo.common.ResultState
import kotlinx.coroutines.flow.Flow


interface AuthRepo{



     suspend fun createUserWithPhone(
        phoneNumber:String,
        activity:Activity
    ): Flow<ResultState<String>>


      suspend fun signWithCredential(
        otp:String
    ): Flow<ResultState<String>>

}