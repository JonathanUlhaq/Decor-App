package com.belajar.roomdecorapp.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.belajar.roomdecorapp.R

@Composable
fun DetailLayout(
    navController: NavController
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.primary ,
        topBar = { TopBar(navController = navController)}
    ) {

            DetailMenu(modifier = Modifier
                .padding(it))


    }
}

@Composable
fun TextDetail() {
    Column(
        Modifier.padding(20.dp)
    ) {
        Text(text = "About Us",
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.surface)
        Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi" +
                " ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit " +
                "in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint" +
                " occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit" +
                " anim id est laborum.",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary)
    }
}

@Composable
fun DetailMenu(
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            DetailMenuImage()
            MenuBar()
            TextDetail()
        }
    }
}

@Composable
fun MenuBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        Card (
            elevation = 10.dp,
            shape = RoundedCornerShape(20.dp),
            backgroundColor = MaterialTheme.colors.background,

                ) {
            LazyRow( modifier = Modifier
                .padding(12.dp)) {
               item {
                   IconButton(onClick = { /*TODO*/ }) {
                       Column (
                           modifier = Modifier
                               .fillMaxWidth(),
                           horizontalAlignment = CenterHorizontally
                       ) {
                           Icon(imageVector = Icons.Default.Share,
                               contentDescription = null,
                               tint = MaterialTheme.colors.surface)
                           Text(text = "Share",
                               color = MaterialTheme.colors.surface,
                               style = MaterialTheme.typography.body1)
                       }
                   }
                   Spacer(modifier = Modifier.width(30.dp))
                   IconButton(onClick = { /*TODO*/ }) {
                       Column (
                           modifier = Modifier
                               .fillMaxWidth(),
                           horizontalAlignment = CenterHorizontally
                       ) {
                           Icon(imageVector = Icons.Default.MailOutline,
                               contentDescription = null,
                               tint = MaterialTheme.colors.surface)
                           Text(text = "Message",
                               color = MaterialTheme.colors.surface,
                               style = MaterialTheme.typography.body1)
                       }
                   }
                   Spacer(modifier = Modifier.width(30.dp))
                   IconButton(onClick = { /*TODO*/ }) {
                       Column (
                           modifier = Modifier
                               .fillMaxWidth(),
                           horizontalAlignment = CenterHorizontally
                       ) {
                           Icon(imageVector = Icons.Default.ThumbUp,
                               contentDescription = null,
                               tint = MaterialTheme.colors.surface)
                           Text(text = "Save",
                               color = MaterialTheme.colors.surface,
                               style = MaterialTheme.typography.body1)
                       }
                   }
               }
            }
        }
    }
}

@Composable
fun DetailMenuImage() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(CenterHorizontally),
        horizontalAlignment = CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.gmbr_door),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .padding(start = 20.dp, end = 20.dp)
                )
        Spacer(modifier = Modifier
            .height(10.dp))
        Text(text = "Mattle Grey Room",
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.surface)
        Spacer(modifier = Modifier
            .height(10.dp))
        Text(text = "$29.00",
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.surface)
    }
}

@Composable
fun TopBar(
    navController:NavController
) {
    Row(
        modifier = Modifier.padding(20.dp)
    ) {
        IconButton(onClick = { navController.navigateUp() },
                   modifier = Modifier
                       .clip(RoundedCornerShape(20.dp))
                       .background(MaterialTheme.colors.background)) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colors.surface)

        }

        Spacer(modifier = Modifier.weight(1F))
        IconButton(onClick = {  },
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colors.background)) {
            Icon(imageVector = Icons.Default.Menu,
                contentDescription = null,
                tint = MaterialTheme.colors.surface)

        }
    }
}