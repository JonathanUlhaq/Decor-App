package com.belajar.roomdecorapp.routes

sealed class Routes(val route:String) {
    object Detail:Routes("detail")
    object Home:Routes("home")
    object SplashScreen:Routes("splash")
    object ViewPager:Routes("view_pager")
}
