package neuroark.appsytutoriales.pruebadigitalcoaster.ui.main.actividad_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import neuroark.appsytutoriales.pruebadigitalcoaster.databinding.FragmentTextosBinding

class TextosFragment : Fragment() {
    companion object {
        fun newInstance() = TextosFragment()
    }
    private lateinit var viewModel: TextosModel
    private lateinit var binding: FragmentTextosBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTextosBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TextosModel::class.java)
        binding.viewModel=viewModel
        binding.botonTexto.setOnClickListener {
            viewModel.anadirTexto(binding.editTexto.text.toString())
            binding.editTexto.setText("")
        }
    }

}