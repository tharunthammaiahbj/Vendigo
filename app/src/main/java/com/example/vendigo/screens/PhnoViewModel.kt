package com.example.vendigo.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@HiltViewModel
@InstallIn(SingletonComponent::class)

class PhnoViewModel @Inject constructor() : ViewModel() {

    // mutable state to hold the phone number
     val phoneNumber: MutableState<String> = mutableStateOf("")


    //function enable/disable button upon condition
    fun isButtonEnabled():Boolean{
        return phoneNumber.value.length == 10
    }


}