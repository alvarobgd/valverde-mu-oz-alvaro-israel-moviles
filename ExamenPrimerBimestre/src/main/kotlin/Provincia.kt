import java.io.File
import java.io.FileWriter
import java.io.PrintWriter


class Provincia(var Nombre: String, var Area_km: Double, var region:String, var Prefecto:String, var Ciudades:Array<Ciudad>) {

    constructor(Nombre: String?, Area_km: Double?, region: String?, Prefecto:String?,Ciudades: Array<Ciudad>?):this(
        if (Nombre==null) ""  else Nombre, if (Area_km==null) 0.0  else Area_km,if (region==null) ""  else region,
        if (Prefecto==null) ""  else Prefecto,if (Ciudades==null)emptyArray<Ciudad>()  else Ciudades){}
    fun nuevoNombre(name: String){
        this.Nombre = name
    }
    fun nuevoArea(area:Double){
        this.Area_km = area
    }
    fun nuevoRegion(reg:String){
        this.region=reg
    }

    fun nuevoPrefecto(prefec:String){
        this.Prefecto=prefec
    }

    fun nuevoCiudades(Cities: Array<Ciudad>){
        this.Ciudades = Cities
    }


    fun returnCiudades():String{
        var ciudades = ""
        Ciudades.forEach {
            ciudades = ciudades+it.Nombre+" "
        }
        return ciudades
    }


    fun GuardarProvincia() :Boolean{
        if(getProvincia(this.Nombre).Nombre==""){
            val writer = PrintWriter(FileWriter(archivoProvincia,true))

            val line = "${Nombre},${Area_km},${region},${Prefecto},${returnCiudades()}\n"
            writer.write(line)

            writer.close()
            return true
        }else{
            return false
        }
    }

    fun eliminaProvinciaPorNombre(name: String) {
        val tempFile = File("temp.txt")
        val originalFile = File(archivoProvincia)
        val writer = PrintWriter(tempFile)

        originalFile.forEachLine {
            if (!it.startsWith("$name,")) {
                writer.write("$it\n")
            }
        }

        writer.close()
        originalFile.delete()
        tempFile.renameTo(originalFile)
    }




    fun getProvincia(name: String): Provincia {
        val file = File(archivoProvincia)
        var aux = false
        var provincia =Provincia(null,null,null,null,null)
        var city : Ciudad=Ciudad(null,null,null,null,null)

        file.forEachLine {
            val parts = it.split(",")
            if (parts[0] == name) {

                val ciudades = parts[4].split(" ")
                val cities : Array<String> = ciudades.toTypedArray()

                provincia = Provincia(parts[0], parts[1].toDouble(),parts[2],parts[3],city.obtenerCiudades(cities));

                return@forEachLine
            }

        }
        return provincia
    }

    fun actualizaProvincia(name: String,campo:Int,nuevo:String){
        var provincia = getProvincia(name)
        eliminaProvinciaPorNombre(name)
        when(campo){
            (1)->{//nombre
                provincia.nuevoNombre(nuevo)
            }
            (2)->{//area
                provincia.nuevoArea(nuevo.toDouble())
            }
            (3)->{//region
                provincia.nuevoRegion(nuevo)
            }
            (4)->{//prefecto
                provincia.nuevoPrefecto(nuevo)
            }

        }
        provincia.GuardarProvincia()

    }


    fun printProvinciaTable() {
        val provincia = mutableListOf<Provincia>()
        var city : Ciudad=Ciudad(null,null,null,null,null)
        var arch = File(archivoProvincia)
        // Encabezado de la tabla
        val table = mutableListOf("Nombre".padEnd(20) + "Area".padEnd(20) +"Region".padEnd(20) +
                "Prefecto".padEnd(20) +"Ciudades" )
        if(!(arch.length()==0L)) {
            arch.forEachLine {
                val parts = it.split(",")
                val ciudades = parts[4].split(" ")
                val cities: Array<String> = ciudades.toTypedArray()
                provincia.add(
                    Provincia(
                        parts[0],
                        parts[1].toDouble(),
                        parts[2],
                        parts[3],
                        city.obtenerCiudades(cities)
                    )
                )
            }


            provincia.forEach {
                table.add(
                    "${it.Nombre}".padEnd(20) + "${it.Area_km}".padEnd(20) + "${it.region}".padEnd(20) +
                            "${it.Prefecto}".padEnd(20) + "${it.returnCiudades()}"
                )
            }
        }
        table.forEach { println(it) }
    }







    companion object{
        val archivoProvincia = "C:\\Users\\israv\\OneDrive\\Documentos\\GitHub\\valverde-mu-oz-alvaro-israel-moviles\\ExamenPrimerBimestre\\src\\main\\kotlin\\Provincias.txt"
    }
}