package com.adriano.whatsapppro

import android.text.format.DateUtils

class ChatBubbleFactory {

    fun create(
        message: String,
        isSender: Boolean,
        date: Long = System.currentTimeMillis()
    ): ChatBubbleData {
        return ChatBubbleData(
            message = message,
            isSender = isSender,
            date = DateUtils.getRelativeTimeSpanString(date).toString()
        )
    }
}