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


    val contenidoIntentExplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
            if (result.resultCode == Activity.RESULT_OK){
                if(result.data!=null){
                    val data = result.data
                    Log.i("intent-epn","${data?.getStringExtra("nombreModificado")}")
                }
            }
        }

    val contenidoImplicito = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
        if(result.resultCode == RESULT_OK){
            if(result.data!!.data != null){
                val uri: Uri = result.data!!.data!!
                val cursor = contentResolver.query(
                    uri,
                    null,
                    null,
                    null,
                    null,
                    null
                )
                cursor?.moveToFirst()
                val indiceTelefono = cursor?.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                )
                val telefono = cursor?.getString(
                    indiceTelefono!!
                )
                cursor?.close()
                Log.i("intent-epn","Telefono ${telefono}")
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val botonRestaurante = findViewById<ImageButton>(R.id.id_boton_imagen_restaurante)
        botonRestaurante.setOnClickListener{irActividad(Restaurante::class.java)}

        val botonMercado = findViewById<ImageButton>(R.id.id_boton_imagen_mercado)
        botonMercado.setOnClickListener { irActividad(Restaurante::class.java) }

    }



    fun irActividad(clase:Class<*>){
        val intent =  Intent(this,clase)
        startActivity(intent)
    }
}