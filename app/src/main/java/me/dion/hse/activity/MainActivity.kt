package me.dion.hse.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import me.dion.hse.R
import me.dion.hse.network.ApiParser

class MainActivity : AppCompatActivity() {
    private lateinit var searchBtn: Button
    private lateinit var characterNameEdit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchBtn = findViewById(R.id.search_btn)
        characterNameEdit = findViewById(R.id.char_name_edit)

        searchBtn.setOnClickListener {
            ApiParser(this).getCharacters(characterNameEdit.text.toString())
        }
    }
}