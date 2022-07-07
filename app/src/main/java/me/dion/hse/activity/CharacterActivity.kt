package me.dion.hse.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import me.dion.hse.R
import me.dion.hse.network.NetBitmap
import me.dion.hse.traits.Character

class CharacterActivity : AppCompatActivity() {
    private lateinit var character: Character
    private lateinit var characterImg: ImageView
    private lateinit var characterName: TextView
    private lateinit var characterId: TextView
    private lateinit var characterSpecies: TextView
    private lateinit var characterStatus: TextView
    private lateinit var characterGender: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        character = intent.getSerializableExtra("character") as Character
        characterImg = findViewById(R.id.characterImg)
        characterName = findViewById(R.id.charName)
        characterId = findViewById(R.id.charId)
        characterSpecies = findViewById(R.id.charSpecies)
        characterStatus = findViewById(R.id.charStatus)
        characterGender = findViewById(R.id.charGender)

        applyMetadata()
    }

    @SuppressLint("SetTextI18n")
    private fun applyMetadata() {
        val thread = NetBitmap(character.image, characterImg)
        thread.start()
        characterName.text = "Name: ${character.name}"
        characterId.text = "UUID: ${character.id}"
        characterSpecies.text = "Species: ${character.species}"
        characterStatus.text = "Status: ${character.status}"
        characterGender.text = "Gender: ${character.gender}"
    }
}