package com.example.gruppenarbeit

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate


class rechen_screen : AppCompatActivity() {
    var Ergebnis = 0
    var zaehler = 0 //wird für die Loop benötigt
    var anzahl_richtig = 0 //speichert die totale Anzahl an richtigen Antworten
    var korrekt =
        false //Variable hält fest ob der User Input dem korrekten Ergebnis entspricht, ist  am Anfang jeden Durchlaufs by default auf FALSE


    //Globale Variablen
    //Timervariable erstellen
    var countdown = Timer("schedule", true)
    // Kontrollvariable
    var trigger = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Timer starten
        TimerProgress(true)
        val auswahl = intent.getStringExtra("auswahl")
        if (auswahl == "Addition") {
            rs_textrechnung.text = Addition()
        } else {
            rs_textrechnung.text = Subtraction()
        }

        val button = findViewById<Button>(R.id.rs_button)
        button.setOnClickListener { controlloop() }

    }

    //Button For Loop
    fun controlloop() {


        if (trigger == false) {
            zaehler++
            korrekt = Kontrolle() //siehe Kommentar rechenFunktionAufrufen
            val bild = findViewById<ImageView>(R.id.rs_bild)
            bild.visibility = View.INVISIBLE

            if (korrekt == true) {
                anzahl_richtig++
                val inc = 0.5
                rs_ratingbar.rating += inc.toFloat()
            }

            // wechselt nach 10. Durchlauf screen und gibt anzahl der richtigen Antwort weiter
            if (zaehler == 10) {
                val screenschalter = Intent(this, feedback_screen::class.java)
                screenschalter.putExtra("anzahl_richtig", anzahl_richtig)
                startActivity(screenschalter)
            }

            val auswahl = intent.getStringExtra("auswahl")
            if (auswahl == "Addition") {
                rs_textrechnung.text = Addition()
            } else {
                rs_textrechnung.text = Subtraction()
            }
        }

        if (trigger == true) {
            TimerProgress(false)
            trigger = false
        } else {
            TimerProgress(true)
            trigger = true
        }


    }

    //Zufallszahl
    fun RandomInt(min: Int, max: Int): Int {
        //Methode gibt ganzzahlige Zahl zwischen Min und Max zurück

        val range = max - min + 1
        //range = Menge an Zahlen im Zufallsbereich
        // Math.random gibt Zahl zwischen 0 und 1, es kommt eine Kommazahl raus; bei Wandlung in int -->schneidet Kommastellen ab; daher + 1;

        return (Math.random() * range).toInt() + min
        //gibt Zufallszahl
        //Die Basis wird ums Minimum verschoben; wenn zum Beispiel Math.random 0 ist;
    }

    //Addition
    fun Addition(): String {

        //Zufallszahl zwischen 0 und 50
        val a = RandomInt(0, 50)
        val b = RandomInt(0, 50)
        Ergebnis = a + b

        val bAnswer = "$a + $b"

        return bAnswer
    }

    //Subtraktion
    fun Subtraction(): String {

        var first = RandomInt(0, 100)
        var second = RandomInt(0, 100)

        var left: Int //left variable to be displayed
        var right: Int //right variable to be displayed

        if (first <= second) {
            left = second
            right = first
        } else {
            left = first
            right = second
        }

        Ergebnis = left - right



        return "$left - $right"
    }


    // Funktion für den OK Button um die Antwort abzugeben und den Timer zu stoppen bzw ihn auch wieder neu zu starten
    // Bitte einbauen im Aufgabenpunkt Rechen Screen For Loop, dass der erste Klick auf den OK Button den Timer stoppt
    // und das Ergebnis kontrolliert und der nächste Klick ein neue Rechnung anzeigt und den Timer neu startet
    // die trigger Variable funktioniert in diesem Zusammenhang als Kontrolle ob der Timer gestartet bzw gestoppt werden soll
    // um das Ergebnis zu kontrollieren

    fun TimerProgress(StartStop: Boolean) {

        // Kontrolle ob neuer Timer erstell werden soll
        if (StartStop == true) {
            rs_progressbar.max = 100
            rs_progressbar.progress = 100
            countdown = Timer("schedule", true)
        }


        countdown.scheduleAtFixedRate(0, 100) {
            //1000 sind eine Sekunde

            this@rechen_screen.runOnUiThread(java.lang.Runnable {
                //Kontrolle ob die Zeit abgelaufen ist
                if (rs_progressbar.progress <= 0) {

                    trigger = false
                    countdown.cancel()
                    Kontrolle()
                    //rs_button.isClickable=true

                } else {

                    // Kontrolle ob der Timer gestoppt wurde oder nicht
                    if (StartStop == true) {
                        rs_progressbar.progress--
                        //rs_button.isClickable = false

                    } else {
                        trigger = false
                        countdown.cancel()
                        //rs_button.isClickable=true
                        Kontrolle()
                    }

                }

            })
        }
    }


    fun Kontrolle(): Boolean {

        var Antwort = 0

        if (rs_nummerEingabe.text.toString() == "") {
            bildAnzeigen(false)
            return false

        } else {

            Antwort = rs_nummerEingabe.text.toString().toInt()

            if (Antwort == Ergebnis) {
                bildAnzeigen(true)
                return true

            } else {
                bildAnzeigen(false)
                return false
            }
        }
    }

    fun bildAnzeigen(RichtigFalsch: Boolean) {

        val bild = findViewById<ImageView>(R.id.rs_bild)
        bild.visibility = View.VISIBLE

        if (RichtigFalsch == true) {
            bild.setImageResource(R.drawable.bild_super)
        } else {
            bild.setImageResource(R.drawable.fehler)
        }
    }

}
