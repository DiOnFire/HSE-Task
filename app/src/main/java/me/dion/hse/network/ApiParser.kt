package me.dion.hse.network

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.os.Message
import com.google.gson.JsonParser
import me.dion.hse.activity.SearchActivity
import me.dion.hse.traits.Character
import okhttp3.Request
import okhttp3.Response
import java.io.Serializable

@SuppressLint("HandlerLeak")
class ApiParser(val activity: Activity) {
    var characters: MutableList<Character>? = null

    fun getCharacters(name: String) {
        val request = Request.Builder()
            .url("https://rickandmortyapi.com/api/character/?name=$name")
            .build()

        val handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                val bundle = msg.data
                val res = bundle.getSerializable("response") as ISerializable
                val response = res.metadata as Response
                if (response.isSuccessful) {
                    val json = response.body.string()
                    val jsonObj = JsonParser.parseString(json).asJsonObject
                    val results = jsonObj.get("results").asJsonArray
                    characters = Character.parseJsonArray(results)

                    val intent = Intent(activity, SearchActivity::class.java)

                    intent.putExtra("characters", characters as Serializable)

                    activity.startActivity(intent)
                }
            }
        }

        val thread = NetThread(handler, request)
        thread.start()
        thread.join(10000)
    }
}