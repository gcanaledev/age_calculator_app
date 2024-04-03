package com.example.age_calculator_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val conversionOptionsSpinner = findViewById<Spinner>(R.id.conversionOptionsSpinner)

        ArrayAdapter.createFromResource(this, R.array.conversionOptions,
            android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            conversionOptionsSpinner.adapter = adapter
        }
    }
}