package com.example.gruppenarbeit

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class feedback_screen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback_screen)

        bildAnzeigenfs()
        textAnzeigen()
        textAnzeigen2()

    }

    fun nochmal(view: View) {
        val startagain = Intent(this, home_screen::class.java)
        startActivity(startagain)
    }

    fun bildAnzeigenfs() {
        val anzahl_richtig = intent.getIntExtra("anzahl_richtig", 0)
        val bild2 = findViewById<ImageView>(R.id.fb_bilder)
        bild2.visibility = View.VISIBLE

        if (anzahl_richtig <= 3) {
            bild2.setImageResource(R.drawable.besser)
        }
        if (anzahl_richtig > 3 && anzahl_richtig < 7) {
            bild2.setImageResource(R.drawable.mehr_ueben)
        }
        if (anzahl_richtig > 6 && anzahl_richtig < 10) {
            bild2.setImageResource(R.drawable.weiterso)
        }

        if (anzahl_richtig == 10) {
            bild2.setImageResource(R.drawable.rechenkoenig)
        }
    }

    fun textAnzeigen() {
        val anzahl_richtig = intent.getIntExtra("anzahl_richtig", 0)
        val text1 = findViewById<TextView>(R.id.fb_text1)

        if (anzahl_richtig <= 3) {
            text1.text = "Das war leider noch nicht so gut"
        }
        if (anzahl_richtig > 3 && anzahl_richtig < 7) {
            text1.text = "Das war schon ganz ok, du solltest aber noch ein wenig üben"
        }

        if (anzahl_richtig > 6 && anzahl_richtig < 10) {
            text1.text = "Gut, mach weiter so !"
        }

        if (anzahl_richtig == 10) {
            text1.text = "Sehr gut! Du hast alles Richtig"
        }
    }

    fun textAnzeigen2() {
        val anzahl_richtig = intent.getIntExtra("anzahl_richtig", 0)
        val text2 = findViewById<TextView>(R.id.fb_text2)

        text2.text = ("Du hast " + anzahl_richtig + " von 10 Rechnungen richtig.")
    }

}
