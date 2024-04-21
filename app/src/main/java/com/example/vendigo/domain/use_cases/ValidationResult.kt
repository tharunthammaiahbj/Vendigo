package com.example.vendigo.domain.use_cases

data class ValidationResult(
    val successfull :Boolean,
    val errorMessage:String? = null
)
