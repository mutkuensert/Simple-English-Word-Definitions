package com.mutkuensert.simpleenglishworddefinitions

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.mutkuensert.simpleenglishworddefinitions.ui.theme.DarkBlue
import com.mutkuensert.simpleenglishworddefinitions.ui.theme.LightBlue
import com.mutkuensert.simpleenglishworddefinitions.util.Resource
import com.mutkuensert.simpleenglishworddefinitions.util.Status
import com.mutkuensert.simpleenglishworddefinitions.viewmodel.MainScreenViewModel

@Composable
fun MainScreen(context: Context,viewModel: MainScreenViewModel) {
    Surface(modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colors.surface) {
        SearchArea(context, viewModel = viewModel)
    }
}

@Composable
fun SearchArea(context: Context,viewModel: MainScreenViewModel){
    val (searchText, setText) = remember { mutableStateOf("") }
    var contentVisibility by remember { mutableStateOf(false)}
    var loading by remember { mutableStateOf(false)}

    var response = viewModel.response.observeAsState()

    when(response.value?.status){
        Status.STANDBY->{
            contentVisibility = false
            loading = false
        }

        Status.LOADING->{
            contentVisibility = false
            loading = true
        }

        Status.SUCCESS->{
            contentVisibility = true
            loading = false
        }

        Status.ERROR->{
            contentVisibility = false
            loading = false
            Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()
        }

    }

    Column(modifier = Modifier
        .fillMaxWidth()
        //.verticalScroll(rememberScrollState())
        ,horizontalAlignment = Alignment.CenterHorizontally) {

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

                Button(onClick = {viewModel.requestDefinition(searchText)},
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
                LazyColumn(modifier = Modifier.padding(10.dp)){
                    response.value!!.data?.let { //Response.value is non-null because it was assigned with a default in view model.
                        items(it){item->
                            Row{
                                Text(text = item.word.toString(), modifier = Modifier.weight(1f))
                                Spacer(modifier = Modifier.width(3.dp))
                                Column(modifier = Modifier.weight(3f)) {
                                    item.defs?.let { defsList->
                                        for (i in defsList){
                                            Text(text = i.toString())
                                            Spacer(modifier = Modifier.height(3.dp))
                                            Divider(modifier = Modifier.fillMaxWidth())
                                            Spacer(modifier = Modifier.height(3.dp))
                                        }
                                    }

                                }
                            }
                            if(it.size>1){
                                Spacer(modifier = Modifier.height(8.dp))
                                Divider(modifier = Modifier.fillMaxWidth().height(3.dp))
                                Spacer(modifier = Modifier.height(8.dp))
                            }

                        }
                    }

                }
            }
        }

        AnimatedVisibility(visible = loading) {
            Card(modifier = Modifier
                .padding(12.dp)
                .shadow(elevation = 10.dp)
                .fillMaxWidth(),
                backgroundColor = MaterialTheme.colors.surface) {
                CircularProgressIndicator(modifier = Modifier
                    .padding(20.dp)
                    .requiredHeight(80.dp)
                    .requiredWidth(80.dp), strokeWidth = 4.dp)
            }
        }

        Spacer(modifier = Modifier.height(35.dp))
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