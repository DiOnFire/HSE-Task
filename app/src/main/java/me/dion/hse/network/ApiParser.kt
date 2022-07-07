package me.dion.hse.network

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import com.google.gson.JsonParser
import me.dion.hse.activity.SearchActivity
import me.dion.hse.traits.Character
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.Serializable

@SuppressLint("HandlerLeak")
class ApiParser(val activity: Activity) {
    private var characters: MutableList<Character> = mutableListOf()

    fun getCharacters(name: String) {
        val thread = Thread {
            val request = Request.Builder()
                .url("https://rickandmortyapi.com/api/character/?name=$name")
                .build()

            val client = OkHttpClient()
            val response = client.newCall(request).execute()
            val intent = Intent(activity, SearchActivity::class.java)
            if (response.isSuccessful) {
                val json = response.body.string()
                val jsonObj = JsonParser.parseString(json).asJsonObject
                val pages = jsonObj.get("info").asJsonObject.get("pages").asInt
                characters = parseAllPages(name, pages)
                intent.putExtra("characters", characters as Serializable)
            }
            activity.startActivity(intent)
        }

        thread.start()
    }

    private fun parseAllPages(name: String, pages: Int): MutableList<Character> {
        val chars = mutableListOf<Character>()
        for (i in 1..pages) {
            val request = Request.Builder()
                .url("https://rickandmortyapi.com/api/character/?page=$i&name=$name")
                .build()

            val client = OkHttpClient.Builder().build()
            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                val json = response.body.string()
                val jsonObj = JsonParser.parseString(json).asJsonObject

                val results = jsonObj.get("results").asJsonArray
                chars.addAll(Character.parseJsonArray(results))
            }
        }
        return chars
    }
}