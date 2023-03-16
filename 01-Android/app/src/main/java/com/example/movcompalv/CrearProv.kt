package com.example.movcompalv

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class CrearProv : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_prov)

        val nombresCiudades = BaseDatosHelper(this).leerCiudadesNombre()
        val spinnerCiudades = findViewById<Spinner>(R.id.id_spinner_crear_prov_ciudad)
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombresCiudades)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCiudades.setAdapter(adapter)

        val nombreProv = findViewById<EditText>(R.id.id_nombre_provincia)
        val areaProv = findViewById<EditText>(R.id.id_area_prov)
        val regionProv = findViewById<Spinner>(R.id.id_spinner_region_prov)
        val prefectoProv = findViewById<EditText>(R.id.id_prefecto_prov)
        val ciudadProv =  findViewById<Spinner>(R.id.id_spinner_crear_prov_ciudad)
        val botonCrearProv = findViewById<Button>(R.id.id_boton_crear_prov)

        val mensaje = "Se guardo la provincia"
        val duracion = Toast.LENGTH_SHORT
        val duracion1 = Toast.LENGTH_LONG

        val toaster = Toast.makeText(this, mensaje, duracion)


        botonCrearProv.setOnClickListener {
            val ciudades= ArrayList<Ciudad>()
            ciudades.add(BaseDatosHelper(this).leerCiudadbyNombre(ciudadProv.selectedItem.toString()))

            val toaster1 = Toast.makeText(this, ciudades.get(0).Nombre, duracion1)
            toaster1.show()

            val prov = Provincia(
                0,
                nombreProv.text.toString(),
                areaProv.text.toString().toDouble(),
                regionProv.selectedItem.toString(),
                prefectoProv.text.toString(),
                ciudades
            )
            BaseDatosHelper(this).crearProvincia(prov)
            val provi = BaseDatosHelper(this).leerProvinciaByname(prov.Nombre)
            BaseDatosHelper(this).crearCiudadProvincia(ciudades[0].id,provi.id)
            toaster.show()
            irActividad(MainActivity::class.java)
        }

    }
    fun irActividad(clase:Class<*>){
        val intent =  Intent(this,clase)
        startActivity(intent)
    }
}