package com.example.vendigo.VM_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.vendigo.domain.use_cases.ValidateButton
import com.example.vendigo.domain.use_cases.ValidateName

class ValidationViewModel(
    private val validateName: ValidateName = ValidateName(),
    private val validateButton: ValidateButton = ValidateButton()

):ViewModel(

) {

    var state by mutableStateOf(RegistrationFormState())


}