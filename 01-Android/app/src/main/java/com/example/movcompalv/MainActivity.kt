package com.example.movcompalv

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val botonCrear = findViewById<Button>(R.id.id_boton_crear)
        botonCrear.setOnClickListener {
            val intent = Intent(this, FormCrear::class.java)
            intent.putExtra("identificador","Crear")
            startActivity(intent)
            //irActividad(FormCrear::class.java)
            }

        val botonLeer = findViewById<Button>(R.id.id_boton_leer)
        botonLeer.setOnClickListener {
            val intent = Intent(this, FormCrear::class.java)
            intent.putExtra("identificador","Leer")
            startActivity(intent)
            //irActividad(FormCrear::class.java)
            }

        val botonActualizar = findViewById<Button>(R.id.id_boton_actualizar)
        botonActualizar.setOnClickListener {
            val intent = Intent(this, FormCrear::class.java)
            intent.putExtra("identificador","Actualizar")
            startActivity(intent)
            //irActividad(FormCrear::class.java)
            }

        val botonEliminar = findViewById<Button>(R.id.id_boton_eliminar)
        botonEliminar.setOnClickListener {
            val intent = Intent(this, FormCrear::class.java)
            intent.putExtra("identificador","Eliminar")
            startActivity(intent)
            //irActividad(FormCrear::class.java)
        }

    }



    fun irActividad(clase:Class<*>){
        val intent =  Intent(this,clase)
        startActivity(intent)
    }
}