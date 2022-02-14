package neuroark.appsytutoriales.pruebadigitalcoaster.basededatos

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import neuroark.appsytutoriales.pruebadigitalcoaster.R
import java.util.concurrent.Executors

class PerfilRepository (appContext: Context){
    interface CargaPerfil{
        fun perfilCargado(per:Perfil?)
        fun perfilesCargados(vararg :Perfil?)
    }
    val database: BaseDeDatosDAO = BaseDeDatosDAO.getDatabase(appContext)
    val perfilDao: PerfilDao = database.perfilDao()

    fun insertarPerfil(v: View, vararg s: Perfil) {
        if (verificarPerfil(v, *s)) {
            val executor = Executors.newSingleThreadExecutor()
            executor.execute { perfilDao.insertar(*s) }
            Snackbar.make(v, R.string.hecho, Snackbar.LENGTH_LONG).show()
        }
    }
    fun eliminarPerfil(v: View, vararg s: Perfil) {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute { perfilDao.eliminar(*s) }
        Snackbar.make(v, R.string.hecho, Snackbar.LENGTH_LONG).show()
    }
    fun modificarPerfil(v: View, vararg s: Perfil) {
        if (verificarPerfil(v, *s)) {
            val executor = Executors.newSingleThreadExecutor()
            executor.execute { perfilDao.modificar(*s) }
            Snackbar.make(v, R.string.hecho, Snackbar.LENGTH_LONG).show()
        }
    }
    fun cargarPerfil(id:Int=1, cargaPerfil: CargaPerfil) {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute { cargaPerfil.perfilCargado(perfilDao.cargarConId(id)) }
    }
    fun cargarPerfiles(): LiveData<List<Perfil>> {
        return perfilDao.cargarTodos()
    }
    private fun verificarPerfil(v: View, vararg perfiles: Perfil): Boolean {
        for (son in perfiles) {
            if (son.nombre.trim() == "") {
                Snackbar.make(v, R.string.err_nombre, Snackbar.LENGTH_LONG).show()
                return false
            }
            if (son.contrasena.trim().length<=3) {
                Snackbar.make(v, R.string.err_contrasena, Snackbar.LENGTH_LONG).show()
                return false
            }
        }
        Snackbar.make(v, R.string.hecho, Snackbar.LENGTH_LONG).show()
        return true
    }
}