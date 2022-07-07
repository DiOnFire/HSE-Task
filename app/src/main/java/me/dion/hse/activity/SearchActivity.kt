package me.dion.hse.activity

import android.content.ClipData.newIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import me.dion.hse.R
import me.dion.hse.traits.Character

class SearchActivity : AppCompatActivity() {
    private lateinit var characters: MutableList<Character>
    private lateinit var searchList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchList = findViewById(R.id.searchList)
        characters = intent.getSerializableExtra("characters") as MutableList<Character>

        searchList.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, convertToString(characters))

        searchList.setOnItemClickListener { _, item, _, _ ->
            val textView = item as TextView
            val charName = textView.text.toString()
            val character = characters.find { it.name == charName }
            val intent = Intent(
                this,
                CharacterActivity::class.java
            )
            intent.putExtra("character", character)
            startActivity(intent)
        }
    }

    private fun convertToString(characters: List<Character>): MutableList<String> {
        val strings = mutableListOf<String>()
        for (character in characters) {
            strings.add(character.name)
        }
        return strings
    }
}