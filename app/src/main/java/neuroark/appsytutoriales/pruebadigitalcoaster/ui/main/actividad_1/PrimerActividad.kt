package neuroark.appsytutoriales.pruebadigitalcoaster.ui.main.actividad_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import neuroark.appsytutoriales.pruebadigitalcoaster.R

class PrimerActividad : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_primer_actividad, container, false)
        if (savedInstanceState == null) {
            var fragTxt = childFragmentManager.findFragmentByTag("fragText")
            var fragNum = childFragmentManager.findFragmentByTag("fragNum")
            if (fragTxt == null) fragTxt = TextosFragment.newInstance()
            if (fragNum == null) fragNum = NumerosFragment.newInstance()
            childFragmentManager.beginTransaction()
                .replace(R.id.containerTexto, fragTxt)
                .replace(R.id.containerNumero, fragNum)
                .commitNow()
        }
        return v
    }
}