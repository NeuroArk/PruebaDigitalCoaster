package neuroark.appsytutoriales.pruebadigitalcoaster.basededatos
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface PerfilDao {
    @Query("SELECT * FROM Perfil")
    fun cargarTodos(): LiveData<List<Perfil>>
    @Query("SELECT * FROM Perfil WHERE id = :id LIMIT 1")
    fun cargarConId(id:Int=1): Perfil?
    @Query("SELECT * FROM Perfil WHERE id IN (:perfilesIds)")
    fun cargarTodosConIds(perfilesIds: IntArray): LiveData<List<Perfil>>
    @Query("SELECT * FROM Perfil WHERE nombre = :nombre LIMIT 1")
    fun buscarConNombre(nombre: String): LiveData<List<Perfil>>
    @Query("SELECT * FROM Perfil WHERE nombre LIKE :nombre")
    fun buscarConNombres(nombre: String): LiveData<List<Perfil>>
    @Insert (onConflict = REPLACE)
    fun insertar(vararg perfiles: Perfil)
    @Delete
    fun eliminar(vararg perfiles: Perfil)
    @Update
    fun modificar(vararg perfiles: Perfil)
}