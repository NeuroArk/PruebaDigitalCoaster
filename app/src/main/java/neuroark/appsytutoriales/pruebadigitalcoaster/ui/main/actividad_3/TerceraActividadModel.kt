package neuroark.appsytutoriales.pruebadigitalcoaster.ui.main.actividad_3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TerceraActividadModel : ViewModel() {
    val perfil:MutableLiveData<Perfil> = MutableLiveData()
    init{
        perfil.value = Perfil("Anna Avetisyan","23/04/1995","8181234567","dross","info@aplusdesign.co","")
    }
}