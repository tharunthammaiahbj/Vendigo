package com.example.vendigo.domain.use_cases

class ValidateName {

    fun execute(name: String):ValidationResult{

        val trimName = name.trim()

        if(trimName.isBlank()){
            return ValidationResult(
                successfull = false,
                errorMessage = "Please enter your name"
            )
        }
        if(trimName.length < 4){
            return ValidationResult(
                successfull = false,
                errorMessage = "Name too short.Must be 4 characters"
            )

        }
        if(trimName.length > 30){
            return ValidationResult(
                successfull = false,
                errorMessage = "Name too long. Limit 30 characters"
            )

        }
        return ValidationResult(
            successfull = true
        )
    }
}