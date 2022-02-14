package neuroark.appsytutoriales.pruebadigitalcoaster.ui.main.actividad_3

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import neuroark.appsytutoriales.pruebadigitalcoaster.basededatos.Perfil
import neuroark.appsytutoriales.pruebadigitalcoaster.basededatos.PerfilRepository
import neuroark.appsytutoriales.pruebadigitalcoaster.basededatos.PerfilRepository.CargaPerfil

class TerceraActividadModel: ViewModel() {
    val perfil:MutableLiveData<Perfil> = MutableLiveData()
    lateinit var repositorio: PerfilRepository
    init{
        perfil.value = Perfil(1,"Anna Avetisyan","23/04/1995","8181234567","soydrossrotzank","info@aplusdesign.co","")
    }
    fun actualizarPerfil(vista: View) {
        repositorio.insertarPerfil(vista, perfil.value!!)
    }
    fun cargarPerfil(){
        repositorio.cargarPerfil(1,object:CargaPerfil{
            override fun perfilCargado(per: Perfil?) {
                Log.e("Errores","$perfil, $per")
                if(per!=null){ perfil.postValue(per!!) }
            }
            override fun perfilesCargados(vararg: Perfil?) {}
        })
    }
}