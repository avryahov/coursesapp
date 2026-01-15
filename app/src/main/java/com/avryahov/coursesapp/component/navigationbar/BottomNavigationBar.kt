package com.avryahov.coursesapp.component.navigationbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.avryahov.coursesapp.R
import com.avryahov.coursesapp.navigation.CoursesAppDestinations
import com.avryahov.coursesapp.ui.model.BottomNavItem
import com.avryahov.coursesapp.ui.modifiers.borderTop

@Composable
fun BottomNavigationBar(
    navController: NavController,
    onNavigateToHome: () -> Unit,
    onNavigateToFavourite: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    val homeLabel = stringResource(R.string.bottom_nav_home)
    val favouriteLabel = stringResource(R.string.bottom_nav_favourite)
    val profileLabel = stringResource(R.string.bottom_nav_profile)

    val itemsByRoute = remember {
        mapOf(
            CoursesAppDestinations.HOME_ROUTE to BottomNavItem(
                label = homeLabel,
                iconResId = R.drawable.ic_home
            ),
            CoursesAppDestinations.FAVOURITE_ROUTE to BottomNavItem(
                label = favouriteLabel,
                iconResId = R.drawable.ic_favorite
            ),
            CoursesAppDestinations.PROFILE_ROUTE to BottomNavItem(
                label = profileLabel,
                iconResId = R.drawable.ic_account
            )
        )
    }

    fun shouldShowBottomBar(route: String?): Boolean {
        if (route == null) return false
        return itemsByRoute.containsKey(route) || route.startsWith("course/")
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""

    if (!shouldShowBottomBar(currentRoute)) return

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(MaterialTheme.colorScheme.background)
            .borderTop(
                color = MaterialTheme.colorScheme.outline,
                strokeWidth = 1.5.dp
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            itemsByRoute.forEach { (route, item) ->
                BottomNavigationItem(
                    item = item,
                    isSelected = currentRoute == route,
                    onClick = {
                        when (route) {
                            CoursesAppDestinations.HOME_ROUTE -> onNavigateToHome()
                            CoursesAppDestinations.FAVOURITE_ROUTE -> onNavigateToFavourite()
                            CoursesAppDestinations.PROFILE_ROUTE -> onNavigateToProfile()
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}