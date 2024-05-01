package com.example.vendigo.presentation.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.vendigo.R
import com.example.vendigo.common.ResultState
import com.example.vendigo.common.commonDialog
import com.example.vendigo.navigation.VendigoScreens
import com.example.vendigo.presentation.components.Button
import com.example.vendigo.presentation.components.TextField
import com.example.vendigo.presentation.components.VendigoAppBar
import com.example.vendigo.presentation.ui.theme.fontFamily
import com.example.vendigo.presentation.viewmodel.PhoneAuthViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun UserPhnoInputScreen(
    navController: NavController,
    viewModel: PhoneAuthViewModel = hiltViewModel(),
    activity: Activity
)

{

    var phoneNumber = remember {
        mutableStateOf("")
    }

    val controller = LocalSoftwareKeyboardController.current

    val scope = rememberCoroutineScope()
    var isDialog by remember {
        mutableStateOf(false)
    }

    if(isDialog){
        commonDialog()
    }

        val context = LocalContext.current


        Surface(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)) {
            Column(modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
               ){
                Row( horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth())
                {
                VendigoAppBar()
                }


                Spacer(modifier = Modifier.height(30.dp))
// Intro Text
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp)) {
                    //can only use image painter resources in screen on surface ig//
                    Image(
                        painter = painterResource(id = R.drawable.logo1),
                        contentDescription ="logo",
                        modifier =Modifier.
                        size(50.dp)
                    )

                    Text(
                        text = "Go",
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.W600,
                        fontSize = 32.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )

                Text(
                    text = "Welcome to Vendigo",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W600,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(start = 30.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(
                    modifier = Modifier
                        .height(15.dp)
                )

                Text(
                    text = "Enter your phone number",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W100,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(start = 30.dp)
                )
                Spacer(
                    modifier = Modifier
                        .height(30.dp)
                )

                Row (modifier = Modifier.padding(start = 30.dp)){

                    TextField(phoneNumber = phoneNumber)

                }

                Spacer(modifier = Modifier.height(30.dp))

                Row(modifier = Modifier
                    .padding(start = 30.dp)
                    .fillMaxWidth())
                {
                   Button(
                       label = "Continue",
                       onClick = {
                                 navController.navigate(VendigoScreens.OtpVerifyScreen.name)
                                controller?.hide()
                                viewModel.finalPhoneNumber.value = "+91${phoneNumber.value}"

                                scope.launch(Dispatchers.Main){
                                    viewModel.createUserWithPhone(
                                         viewModel.finalPhoneNumber.value,
                                        activity
                                    ).collect{
                                        when(it){
                                            is ResultState.Failure -> {
                                                 isDialog = false
                                                Toast.makeText(context,"${it.msg.toString()}",Toast.LENGTH_SHORT).show()

                                            }
                                            ResultState.Loading -> {
                                                isDialog = true
                                            }
                                            is ResultState.Success -> {
                                                Toast.makeText(context,"${it.data}", Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                    }
                                }


                                 },
                       enable =viewModel.buttonEnable(phoneNumber = phoneNumber)
                   )
                }



            }
        }




    }





