package com.example.vendigo.firebase.repository

import android.app.Activity
import com.example.vendigo.common.ResultState
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth : FirebaseAuth
) : AuthRepo {


    private lateinit var onVerificationCode : String



    override suspend fun createUserWithPhone(phoneNumber: String, activity: Activity): Flow<ResultState<String>> = callbackFlow {


        val onVerificationCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){


            override fun onVerificationCompleted(p0: PhoneAuthCredential) {


            }

            override fun onVerificationFailed(p0: FirebaseException) {

                trySend(ResultState.Failure(p0))

            }

            override fun onCodeSent(verificationCode: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(verificationCode, p1)
                trySend(ResultState.Success("OTP Sent Successfully"))
                onVerificationCode = verificationCode
            }

        }

            val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+91$phoneNumber")
            .setTimeout(60L,TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(onVerificationCallback)
            .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
            awaitClose {
                close()
            }

    }

      override suspend  fun signWithCredential(otp: String): Flow<ResultState<String>> = callbackFlow {

        trySend(ResultState.Loading)
        val credential = PhoneAuthProvider.getCredential(onVerificationCode,otp)
        auth.signInWithCredential(credential)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    trySend(ResultState.Success("Otp Verified"))
                }
            }
            .addOnFailureListener {

                trySend(ResultState.Failure(it))
            }
        awaitClose {
            close()
        }

    }
}