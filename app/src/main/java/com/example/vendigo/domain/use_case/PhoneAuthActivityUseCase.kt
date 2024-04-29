package com.example.vendigo.domain.use_case

import android.app.Activity
import android.os.Bundle
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import javax.inject.Inject

class PhoneAuthActivityUseCase @Inject constructor(
    navController: NavController

):Activity()

{

    private lateinit var auth : FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //firebase.auth is for making access for authentication tasks from firebase
        auth = Firebase.auth




        }


    }




