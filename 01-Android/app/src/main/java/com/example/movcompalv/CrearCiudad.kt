package com.example.movcompalv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class CrearCiudad : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_ciudad)

        val nombreCiudad = findViewById<EditText>(R.id.id_crear_nombre_ciudad)
        val poblacionCiudad = findViewById<EditText>(R.id.id_crear_poblacion_ciudad)
        val areaCiudad = findViewById<EditText>(R.id.id_crear_area_ciudad)
        val capitalCiudad = findViewById<Spinner>(R.id.id_spinner_capital_ciudad_crear)
        val alcaldeCiudad = findViewById<EditText>(R.id.id_crear_alcalde_ciudad)
        val botonGenerarCiudad = findViewById<Button>(R.id.id_boton_para_crear_ciudad)

        val mensaje = "Se guardo la ciudad"
        val mensaje1 = "Se actualizo"
        val duracion = Toast.LENGTH_SHORT

        val toaster = Toast.makeText(this, mensaje, duracion)
        val toaster1 = Toast.makeText(this, mensaje1, duracion)


        val ciudadElejida = intent.getStringExtra("ciudad")


        if (ciudadElejida=="none"){
            botonGenerarCiudad.setText("Crear")
            botonGenerarCiudad.setOnClickListener {
                var escapital = false
                if(capitalCiudad.selectedItem.toString()=="Verdadero"){
                    escapital = true
                }
                val city=Ciudad(
                    0,
                    nombreCiudad.text.toString(),
                    poblacionCiudad.text.toString().toInt(),
                    areaCiudad.text.toString().toDouble(),
                    escapital,
                    alcaldeCiudad.text.toString()
                )
                BaseDatosHelper(this).crearCiudad(city)
                toaster.show()
                irActividad(MainActivity::class.java)
            }

            }else{
              val ciudad = BaseDatosHelper(this).leerCiudadbyNombre(ciudadElejida)
            nombreCiudad.setText(ciudad.Nombre.toString())
            poblacionCiudad.setText(ciudad.Poblacion.toString())
            areaCiudad.setText(ciudad.Area.toString())
            alcaldeCiudad.setText(ciudad.Alcalde)
            botonGenerarCiudad.setText("Actualizar")

            botonGenerarCiudad.setOnClickListener {
                var escapital = false
                if(capitalCiudad.selectedItem.toString()=="Verdadero"){
                    escapital = true
                }
                val city1=Ciudad(
                    ciudad.id,
                    nombreCiudad.text.toString(),
                    poblacionCiudad.text.toString().toInt(),
                    areaCiudad.text.toString().toDouble(),
                    escapital,
                    alcaldeCiudad.text.toString()
                )
                BaseDatosHelper(this).actualizaCiudad(city1)
                toaster1.show()
                irActividad(MainActivity::class.java)
            }
            }
        }




    fun irActividad(clase:Class<*>){
        val intent =  Intent(this,clase)
        startActivity(intent)
    }
}