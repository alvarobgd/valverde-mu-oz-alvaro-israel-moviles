import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.

    val inmutable: String = "Alvaro";

    var mutable :String = "Juan";
    mutable="Cualquier cosa";

    //duck typing
    val ejemploVariable = "esto es un string"
    ejemploVariable.trim();
    val edadEjemplo = 56;
    val otroEjempo : Int = 85


    //variables primitivas
    val nombreProfesor : String = "No es alvaro";
    val sueldo : Double = 1.2
    val estadoCivil : Char = 'S';
    val mayorEdad= true;
    //clases
    val fechaNacimiento: Date = Date();

    //switch no existe :o

    when(estadoCivil){
        ('S')->{
           println("Soltero");
        }
        ('C')->{
            println("Casado");
        }
        else->{
            println("Complicado");
        }
    }
    val coqueto = if(estadoCivil == 'S')"si" else "no";
    println(coqueto);
    val sumaUno = suma(1,3);
    val SumaDos = suma(4,null);
    val sumaTRES = suma(null,5);
    val suma4 = suma(null,null);
    sumaUno.sumar();
    SumaDos.sumar();
    sumaTRES.sumar();
    suma4.sumar()
    println(suma.historialSumas)
}

fun imprimirNombre(nombre:String):Unit{
    println("nombre: ${nombre}");
}

fun calculaSueldo(
    sueldo:Double,//requerido
    tasa: Double=12.00,//valor opcional
    bonoEspecial: Double? = null//opcional nulo
):Double{
    if (bonoEspecial != null){
        return sueldo*tasa*bonoEspecial;
    }else{
        return sueldo*tasa;
    }
}

abstract class NumerosJava{
    protected val numeroUno: Int;
    private val numeroDos: Int;
    constructor(uno:Int,dos:Int){
        this.numeroDos = dos;
        this.numeroUno =uno;
    }
}

abstract class Numeros(// constructor primario
//uno : Int //parametro
//public var uno:Int //propiedad de la clase publica
    protected val numeroUno: Int,
    protected val numeroDos: Int
){
    init {
        numeroUno
        numeroDos
        println("Inicialisados");
    }
}

class suma(uno: Int, dos: Int):Numeros(uno,dos){
    init {
        this.numeroUno
        this.numeroDos
    }

    constructor(uno: Int?,dos: Int):this(if (uno==null) 0  else uno,dos){

    }
    constructor(uno: Int,dos: Int?):this(uno,if (dos==null) 0  else dos){

    }
    constructor(uno: Int?,dos: Int?):this(if (uno==null) 0  else uno,if (dos==null) 0  else dos){

    }

    fun sumar():Int{
        val total = numeroDos+numeroUno;
        agregarHistorial(total);
        return total;
    }
    companion object {
        val pi = 3.14; //Suma.pi = 3.14
        val historialSumas =  arrayListOf<Int>();
        fun agregarHistorial (valorNuevaSuma :Int){
            historialSumas.add(valorNuevaSuma);
        }
    }

    }

