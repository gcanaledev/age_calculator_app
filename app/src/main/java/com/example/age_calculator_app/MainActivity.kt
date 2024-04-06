package com.example.age_calculator_app

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.age_calculator_app.conversionProcess.AgeConversionOperator
import com.example.age_calculator_app.enumerator.Enumerator
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fillOptionsSpinner()
        createCalendarButtonFunctionality()
        createCalculateButtonFunctionality()
    }

    private fun createCalculateButtonFunctionality(){
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val optionsSpinner = findViewById<Spinner>(R.id.conversionOptionsSpinner)
        val chosenDateTextView = findViewById<TextView>(R.id.chosenAgeTextView)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        fun onCalculateButtonClicked(){

            if (optionsSpinner.selectedItemPosition == 0 || chosenDateTextView.text.isNullOrBlank()){
                Toast.makeText(this, "please, choose a valid conversion option and date",
                    Toast.LENGTH_SHORT).show()

                return
            }

            val userChosenConversionType = enumValueOf<Enumerator.ConversionOptions>(optionsSpinner.selectedItem.toString())
            val conversionResult = AgeConversionOperator().convertAge(chosenDateTextView.text.toString(), userChosenConversionType)

            resultTextView.text = String.format("your age in %s is %d!!", userChosenConversionType, conversionResult)
        }

        calculateButton.setOnClickListener { onCalculateButtonClicked() }
    }

    private fun createCalendarButtonFunctionality(){

        fun setChosenDateTextViewText(year: Int, month: Int, dayOfMonth: Int){

            val chosenDateTextView = findViewById<TextView>(R.id.chosenAgeTextView)
            chosenDateTextView.text = String.format("%d/%d/%d", dayOfMonth, month, year)
        }

        fun openDatePicker(){

            val calendar = Calendar.getInstance()

            val datePickerDialog = DatePickerDialog(this,
                { _, receivedYear, receivedMonth, receivedDayOfMonth ->  setChosenDateTextViewText(receivedYear, receivedMonth, receivedDayOfMonth)},
                calendar[Calendar.YEAR],
                calendar[Calendar.MONTH],
                calendar[Calendar.DAY_OF_MONTH])

            datePickerDialog.show()
        }

        val calendarButton = findViewById<Button>(R.id.calendarButton)

        calendarButton.setOnClickListener{ openDatePicker() }
    }

    private fun fillOptionsSpinner(){
        val conversionOptionsSpinner = findViewById<Spinner>(R.id.conversionOptionsSpinner)

        ArrayAdapter.createFromResource(this, R.array.conversionOptions,
            android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            conversionOptionsSpinner.adapter = adapter
        }
    }
}