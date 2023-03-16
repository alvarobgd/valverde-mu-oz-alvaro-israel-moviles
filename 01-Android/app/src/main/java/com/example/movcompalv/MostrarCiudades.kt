package com.example.movcompalv

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.String


class MostrarCiudades : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_ciudades)

       val tabla = findViewById<TableLayout>(R.id.tabla_muestra_ciudades)

        val ciudades = BaseDatosHelper(this).leerCiudades()

        for (ciudad in ciudades){

            val fila = TableRow(this)

            val idView = TextView(this)
            idView.setText(ciudad.id.toString())
            idView.setPadding(5, 5, 5, 5)
            fila.addView(idView)

            val nombreView = TextView(this)
            nombreView.setText(String.valueOf(ciudad.Nombre.toString()))
            nombreView.setPadding(5, 5, 5, 5)
            fila.addView(nombreView)

            val poblacionView = TextView(this)
            poblacionView.setText(ciudad.Poblacion.toString())
            poblacionView.setPadding(5, 5, 5, 5)
            fila.addView(poblacionView)

            val areaView = TextView(this)
            areaView.setText(String.valueOf(ciudad.Area.toString()))
            areaView.setPadding(5, 5, 5, 5)
            fila.addView(areaView)

            val capitalView = TextView(this)
            capitalView.setText(ciudad.Capital.toString())
            capitalView.setPadding(5, 5, 5, 5)
            fila.addView(capitalView)

            val alcaldeView = TextView(this)
            alcaldeView.setText(ciudad.Alcalde.toString())
            alcaldeView.setPadding(5, 5, 5, 5)
            fila.addView(alcaldeView)


            tabla.addView(fila)

        }

        val botonRegresar = findViewById<Button>(R.id.id_boton_regresar_city)

        botonRegresar.setOnClickListener{
            irActividad(MainActivity::class.java)
        }

    }

    fun irActividad(clase:Class<*>){
        val intent =  Intent(this,clase)
        startActivity(intent)
    }
}