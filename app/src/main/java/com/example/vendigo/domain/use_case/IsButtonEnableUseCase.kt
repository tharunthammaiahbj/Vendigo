package com.example.vendigo.domain.use_case

import androidx.compose.runtime.MutableState
import javax.inject.Inject

class IsButtonEnableUseCase @Inject constructor() {

    fun isButtonEnable(phoneNumber: MutableState<String>):Boolean{
        return phoneNumber.value?.length == 10
    }

}