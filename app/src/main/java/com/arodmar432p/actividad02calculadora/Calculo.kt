package com.arodmar432p.actividad02calculadora

import android.content.Context
import android.widget.Toast

class Calculo(private val context: Context) {
    var num1: String = ""
    var num2: String = ""
    var operacion: String = ""
    var resultado: Double = 0.0
    var fase: Int = 0 // Controla la fase en la que está mi calculadora (de 0 a 3).
    var operacionRecienIntroducida = false

    /**
     * Los siguientes funciones se encargan de crear la lógica operacional de la calculadora.
     */
    fun sumar() {
        resultado = num1.toDouble() + num2.toDouble()
    }

    fun restar() {
        resultado = num1.toDouble() - num2.toDouble()
    }

    fun multiplicar() {
        resultado = num1.toDouble() * num2.toDouble()
    }

    fun dividir() {
        resultado = num1.toDouble() / num2.toDouble()
    }

    fun mostrarToast(mensaje: String) {
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
    }

    /**
     * La función calcular maneja la situación en la que se dispara el Toast. Además, euipara los símbolos Strings
     * de operaciones a los métodos de sumar, restar, multiplicar y dividir. Añado un condicional para comprobar que
     * el decimal sea 0, si es 0, convierto el número a entero antes de convertirlo a String. Por otra parte, al haberse
     * pulsado el operador, la calculadora pasa a fase 3 (Inserte num2).
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
        fase =
            3 // Se ha realizado una operación, si se presiona un número la calculadora se resetea.
    }

    /**
     * Si calculadora está en fase 0, 2, 3, lleva a cabo las siguientes tareas.
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
     * Comprueba que num1 no esté vacio, Si es así, la calculadora pasa
     * a fase2, espera a que inserte el botón de operación "/" "-" "x" "+"
     */
    fun setOperaciones(oper: String) {
        if (num1.isNotEmpty()) {
            if (operacion.isNotEmpty() && num2.isNotEmpty()) {
                calcular()
            }
            operacion = oper
            fase = 2 // Esperando a num2
        }
    }

    /**
     * Todas las variables quedan vacias cuando se llama a esta función. Calculadora pasa a fase 0,
     * (espera a num1). Llamo a este método cuando quiero resetear.
     */
    fun resetear() {
        num1 = ""
        num2 = ""
        operacion = ""
        resultado = 0.0
        fase = 0
        operacionRecienIntroducida = false
    }

    fun setPuntoClicked() {
        if (fase == 0 && !num1.contains(".")){
            num1 += "."
        } else if (fase == 2 && !num2.contains(".")) {
            num2 += "."
        }
    }

    /**
     * Método `botonLimpiarC` que se llama cuando se pulsa el botón C.
     * Borra los dígitos uno a uno del número actual.
     * Si no hay más dígitos para borrar, borra la operación.
     * Si no hay operación para borrar, borra los dígitos del primer número.
     * Si no hay nada que eliminar, se emite un mensaje tipo toast "No hay nada más que borrar".
     */
    fun botonLimpiarC() {
        if (num2.isNotEmpty()) {
            num2 = num2.dropLast(1)
        } else if (operacion.isNotEmpty()) {
            operacion = ""
            fase = 0 // Esperando a num1
        } else if (num1.isNotEmpty()) {
            num1 = num1.dropLast(1)
        } else {
            mostrarToast("No hay nada más que borrar.")
        }
    }
}
