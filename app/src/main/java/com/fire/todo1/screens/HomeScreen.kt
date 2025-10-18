package com.fire.todo1.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.util.TableInfo
import com.fire.todo1.database.TodoEntity

@Composable
fun HomScreen(
    viewmodel: HomeViewmodel = viewModel()
) {
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
        Dialog(onDismissRequest = { setDialogOpen(false) })
        {


            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                OutlinedTextField(
                    value = title, onValueChange = {
                    setTitle(it)
                }, modifier = Modifier.padding(8.dp), label = {
                    Text(text = "Title")
                }, colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.Blue,
                )
                )

                Spacer(modifier = Modifier.height(4.dp))

                OutlinedTextField(
                    value = description, onValueChange = {
                    setDescription(it)
                }, modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp), label = {
                    Text(text = "Description")
                }, colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.Blue,
                )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (title.isNotEmpty() && description.isNotEmpty()) {
                            viewmodel.addTodo(
                                TodoEntity(
                                    title = title,
                                    subTitle = description
                                )
                            )
                            setDialogOpen(false)
                        }

                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {//basic editing for button color and text
                    Text(
                        "Submit",
                        color = Color.White,
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily
                    )
                }
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.tertiary,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { setDialogOpen(true) },
                contentColor = Color.Green,
                containerColor = Color.Black
            ) {
                //Icon(imageVector = Icons.Default.Add, , contentDescription = null)

            }
        })


    { paddingValues ->
        Box(
            modifier = Modifier

                .fillMaxSize(), contentAlignment = Alignment.Center
        )
        {


            AnimatedVisibility(
                visible = todos.isEmpty(),
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut()
            ) {
                Text(
                    "No Todos, please add one",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White,
                    fontSize = 22.sp
                )
            }

            AnimatedVisibility(
                visible = todos.isNotEmpty(),
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut()
            ) {


                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(
                        bottom = paddingValues.calculateBottomPadding() + 8.dp,
                        top = 8.dp,
                        start = 8.dp,
                        end = 8.dp
                    ), verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(todos.sortedBy { it.done }, key = { it.id }) {
                        TodoItem(
                            todo = it,
                            onClick = { viewmodel.updateTodo(it.copy(done = !it.done)) },
                            onDelete = { viewmodel.deleteTodo(it) }
                        )
                    }
                }


            }


        }

    }
}

@Composable
fun TodoItem(todo: TodoEntity,onClick:()->Unit, onDelete:()->Unit) {

    val color by animateColorAsState(
        targetValue = if (todo.done) Color.Green else Color.White,
        animationSpec = tween(500)
    )



    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color)
                .clickable(onClick = onClick)
                .padding(
                    horizontal = 12.dp,
                    vertical = 16.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.tertiary)
                        .padding(4.dp), contentAlignment = Alignment.Center
                ) {
                    Row {
                        AnimatedVisibility(
                            visible = todo.done,
                            enter = scaleIn() + fadeIn(),
                            exit = scaleOut() + fadeOut()
                        ) {
                            //   Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color.White) { } //still don't know why it snot working

                        }


                    }
                }
                Column(modifier = Modifier.weight(1f)) {

                    Text(
                        text = todo.title,
                        fontSize = 22.sp,
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                        fontWeight = MaterialTheme.typography.titleLarge.fontWeight
                    )

                    Text(
                        text = todo.subTitle, fontSize = 12.sp,color = Color(0xB2AD6868)
                    )
                }

            }
        }
        if (!todo.done){
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .size(25.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
                    .clickable(onClick = onDelete)
                    .padding(4.dp)
                , contentAlignment = Alignment.Center
            ){
                //Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = Color.White)
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HomScreen()
}














