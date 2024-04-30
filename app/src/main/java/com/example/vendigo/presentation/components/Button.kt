package com.example.vendigo.presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.vendigo.navigation.VendigoScreens
import com.example.vendigo.presentation.viewmodel.PhnoViewModel
import com.example.vendigo.presentation.ui.theme.fontFamily

@Composable
fun Button(navController: NavController,
           viewModel: PhnoViewModel = hiltViewModel()
            ){

    val controller = LocalSoftwareKeyboardController.current

    Box(modifier = Modifier
        .fillMaxWidth()){
        androidx.compose.material3.Button(colors = ButtonDefaults.buttonColors(if (isSystemInDarkTheme()) Color(0xFF3C732E) else Color(
            0xFF61CD46
        )
        ),
            onClick =
            {

                navController.navigate(VendigoScreens.OtpVerifyScreen.name)
                controller?.hide()


            },
            modifier = Modifier
                .width(325.dp),
            enabled = viewModel.buttonEnable()
        )
        {
            Text(text = "Continue", fontFamily = fontFamily, fontWeight = FontWeight(800), color = MaterialTheme.colorScheme.onSurface)

        }
    }
}