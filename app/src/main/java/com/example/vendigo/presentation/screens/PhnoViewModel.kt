package com.example.vendigo.presentation.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

class PhnoViewModel @Inject constructor() : ViewModel() {

    // mutable state to hold the phone number
    val phoneNumber: MutableState<String> = mutableStateOf("")


    //function enable/disable button upon condition
    fun isButtonEnabled():Boolean{
        return phoneNumber.value?.length == 10
    }


}