package neuroark.appsytutoriales.pruebadigitalcoaster.ui.main.actividad_1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NumerosModel : ViewModel() {
    val integerArray = MutableLiveData<Array<Int>>()
    val textoListaAnterior = MutableLiveData<String>()
    val textoListaNueva = MutableLiveData<String>()
    val editNumeroAnadir = MutableLiveData<Int>()
    val editQueSustituir = MutableLiveData<Int>()
    val editConQueSustituir = MutableLiveData<Int>()
    init {
        integerArray.value= arrayOf(1,2,1)
        val tmp = integerArray.value.contentToString()
        textoListaNueva.value = tmp
        textoListaAnterior.value = tmp
        editNumeroAnadir.value = 0
        editQueSustituir.value = 0
        editConQueSustituir.value = 0
    }
    fun anadirEntero(num:Int){
        val tmp: MutableList<Int> = integerArray.value!!.toMutableList()
        tmp.add(num)
        val arr = tmp.toTypedArray()
        integerArray.value = arr
        textoListaAnterior.value = textoListaNueva.value.toString()
        textoListaNueva.value = integerArray.value.contentToString()
    }
    fun sustituir(){
        val tam  = integerArray.value!!.size
        if(integerArray.value!!.contains(editQueSustituir.value!!))
            textoListaAnterior.value = textoListaNueva.value.toString()
            for (i in 0 until tam){
                if(integerArray.value!![i] == editQueSustituir.value!!)
                    integerArray.value!![i] = editConQueSustituir.value!!}
            textoListaNueva.value = integerArray.value.contentToString()
    }
}