package com.example.gruppenarbeit

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner

class home_screen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }

    fun AuswahlLesen(view: View) {
        val RadioAuswahl = findViewById<RadioGroup>(R.id.hs_radiogroup).checkedRadioButtonId
        val auswahl = findViewById<RadioButton>(RadioAuswahl).text

        //1x1 ist nicht in rechen_screen sondern hat eigenes fenster
        if (auswahl == "Kleines 1x1") {
            val spinnerauswahl = findViewById<Spinner>(R.id.spinner_Kleines1x1).selectedItem.toString()
            val beginnen = Intent(this, kleinesEinsMalEins_screen::class.java)
            beginnen.putExtra("spinnerauswahl", spinnerauswahl)
            beginnen.putExtra("auswahl", auswahl)
            startActivity(beginnen)
            // Addition, Subtraktion, Multiplikation, Division und Gemischt werden auf schwierigkeits_screen umgeleitet
            // Die Schwierigkeit wird auf schwierigkeits_screen ausgewählt und dann wird man auf entsprechenden rechen_screen umgeleitet
        } else {
            val beginnen = Intent(this, schwierigkeit_screen::class.java)
            beginnen.putExtra("auswahl", auswahl)
            startActivity(beginnen)
        }

    }

    fun tohelp(view: View) {
        val intent = Intent(this, hilfe_screen::class.java)
        startActivity(intent)
    }
}

