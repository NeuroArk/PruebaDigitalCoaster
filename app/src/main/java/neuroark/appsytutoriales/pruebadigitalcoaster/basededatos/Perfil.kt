package neuroark.appsytutoriales.pruebadigitalcoaster.basededatos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Perfil(@PrimaryKey(autoGenerate = true) var id:Int, var nombre:String, var cumpleanos:String, var celular:String, var instagram:String, var correo:String, var contrasena:String)
