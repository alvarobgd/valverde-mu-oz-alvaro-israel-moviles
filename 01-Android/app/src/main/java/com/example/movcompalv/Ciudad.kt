package com.example.movcompalv

class Ciudad (
    val id:Int,
    val Nombre:String,
    val Poblacion:Int,
    val Area:Double,
    val Capital:Boolean,
    val Alcalde:String
        ) {

        override fun toString(): String {
                return "$id $Nombre $Poblacion $Area $Capital $Alcalde"
        }
}