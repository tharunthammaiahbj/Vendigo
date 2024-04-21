package com.example.vendigo.domain.use_cases

class ValidateButton {

    fun execute(phoneNumber: String):ValidationResult{

        if(phoneNumber.length == 10){
            return ValidationResult(
                successfull = true
            )
        }

        return ValidationResult(
            successfull = false
        )
    }
}