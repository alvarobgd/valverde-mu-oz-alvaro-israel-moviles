package com.example.movcompalv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class PreActualizarCiudad : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_actualizar_ciudad)

        val nombresCiudades = BaseDatosHelper(this).leerCiudadesNombre()
        val spinnerCiudades = findViewById<Spinner>(R.id.spinner_select_ciudad)
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombresCiudades)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCiudades.setAdapter(adapter)

        val ciudadSelect =  findViewById<Spinner>(R.id.spinner_select_ciudad)

        val botonParaActualizar = findViewById<Button>(R.id.boton_para_actualizar)


        val modo = intent.getStringExtra("modo")

        if(modo=="Actualizar"){
            botonParaActualizar.setOnClickListener {
                val citySeleccionada =  ciudadSelect.selectedItem.toString()
                val intent = Intent(this, CrearCiudad::class.java)
                intent.putExtra("ciudad",citySeleccionada)
                startActivity(intent)
            }

        }else{
            val citySeleccionada =  ciudadSelect.selectedItem.toString()
            botonParaActualizar.setText("Eliminar")
            botonParaActualizar.setOnClickListener {
                val eliminada = BaseDatosHelper(this).leerCiudadbyNombre(citySeleccionada)
                BaseDatosHelper(this).eliminarCiudad(eliminada.id)
                irActividad(MainActivity::class.java)
            }
        }



    }

    fun irActividad(clase:Class<*>){
        val intent =  Intent(this,clase)
        startActivity(intent)
    }
}