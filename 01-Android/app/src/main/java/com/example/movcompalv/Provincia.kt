package com.example.movcompalv

class Provincia (
    val id:Int,
    val Nombre:String,
    val Area:Double,
    val Region:String,
    val Prefecto: String,
    val Ciudades:ArrayList<Ciudad>
        ){

    override fun toString(): String {
        var idCiudades:String = ""
        Ciudades.forEach{ciudad: Ciudad ->
            idCiudades = ciudad.id.toString()+" "
        }
        return "$id $Nombre $Area $Region $Prefecto $idCiudades"
    }



}

