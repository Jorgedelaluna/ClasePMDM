package com.unirfp.calculadora

import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import android.widget.NumberPicker
import android.widget.Button
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Definicion de variables
    private lateinit var nombreApp: TextView
    private lateinit var salarioBruto: EditText
    private lateinit var numeroPagas: Spinner
    private lateinit var edad: EditText
    private lateinit var grupoProfesional: Spinner
    private lateinit var gradoDiscapacidad: Switch
    private lateinit var estadoCivil: Spinner
    private lateinit var numeroHijos: NumberPicker
    private lateinit var btnCalcular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializacion de variables
        nombreApp = findViewById(R.id.nombre_app)
        salarioBruto = findViewById(R.id.salario_bruto)
        numeroPagas = findViewById(R.id.numero_pagas)
        edad = findViewById(R.id.edad)
        grupoProfesional = findViewById(R.id.grupo_profesional)
        gradoDiscapacidad = findViewById(R.id.grado_discapacidad)
        estadoCivil = findViewById(R.id.estado_civil)
        numeroHijos = findViewById(R.id.numero_hijos)
        btnCalcular = findViewById(R.id.btn_calcular)

        // Configuración del Spinner de número de pagas
        val paysOptions = arrayOf("12", "14")
        val paysAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, paysOptions)
        paysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        numeroPagas.adapter = paysAdapter

        val professionalGroupOptions = arrayOf("Consultor", "Programador", "Administrativo")
        val professionalGroupAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, professionalGroupOptions)
        professionalGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        grupoProfesional.adapter = professionalGroupAdapter

        val maritalStatusOptions = arrayOf("Soltero", "Casado", "Divorciado")
        val maritalStatusAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, maritalStatusOptions)
        maritalStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        estadoCivil.adapter = maritalStatusAdapter

        // Configuración del NumberPicker para número de hijos
        numeroHijos.minValue = 0
        numeroHijos.maxValue = 10

        // Configuración del botón calcular
        btnCalcular.setOnClickListener {
            // Obtener valores de entrada
            val salarioBruto = salarioBruto.text.toString().toDoubleOrNull() ?: 0.0
            val numeroPagas = numeroPagas.selectedItem.toString().toInt()
            val edadUsuario = edad.text.toString().toIntOrNull() ?: 0
            val discapacidad = gradoDiscapacidad.isChecked
            val numeroHijos = numeroHijos.value

            // Simulación de cálculos
            val irpf = if (salarioBruto <= 12450) {
                0.19
            } else if (salarioBruto in 12451.0..20200.0) {
                0.24
            } else if (salarioBruto in 20201.0..35200.0) {
                0.30
            } else if (salarioBruto in 35201.0..60000.0) {
                0.37
            } else if (salarioBruto in 60001.0..300000.0) {
                0.45
            } else {
            0.47 // Para salarios > 300001€
            }
            val deducciones = (if (discapacidad) 500 else 0) + (numeroHijos * 100)
            val salarioNeto = salarioBruto - (salarioBruto * irpf) - deducciones

            // Pasar datos a la actividad de resultados
            val intent = Intent(this, ResultsActivity::class.java)
            intent.putExtra("SALARIO BRUTO", salarioBruto)
            intent.putExtra("SALARIO NETO", salarioNeto)
            intent.putExtra("IRPF", irpf)
            intent.putExtra("DEDUCCIONES" , deducciones)
            startActivity(intent)
        }
    }
}