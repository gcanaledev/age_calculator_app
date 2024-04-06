package com.example.age_calculator_app.conversionProcess

import com.example.age_calculator_app.enumerator.Enumerator
import java.util.Calendar

class AgeConversionOperator {

    private val secondsInAYear = 31557600
    private val minutesInAYear = 525960
    private val hoursInAYear = 8766
    private val daysInAYear = 365
    private val weeksInAYear = 52
    private val monthsInAYear = 12

    fun convertAge(convertValue: String,
                   targetDateType: Enumerator.ConversionOptions): Int{

        val userSplitBirth = convertValue.split('/')
        val userBirthYear = userSplitBirth.last().toInt()
        val userBirthDay = userSplitBirth.first().toInt()

        val userAge = Calendar.getInstance()[(Calendar.YEAR)] - userBirthYear
        if (userBirthDay <= Calendar.getInstance()[Calendar.MONTH])
            userAge - 1

        return calculateAge(userAge, targetDateType)
    }

    private fun calculateAge(convertValue: Int, convertOptions: Enumerator.ConversionOptions): Int{

        return when(convertOptions){
            Enumerator.ConversionOptions.Seconds -> convertValue * secondsInAYear
            Enumerator.ConversionOptions.Minutes -> convertValue * minutesInAYear
            Enumerator.ConversionOptions.Hours -> convertValue * hoursInAYear
            Enumerator.ConversionOptions.Days -> convertValue * daysInAYear
            Enumerator.ConversionOptions.Weeks -> convertValue * weeksInAYear
            Enumerator.ConversionOptions.Months -> convertValue * monthsInAYear
        }
    }
}