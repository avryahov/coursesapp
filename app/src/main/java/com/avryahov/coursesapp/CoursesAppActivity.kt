package com.avryahov.coursesapp;

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.avryahov.coursesapp.navigation.CoursesAppNavGraph
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity for the CoursesApp
 */
@AndroidEntryPoint
class CoursesAppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesAppTheme {
                CoursesAppNavGraph()
            }
        }
    }
}