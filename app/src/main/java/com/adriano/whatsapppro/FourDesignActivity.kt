package com.adriano.whatsapppro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class FourDesignActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val question = ChatBubbleData(
            message = "Wie gehts",
            date = "Gestern",
            isSender = true
        )

        val answer = ChatBubbleData(
            message = "Gut",
            date = "Gestern",
            isSender = false
        )

        setContent {

            val systemUiController = rememberSystemUiController()
            val chatBubbleList = remember { mutableStateListOf(question, answer) }
            val textInput = remember { mutableStateOf("") }

            MaterialTheme(
                colors = if (isSystemInDarkTheme()) DarkColors else LightColors
            ) {

                systemUiController.setStatusBarColor(MaterialTheme.colors.primary)

                Box(modifier = Modifier.fillMaxSize()) {
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
                                val newBubble = ChatBubbleData(
                                    date = "Now",
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

    @Composable
    fun ChatBubble(chatBubbleData: ChatBubbleData) {
        Surface(
            color = if (chatBubbleData.isSender) MaterialTheme.colors.surface else MaterialTheme.colors.primary,
            shape = if (chatBubbleData.isSender) senderShape() else receiverShape(),
            elevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp,
                    bottom = 16.dp,
                    start = if (chatBubbleData.isSender) 16.dp else 64.dp,
                    end = if (chatBubbleData.isSender) 64.dp else 16.dp,
                )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = chatBubbleData.message,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = chatBubbleData.date,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.align(Alignment.End),
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }

    private fun receiverShape(): RoundedCornerShape {
        return RoundedCornerShape(
            topStart = 8.dp,
            topEnd = 0.dp,
            bottomEnd = 8.dp,
            bottomStart = 8.dp
        )

    }

    private fun senderShape(): RoundedCornerShape {
        return RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 8.dp,
            bottomEnd = 8.dp,
            bottomStart = 8.dp
        )
    }
}