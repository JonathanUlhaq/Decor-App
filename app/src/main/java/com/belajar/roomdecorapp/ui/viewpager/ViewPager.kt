package com.belajar.roomdecorapp.ui.viewpager

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.belajar.roomdecorapp.R
import com.belajar.roomdecorapp.routes.Routes
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ViewPager(
    navController:NavController
) {

    val room = listOf<Int>(
        R.drawable.gmb_diningroom,
        R.drawable.gmb_livingroom,
        R.drawable.gmbr_door,
    )

    val state = rememberPagerState()
    val scope = rememberCoroutineScope()
    var show by remember {
        mutableStateOf(false)
    }

  LazyColumn(modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight()
      .wrapContentWidth(CenterHorizontally)
      .wrapContentHeight(CenterVertically),
      content = {
      item {
          HorizontalPager(count = room.size,
              modifier = Modifier
                  .fillMaxWidth()
                  .fillMaxHeight()
                  .wrapContentWidth(CenterHorizontally)
                  .wrapContentHeight(CenterVertically),
              state = state) {

              ItemViewPager(image = room[it],
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(CenterHorizontally)
                                .padding(12.dp)
                                .graphicsLayer {
                                    val pageOffSet = calculateCurrentOffsetForPage(it).absoluteValue
                                    // We animate the scaleX + scaleY, between 85% and 100%
                                    lerp(
                                        start = 0.85f,
                                        stop = 1f,
                                        fraction = 1f - pageOffSet.coerceIn(0f, 1f)
                                    ).also { scale ->
                                        scaleX = scale
                                        scaleY = scale
                                    }

                                    // We animate the alpha, between 50% and 100%
                                    alpha = lerp(
                                        start = 0.5f,
                                        stop = 1f,
                                        fraction = 1f - pageOffSet.coerceIn(0f, 1f)
                                    )
                                })




          }
          Spacer(modifier = Modifier.padding(15.dp))
          DotsIndicator(numIndex = 3, currentIndex = state.currentPage ) {
              scope.launch {
                  state.scrollToPage(it)
              }
          }
          show = state.currentPage == 2
          Spacer(modifier = Modifier.padding(10.dp))
          AnimatedVisibility(visible = show,
                    enter = fadeIn(tween(500)),
                    exit = fadeOut(tween(500))
          ) {
              Column(
                  modifier = Modifier
                      .fillMaxWidth()
                      .wrapContentWidth(End)
                      .padding(end = 20.dp)
              ) {
                  Button(onClick = { navController.navigate(Routes.Home.route) },
                      colors = ButtonDefaults.buttonColors(MaterialTheme.colors.onBackground)) {
                      Text(text = "Home",
                          style = MaterialTheme.typography.body1,
                          color = MaterialTheme.colors.onSurface)
                  }
              }
          }

      }
  })
}

@Composable
fun DotsIndicator(
    numIndex:Int,
    currentIndex:Int,
    changePosition:(Int)->Unit
) {
    var current by remember {
        mutableStateOf(false)
    }


    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .wrapContentWidth(CenterHorizontally)
        ,content = {

        items(numIndex) {
            current = it == currentIndex
            val colorrs by animateColorAsState(targetValue = if (current) Color.Black else Color.LightGray)
            val ukuran by animateIntAsState(targetValue = if (current) 16 else 10,
                animationSpec = tween(700)
            )
            Box(
                modifier = Modifier
                    .size(ukuran.dp)
                    .clip(CircleShape)
                    .padding(1.dp)
                    .background(colorrs)
                    .clickable { changePosition.invoke(it) }

            )
            Spacer(modifier = Modifier.width(12.dp))
        }
    })
}

@Composable
fun ItemViewPager(
    image:Int,
    modifier:Modifier

) {
    Column (
        modifier = modifier
    ) {
   Card (
       backgroundColor = MaterialTheme.colors.background,
       modifier = Modifier
           .fillMaxWidth()
           .wrapContentWidth(CenterHorizontally),
       shape = CircleShape,
       elevation = 0.dp
           ){

           Image(painter = painterResource(id = image),
               contentDescription = null,
               modifier = Modifier
                   .size(300.dp)
           )

   }

        Text(text = "Kursi adalah sebuah perabotan rumah tangga yang digunakan untuk" +
                "melakukan suatu kegiatan, yaitu duduk atau menempelkan pantat pada kain halus" +
                "di kursi tersebut atau sering juga disebut lungguh",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.surface)

   }
}