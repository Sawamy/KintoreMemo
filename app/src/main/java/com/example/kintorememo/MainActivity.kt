package com.example.kintorememo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.kintorememo.ui.exercise.ExerciseEntryRoute
import com.example.kintorememo.ui.theme.KintoreMemoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.text.Typography.dagger

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KintoreMemoTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ExerciseEntryRoute()
                }
            }
        }
    }
}
