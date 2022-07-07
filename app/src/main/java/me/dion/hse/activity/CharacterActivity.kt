package me.dion.hse.activity

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.ImageView
import android.widget.TextView
import me.dion.hse.R
import me.dion.hse.dialog.LoadingDialog
import me.dion.hse.network.ISerializable
import me.dion.hse.network.NetBitmap
import me.dion.hse.traits.Character

@SuppressLint("HandlerLeak")
class CharacterActivity : AppCompatActivity() {
    private lateinit var character: Character
    private lateinit var characterImg: ImageView
    private lateinit var characterName: TextView
    private lateinit var characterId: TextView
    private lateinit var characterSpecies: TextView
    private lateinit var characterStatus: TextView
    private lateinit var characterGender: TextView
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        loadingDialog = LoadingDialog(this@CharacterActivity)
        loadingDialog.startLoadingDialog()

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
        val handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                val bundle = msg.data
                val bitmap = bundle.getSerializable("bitmap") as ISerializable
                characterImg.setImageBitmap(bitmap.metadata as Bitmap)
                loadingDialog.dismissDialog()
            }
        }

        val thread = NetBitmap(character.image, handler)
        thread.start()
        characterName.text = "Name: ${character.name}"
        characterId.text = "UUID: ${character.id}"
        characterSpecies.text = "Species: ${character.species}"
        characterStatus.text = "Status: ${character.status}"
        characterGender.text = "Gender: ${character.gender}"
    }
}