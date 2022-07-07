package me.dion.hse.network

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.ImageView
import java.io.Serializable
import java.net.HttpURLConnection
import java.net.URL

class NetBitmap(
    private val url: String,
    private val handler: Handler
) : Thread() {

    override fun run() {
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()
        val inputStream = connection.inputStream
        val bitmap = BitmapFactory.decodeStream(inputStream)
        connection.disconnect()
        val bundle = Bundle()
        bundle.putSerializable("bitmap", ISerializable(bitmap))
        val message = Message()
        message.data = bundle
        handler.sendMessage(message)
    }
}