package com.example.gruppenarbeit

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

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

        dropdownHilfe.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedcalc = findViewById<Spinner>(R.id.spinner_hilfe).selectedItem.toString()

                if (selectedcalc == "Addition") {
                    findViewById<TextView>(R.id.textView_additional).text = getString(R.string.Hilftext1)
                } else if (selectedcalc == "Subtraktion") {
                    findViewById<TextView>(R.id.textView_additional).text = getString(R.string.Hilftext2)
                } else if (selectedcalc == "Multiplikation") {
                    findViewById<TextView>(R.id.textView_additional).text = getString(R.string.Hilftext3)
                } else if (selectedcalc == "Division") {
                    findViewById<TextView>(R.id.textView_additional).text = getString(R.string.Hilftext4)
                }
            }

        }
    }

    fun tohome(view: View) {
        val intent = Intent(this, home_screen::class.java)
        startActivity(intent)
    }

}
