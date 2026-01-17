package com.avryahov.coursesapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.avryahov.coursesapp.component.navigationbar.BottomNavigationBar
import com.avryahov.coursesapp.presentation.auth.ForgotPasswordScreen
import com.avryahov.coursesapp.presentation.auth.LoginScreen
import com.avryahov.coursesapp.presentation.auth.RegistrationScreen
import com.avryahov.coursesapp.presentation.course.CourseScreen
import com.avryahov.coursesapp.presentation.favorite.FavouriteScreen
import com.avryahov.coursesapp.presentation.home.HomeScreen
import com.avryahov.coursesapp.presentation.onboarding.OnboardingScreen
import com.avryahov.coursesapp.presentation.profile.ProfileScreen

@Composable
fun CoursesAppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val navActions = remember(navController) {
        CoursesAppNavigationActions(navController)
    }

    val currentRoute by navController.currentBackStackEntryAsState()
    val route = currentRoute?.destination?.route

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar(route)) {
                BottomNavigationBar(
                    navController = navController,
                    onNavigateToHome = { navActions.navigateToHome() },
                    onNavigateToFavourite = { navActions.navigateToFavourite() },
                    onNavigateToProfile = { navActions.navigateToProfile() }
                )
            }
        }
    ) { padding: PaddingValues ->
        NavHost(
            navController = navController,
            startDestination = CoursesAppDestinations.ONBOARDING_ROUTE,
            modifier = Modifier.padding(padding)
        ) {
            composable(CoursesAppDestinations.ONBOARDING_ROUTE) {
                OnboardingScreen(onContinueClick = {
                    navController.navigate(CoursesAppDestinations.REGISTRATION_ROUTE)
                })
            }

            composable(CoursesAppDestinations.REGISTRATION_ROUTE) {
                RegistrationScreen(
                    onLoginClick = {
                        navController.navigate(CoursesAppDestinations.LOGIN_ROUTE) {
                            popUpTo(CoursesAppDestinations.ONBOARDING_ROUTE) { inclusive = true }
                        }
                    },
                    onRegistrationSuccess = {
                        navController.navigate(CoursesAppDestinations.LOGIN_ROUTE) {
                            popUpTo(CoursesAppDestinations.ONBOARDING_ROUTE) { inclusive = true }
                        }
                    }
                )
            }

            composable(CoursesAppDestinations.LOGIN_ROUTE) {
                LoginScreen(
                    onRegisterClick = {
                        navController.navigate(CoursesAppDestinations.REGISTRATION_ROUTE)
                    },
                    onForgotPasswordClick = {
                        navController.navigate(CoursesAppDestinations.FORGOT_PASSWORD_ROUTE)
                    },
                    onLoginSuccess = {
                        navController.navigate(CoursesAppDestinations.HOME_ROUTE) {
                            popUpTo(CoursesAppDestinations.ONBOARDING_ROUTE) { inclusive = true }
                        }
                    }
                )
            }

            composable(CoursesAppDestinations.FORGOT_PASSWORD_ROUTE) {
                ForgotPasswordScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(CoursesAppDestinations.HOME_ROUTE) {
                HomeScreen(navController) { courseId ->
                    navActions.navigateToCourse(courseId)
                }
            }

            composable(CoursesAppDestinations.FAVOURITE_ROUTE) {
                FavouriteScreen(navController)
            }

            composable(CoursesAppDestinations.PROFILE_ROUTE) {
                ProfileScreen(navController)
            }

            composable(
                route = CoursesAppDestinations.COURSE_ROUTE,
                arguments = listOf(navArgument(CoursesAppDestinationsArgs.COURSE_ID_ARG) {
                    type = NavType.StringType
                    nullable = true
                })
            ) { entry ->
                val courseId = entry.arguments?.getString(CoursesAppDestinationsArgs.COURSE_ID_ARG)
                    ?: error("courseId was required")

                CourseScreen(
                    courseId = courseId,
                    onBackClick = { navActions.popBackStack() },
                    navController = navController
                )
            }
        }
    }
}

private fun shouldShowBottomBar(route: String?): Boolean {
    if (route == null) return false
    return setOf(
        CoursesAppDestinations.HOME_ROUTE,
        CoursesAppDestinations.FAVOURITE_ROUTE,
        CoursesAppDestinations.PROFILE_ROUTE
    ).contains(route) || route.startsWith("course/")
}