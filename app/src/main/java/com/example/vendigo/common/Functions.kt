package com.example.vendigo.common

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
fun commonDialog(){
    Dialog(onDismissRequest = {   }) {

        CircularProgressIndicator()
        
    }
}