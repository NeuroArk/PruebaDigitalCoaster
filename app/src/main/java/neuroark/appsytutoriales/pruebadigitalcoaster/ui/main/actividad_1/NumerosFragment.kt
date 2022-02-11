package neuroark.appsytutoriales.pruebadigitalcoaster.ui.main.actividad_1

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import neuroark.appsytutoriales.pruebadigitalcoaster.databinding.FragmentNumerosBinding

class NumerosFragment : Fragment() {

    companion object {
        fun newInstance() = NumerosFragment()
    }

    private lateinit var viewModel: NumerosModel
    private lateinit var binding: FragmentNumerosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNumerosBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NumerosModel::class.java)
        binding.viewModel=viewModel
        binding.botonNumero.setOnClickListener {
            viewModel.anadirEntero(binding.editNumero.text.toString().toInt())
            binding.editNumero.setText("0")
        }
        binding.botonSustituir.setOnClickListener{
            viewModel.sustituir()
        }
    }

}