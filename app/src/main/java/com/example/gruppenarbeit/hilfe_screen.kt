package com.example.gruppenarbeit

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner

class hilfe_screen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hilfe_screen)

        val dropdownHilfe = findViewById<Spinner>(R.id.spinner_hilfe)
        dropdownHilfe.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.Calculations)
        )
    }

    fun tohome(view: View) {
        val intent = Intent(this, home_screen::class.java)
        startActivity(intent)
    }
}
