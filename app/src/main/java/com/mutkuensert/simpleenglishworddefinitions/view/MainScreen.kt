package com.mutkuensert.simpleenglishworddefinitions

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.mutkuensert.simpleenglishworddefinitions.ui.theme.DarkBlue
import com.mutkuensert.simpleenglishworddefinitions.ui.theme.LightBlue
import com.mutkuensert.simpleenglishworddefinitions.viewmodel.MainScreenViewModel

@Composable
fun MainScreen(viewModel: MainScreenViewModel) {
    Surface(modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colors.surface) {
        SearchArea(viewModel = viewModel)
    }
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SearchArea(viewModel: MainScreenViewModel){
    val (searchText, setText) = remember { mutableStateOf("") }
    var contentVisibility by remember { mutableStateOf(false)}

    var response = viewModel.response.observeAsState()

    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(35.dp))

        Card(modifier = Modifier
            .padding(12.dp)
            .shadow(elevation = 10.dp)
            .fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.surface) {
            
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 10.dp)) {
                
                CustomTextField(text = searchText, setValue = setText,
                    modifier = Modifier
                        .weight(5f)
                        .padding(start = 15.dp, bottom = 5.dp))

                Spacer(modifier = Modifier.width(20.dp))

                Button(onClick = {
                    contentVisibility = true
                    viewModel.requestDefinition(searchText)
                                 },
                    elevation = ButtonDefaults.elevation(defaultElevation = 10.dp),
                    modifier = Modifier
                        .weight(2f)
                        .padding(end = 15.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface)) {
                    Text(text = "Search")
                }
            }
        }

        AnimatedVisibility(visible = contentVisibility) {
            Card(modifier = Modifier
                .padding(12.dp)
                .shadow(elevation = 10.dp)
                .fillMaxWidth(),
                backgroundColor = MaterialTheme.colors.surface) {
                AnimatedContent(targetState = response, modifier = Modifier.padding(10.dp)) {
                    Text(text = response.value.toString())
                }
            }
        }
    }
}


@Composable
fun CustomTextField(text: String, setValue: (String)-> Unit, modifier: Modifier = Modifier){
    OutlinedTextField(modifier = modifier,
        value = text,
        onValueChange = setValue,
        label = { Text("Search") },
        singleLine = true
        /*,colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = LightBlue,
            focusedLabelColor = DarkBlue,
            unfocusedBorderColor = LightBlue, cursorColor = DarkBlue, leadingIconColor = DarkBlue)*/)
}