package com.example.movcompalv

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatosHelper(
    context: Context?,
):SQLiteOpenHelper(context, "ciudades.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS Ciudad(id INTEGER PRIMARY KEY AUTOINCREMENT, Nombre TEXT, Poblacion INTEGER," +
                "Area REAL,Capital TEXT, Alcalde TEXT)")
        db?.execSQL("CREATE TABLE IF NOT EXISTS Provincia(id INTEGER PRIMARY KEY AUTOINCREMENT, Nombre TEXT,Area REAL," +
                "Region TEXT, Prefecto TEXT)")
        db?.execSQL("CREATE TABLE IF NOT EXISTS CiudadProvincia(id INTEGER PRIMARY KEY AUTOINCREMENT,idCiudad INTEGER, " +
                "idProvincia INTEGER,FOREIGN KEY(idCiudad) REFERENCES Ciudad(id), FOREIGN KEY(idProvincia) REFERENCES Provincia(id))")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun dropTables() {
        val db = this.writableDatabase
        db.execSQL("DROP TABLE IF EXISTS Ciudad")
        db.execSQL("DROP TABLE IF EXISTS Provincia")
        db.execSQL("DROP TABLE IF EXISTS CiudadProvincia")
        onCreate(db)
    }

    fun crearCiudad(ciudad: Ciudad) {
        val db = this.writableDatabase
        var capital:String ="Falso"
        if(ciudad.Capital==true){
            capital = "Verdadero"
        }
        db.execSQL("INSERT INTO Ciudad ( Nombre, Poblacion, Area, Capital, Alcalde) " +
                "VALUES ('${ciudad.Nombre}', ${ciudad.Poblacion}, " +
                "${ciudad.Area}, '${capital}', '${ciudad.Alcalde}')")
    }

    fun crearProvincia(provincia: Provincia) {
        val db = this.writableDatabase
        db.execSQL("INSERT INTO Provincia ( Nombre, Area, Region, Prefecto) " +
                "VALUES ('${provincia.Nombre}', ${provincia.Area}, " +
                "'${provincia.Region}', '${provincia.Prefecto}')")

    }

    fun crearCiudadProvincia(idCiudad: Int,idProvincia: Int){
        val db = this.writableDatabase
        db.execSQL("INSERT INTO CiudadProvincia ( idCiudad,idProvincia) " +
                "VALUES ($idCiudad,$idProvincia)")
    }


    fun leerProvincia(id: Int): Provincia {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Provincia WHERE id = $id", null)
        cursor.moveToFirst()
        val ciudades =
            if (cursor.count > 0) leerCiudadProvincia(cursor.getInt(0)) else ArrayList()
        val provincia = Provincia(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getDouble(2),
            cursor.getString(3),
            cursor.getString(4),
            ciudades
        )
        cursor.close()
        db.close()
        return provincia
    }

    fun leerProvinciaByname(name: String): Provincia {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Provincia WHERE Nombre = '$name'", null)
        cursor.moveToFirst()
        val ciudades =
            if (cursor.count > 0) leerCiudadProvincia(cursor.getInt(0)) else ArrayList()
        val provincia = Provincia(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getDouble(2),
            cursor.getString(3),
            cursor.getString(4),
            ciudades
        )
        cursor.close()
        db.close()
        return provincia
    }

    fun leerProvincias(): ArrayList<Provincia> {
        val provincias = ArrayList<Provincia>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Provincia", null)
        if (cursor.moveToFirst()) {
            do {
                val ciudades = leerCiudadProvincia(cursor.getInt(0))
                val provincia =Provincia(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    ciudades
                )
                provincias.add(provincia)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return provincias
    }

    fun actualizarProvincia(provincia: Provincia) {
        val db = this.writableDatabase
        db.execSQL("UPDATE Provincia SET Nombre = '${provincia.Nombre}', Area = ${provincia.Area}" +
                ", Region = '${provincia.Region}', Prefecto = '${provincia.Prefecto}' WHERE id = ${provincia.id}")
    }


    fun eliminarProvincia(id: Int) {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM CiudadProvincia WHERE idProvincia = $id")
        db.execSQL("DELETE FROM Provincia WHERE id = $id")
    }

    fun leerCiudadProvincia(idProvincia:Int): ArrayList<Ciudad> {
        val ciudades = ArrayList<Ciudad>()
        val db = this.readableDatabase
        val cursor =
            db.rawQuery("SELECT * FROM CiudadProvincia WHERE idProvincia = $idProvincia", null)
        if (cursor.moveToFirst()) {
            do {
                val ciudad = leerCiudad(cursor.getInt(0))
                ciudades.add(ciudad)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return ciudades
    }

    fun existCiudadId(id: Int): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Ciudad WHERE id = $id", null)
        if (cursor.moveToFirst()) {
            return true
        }
        cursor.close()
        db.close()
        return false
    }

    fun existCiudadProvincia(idCiudad: Int, idProvincia: Int): Boolean {
        val db = this.readableDatabase
        val cursor =
            db.rawQuery(
                "SELECT * FROM CiudadProvincia WHERE idCiudad = $idCiudad AND idProvincia = $idProvincia",
                null
            )
        if (cursor.moveToFirst()) {
            return true
        }
        cursor.close()
        db.close()
        return false
    }

    fun leerCiudad(id: Int): Ciudad {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Ciudad WHERE id = $id", null)
        if (cursor.moveToFirst()) {
            do {
                var capital:Boolean =false
                if(cursor.getString(4)=="Verdadero"){
                    capital = true
                }
                val ciudad = Ciudad(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getDouble(3),
                    capital,
                    cursor.getString(5)
                )
                return ciudad
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return Ciudad(-1, "", 0, 0.0, false, "")
    }

    fun leerCiudadbyNombre(name: String?): Ciudad {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Ciudad WHERE Nombre = '$name'", null)
        if (cursor.moveToFirst()) {
            do {
                var capital:Boolean =false
                if(cursor.getString(4)=="Verdadero"){
                    capital = true
                }
                val ciudad = Ciudad(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getDouble(3),
                    capital,
                    cursor.getString(5)
                )
                return ciudad
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return Ciudad(-1, "", 0, 0.0, false, "")
    }


    fun leerCiudades(): ArrayList<Ciudad> {
        val ciudades = ArrayList<Ciudad>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Ciudad", null)
        if (cursor.moveToFirst()) {
            do {
                var capital:Boolean =false
                if(cursor.getString(4)=="Verdadero"){
                    capital = true
                }
                val ciudad = Ciudad(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getDouble(3),
                    capital,
                    cursor.getString(5)
                )
                ciudades.add(ciudad)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return ciudades
    }

    fun leerCiudadesNombre(): ArrayList<String> {
        val nombres = ArrayList<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Ciudad", null)
        if (cursor.moveToFirst()) {
            do {

                nombres.add(cursor.getString(1))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return nombres
    }


    fun actualizaCiudad(ciudad: Ciudad) {
        val db = this.writableDatabase
        var capital:String ="Falso"
        if(ciudad.Capital==true){
            capital = "Verdadero"
        }
        db.execSQL("UPDATE Ciudad SET Nombre = '${ciudad.Nombre}', Poblacion = ${ciudad.Poblacion}, Area = ${ciudad.Area}, " +
                "Capital = '${capital}', Alcalde = '${ciudad.Alcalde}' WHERE id = ${ciudad.id}")
    }

    fun eliminarCiudad(id: Int) {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM CiudadProvincia WHERE idCiudad = $id")
        db.execSQL("DELETE FROM Ciudad WHERE id = $id")
    }




}