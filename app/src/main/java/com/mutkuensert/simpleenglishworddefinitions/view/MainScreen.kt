package com.mutkuensert.simpleenglishworddefinitions

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.mutkuensert.simpleenglishworddefinitions.ui.theme.DarkBlue
import com.mutkuensert.simpleenglishworddefinitions.ui.theme.LightBlue

@Composable
fun MainScreen() {
    Surface(modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colors.primary) {
        SearchArea()
    }
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SearchArea(){
    val (searchText, setText) = remember { mutableStateOf("") }
    var contentVisibility by remember { mutableStateOf(false)}

    var response by remember { mutableStateOf("Response")}

    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(35.dp))

        Card(modifier = Modifier
            .padding(12.dp)
            .shadow(elevation = 10.dp)
            .fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.primary) {
            
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 10.dp)) {
                
                CustomTextField(text = searchText, setValue = setText,
                    modifier = Modifier
                        .weight(5f)
                        .padding(start = 15.dp, bottom = 5.dp))

                Spacer(modifier = Modifier.width(20.dp))

                Button(onClick = { contentVisibility = !contentVisibility },
                    elevation = ButtonDefaults.elevation(defaultElevation = 10.dp),
                    modifier = Modifier
                        .weight(2f)
                        .padding(end = 15.dp)) {
                    Text(text = "Search")
                }
            }
        }

        AnimatedVisibility(visible = contentVisibility) {
            Card(modifier = Modifier
                .padding(12.dp)
                .shadow(elevation = 10.dp)
                .fillMaxWidth(),
                backgroundColor = MaterialTheme.colors.primary) {
                AnimatedContent(targetState = response, modifier = Modifier.padding(10.dp)) {
                    Text(text = response)
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
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = LightBlue, focusedLabelColor = DarkBlue, unfocusedBorderColor = LightBlue))
}