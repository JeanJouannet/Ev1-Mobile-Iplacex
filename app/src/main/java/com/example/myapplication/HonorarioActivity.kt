package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController

class HonorarioActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PantallaCalculoSueldoHonorario()
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PantallaCalculoSueldoHonorario() {
        val contexto = LocalContext.current
        val estadoInicial = ""
        var sueldo by remember { mutableStateOf( estadoInicial) }
        var resultado by remember { mutableStateOf( estadoInicial) }

        Column() {
            Text(text = "Calculo de sueldo a Honorario")

            TextField(
                value = sueldo,
                onValueChange = {sueldo = it},
                label = { Text(text = "Sueldo Bruto") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            Button(onClick = {
                val sueldoBruto = sueldo.toIntOrNull() ?: 0
                val sueldoLiquido = calcularSueldoHonorario(sueldoBruto)
                resultado = "Su sueldo liquido es:  $sueldoLiquido"
            }) {
                Text(text = "Calcular el sueldo")
            }
            Text(text = resultado)
            Button(onClick = {
                val intent = Intent(contexto, MainActivity::class.java)
                contexto.startActivity(intent)
            }) {
                Text(text = "Volver")
            }
        }
    }
    fun calcularSueldoHonorario(sueldo: Int): Int {
        if(sueldo < 0 ) {
            return 0;
        }
        if (sueldo == null){
            return 0;
        }
        return sueldo - ((sueldo / 100) * 13)
    }
}    
    