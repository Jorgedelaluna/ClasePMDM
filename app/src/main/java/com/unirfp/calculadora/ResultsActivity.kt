package com.unirfp.calculadora

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        // Obtener datos del intent
        val salarioBruto = intent.getDoubleExtra("SALARIO BRUTO", 0.0)
        val salarioNeto = intent.getDoubleExtra("SALARIO NETO", 0.0)
        val irpf = intent.getDoubleExtra("IRPF", 0.0)
        val deducciones = intent.getIntExtra("DEDUCCIONES", 0)

        // Asignar valores a las vistas
        findViewById<TextView>(R.id.text_salario_bruto).text = "Salario Bruto: $salarioBruto €"
        findViewById<TextView>(R.id.text_salario_neto).text = "Salario Neto: $salarioNeto €"
        findViewById<TextView>(R.id.text_irpf).text = String.format("Retención IRPF: %.2f%%", irpf * 100)
        findViewById<TextView>(R.id.text_deducciones).text =
            if (deducciones > 0) "Posibles Deducciones: $deducciones €" else "No hay deducciones"
    }
}