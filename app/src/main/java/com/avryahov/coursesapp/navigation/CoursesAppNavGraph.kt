package com.avryahov.coursesapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.avryahov.coursesapp.presentation.auth.LoginScreen
import com.avryahov.coursesapp.presentation.auth.RegistrationScreen
import com.avryahov.coursesapp.presentation.course.CourseScreen
import com.avryahov.coursesapp.presentation.favorite.FavouriteScreen
import com.avryahov.coursesapp.presentation.home.HomeScreen
import com.avryahov.coursesapp.presentation.onboarding.OnboardingScreen
import com.avryahov.coursesapp.presentation.profile.ProfileScreen

@Composable
fun CoursesAppNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = CoursesAppDestinations.ONBOARDING_ROUTE,
    navActions: CoursesAppNavigationActions = remember(navController) {
        CoursesAppNavigationActions(
            navController
        )
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(CoursesAppDestinations.ONBOARDING_ROUTE) { OnboardingScreen(onContinueClick = {}) }
        composable(CoursesAppDestinations.REGISTRATION_ROUTE) {
            RegistrationScreen(
                onLoginClick = {},
                onRegistrationSuccess = {})
        }
        composable(CoursesAppDestinations.LOGIN_ROUTE) {
            LoginScreen(
                onRegistrationClick = {},
                onLoginSuccess = {})
        }
        composable(CoursesAppDestinations.HOME_ROUTE) {
            HomeScreen { courseId ->
                println("Clicked course: $courseId")
            }
        }
        composable(CoursesAppDestinations.FAVOURITE_ROUTE) { FavouriteScreen() }
        composable(CoursesAppDestinations.PROFILE_ROUTE) { ProfileScreen() }
        composable(
            route = CoursesAppDestinations.COURSE_ROUTE,
            arguments = listOf(navArgument(CoursesAppDestinationsArgs.COURSE_ID_ARG) {
                type =
                    NavType.StringType
                nullable = true
            })
        ) { entry ->
            val courseId = entry.arguments?.getString(CoursesAppDestinationsArgs.COURSE_ID_ARG)
                ?: error("courseId was required")

            CourseScreen(
                courseId,
                onBackClick = { navActions.popBackStack() })
        }

    }


}