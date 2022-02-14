package neuroark.appsytutoriales.pruebadigitalcoaster.ui.main.actividad_2

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import neuroark.appsytutoriales.pruebadigitalcoaster.R

class SegundaActividad : Fragment() {

    companion object {
        fun newInstance() = SegundaActividad()
    }

    private lateinit var viewModel: SegundaActividadModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_segunda_actividad, container, false)
        val navController = Navigation.findNavController(
            requireActivity(),
            R.id.nav_host_fragment
        )
        v.findViewById<Button>(R.id.act2_btn_regresar).setOnClickListener{
            navController.navigate(R.id.action_segundaActividad_to_navegadorFragment)
        }
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SegundaActividadModel::class.java)
        // TODO: Use the ViewModel
    }

}