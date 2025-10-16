package com.fire.todo1.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fire.todo1.database.TodoEntity

@Composable
fun HomScreen(
    viewmodel: HomeViewmodel = viewModel()
){
    val todos by viewmodel.todos.collectAsState()

    val (dialogOpen, setDialogOpen) = remember {
        mutableStateOf(false)
    }
    if (dialogOpen) {
       val (title, setTitle) = remember {
            mutableStateOf("")
        }
       val (description, setDescription) = remember {
            mutableStateOf("")
        }
        Dialog(onDismissRequest = { setDialogOpen(false)})
        {



            Column(modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {

                OutlinedTextField(value = title, onValueChange = {
                    setTitle(it)
                }, modifier = Modifier.padding(8.dp),label = {
                    Text(text = "Title")
                },colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.Blue,
                ))


                Spacer(modifier = Modifier.height(4.dp))




                OutlinedTextField(value = description, onValueChange = {
                    setDescription(it)
                }, modifier = Modifier.padding(8.dp),label = {
                    Text(text = "Description")
                },colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.Blue,
                ))




            }
            Spacer(modifier = Modifier.height(4.dp))

            Button(onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()){
                    viewmodel.addTodo(TodoEntity(title=title,
                        subTitle = description))
                    setDialogOpen(false)
                }

            }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)) {//basic editing for button color and text
                Text("Submit",color=Color.White)
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
                //Icon(imageVector = Icons.Default.Add, , contentDescription = null)

            }
        })





    {
        paddingValues ->
        Box(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()){


            LazyColumn(modifier = Modifier.fillMaxSize()) {

                items(todos){todos->
                    Text(text=todos.title)
                }




            }



        }

    }








}