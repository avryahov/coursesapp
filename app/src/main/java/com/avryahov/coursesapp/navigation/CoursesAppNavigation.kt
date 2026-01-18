package com.avryahov.coursesapp.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * Screens used in [CoursesAppDestinations]
 */
private object CoursesAppScreens {
    const val ONBOARDING_SCREEN = "onboarding"
    const val REGISTRATION_SCREEN = "registration"
    const val LOGIN_SCREEN = "login"
    const val FORGOT_PASSWORD_SCREEN = "forgot_password"
    const val HOME_SCREEN = "home"
    const val COURSE_SCREEN = "course"
    const val FAVOURITE_SCREEN = "favourite"
    const val PROFILE_SCREEN = "profile"
}

/**
 * Arguments used in [CoursesAppDestinations] routes
 */
object CoursesAppDestinationsArgs {
    const val COURSE_ID_ARG = "courseId"
}

/**
 * Destinations used in the [com.avryahov.coursesapp.CoursesAppActivity]
 */
object CoursesAppDestinations {
    const val ONBOARDING_ROUTE = CoursesAppScreens.ONBOARDING_SCREEN
    const val REGISTRATION_ROUTE = CoursesAppScreens.REGISTRATION_SCREEN
    const val LOGIN_ROUTE = CoursesAppScreens.LOGIN_SCREEN
    const val FORGOT_PASSWORD_ROUTE = CoursesAppScreens.FORGOT_PASSWORD_SCREEN
    const val HOME_ROUTE = CoursesAppScreens.HOME_SCREEN
    const val FAVOURITE_ROUTE = CoursesAppScreens.FAVOURITE_SCREEN
    const val PROFILE_ROUTE = CoursesAppScreens.PROFILE_SCREEN
    const val COURSE_ROUTE =
        "${CoursesAppScreens.COURSE_SCREEN}/{${CoursesAppDestinationsArgs.COURSE_ID_ARG}}"
}

/**
 * Models the navigation actions in the app.
 */
class CoursesAppNavigationActions(private val navController: NavHostController) {

    private fun navigateTo(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToRegistration() {
        navController.navigate(CoursesAppDestinations.REGISTRATION_ROUTE)
    }

    fun navigateToLogin() {
        navController.navigate(CoursesAppDestinations.LOGIN_ROUTE)
    }

    fun navigateToForgotPassword() {
        navController.navigate(CoursesAppDestinations.FORGOT_PASSWORD_ROUTE)
    }

    fun navigateToHome() {
        navigateTo(CoursesAppDestinations.HOME_ROUTE)
    }

    fun navigateToFavourite() {
        navigateTo(CoursesAppDestinations.FAVOURITE_ROUTE)
    }

    fun navigateToProfile() {
        navigateTo(CoursesAppDestinations.PROFILE_ROUTE)
    }

    fun navigateToCourse(courseId: Int) {
        navController.navigate("${CoursesAppScreens.COURSE_SCREEN}/$courseId")
    }

    fun popBackStack() {
        navController.popBackStack()
    }
}