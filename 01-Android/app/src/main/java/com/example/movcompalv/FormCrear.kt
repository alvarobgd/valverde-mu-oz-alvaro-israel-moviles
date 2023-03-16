package com.example.movcompalv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class FormCrear : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_form_crear)

        val textoVariable = findViewById<TextView>(R.id.id_texto_variable)
        val accion = intent.getStringExtra("identificador")
        textoVariable.text = accion

        if(accion=="Crear"){
            val botonProvincia =  findViewById<Button>(R.id.id_Boton_crear_prov)
            botonProvincia.setOnClickListener { irActividad(CrearProv::class.java) }

            val botonCiudad =  findViewById<Button>(R.id.id_boton_crear_ciudad)
            botonCiudad.setOnClickListener {
                val intent = Intent(this, CrearCiudad::class.java)
                intent.putExtra("ciudad","none")
                startActivity(intent)
            }
        }

        if(accion=="Leer"){
            val botonProvincia =  findViewById<Button>(R.id.id_Boton_crear_prov)
            botonProvincia.setOnClickListener { irActividad(MostrarProvincia::class.java) }

            val botonCiudad =  findViewById<Button>(R.id.id_boton_crear_ciudad)
            botonCiudad.setOnClickListener { irActividad(MostrarCiudades::class.java) }

        }

        if(accion=="Actualizar"){
            val botonProvincia =  findViewById<Button>(R.id.id_Boton_crear_prov)
            botonProvincia.setOnClickListener {
                val intent = Intent(this, PreActualizarCiudad::class.java)
                intent.putExtra("modo","Actualizar")
                startActivity(intent)
            }

            val botonCiudad =  findViewById<Button>(R.id.id_boton_crear_ciudad)
            botonCiudad.setOnClickListener {
                val intent = Intent(this, PreActualizarCiudad::class.java)
                intent.putExtra("modo","Actualizar")
                startActivity(intent)}

        }

        if(accion=="Eliminar"){
            val botonProvincia =  findViewById<Button>(R.id.id_Boton_crear_prov)
            botonProvincia.setOnClickListener {
                val intent = Intent(this, PreActualizarCiudad::class.java)
                intent.putExtra("modo","Eliminar")
                startActivity(intent)
            }

            val botonCiudad =  findViewById<Button>(R.id.id_boton_crear_ciudad)
            botonCiudad.setOnClickListener {
                val intent = Intent(this, PreActualizarCiudad::class.java)
                intent.putExtra("modo","Eliminar")
                startActivity(intent)
            }

        }

    }


    fun irActividad(clase:Class<*>){
        val intent =  Intent(this,clase)
        startActivity(intent)
    }
}