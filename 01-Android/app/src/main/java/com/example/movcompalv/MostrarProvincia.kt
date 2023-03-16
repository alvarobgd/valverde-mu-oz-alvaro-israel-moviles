package com.example.movcompalv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import java.lang.String

class MostrarProvincia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_provincia)

        val tabla = findViewById<TableLayout>(R.id.tabla_mostrar_provincia)
        val provincias = BaseDatosHelper(this).leerProvincias()

        for (prov in provincias){
            val fila = TableRow(this)

            val idView = TextView(this)
            idView.setText(prov.id.toString())
            idView.setPadding(5, 5, 5, 5)
            fila.addView(idView)

            val nombreView = TextView(this)
            nombreView.setText(String.valueOf(prov.Nombre.toString()))
            nombreView.setPadding(5, 5, 5, 5)
            fila.addView(nombreView)

            val areaView = TextView(this)
            areaView.setText(prov.Area.toString())
            areaView.setPadding(5, 5, 5, 5)
            fila.addView(areaView)

            val regionView = TextView(this)
            regionView.setText(prov.Region.toString())
            regionView.setPadding(5, 5, 5, 5)
            fila.addView(regionView)

            val prefectoView = TextView(this)
            prefectoView.setText(prov.Prefecto.toString())
            prefectoView.setPadding(5, 5, 5, 5)
            fila.addView(prefectoView)

            val cities = BaseDatosHelper(this).leerCiudadProvincia(prov.id)
            val ciudadView = TextView(this)
            ciudadView.setText(cities[0].Nombre.toString())
            ciudadView.setPadding(5, 5, 5, 5)
            fila.addView(ciudadView)


            tabla.addView(fila)
        }
        val botonregresar = findViewById<Button>(R.id.boton_regresar_prov)
        botonregresar.setOnClickListener { irActividad(MainActivity::class.java) }
    }

    fun irActividad(clase:Class<*>){
        val intent =  Intent(this,clase)
        startActivity(intent)
    }
}