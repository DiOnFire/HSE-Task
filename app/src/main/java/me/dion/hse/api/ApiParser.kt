package me.dion.hse.api

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import com.google.gson.JsonParser
import me.dion.hse.traits.Character
import me.dion.hse.network.NetThread
import me.dion.hse.network.SerializableResponse
import okhttp3.Request

@SuppressLint("HandlerLeak")
class ApiParser {
    companion object {
        fun getCharacters(name: String): List<Character> {
            var characters = mutableListOf<Character>()

            val request = Request.Builder()
                .url("https://rickandmortyapi.com/api/character/?name=$name")
                .build()

            val handler = object : Handler() {
                override fun handleMessage(msg: Message) {
                    val bundle = msg.data
                    val response = bundle.getSerializable("response") as SerializableResponse
                    if (response.response.isSuccessful) {
                        val json = response.response.body.string()
                        val jsonArray = JsonParser.parseString(json).asJsonArray
                        characters = Character.parseJsonArray(jsonArray)
                    }
                }
            }

            val thread = NetThread(handler, request)
            thread.start()
            thread.join()
            return characters
        }
    }
}