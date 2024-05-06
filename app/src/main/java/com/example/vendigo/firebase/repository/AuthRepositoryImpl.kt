package com.example.vendigo.firebase.repository

import android.app.Activity
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.widget.Toast
import com.example.vendigo.common.ResultState
import com.example.vendigo.presentation.MainActivity
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
        private val auth:FirebaseAuth
):AuthRepo {


    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks


    override suspend fun createUserWithPhoneNumber(
        phoneNumber: String,
        activity: Activity
    ): Flow<ResultState<String>> = callbackFlow {

        trySend(ResultState.Loading)


        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {



            override fun onVerificationCompleted(credential: PhoneAuthCredential) {



                Log.d(TAG, "onVerificationCompleted:$credential")

                CoroutineScope(Dispatchers.Main).launch {
                    signInWithPhoneAuthCredential(credential)
                }

            }


            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    trySend(ResultState.Failure(e))

                } else if (e is FirebaseTooManyRequestsException) {
                    trySend(ResultState.Failure(e))

                // Show a message and update the UI
            }
            }


                override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken,
            ) {

                Log.d(TAG, "onCodeSent:$verificationId")

                    super.onCodeSent(verificationId, token)
                    trySend(ResultState.Success("OTP Sent Successfully"))
                    storedVerificationId = verificationId
                    resendToken = token
            }

        }
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        awaitClose()
        close()
        // [END phone_auth_callbacks]
    }





    // [START signInWithPhoneCred]
    override suspend fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential

    ): Flow<ResultState<String>> = callbackFlow {


        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    trySend(ResultState.Success("otp verified"))

                }

                else {
                    // Sign in failed, display a message and update the UI
                    Toast.makeText( MainActivity(),"Sign in Failed",Toast.LENGTH_SHORT).show()
                    Log.w(TAG, "signInWithCredential:failure", task.exception)


                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Log.d(TAG, "Verification Code Invalid")

                    }
                    // Update UI
                }
            }
    }


    // [END signInWithPhoneCred]



    // [START resendVerificationCode]
    override suspend fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken,
        activity: Activity
    ): Flow<ResultState<String>> = callbackFlow {

        val optionsBuilder = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity) // (optional) Activity for callback binding
            // If no activity is passed, reCAPTCHA verification can not be used.
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
        if (token != null) {
            optionsBuilder.setForceResendingToken(token) // callback's ForceResendingToken
        }
        PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())

    }


     // [END resendVerificationCode]


        override suspend fun verifyPhoneNumberWithCode(code: String): PhoneAuthCredential {
            val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, code)
            return  credential
        }


}