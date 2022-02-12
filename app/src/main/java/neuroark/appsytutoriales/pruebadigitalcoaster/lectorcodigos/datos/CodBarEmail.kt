package neuroark.appsytutoriales.pruebadigitalcoaster.lectorcodigos.datos

data class CodBarEmail(override var tipoResultado:Int, var tipoMail:Int, var direccion:String, var asunto:String, var cuerpo:String): ResultadoCodBar(tipoResultado)
