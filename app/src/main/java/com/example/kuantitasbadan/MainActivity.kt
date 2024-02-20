package com.example.kuantitasbadan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.RadioButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var gender: String = "Laki-Laki"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //init widget
        val nameNama = findViewById<EditText>(R.id.edtName)
        val alamat = findViewById<EditText>(R.id.edtAlamat)
        val btnCalc = findViewById<Button>(R.id.buttonCalculate)
        val reset = findViewById<Button>(R.id.resetBtn)
        val editTextHeight = findViewById<EditText>(R.id.edtTextHeight)
        val editTextWeight = findViewById<EditText>(R.id.edtTextWeight)
        val textViewResult = findViewById<TextView>(R.id.viewResult)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupGender)

        btnCalc.setOnClickListener {
            val bmi = calculateBMI(editTextHeight, editTextWeight, radioGroup, textViewResult)
            textViewResult.text = "Name: ${nameNama.text}\nAlamat: ${alamat.text}\n$bmi"

        }

        reset.setOnClickListener{
            reset(nameNama, alamat, editTextWeight, editTextHeight,radioGroup,textViewResult)
        }
    }

    private fun reset(nameNama : EditText, alamat : EditText, editTextHeight: EditText, editTextWeight: EditText, radioGroup: RadioGroup, textViewResult: TextView){
        nameNama.setText("")
        alamat.setText("")
        editTextHeight.setText("")
        editTextWeight.setText("")
        radioGroup.clearCheck()
        textViewResult.setText("")
    }

    private fun calculateBMI(
        editTextHeight: EditText,
        editTextWeight: EditText,
        radioGroup: RadioGroup,
        textViewResult: TextView
    ): String {
        val height = editTextHeight.text.toString().toDouble()
        val weight = editTextWeight.text.toString().toDouble()

        // ID RadioButton yang dipilih
        val selectedGenderId = radioGroup.checkedRadioButtonId

        // choose gender
        gender = when (selectedGenderId) {
            R.id.radioMale -> "Laki-laki"
            R.id.radioFemale -> "Perempuan"
            else -> "Laki-laki"
        }

        // BMI menghitung
        val bmi = when (gender) {
            "Laki-laki" -> weight / ((height / 100) * (height / 100))
            "Perempuan" -> weight / ((height / 100) * (height / 100) * 0.9)
            else -> 0.0
        }

        val result = when {
            bmi < 18.5 -> "Cacingan"
            bmi >= 18.5 && bmi < 24.9 -> "Berat badan normal"
            bmi >= 25 && bmi < 29.9 -> "Berat badan berlebih"
            else -> "Obesitas"
        }
        return "BMI: %.2f\n$result".format(bmi)
    }
}