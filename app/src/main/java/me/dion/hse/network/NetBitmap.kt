package me.dion.hse.network

import android.graphics.BitmapFactory
import android.widget.ImageView
import java.net.HttpURLConnection
import java.net.URL

class NetBitmap(
    private val url: String,
    private val imageView: ImageView
) : Thread() {

    override fun run() {
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()
        val inputStream = connection.inputStream
        val bitmap = BitmapFactory.decodeStream(inputStream)
        connection.disconnect()
        imageView.setImageBitmap(bitmap)
    }
}