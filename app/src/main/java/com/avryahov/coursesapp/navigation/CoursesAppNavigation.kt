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
    const val HOME_ROUTE = CoursesAppScreens.HOME_SCREEN
    const val FAVOURITE_ROUTE = CoursesAppScreens.FAVOURITE_SCREEN
    const val PROFILE_ROUTE = CoursesAppScreens.PROFILE_SCREEN

    const val COURSE_ROUTE =
        "${CoursesAppScreens.COURSE_SCREEN}/${CoursesAppDestinationsArgs.COURSE_ID_ARG}"
}


/**
 * Models the navigation actions in the app.
 */
class CoursesAppNavigationActions(private val navController: NavHostController) {

    fun navigateToRegistration() {
        navController.navigate(CoursesAppDestinations.REGISTRATION_ROUTE)
    }

    fun navigateToLogin() {
        navController.navigate(CoursesAppDestinations.LOGIN_ROUTE)
    }

    fun navigateToHome() {
        navController.navigate(CoursesAppDestinations.HOME_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true;
            }
        }
    }

    fun navigateToFavourite() {
        navController.navigate(CoursesAppDestinations.FAVOURITE_ROUTE)
    }

    fun navigateToProfile() {
        navController.navigate(CoursesAppDestinations.PROFILE_ROUTE)
    }

    fun navigateToCourse(courseId: String) {
        navController.navigate("${CoursesAppScreens.COURSE_SCREEN}/${courseId}")
    }

    fun popBackStack() {
        navController.popBackStack()
    }
}