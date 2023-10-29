package com.example.kotlinweather.ui.view.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition


@Composable
fun InfoCard(){
    Column (modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Card (modifier = Modifier
            .width(300.dp)
            .height(450.dp)
            .offset(0.dp, (-45).dp), shape = RoundedCornerShape(25.dp), colors = CardDefaults.cardColors(
            Color(0xB34C6D8E)
        )){
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp), Alignment.BottomCenter){
                Text(text = "KotlinWeather", style = TextStyle(fontSize = 35.sp, fontStyle = FontStyle.Italic, color = Color(0xCCFFFFFF), textAlign = TextAlign.Center))
            }
            Box(modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(), Alignment.BottomCenter){
                Text(text = "This app was made by MIREA student, Ilya Vanyaev, IKBO-07-21", style = TextStyle(fontSize = 15.sp, fontStyle = FontStyle.Italic, color = Color(0xCCFFFFFF), textAlign = TextAlign.Center))
            }
            Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(), Alignment.Center) {
                WeatherAnimation()
            }
        }
    }
}

@Composable
fun WeatherAnimation(){
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("weather_icon_animation.json"))

    LottieAnimation(composition = composition, modifier = Modifier.size(90.dp), iterations = LottieConstants.IterateForever)
}


// border(1.dp, Color.White)