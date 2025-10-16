package com.fire.todo1.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun HomScreen(
    viewmodel: HomeViewmodel
){
    val todos by viewmodel.todos.collectAsState()

    val (dialogOpen, setDialogOpen) = remember {
        mutableStateOf(false)
    }
    if (dialogOpen) {
        Dialog(onDismissRequest = { setDialogOpen(false) }) {
            Column(modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(Color.White).padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {

                OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.padding(8.dp),label = {
                    Text(text = "Title")
                },colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.Blue,
                ))

            }
        }
    }

    Scaffold(containerColor = MaterialTheme.colorScheme.tertiary,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { setDialogOpen(true) },
                containerColor = Color.Green,
                contentColor = Color.Black
            ) {
               // Icon(imageVector = Icons.Default.Add, contentDescription = null)

            }
        })





    {
        paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()){



        }

    }








}