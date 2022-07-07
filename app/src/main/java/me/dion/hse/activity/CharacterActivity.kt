package me.dion.hse.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.dion.hse.R
import me.dion.hse.traits.Character

class CharacterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
    }
}