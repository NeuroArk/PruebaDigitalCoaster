package neuroark.appsytutoriales.pruebadigitalcoaster.herramientas

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import neuroark.appsytutoriales.pruebadigitalcoaster.R

class ManejadorPermisos(val act: Fragment) {
    private val requestPermissionLauncher: ActivityResultLauncher<String> = act.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.i("Permission: ", "Granted")
        } else {
            Log.i("Permission: ", "Denied")
        }
    }
    private var lanzado = false
    fun pedirPermisos(view: View, permisos: String = Manifest.permission.CAMERA) {
        val msgOk = act.getString(R.string.permisos_concedidos)
        val msgNo = act.getString(R.string.permisos_denegados)
        when {
            ContextCompat.checkSelfPermission(act.requireContext(), permisos)
                == PackageManager.PERMISSION_GRANTED -> {
                if(lanzado) Snackbar.make(view, msgOk, Snackbar.LENGTH_LONG).show()
            }
            ContextCompat.checkSelfPermission(act.requireContext(), permisos)
                    == PackageManager.PERMISSION_DENIED -> {
                Snackbar.make(view, msgNo, Snackbar.LENGTH_LONG).show()
                if(!lanzado){requestPermissionLauncher.launch(permisos)
                    lanzado = true
                }
                lanzado = false
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                act.requireActivity(), permisos) -> {
                requestPermissionLauncher.launch(permisos)
                lanzado = true
            }
            else -> { requestPermissionLauncher.launch(permisos)
                lanzado = true
            }
        }
    }
}