package com.belajar.roomdecorapp.ui.splashscreen

import android.os.Handler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.belajar.roomdecorapp.R
import com.belajar.roomdecorapp.routes.Routes
import com.belajar.roomdecorapp.ui.theme.Purple500

@Composable
fun SplashScreen(
    navController:NavController
) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .wrapContentWidth(CenterHorizontally)
                .wrapContentHeight(CenterVertically)
        ) {


            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(CenterHorizontally)
                    .padding(12.dp)
            ) {
                Card (
                    backgroundColor = MaterialTheme.colors.background,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(CenterHorizontally),
                    shape = CircleShape,
                    elevation = 0.dp
                ){

                    Image(painter = painterResource(id = R.drawable.gmb_livingroom),
                        contentDescription = null,
                        modifier = Modifier
                            .size(300.dp)
                    )

                }

                Text(text = "Aplikasi Gabut",
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.surface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(CenterHorizontally),
                    textAlign = TextAlign.Center)

            }

            Handler().postDelayed({
                navController.navigate(Routes.ViewPager.route)
            },2000)
        }
}