package com.belajar.roomdecorapp.ui.home

import android.graphics.drawable.Icon
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.belajar.roomdecorapp.R
import com.belajar.roomdecorapp.routes.Routes
import kotlinx.coroutines.launch

@Composable
fun HomeLayout(
    navController: NavController
) {
    var cart:Int by remember {
        mutableStateOf(0)
    }

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        backgroundColor = MaterialTheme.colors.primary,
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(cart,
                visible = cart > 0) {
                scope.launch {
                    scaffoldState.drawerState.apply {
                        open()
                    }
                }
            }
        },
        drawerContent = {
            Column(
                Modifier.padding(14.dp)
            ) {
                Text(text = "Pengaturan",
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.surface)
                Divider()
                (0..10).forEach {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Setting",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.surface)
                }
            }
        },
        drawerBackgroundColor = MaterialTheme.colors.background,
        drawerElevation = 12.dp
    , drawerGesturesEnabled = true
    ) {
            Column(modifier = Modifier
                .padding(it)) {
                HomeContent(navController = navController,
                    plus = {
                        cart = cart.plus(1)
                    })
            }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    plus:()->Unit,
    navController:NavController
) {
    val categories = listOf<String>(
        "All",
        "Drawing",
        "Accessories",
        "Bed",
        "Main",
        "Kitchen",
        "Study",
        "Sleep",
        "Dinning Room",
        "Aestetik Room",
        "Children",
        "Reading"
    )

    val room = listOf<Int>(
        R.drawable.gmb_diningroom,
        R.drawable.gmb_livingroom,
        R.drawable.gmbr_door,
        R.drawable.gmbr_sofa,
        R.drawable.gmbr_windows,
        R.drawable.gmb_diningroom,
        R.drawable.gmb_livingroom,
        R.drawable.gmbr_door,
        R.drawable.gmbr_sofa,
        R.drawable.gmbr_windows,
        R.drawable.gmb_diningroom,
        R.drawable.gmb_livingroom,
        R.drawable.gmbr_door,
        R.drawable.gmbr_sofa,
        R.drawable.gmbr_windows,
        R.drawable.gmb_diningroom,
        R.drawable.gmb_livingroom,
        R.drawable.gmbr_door,
        R.drawable.gmbr_sofa,
        R.drawable.gmbr_windows
    )

    var currentIndex:Int? by remember {
        mutableStateOf(null)
    }
    var hidden by remember {
        mutableStateOf(false)
    }
    var pageState = rememberLazyGridState()
    val percent = percentOffSet(pageState.firstVisibleItemIndex,room.size,pageState.layoutInfo.visibleItemsInfo.size)
    hidden = percent < 0.2
    AnimatedVisibility(visible = hidden,
        enter = expandVertically(tween(1000), expandFrom = Alignment.Top),
        exit = shrinkVertically(tween(1000),Alignment.Top)
    ) {
        CardItem()
    }

    val paddings by animateIntAsState(targetValue = if (hidden) 35 else 10 )
    Column(
        Modifier.padding(start = 20.dp, top = paddings.dp, bottom = 35.dp)
    ) {
        Text(text = "Bedroom Collection",
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .padding(bottom = 15.dp),
            color = MaterialTheme.colors.surface)
        LazyRow(content = {
           itemsIndexed(categories) { index, item ->
               CategoriesItem(cat = item,
                   onClick = index == currentIndex,
                    clickListener = {
                        currentIndex = index
                    })
           }
        },
        modifier = Modifier
            .padding(bottom = 30.dp))

        LazyVerticalGrid(columns = GridCells.Fixed(2),
            state = pageState,
            content = {
        itemsIndexed(room) {
            index,item ->
            HomeItem(image = item,
                    addCart = {plus.invoke()},
                    navController =  navController)
        }
        })
    }
}

@Composable
fun HomeItem(
    image:Int,
    addCart:() -> Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .padding(end = 20.dp, top = 14.dp)
    ) {
        Box {
            Image(painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .fillMaxSize(0.95F)
                    .clickable { navController.navigate(Routes.Detail.route) }
                    )
            
            IconButton(onClick = { addCart.invoke() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                        .padding(end = 10.dp)) {
                Card(
                    backgroundColor = Color.Black.copy(0.3F),
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(14.dp),
                    contentColor = Color.White
                ) {
                        Icon(imageVector = Icons.Filled.Add,
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp))
                }
                
            }
        }

        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "Executive Grey Room",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.surface)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "$29.00",
            style = MaterialTheme.typography.h1,
            fontSize = 14.sp,
            color = MaterialTheme.colors.surface)
    }
}

@Composable
fun CategoriesItem(
    cat:String,
    onClick:Boolean,
    clickListener:()->Unit
) {

    val color by animateColorAsState(targetValue = if (onClick) MaterialTheme.colors.surface else MaterialTheme.colors.secondary)

    Column (
        modifier = Modifier
            .fillMaxWidth()
            ) {
        Text(text = "$cat",
            style = MaterialTheme.typography.h1,
            color = color,
            modifier = Modifier
                .padding(end = 18.dp)
                .clickable { clickListener.invoke() },
            fontSize = 16.sp)
        AnimatedVisibility(visible = onClick) {
            Box(modifier = Modifier
                .clip(CircleShape)
                .background(Color.Black)
                .height(3.dp)
                .width(20.dp)

                )
        }
    }
}


@Composable
fun CardItem() {

            Box (
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, top = 30.dp)
                    ) {

                Image(painter = painterResource(id = R.drawable.banner_room),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp)))

                Column(
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .fillMaxHeight(0.3F)
                        .wrapContentHeight(CenterVertically)
                ) {
                    Text(text = "Decore For \n Your Home!!",
                        style = MaterialTheme.typography.h1,
                        textAlign = TextAlign.Start
                    )

                    Button(onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(Color.Black),
                    ) {
                        Text(text = "View More",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface)
                    }
                }



            }

    }


@Composable
fun TopBar(
    jumlah:Int,
    visible:Boolean,
    drawer:()->Unit
) {
    Row(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 14.dp, bottom = 14.dp),
        verticalAlignment = CenterVertically
    ) {
        IconButton(onClick = { drawer.invoke() }) {
               Card(
                   shape = CircleShape,
                   backgroundColor = MaterialTheme.colors.background
               ) {
                   Icon(painter = painterResource(id = R.drawable.menu_icon),
                       contentDescription = null,
                       modifier = Modifier
                           .padding(10.dp),
                    tint = MaterialTheme.colors.surface)

               }


        }

        Spacer(modifier = Modifier
            .weight(1F))
       Column {
           AnimatedVisibility(visible = visible ) {
               Box(modifier = Modifier
                   .padding(start = 5.dp)
                   .clip(CircleShape)
                   .background(Color.Red)) {
                   Text(text = "$jumlah",
                       Modifier.padding(10.dp),
                       color = MaterialTheme.colors.onSurface,
                       fontSize = 10.sp)
               }
           }

           Icon(painter = painterResource(id = R.drawable.cart_icon),
               contentDescription = null,
               tint = MaterialTheme.colors.surface,
            modifier = Modifier)

       }

    }
}

private fun percentOffSet(currentIndex:Int,allItem:Int,currentShowIndex: Int):Float {
    return (currentIndex / (allItem - currentShowIndex).toFloat())* 100
}