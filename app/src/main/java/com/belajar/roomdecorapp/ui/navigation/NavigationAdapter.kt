package com.belajar.roomdecorapp.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable
import com.belajar.roomdecorapp.routes.Routes
import com.belajar.roomdecorapp.ui.detail.DetailLayout
import com.belajar.roomdecorapp.ui.home.HomeLayout
import com.belajar.roomdecorapp.ui.splashscreen.SplashScreen
import com.belajar.roomdecorapp.ui.viewpager.ViewPager
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationAdapter() {
    val navHost = rememberAnimatedNavController()
    AnimatedNavHost(navController = navHost, startDestination = Routes.SplashScreen.route ) {
        composable(Routes.Home.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Right, tween(1000))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Up, tween(1000))
            }
                     ) {
            HomeLayout(navHost)
        }

        composable(Routes.SplashScreen.route,
            enterTransition = {
                fadeIn(tween(1000))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, tween(1000))
            }
        ) {
            SplashScreen(navController = navHost)
        }

        composable(Routes.ViewPager.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Right, tween(1000))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, tween(1000))
            }) {
                ViewPager(navController = navHost)
        }

        composable(Routes.Detail.route,
                    enterTransition = {
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Up, tween(1000))
                    },
                    exitTransition = {
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, tween(1000))
                    }
        ) {
            DetailLayout(navHost)
        }
    }
}