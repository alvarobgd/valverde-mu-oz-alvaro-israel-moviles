package com.example.movcompalv

import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.movcompalv.databinding.ActivityAcicloVidaBinding

class ACicloVida : AppCompatActivity() {
    var textoGlobal = ""

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAcicloVidaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAcicloVidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_aciclo_vida)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        mostrarSnackBar("OnCreate")
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_aciclo_vida)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()

    }

    override fun onStart() {
        super.onStart()
        mostrarSnackBar("onStart")
    }

    override fun onResume() {
        super.onResume()
        mostrarSnackBar("onResume")
    }

    override fun onRestart() {
        super.onRestart()
        mostrarSnackBar("onRestart")
    }

    override fun onPause() {
        super.onPause()
        mostrarSnackBar("onPause")
    }

    override fun onStop() {
        super.onStop()
        mostrarSnackBar("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        mostrarSnackBar("onDestroy")
    }

    fun mostrarSnackBar(texto:String){
        textoGlobal += texto
        Snackbar.make(findViewById(R.id.cl_ciclo_vida),
        textoGlobal,Snackbar.LENGTH_LONG)
            .setAction("Action",null).show()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString("textoGuardado",textoGlobal)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val textoRecuperado:String? = savedInstanceState.getString("textoGuardado")
        if(textoRecuperado != null){
            mostrarSnackBar(textoRecuperado)
            textoGlobal = textoRecuperado
        }
    }
}