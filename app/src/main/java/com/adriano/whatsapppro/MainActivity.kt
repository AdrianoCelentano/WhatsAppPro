package com.adriano.whatsapppro

import android.os.Bundle
import android.text.format.DateUtils
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.boredapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: BoredService = retrofit.create(BoredService::class.java)

        val bubbleFactory = ChatBubbleFactory()

        val question = bubbleFactory.create(
            message = "Wie Gehts",
            isSender = true,
            date = 1620478640000L
        )

        val answer = bubbleFactory.create(
            message = "Gut",
            isSender = false,
            date = 1620478640000L
        )

        setContent {

            val systemUiController = rememberSystemUiController()
            val chatBubbleList = remember { mutableStateListOf(question, answer) }
            val textInput = remember { mutableStateOf("") }

            MaterialTheme(
                colors = if (isSystemInDarkTheme()) DarkColors else LightColors
            ) {

                systemUiController.setStatusBarColor(MaterialTheme.colors.primary)

                Scaffold(
                    topBar = {
                        TopAppBar(
                            backgroundColor = MaterialTheme.colors.primary
                        ) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "Senden",
                                tint = MaterialTheme.colors.onPrimary,
                                modifier = Modifier.padding(start = 8.dp)
                                    .align(Alignment.CenterVertically)
                                    .clickable {
                                        lifecycleScope.launch {
                                            val serviceAnswer = service.getActivity()
                                            val bubble = bubbleFactory.create(
                                                message = serviceAnswer.activity,
                                                isSender = true
                                            )
                                            chatBubbleList.add(bubble)
                                        }
                                    }
                            )
                        }
                    }
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {

                        Background()

                        LazyColumn {
                            items(chatBubbleList) {
                                ChatBubble(chatBubbleData = it)
                            }
                        }

                        Row(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 16.dp)
                        ) {
                            TextField(
                                value = textInput.value,
                                onValueChange = {
                                    textInput.value = it
                                }
                            )
                            FloatingActionButton(
                                onClick = {
                                    val newBubble = bubbleFactory.create(
                                        isSender = true,
                                        message = textInput.value
                                    )
                                    chatBubbleList.add(newBubble)
                                    textInput.value = ""
                                },
                                modifier = Modifier.padding(start = 16.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Send,
                                    tint = MaterialTheme.colors.onSecondary,
                                    contentDescription = "Senden"
                                )
                            }
                        }
                    }

                }

            }
        }
    }

    @Composable
    private fun Background() {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.1f,
            painter = backgroundPainter(),
            contentDescription = "background"
        )
    }

    @Composable
    private fun backgroundPainter() = painterResource(
        if (isSystemInDarkTheme()) R.drawable.background_dark else R.drawable.background_light
    )
}