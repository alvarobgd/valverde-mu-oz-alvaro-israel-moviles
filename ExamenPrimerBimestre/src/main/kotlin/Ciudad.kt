import java.io.File
import java.io.PrintWriter
import java.io.FileWriter

class Ciudad(var Nombre: String, var Poblacion:Int, var Area_km: Double, var Capital:Boolean, var Alcalde:String){

    constructor(Nombre: String?, Poblacion:Int?, Area_km: Double?, Capital:Boolean?, Alcalde:String?):this(if (Nombre==null) ""  else Nombre,
        if (Poblacion==null) 0  else Poblacion,if (Area_km==null) 0.0  else Area_km,if (Capital==null) false  else Capital
        ,if (Alcalde==null) ""  else Alcalde){}


    fun nuevoNombre(name: String){
        this.Nombre = name
    }
    fun nuevoPoblacion(habitantes:Int){
        this.Poblacion = habitantes
    }

    fun nuevoCapital(capital:Boolean){
        this.Capital=capital
    }

    fun nuevoAlcalde(alcalde:String){
        this.Alcalde=alcalde
    }

    fun nuevoArea(area:Double){
        this.Area_km = area
    }

    fun GuardarCiudad():Boolean {

        if(getCiudad(this.Nombre).Nombre==""){
            val writer = PrintWriter(FileWriter(archivo,true))

            val line = "${Nombre},${Poblacion},${Area_km},${Capital},${Alcalde}\n"
            writer.write(line)

            writer.close()
            return true
        }else{
              return false
            }
    }


    fun eliminaCiudadPorNombre(name: String) {
        val tempFile = File("temp.txt")
        val originalFile = File(archivo)
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

    fun getCiudad(name: String): Ciudad {
        val file = File(archivo)
        var aux = false
        var ciudad = Ciudad(null,null,null,null,null)

        file.forEachLine {
            val parts = it.split(",")
            if (parts[0] == name) {

                if (parts[3]=="true"){
                    aux = true
                }
                ciudad = Ciudad(parts[0], parts[1].toInt(),parts[2].toDouble(),aux,parts[4]);

                return@forEachLine
            }

        }
        return ciudad
    }

    fun actualizaCiudad(name: String,campo:Int,nuevo:String){
        var ciudad = getCiudad(name)
        eliminaCiudadPorNombre(name)
        when(campo){
            (1)->{//nombre
                ciudad.nuevoNombre(nuevo)
            }
            (2)->{//poblacion
                ciudad.nuevoPoblacion(nuevo.toInt())
            }
            (3)->{//area
                ciudad.nuevoArea(nuevo.toDouble())
            }
            (4)->{//capital
                if(nuevo=="false"){
                    ciudad.nuevoCapital(false)
                }else{
                    ciudad.nuevoCapital(true)
                }
            }
            (5)->{//alcalde
                ciudad.nuevoAlcalde(nuevo)
            }
        }
        ciudad.GuardarCiudad()

    }

    fun printCiudadTable() {
        val city = mutableListOf<Ciudad>()
        var arch = File(archivo)
        // Encabezado de la tabla
        val table = mutableListOf("Nombre".padEnd(20) + "Poblacion".padEnd(20) +"Area".padEnd(20) +
                "Es capital".padEnd(20) +"Alcalde" )

        if(!(arch.length()==0L)) {
            arch.forEachLine {
                var aux = false
                val parts = it.split(",")
                if (parts[3] == "true") {
                    aux = true
                }
                city.add(Ciudad(parts[0], parts[1].toInt(), parts[2].toDouble(), aux, parts[4]))
            }

            city.forEach {
                table.add(
                    "${it.Nombre}".padEnd(20) + "${it.Poblacion}".padEnd(20) + "${it.Area_km}".padEnd(20) +
                            "${it.Capital}".padEnd(20) + "${it.Alcalde}"
                )
            }
        }
        table.forEach { println(it) }
    }


    fun obtenerCiudades(names: Array<String>): Array<Ciudad> {
        val ciudad = Ciudad(null,null,null,null,null)
        return names.map { ciudad.getCiudad(it) }.toTypedArray()
    }
    companion object{
        val archivo = "C:\\Users\\israv\\OneDrive\\Documentos\\GitHub\\valverde-mu-oz-alvaro-israel-moviles\\ExamenPrimerBimestre\\src\\main\\kotlin\\Ciudades.txt"
    }
}