package me.dion.hse.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import me.dion.hse.R
import me.dion.hse.dialog.DialogTemplate
import me.dion.hse.traits.Character

class SearchActivity : AppCompatActivity() {
    private var characters: MutableList<Character> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val searchList: ListView = findViewById(R.id.searchList)
        if (intent.hasExtra("characters")) {
            characters = intent.getSerializableExtra("characters") as MutableList<Character>
        } else {
            DialogTemplate("Error", "No characters found or API is unreachable").show(supportFragmentManager, "error")
        }


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