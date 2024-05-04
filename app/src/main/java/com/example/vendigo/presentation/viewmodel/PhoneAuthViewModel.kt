package com.example.vendigo.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.vendigo.domain.use_case.IsButtonEnableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhoneAuthViewModel @Inject constructor(

):ViewModel() {


    fun buttonEnable(phoneNumber:MutableState<String>):Boolean {

        return IsButtonEnableUseCase().isButtonEnable(phoneNumber)
    }


}