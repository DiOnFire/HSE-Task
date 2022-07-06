package me.dion.hse.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import me.dion.hse.R

class MainActivity : AppCompatActivity() {
    private lateinit var searchBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchBtn = findViewById(R.id.search_btn)

        searchBtn.setOnClickListener {

        }
    }
}