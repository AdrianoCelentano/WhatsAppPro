package com.adriano.whatsapppro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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