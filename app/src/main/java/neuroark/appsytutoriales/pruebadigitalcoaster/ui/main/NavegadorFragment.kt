package neuroark.appsytutoriales.pruebadigitalcoaster.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import neuroark.appsytutoriales.pruebadigitalcoaster.R

class NavegadorFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_navegador, container, false)
        val navController = Navigation.findNavController(
            requireActivity(),
            R.id.nav_host_fragment
        )
        val btnAct1 = v.findViewById<View>(R.id.button_nav_act1)
        val btnAct2 = v.findViewById<View>(R.id.button_nav_act2)
        val btnAct3 = v.findViewById<View>(R.id.button_nav_act3)
        val btnAct4 = v.findViewById<View>(R.id.button_nav_act4)
        btnAct1.setOnClickListener {
            navController.navigate(R.id.action_navegadorFragment_to_primerActividad)
        }
        btnAct2.setOnClickListener {
            navController.navigate(R.id.action_navegadorFragment_to_segundaActividad)
        }
        btnAct3.setOnClickListener {
            navController.navigate(R.id.action_navegadorFragment_to_terceraActividad)
        }
        btnAct4.setOnClickListener {
            navController.navigate(R.id.action_navegadorFragment_to_cuartaActividad)
        }
        return v
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NavegadorFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}