package com.example.vendigo.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.vendigo.domain.use_case.IsButtonEnableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

class PhnoViewModel @Inject constructor(

    private val isButtonEnable: IsButtonEnableUseCase
) : ViewModel() {

    //mutable phone number
    val phoneNumber: MutableState<String> = mutableStateOf("")


    //function enable/disable button upon condition
    fun buttonEnable():Boolean {
        return IsButtonEnableUseCase().isButtonEnable(phoneNumber)
    }

}