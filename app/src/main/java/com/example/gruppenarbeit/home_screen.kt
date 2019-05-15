package com.example.gruppenarbeit

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup

class home_screen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }

    fun AuswahlLesen(view: View) {
        val RadioAuswahl = findViewById<RadioGroup>(R.id.hs_radiogroup).checkedRadioButtonId
        val auswahl = findViewById<RadioButton>(RadioAuswahl).text

        val beginnen = Intent(this, rechen_screen::class.java)
        beginnen.putExtra("auswahl", auswahl)
        startActivity(beginnen)
    }

    fun tohelp(view: View) {
        val intent = Intent(this, hilfe_screen::class.java)
        startActivity(intent)
    }
}
