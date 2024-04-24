package com.example.vendigo.presentation.widgets



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vendigo.R
import com.example.vendigo.presentation.ui.theme.fontFamily


@Composable
fun VendigoAppBar(){

    var showMenu by remember {
        mutableStateOf(false)
    }
    var isExpand by remember {
        mutableStateOf(false)
    }
    var lang by remember {
        mutableStateOf("English")
    }
    val rotation = if(isExpand)180f else 0f




     Row(horizontalArrangement = Arrangement.End,
         verticalAlignment = Alignment.Top,
         modifier = Modifier
             .width(160.dp)
             ) {

//Custom Exposed Drop Down Menu --------------------------------------------------------------------------------------------------- //

         Box (modifier = Modifier
             .width(110.dp)
             .height(40.dp)
             .clickable(onClick = { isExpand = !isExpand })
             .border(
                 BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.onSurface),
                 shape = RoundedCornerShape(
                     CornerSize(10.dp)
                 )
             )
         ){
             Row(modifier = Modifier
                 .fillMaxSize())
             {
                 Icon(imageVector =ImageVector.vectorResource(id = R.drawable.globe),
                     contentDescription = "Language Icon",
                     tint =if (isSystemInDarkTheme())
                         Color(0xFFADD8E6)
                     else
                         Color(0xFF3a81f1),

                     modifier = Modifier
                         .size(30.dp)
                         .padding(top = 10.dp))

                 Text(text = "$lang",
                     fontFamily = fontFamily,
                     fontSize =15.sp,
                     fontWeight = FontWeight.W700,
                     modifier = Modifier
                         .padding(top = 10.dp)
                 )

                     Icon(Icons.Rounded.ArrowDropDown,
                         contentDescription ="ArrowDropDownRotation icon",
                         modifier = Modifier
                             .padding(top = 11.dp, end = 2.dp)
                             .rotate(rotation))



                 DropdownMenu(
                     expanded = isExpand ,
                     onDismissRequest =
                     {isExpand = false})
                 {
                      DropdownMenuItem(
                          text = {
                              Text("English",
                                  fontFamily = fontFamily,
                                  fontWeight =FontWeight(550))
                                 },

                          onClick = {
                              isExpand = false
                              lang = "English"
                          })
                     DropdownMenuItem(
                         text = {
                             Text("ಕನ್ನಡ",
                                 fontFamily = fontFamily,
                                 fontWeight =FontWeight(550))
                                },
                         onClick = {
                             isExpand = false
                             lang = "ಕನ್ನಡ    "
                         })
                 }

             }


         }


//Option Menu //--------------------------------------------------------------------------------------------------------


         IconButton(onClick = {
             showMenu = !showMenu}
         ) {
             Icon(imageVector = Icons.Rounded.MoreVert,
                 contentDescription ="More Option icon",
                 tint =MaterialTheme.colorScheme.onSurface, modifier = Modifier.size(35.dp).padding(bottom = 5.dp))
         }

             Box(modifier = Modifier.padding(top = 50.dp))
             {

                 DropdownMenu(expanded = showMenu,
                     onDismissRequest = { showMenu = false }
                 ) {
                     DropdownMenuItem(
                         text = {
                             Text(text = "About",
                                 fontFamily = fontFamily,
                                 fontWeight =FontWeight(550))},
                         onClick = {}
                     )

                     DropdownMenuItem(
                         text = { Text(text = "Help",
                             fontFamily = fontFamily,
                             fontWeight =FontWeight(550))},
                         onClick = {}
                     )


                 }




             }

         }


     }





