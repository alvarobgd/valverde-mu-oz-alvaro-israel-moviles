package com.example.movcompalv

class BBaseDatosMemoria {

    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador.add(BEntrenador("juan","loco"))
            arregloBEntrenador.add(BEntrenador("alvaro","alv"))
            arregloBEntrenador.add(BEntrenador("mishell","amor"))
        }
    }
}