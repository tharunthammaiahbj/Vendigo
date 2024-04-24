package com.example.vendigo.presentation.widgets

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.vendigo.navigation.VendigoScreens
import com.example.vendigo.presentation.screens.PhnoViewModel
import com.example.vendigo.presentation.ui.theme.fontFamily


@Composable
fun TextField(navController: NavController,
              viewModel: PhnoViewModel = hiltViewModel()) {

    val focusRequester = remember { FocusRequester() }

     val controller = LocalSoftwareKeyboardController.current

Column{
    Box(modifier = Modifier
        .fillMaxWidth()) {
        OutlinedTextField(
            shape = RectangleShape,
            value =   viewModel.phoneNumber.value,
            onValueChange = {
                if(it.isDigitsOnly()){
                    viewModel.phoneNumber.value= it
                }
            },
            modifier = Modifier
                .width(325.dp)
                .height(70.dp)
                .focusRequester(focusRequester),
            textStyle = TextStyle(
                fontFamily = fontFamily,
                fontSize = 25.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 2.sp
            ),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (isSystemInDarkTheme()) Color(0xFFD3FFD8) else Color(
                    0xFF3C732E
                ),
                unfocusedBorderColor = if (isSystemInDarkTheme()) Color(0xFFD3FFD8) else Color(
                    0xFF3C732E
                )
            ),
            placeholder = {
                Text(
                    text = "00000 00000",
                    fontWeight = FontWeight.Thin,
                    color = Color(0xFF636363), fontSize = 25.sp,
                    modifier = Modifier.padding(bottom = 7.dp),
                    fontFamily = fontFamily
                )

            },
            leadingIcon = {
                Row(horizontalArrangement = Arrangement.Start) {
                    Icon(
                        Icons.Outlined.PhoneAndroid,
                        tint = if (isSystemInDarkTheme()) Color(0xFFADD8E6) else Color(0xFF3a81f1),
                        contentDescription = "Flag Icon",
                        modifier = Modifier
                            .padding(
                                start = 5.dp,
                                bottom = 8.dp
                            )
                            .width(40.dp)
                            .height(30.dp)
                    )
                    Text(
                        text = "+91",
                        fontSize = 25.sp,
                        fontFamily = fontFamily,
                        modifier = Modifier
                            .padding(
                                start = 10.dp,
                                end = 7.dp,
                                bottom = 8.dp
                            )
                    )
                }


            }

        )
    }

    Spacer(modifier = Modifier.height(30.dp))

    val context = LocalContext.current
    Box(modifier = Modifier
        .fillMaxWidth()){
        Button(colors = ButtonDefaults.buttonColors(if (isSystemInDarkTheme()) Color(0xFF3C732E) else Color(
            0xFF61CD46
        )
        ),
            onClick =
            {

                    navController.navigate(VendigoScreens.OtpVerifyScreen.name)
                    controller?.hide()
//                Toast.makeText(context, "Next Page Baby !!", Toast.LENGTH_SHORT).show()

            },
            modifier = Modifier
                .width(325.dp),
            enabled = viewModel.isButtonEnabled()
        )
        {
            Text(text = "Continue", fontFamily = fontFamily, fontWeight = FontWeight(800), color = MaterialTheme.colorScheme.onSurface)

        }
    }



}
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

}
