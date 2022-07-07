package me.dion.hse.network

import android.os.Bundle
import android.os.Handler
import android.os.Message
import okhttp3.OkHttpClient
import okhttp3.Request

class NetThread(
    private val handler: Handler,
    private val request: Request
    ) : Thread() {

    override fun run() {
        val client = OkHttpClient.Builder().build()
        val bundle = Bundle()
        bundle.putSerializable("response", SerializableResponse(client.newCall(request).execute()))
        val message = Message()
        message.data = bundle
        handler.sendMessage(message)
    }
}