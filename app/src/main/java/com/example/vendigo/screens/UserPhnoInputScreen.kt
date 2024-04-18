package com.example.vendigo.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vendigo.R
import com.example.vendigo.ui.theme.fontFamily
import com.example.vendigo.widgets.TextField
import com.example.vendigo.widgets.VendigoAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PhoneVerifyScreen(navController: NavController){




        Surface(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)) {
            Column(modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
               ){
                Box(modifier = Modifier
                    .padding(start = 230.dp)
                )
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

                    TextField(navController = navController)

                }
                Spacer(modifier = Modifier.height(60.dp))


            }
        }







    }





