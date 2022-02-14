package neuroark.appsytutoriales.pruebadigitalcoaster.ui.main.actividad_3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import neuroark.appsytutoriales.pruebadigitalcoaster.basededatos.Perfil
import neuroark.appsytutoriales.pruebadigitalcoaster.basededatos.PerfilRepository

class TerceraActividadModel (val repositorio: PerfilRepository): ViewModel() {
    val perfil:MutableLiveData<Perfil> = MutableLiveData()
    init{
        if(repositorio.perfil == null)
            perfil.value = Perfil(1,"Anna Avetisyan","23/04/1995","8181234567","soydrossrotzank","info@aplusdesign.co","")
        else perfil.postValue(repositorio.perfil)
    }
    fun actualizarPerfil() = viewModelScope.launch {
        repositorio.modificar(perfil.value!!)
    }
    fun cargarPerfil() = viewModelScope.launch {
        perfil.postValue(repositorio.cargar())
    }
}