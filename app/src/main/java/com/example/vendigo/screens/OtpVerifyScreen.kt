package com.example.vendigo.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.vendigo.R
import com.example.vendigo.navigation.VendigoScreens
import com.example.vendigo.ui.theme.fontFamily
import com.example.vendigo.widgets.VendigoAppBar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OtpVerifyScreen(navController: NavController){

    //by remember means mutable variable(otp)
    var otp by remember {
        mutableStateOf(arrayOf("","","","","",""))
    }

    // = remember means immutable and is to remembered through the code
    val focusRequesters = remember {
        Array(6){FocusRequester()}
    }


    var phoneNo = 8971552754

    Surface(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()) {

            Box(modifier = Modifier
                .padding(start = 230.dp)
            )
            {
                VendigoAppBar()
            }

// Logo with Text

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)) {
                //can only use image painter resources in screen on surface ig//
                Image(
                    painter = painterResource(id = R.drawable.logo1),
                    contentDescription ="logo",
                    modifier =Modifier.
                    size(45.dp)

                )

                Text(
                    text = "Go",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W600,
                    fontSize = 28.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Verify your number",
                fontSize = 25.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight(600),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 30.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Enter the OTP sent to +91$phoneNo",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Thin,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 30.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))


            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            )
            {
                 for (index in otp.indices){
                     var otpDigit by remember { mutableStateOf(otp[index])}
                     TextField(
                        value = otpDigit,
                        onValueChange = {
                            value ->
//                            otp[index] = value.takeLast(1)
                            if(value.length <= 1 && value.isDigitsOnly()){
                                otpDigit = value
                            }
                            if (value.length == 1 && index < 5 && value.isDigitsOnly()){
                                focusRequesters[index+1].requestFocus()
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = if(index == 5) ImeAction.Done else ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                if(index < 5){
                                    focusRequesters[index+1].requestFocus()
                                }
                            },
                            onDone = {
                                navController.navigate(VendigoScreens.LocationScreen.name)
                            }
                        ),
                        singleLine = true,
                        maxLines = 1,
                        modifier = Modifier
                            .width(50.dp)
                            .focusRequester(focusRequesters[index])
                            .onKeyEvent { event ->
                                        if(event.key == Key.Backspace){
                                         if(index > 0){
                                             focusRequesters[index - 1].requestFocus()
                                         }
                                            return@onKeyEvent true
                                        }
                                false
                            },
                        textStyle = TextStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontFamily = fontFamily,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.W600
                        )
                    )
                }

            }



        }


    }
}