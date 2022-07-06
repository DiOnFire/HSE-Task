package me.dion.hse.api

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import com.google.gson.JsonParser
import me.dion.hse.traits.Character
import me.dion.hse.traits.NetThread
import me.dion.hse.traits.SerializableResponse
import okhttp3.Request

@SuppressLint("HandlerLeak")
class ApiParser {
    companion object {
        fun getAllCharacters(): List<Character> {
            return TODO("No implementation yet")
        }

        fun getPagesCount(): Int {
            var pagesCount = 0

            val request = Request.Builder()
                .url("https://rickandmortyapi.com/api/character/")
                .build()

            val handler = object : Handler() {
                override fun handleMessage(msg: Message) {
                    val bundle = msg.data
                    val response = bundle.getString("response") as SerializableResponse
                    val data = response.response.body.string()
                    val json = JsonParser.parseString(data).asJsonObject
                    pagesCount = json.get("info").asJsonObject.get("pages").asInt
                }
            }

            NetThread(handler, request).start()

            return pagesCount
        }
    }
}