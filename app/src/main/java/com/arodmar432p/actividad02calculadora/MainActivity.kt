package com.arodmar432p.actividad02calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Clase MainActivity que hereda de AppCompatActivity.
 * Esta clase se encarga de manejar la interfaz de usuario de la aplicación.
 *
 * @property calculo Instancia de la clase Calculo que se encarga de realizar los cálculos.
 * @property txtResultado TextView que muestra el resultado de los cálculos.
 */
@Suppress("SpellCheckingInspection")
class MainActivity : AppCompatActivity() {
    private lateinit var calculo: Calculo
    private lateinit var txtResultado: TextView

    /**
     * Declaro botones numéricos y operacionales que usaré asociados a su id correspondiente.
     */
    private val botonesNumeros = listOf(
        R.id.boton0, R.id.boton1, R.id.boton2, R.id.boton3,
        R.id.boton4, R.id.boton5, R.id.boton6, R.id.boton7,
        R.id.boton8, R.id.boton9
    )

    private val botonesOperaciones = listOf(
        R.id.botonSuma, R.id.botonResta,
        R.id.botonMultiplicacion, R.id.botonDivision
    )

    /**
     * Método onCreate que se ejecuta cuando se crea la actividad
     *
     * @param savedInstanceState Bundle que contiene el estado guardado de la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculo = Calculo(this)
        txtResultado = findViewById(R.id.textViewResultado)

        /**
         * Bucle for para asignar la funcionalidad a los botones numéricos
         */
        for (id in botonesNumeros) {
            val boton = findViewById<Button>(id)
            boton.setOnClickListener {
                calculo.setNumClicked((it as Button).text.toString())
                txtResultado.text = if (calculo.operacion.isEmpty()) calculo.num1 else calculo.num2
            }
        }

        /**
         * Bucle for para asignar la funcionalidad a los botones operacionales.
         */
        for (id in botonesOperaciones) {
            val boton = findViewById<Button>(id)
            boton.setOnClickListener {
                calculo.setOperaciones((it as Button).text.toString())
                txtResultado.text = calculo.operacion
            }
        }

        /**
         * Trabajo con el botón igual, llamo a la función igual. Añado un condicional que me permite discriminar
         * el decimal del número si este es "0".
         */
        val botonIgual = findViewById<Button>(R.id.botonIgual)
        botonIgual.setOnClickListener {
            calculo.calcular()
            // Compruebo si el decimal se puede descartar para convertirlo a entero y que no se muestre el 0
            if (calculo.resultado % 1 == 0.0) {
                txtResultado.text = calculo.resultado.toInt().toString()
            } else { // Si el número no tiene resto "0", respeto el float y lo convierto a string
                txtResultado.text = calculo.resultado.toString()
            }
        }

        /**
         * Botón CE (Clear Entry) para resetear los valores en la calculadora y en el TextView del resultado.
         */
        val botonCE = findViewById<Button>(R.id.botonCE)
        botonCE.setOnClickListener {
            calculo.resetear()
            txtResultado.text = ""
        }
    }
}
