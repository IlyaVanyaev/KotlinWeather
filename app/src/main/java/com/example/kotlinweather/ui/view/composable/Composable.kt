package com.example.kotlinweather.ui.view.composable

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.kotlinweather.data.model.WeatherModel


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
                Text(text = "KotlinWeather", style = TextStyle(fontSize = 35.sp, fontStyle = FontStyle.Italic, color = LightWhite, textAlign = TextAlign.Center))
            }
            Box(modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(), Alignment.BottomCenter){
                Text(text = "This app was made by MIREA student, Ilya Vanyaev, IKBO-07-21", style = TextStyle(fontSize = 15.sp, fontStyle = FontStyle.Italic, color = LightWhite, textAlign = TextAlign.Center))
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(), Alignment.Center) {
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


@Composable
fun HoursList(context: Context, weatherModel: List<WeatherModel>){
    LazyRow(){
        items((weatherModel.indices).toList()){
            HoursItem(weatherModel[it])
        }
    }
}

@Composable
fun HoursItem(weatherModel: WeatherModel){
    Card(modifier = Modifier
        .size(70.dp, 200.dp)
        .padding(3.dp), shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(LightBlue)) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = weatherModel.date.drop(11), style = TextStyle(fontSize = 15.sp, color = LightWhite, textAlign = TextAlign.Center), modifier = Modifier.offset(y = 15.dp))
            AsyncImage(model = "https:${weatherModel.weatherImage}", contentDescription = "weatherConditionImage", modifier = Modifier
                .size(30.dp)
                .offset(y = 25.dp))
            Text(text = "${weatherModel.recentTemperature}\u00B0", style = TextStyle(fontSize = 20.sp, color = LightWhite, textAlign = TextAlign.Center), modifier = Modifier.offset(y = 40.dp))
            Text(text = weatherModel.condition, style = TextStyle(fontSize = 11.sp, color = LightWhite, textAlign = TextAlign.Center), modifier = Modifier
                .height(45.dp)
                .offset(y = 55.dp))
        }
    }
}



// border(1.dp, Color.White)