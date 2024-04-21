package com.example.vendigo.VM_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ValidationViewModel:ViewModel() {

    var state by mutableStateOf(RegistrationFormState())


}