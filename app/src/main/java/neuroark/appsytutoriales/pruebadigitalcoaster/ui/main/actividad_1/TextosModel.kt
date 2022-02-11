package neuroark.appsytutoriales.pruebadigitalcoaster.ui.main.actividad_1
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextosModel: ViewModel() {
    val stringArray = MutableLiveData<Array<String>>()
    val textoListaCompleta = MutableLiveData<String>()
    val textoListaGrandes = MutableLiveData<String>()
    val editTexto = MutableLiveData<String>()
    init {
        stringArray.value= arrayOf("aba", "aa", "ad", "vcd", "aba","bba")
        textoListaGrandes.value = sublistarGrandes()
        textoListaCompleta.value = stringArray.value.contentToString()
        editTexto.value = ""
    }
   fun sublistarGrandes():String{
        val tam  = stringArray.value!!.size
        val max = stringArray.value!!.maxByOrNull { it.length }
        val lista = ArrayList<String>()
       for (i in 0 until tam)
           if (stringArray.value!![i].length>= max!!.length) lista.add(stringArray.value!![i])
        return lista.toString()
    }
    fun anadirTexto(txt:String){
        val tmp: MutableList<String> = stringArray.value!!.toMutableList()
        tmp.add(txt)
        val arr = tmp.toTypedArray()
        stringArray.value = arr
        textoListaGrandes.value = sublistarGrandes()
        textoListaCompleta.value = stringArray.value.contentToString()
    }
}


