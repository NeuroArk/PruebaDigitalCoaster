package neuroark.appsytutoriales.pruebadigitalcoaster.ui.main.actividad_4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CuartaActividadModel : ViewModel() {
    val resultado = MutableLiveData<String>()
    init {
        resultado.postValue("")
    }
}