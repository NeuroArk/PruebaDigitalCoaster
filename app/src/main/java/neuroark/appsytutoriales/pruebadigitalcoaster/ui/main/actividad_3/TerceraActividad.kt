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
import neuroark.appsytutoriales.pruebadigitalcoaster.R
import neuroark.appsytutoriales.pruebadigitalcoaster.databinding.FragmentTerceraActividadBinding

class TerceraActividad : Fragment() {

    companion object {
        fun newInstance() = TerceraActividad()
    }

    private lateinit var viewModel: TerceraActividadModel
    private lateinit var binding: FragmentTerceraActividadBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTerceraActividadBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        binding.act3TxtInstagram.setOnClickListener{
            abrirInstagram()
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[TerceraActividadModel::class.java]
        binding.viewModel=viewModel
    }
    fun abrirInstagram(){
        val url="http://instagram.com/_u/${viewModel.perfil.value!!.instagram}"
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