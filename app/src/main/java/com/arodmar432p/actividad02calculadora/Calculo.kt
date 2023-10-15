package com.arodmar432p.actividad02calculadora

import android.content.Context
import android.widget.Toast

/**
 * Clase Calculo que se encarga de realizar los cálculos para una calculadora.
 *
 * @property context Contexto de la aplicación.
 * @property num1 Primer número en la operación.
 * @property num2 Segundo número en la operación.
 * @property operacion Operación a realizar.
 * @property resultado Resultado de la operación.
 * @property fase Fase en la que está la calculadora (de 0 a 3).
 */
class Calculo(private val context: Context) {
    var num1: String = ""
    var num2: String = ""
    var operacion: String = ""
    var resultado: Double = 0.0
    var fase: Int = 0 // Controla la fase en la que está mi calculadora (de 0 a 3).

    /**
     * Función para sumar num1 y num2.
     */
    fun sumar() {
        resultado = num1.toDouble() + num2.toDouble()
    }

    /**
     * Función para restar num1 y num2.
     */
    fun restar() {
        resultado = num1.toDouble() - num2.toDouble()
    }

    /**
     * Función para multiplicar num1 y num2.
     */
    fun multiplicar() {
        resultado = num1.toDouble() * num2.toDouble()
    }

    /**
     * Función para dividir num1 y num2.
     */
    fun dividir() {
        resultado = num1.toDouble() / num2.toDouble()
    }

    /**
     * Función para mostrar un mensaje Toast.
     *
     * @param mensaje Mensaje a mostrar.
     */
    fun mostrarToast(mensaje: String) {
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
    }

    /**
     * Función para calcular el resultado de la operación. 
     * Maneja la situación en la que se dispara el Toast. 
     * Equipara los símbolos Strings de operaciones a los métodos de sumar, restar, multiplicar y dividir. 
     */
    fun calcular() {
        if (num1.isEmpty() || num2.isEmpty() || operacion.isEmpty()) { // Llamo al toast si pulso igual y no hay nada.
            mostrarToast("Debe introducir 2 números y una operación para mostrar un resultado.")
            return
        }
        when (operacion) {
            "+" -> sumar()
            "-" -> restar()
            "x" -> multiplicar()
            "/" -> dividir()
        }
        // Compruebo si el resultado es un número entero para descartar decimales "0" y mostrar el resultado bonito.
        if (resultado % 1 == 0.0) {
            num1 = resultado.toInt().toString()
        } else {
            num1 = resultado.toString()
        }
        num2 = ""
        operacion = ""
        fase = 3 // Se ha realizado una operación, si se presiona un número la calculadora se resetea.
    }

    /**
     * Función para manejar el número pulsado en la calculadora.
     *
     * @param num Número pulsado.
     */
    fun setNumClicked(num: String) {
        if (fase == 0) {
            num1 += num
        } else if (fase == 2) {
            num2 += num
        } else if (fase == 3) {
            resetear()
            num1 += num
            fase = 0 // Esperando a num1
        }
    }

    /**
     * Función para manejar la operación pulsada en la calculadora.
     *
     * @param oper Operación pulsada.
     */
    fun setOperaciones(oper: String) {
        if (num1.isNotEmpty()) {
            operacion = oper
            fase = 2 // Esperando a num2
        }
    }

    /**
     * Función para resetear los valores de la calculadora. 
     */
    fun resetear() {
        num1 = ""
        num2 = ""
        operacion = ""
        resultado = 0.0
        fase = 0
    }
}
