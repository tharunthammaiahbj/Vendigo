package com.example.vendigo.VM_presentation

data class RegistrationFormState(
    val name: String = "",
    val nameError:String? = null,
    val phoneNumber:Int = -1,
    val phoneNumberError:String? = null,
    val isButtonEnable:Boolean = false


)
