package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext

class ContrataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contrata)

        val editTextSueldo = findViewById<EditText>(R.id.editTextSueldo)
        val textViewResultado = findViewById<TextView>(R.id.textViewResultado)
        val buttonCalcular = findViewById<Button>(R.id.Calcular)
        val buttonVolver = findViewById<Button>(R.id.volverPantallaInicio)

        buttonCalcular.setOnClickListener {
            val sueldo = editTextSueldo.text.toString().toIntOrNull()
            if (sueldo != null) {
                val sueldoCalculado = calcularSueldoContrata(sueldo)
                textViewResultado.text = "Sueldo a Contrata: $sueldoCalculado"
            } else {
                textViewResultado.text = "Ingrese un sueldo válido"
            }
        }
        buttonVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun calcularSueldoContrata(sueldo: Int): Int {

        if (sueldo < 0) {
            return 0;
        }
        if (sueldo == null) {
            return 0;
        }
        return sueldo - ((sueldo / 100) * 20)
    }
}

