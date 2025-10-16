package com.fire.todo1.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun HomScreen(
    viewmodel: HomeViewmodel
){
    val todos by viewmodel.todos.collectAsState()

    Scaffold(containerColor = MaterialTheme.colorScheme.tertiary,
        floatingActionButton = {
            FloatingActionButton(onClick = {}, containerColor = Color.Green, contentColor = Color.Black) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)

            }
        })





    {paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()){

        }

    }








}