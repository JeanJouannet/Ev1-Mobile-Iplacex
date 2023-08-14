package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.navigation.NavController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "pantallaInicio") {
                composable("pantallaInicio") { PantallaInicio(navController) }
                composable("pantallaCalculoSueldoHonorario") { PantallaCalculoSueldoHonorario(navController) }
                composable("pantallaCalculoSueldoContrata") {PantallaCalculoSueldoContrata(navController)}
            }
        }
    }
}

@Composable
fun PantallaInicio(navController: NavController) {
    Column {
        Button(onClick = { navController.navigate("pantallaCalculoSueldoHonorario") }) {
            Text(text = "Calcular sueldo Honorario")
        }
        Button(onClick = { navController.navigate("pantallaCalculoSueldoContrata") }) {
            Text(text = "Calcular sueldo Contrata")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCalculoSueldoHonorario(navController: NavController) {

    val estadoInicial = ""
    var sueldo by remember { mutableStateOf( estadoInicial) }
    var resultado by remember { mutableStateOf( estadoInicial) }

    Column() {
        Text(text = "Calculo de sueldo a Honorario")

        TextField(
            value = sueldo,
            onValueChange = {sueldo = it},
            label = { Text(text = "Sueldo Bruto")},
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
        Button(onClick = {navController.navigate("PantallaInicio")}) {
            Text(text = "Volver")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCalculoSueldoContrata(navController: NavController) {
    val estadoInicial = ""
    var sueldo by remember { mutableStateOf( estadoInicial) }
    var resultado by remember { mutableStateOf( estadoInicial) }

    Column() {
        Text(text = "Calculo de sueldo a Contrata")

        TextField(
            value = sueldo,
            onValueChange = {sueldo = it},
            label = { Text(text = "Sueldo Bruto")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Button(onClick = {
            val sueldoBruto = sueldo.toIntOrNull() ?: 0
            val sueldoLiquido = calcularSueldoContrata(sueldoBruto)
            resultado = "Su sueldo liquido es:  $sueldoLiquido"
        }) {
            Text(text = "Calcular el sueldo")
        }
        Text(text = resultado)
        Button(onClick = {navController.navigate("PantallaInicio")}) {
            Text(text = "Volver")
        }
    }
}

fun calcularSueldoContrata(sueldo: Int): Int {

    if(sueldo < 0 ) {
        return 0;
    }
    if (sueldo == null){
        return 0;
    }
    return sueldo - ((sueldo / 100) * 20)
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
