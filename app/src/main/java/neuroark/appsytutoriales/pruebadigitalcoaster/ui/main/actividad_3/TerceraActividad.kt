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
import neuroark.appsytutoriales.pruebadigitalcoaster.R
import neuroark.appsytutoriales.pruebadigitalcoaster.basededatos.BaseDeDatosDAO
import neuroark.appsytutoriales.pruebadigitalcoaster.basededatos.PerfilRepository
import neuroark.appsytutoriales.pruebadigitalcoaster.databinding.FragmentTerceraActividadBinding
import neuroark.appsytutoriales.pruebadigitalcoaster.herramientas.ManejadorPermisos
import java.util.jar.Manifest

class TerceraActividad : Fragment() {
    companion object {
        fun newInstance() = TerceraActividad()
    }
    private lateinit var binding: FragmentTerceraActividadBinding
    val manejadorPermisos = ManejadorPermisos(this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTerceraActividadBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        val navController = Navigation.findNavController(
            requireActivity(),
            R.id.nav_host_fragment
        )
        binding.act3BtnEditar.setOnClickListener{
            navController.navigate(R.id.action_terceraActividad_to_terceraActividadEditar)
        }
        binding.act3LayoutInstagram.setOnClickListener{
            abrirInstagram()
        }
        binding.act3LayoutCelular.setOnClickListener{
            llamarPorTelefono()
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
    fun llamarPorTelefono(){
        val permiso = android.Manifest.permission.CALL_PHONE
        if(!manejadorPermisos.tienePermisos(permiso)) {manejadorPermisos.pedirPermisos(binding.root,permiso)}
            else{ val dial = "tel:${binding.act3TxtCelular.text}"
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)))
            }
    }
    fun abrirInstagram(){
        val url="http://instagram.com/_u/${binding.viewModel!!.perfil.value!!.instagram}"
        val uri = Uri.parse(url)
        val insta = Intent(Intent.ACTION_VIEW, uri)
        insta.setPackage("com.instagram.android")
        if (estaInstalada(requireContext(), insta)) startActivity(insta)
        else startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    private fun estaInstalada(ctx: Context, intent:Intent):Boolean {
        val list:List<ResolveInfo> = ctx.packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.isNotEmpty()
    }
}