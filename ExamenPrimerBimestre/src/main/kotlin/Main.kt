import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.reflect.typeOf

fun main(args: Array<String>) {
    val opciones = arrayOf("1","2","3","4","5","6","7","8","9")
    val regiones = arrayOf("1","2","3")
    val campos = arrayOf("1","2","3","4","5")
    val campos1 = arrayOf("1","2","3","4")
    var bandera = true
    val reader = BufferedReader(InputStreamReader(System.`in`))
    while(bandera){
        println("****************************************************************************")
        println("Menu de administracion de ciudades y provincias del Ecuador")
        println("Escoja una opcion:")
        println("1. Ver ciudades registradas.")
        println("2. Ver provincias registradas.")
        println("3. Agregar una ciudad.")
        println("4. Agregar una provincia.")
        println("5. Eliminar una ciudad.")
        println("6. Eliminar una provincia.")
        println("7. Actualizar informacion de una ciudad.")
        println("8. Actualizar informacion de una provincia.")
        println("9. Salir")
        print("Ingrese su eleccion: ")
        val input = reader.readLine()
        println("****************************************************************************")
        if(!(input in opciones)){
            println("===================================================")
            println("Ingrese una opcion valida")
            println("===================================================")
        }
        if(input == "9"){
            println("===================================================")
            println("Adios")
            println("===================================================")
            bandera = false
        }
        if (input == "1"){
            val city = Ciudad(null,null,null,null,null)
            println("==========================================================================================")
            println("Ciudades registradas")
            println("==========================================================================================")
            city.printCiudadTable()
            println("==========================================================================================")
        }
        if (input == "2"){
            val prov = Provincia(null,null,null,null,null)
            println("==========================================================================================")
            println("Provincias registradas")
            println("==========================================================================================")
            prov.printProvinciaTable()
            println("==========================================================================================")
        }

        if(input =="3"){
            val city = Ciudad(null,null,null,null,null)
            println("==========================================================================================")
            print("Ingrese el nombre de la ciudad: ")

            val name = reader.readLine()
            if (city.getCiudad(name).Nombre==""){
                print("Ingrese la poblacion: ")
                val poblacion = reader.readLine().toInt()
                print("Ingrese el area en kilometros: ")
                val area = reader.readLine().toDouble()
                print("¿Esta ciudad es capital de su provincia? (si/no) : ")
                var cap = false
                val iscap = reader.readLine()
                if (iscap=="si"){
                    cap = true
                }
                print("Ingrese su alcalde: ")
                val alcalde = reader.readLine()
                val ciudad = Ciudad(name,poblacion,area,cap,alcalde)
                ciudad.GuardarCiudad()
                println("Ciudad registrada")
                println("==========================================================================================")
            }else{
                println("Esa ciudad ya esta registrada")
                println("==========================================================================================")
            }
        }

        if(input =="4"){
            val prov = Provincia(null,null,null,null,null)
            println("==========================================================================================")
            print("Ingrese el nombre de la provincia: ")

            val name = reader.readLine()
            if (prov.getProvincia(name).Nombre==""){
                print("Ingrese el area en kilomentros: ")
                val area = reader.readLine().toDouble()
                var bandReg = true
                var region = ""
                while(bandReg){
                    println("Las posibles regiones son: ")
                    println("1. Costa")
                    println("2. Sierra")
                    println("3. Oriente")
                    print("Escoja la region: ")
                    val reg = reader.readLine()
                    if(reg in regiones){
                        when(reg){
                            ("1")->{
                                region = "Costa"
                            }
                            ("2")->{
                                region = "Sierra"
                            }
                            ("3")->{
                                region = "Oriente"
                            }
                        }
                        bandReg = false
                    }else{
                        println("Ingrese una opcion valida")
                    }
                }

                print("Ingrese a su Prefecto: ")
                val prefecto = reader.readLine()
                println("Para seleccionar las ciudades deben estar previamente registradas")
                println("Esta son las ciudades registradas")
                Ciudad(null,null,null,null,null).printCiudadTable()
                print("¿Estan registradas las ciudades que necesita? (si/no): ")
                val sino = reader.readLine()
                if(sino=="no"){
                    println("==========================================================================================")
                    println("Registre las ciudades primero")
                    println("==========================================================================================")
                }else{
                    print("Ingrese el numero de ciudades que necesita: ")

                    var num = reader.readLine().toInt()
                    val ciudades = Array(num) { "" }
                    for (i in 0..(num-1)){
                        print("Ingrese el nombre de la ciudad :")
                        ciudades[i] = reader.readLine()
                    }
                    val city1 = Ciudad(null,null,null,null,null)
                    val Ciudades = city1.obtenerCiudades(ciudades)
                    val provincia = Provincia(name,area,region,prefecto,Ciudades)
                    provincia.GuardarProvincia()
                    println("Provincia registrada")
                    println("==========================================================================================")
                }

        }else{
                println("Esta provincia ya esta registrada")
                println("==========================================================================================")
        }
    }
        if(input=="5"){
            print("Ingrese el nombre de la ciudad:")
            var Nciudad = reader.readLine()
            var ciudad = Ciudad(null,null,null,null,null)
            if(ciudad.getCiudad(Nciudad).Nombre==""){
                println("==========================================================================================")
                println("No existe esa ciudad en el registro")
                println("==========================================================================================")
            }else{
                ciudad.eliminaCiudadPorNombre(Nciudad)
                println("==========================================================================================")
                println("La ciudad se ha eliminado")
                println("==========================================================================================")
            }

        }
        if(input=="6"){
            print("Ingrese el nombre de la provincia:")
            var Nprovincia = reader.readLine()
            var provincia = Provincia(null,null,null,null,null)
            if(provincia.getProvincia(Nprovincia).Nombre==""){
                println("==========================================================================================")
                println("No existe esa provincia en el registro")
                println("==========================================================================================")
            }else{
                provincia.eliminaProvinciaPorNombre(Nprovincia)
                println("==========================================================================================")
                println("La provincia se ha eliminado")
                println("==========================================================================================")
            }

        }
        if(input=="7"){
            val ciudad =  Ciudad(null,null,null,null,null)
            print("Ingrese el nombre de la ciudad : ")
            val name = reader.readLine()
            if(ciudad.getCiudad(name).Nombre==""){
                println("==========================================================================================")
                println("No existe esa ciudad en el registro")
                println("==========================================================================================")
            }else{

                var bandact =true
                while (bandact) {
                    println("Estos son los campos:")
                    println("1. Nombre")
                    println("2. Poblacion")
                    println("3. Area")
                    println("4. Capital")
                    println("5. Alcalde")
                    print("Ingrese el campo que quiere actualizar: ")
                    val campo = reader.readLine()
                    if (campo in campos) {
                        print("Ingrese el nuevo valor: ")
                        val valor = reader.readLine()
                        ciudad.actualizaCiudad(name,campo.toInt(),valor)
                        println("==========================================================================================")
                        println("Campos actualizados")
                        println("==========================================================================================")
                        bandact =false
                    }else{
                        println("==========================================================================================")
                        println("Ingrese un campo valido")
                        println("==========================================================================================")
                    }
                }
            }
        }
        if(input=="8"){
            val provincia =  Provincia(null,null,null,null,null)
            print("Ingrese el nombre de la provincia : ")
            val name = reader.readLine()
            if(provincia.getProvincia(name).Nombre==""){
                println("==========================================================================================")
                println("No existe esa provincia en el registro")
                println("==========================================================================================")
            }else{

                var bandact =true
                while (bandact) {
                    println("Estos son los campos:")
                    println("1. Nombre")
                    println("2. Area")
                    println("3. Region")
                    println("4. Prefecto")
                    println("5. Ciudades")
                    print("Ingrese el campo que quiere actualizar: ")
                    val campo = reader.readLine()
                    if (campo in campos1) {
                        print("Ingrese el nuevo valor: ")
                        val valor = reader.readLine()
                        provincia.actualizaProvincia(name,campo.toInt(),valor)
                        println("==========================================================================================")
                        println("Campos actualizados")
                        println("==========================================================================================")
                        bandact =false
                    }else{
                        if (campo=="5"){
                            val prov = provincia.getProvincia(name)
                            val Area = prov.Area_km
                            val Region = prov.region
                            val Prefecto = prov.Prefecto

                            println("Para actualizar las ciudades deben estar previamente registradas")
                            println("Esta son las ciudades registradas")
                            Ciudad(null,null,null,null,null).printCiudadTable()
                            print("¿Estan registradas las ciudades que necesita? (si/no): ")
                            val sino = reader.readLine()
                            if(sino=="no"){
                                println("==========================================================================================")
                                println("Registre las ciudades primero")
                                println("==========================================================================================")
                                bandact =false
                            }else{
                                provincia.eliminaProvinciaPorNombre(name)
                                print("Ingrese el numero de ciudades que necesita: ")

                                var num = reader.readLine().toInt()
                                val ciudades = Array(num) { "" }
                                for (i in 0..(num-1)){
                                    print("Ingrese el nombre de la ciudad :")
                                    ciudades[i] = reader.readLine()
                                }
                                val city1 = Ciudad(null,null,null,null,null)
                                val Ciudades1 = city1.obtenerCiudades(ciudades)
                                val provincia1 = Provincia(name,Area,Region,Prefecto,Ciudades1)
                                provincia1.GuardarProvincia()
                                println("Ciudades actualizadas")
                                println("==========================================================================================")
                                bandact =false
                            }
                            bandact =false
                        }else{
                            println("==========================================================================================")
                            println("Ingrese un campo valido")
                            println("==========================================================================================")
                        }

                    }
                }
            }
        }

}}