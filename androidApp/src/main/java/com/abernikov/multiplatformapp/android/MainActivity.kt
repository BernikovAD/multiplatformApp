package com.abernikov.multiplatformapp.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            counted()
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(viewModel = viewModel)
                }
            }
        }

    }
}

var count = 0
fun counted() {
    Log.i("myTag", "Composable ${count++}")
}


@Composable
fun MainScreen(viewModel: MainViewModel) {
    val state by viewModel.state.observeAsState()
    counted()
    LaunchedEffect(Unit) {
        viewModel.getMovies()
    }
    when (state) {
        StateMainVM.Error -> {
            //TODO показать ошибку
        }
        StateMainVM.Loading -> {
            //TODO показать загрузку
        }
        is StateMainVM.Success -> {
            val snackbarHostState = remember { SnackbarHostState() }
            val listMovies = (state as StateMainVM.Success).data.docs
            LazyColumn {
                items(listMovies.size) { index ->
                    val item = listMovies[index]
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(Color.Gray)
                            ,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        AsyncImage(
                            model = item.poster?.url,
                            contentDescription = "Phone",
                            modifier = Modifier.fillMaxWidth(0.3f).padding(8.dp)
                        )
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth(0.7f)
                        ) {
                            Text(
                                text = item.name.toString(),
                                fontSize = 20.sp,
                                fontStyle = FontStyle.Italic
                            )
                            Text(
                                text = item.description.toString(),
                                maxLines = 3,
                                fontSize = 12.sp
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            modifier = Modifier
                                .size(24.dp)
                                .fillMaxWidth(0.1f)
                                .clickable {
                                    CoroutineScope(Dispatchers.Main).launch {
                                        snackbarHostState.showSnackbar(
                                            message = item.name.toString(),
                                            duration = SnackbarDuration.Short
                                        )
                                    }
                                }
                        )
                    }
                }
            }
            SnackbarHost(hostState = snackbarHostState)
        }
        else -> Unit
    }
}


