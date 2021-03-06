package neuroark.appsytutoriales.pruebadigitalcoaster.ui.main.actividad_3

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import neuroark.appsytutoriales.pruebadigitalcoaster.R
import neuroark.appsytutoriales.pruebadigitalcoaster.basededatos.BaseDeDatosDAO
import neuroark.appsytutoriales.pruebadigitalcoaster.basededatos.Perfil
import neuroark.appsytutoriales.pruebadigitalcoaster.basededatos.PerfilRepository
import neuroark.appsytutoriales.pruebadigitalcoaster.databinding.FragmentTerceraActividadBinding
import neuroark.appsytutoriales.pruebadigitalcoaster.databinding.FragmentTerceraActividadEditarBinding

class TerceraActividadEditar : Fragment() {
    companion object {
        fun newInstance() = TerceraActividadEditar()
    }
//    private lateinit var viewModel: TerceraActividadModel
    private lateinit var binding: FragmentTerceraActividadEditarBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTerceraActividadEditarBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        val navController = Navigation.findNavController(
            requireActivity(),
            R.id.nav_host_fragment
        )
        binding.act3EditarBtnEditar.setOnClickListener{
            binding.viewModel!!.actualizarPerfil(binding.root)
        }
        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel:TerceraActividadModel by activityViewModels()
        viewModel.repositorio = PerfilRepository(requireActivity().applicationContext)
        binding.viewModel = viewModel
        viewModel.cargarPerfil()
    }
}