package neuroark.appsytutoriales.pruebadigitalcoaster.ui.main.actividad_4

import android.Manifest
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.camera2.internal.annotation.CameraExecutor
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.snackbar.Snackbar
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.common.Barcode
import neuroark.appsytutoriales.pruebadigitalcoaster.R
import neuroark.appsytutoriales.pruebadigitalcoaster.camara.CamaraManejador
import neuroark.appsytutoriales.pruebadigitalcoaster.databinding.FragmentCuartaActividadBinding
import neuroark.appsytutoriales.pruebadigitalcoaster.herramientas.ManejadorPermisos
import neuroark.appsytutoriales.pruebadigitalcoaster.lectorcodigos.componentes.EscanerCodigoDeBarras
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CuartaActividad : Fragment(), EscanerCodigoDeBarras.ResultadoEscaneo {
    companion object {
        fun newInstance() = CuartaActividad()
    }
    private lateinit var viewModel: CuartaActividadModel
    private lateinit var binding: FragmentCuartaActividadBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCuartaActividadBinding.inflate(layoutInflater,container,false)
        binding.lifecycleOwner = this
        val manejadorPermisos = ManejadorPermisos(this)
        val camaraManejador = CamaraManejador(this, binding.act4CamaraPreview,false,this)
        binding.act4CamaraPreview.setOnClickListener{
            manejadorPermisos.pedirPermisos(binding.root)
        }
        binding.act4BtnCapturar.setOnClickListener{
            manejadorPermisos.pedirPermisos(binding.root,Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CuartaActividadModel::class.java)
       binding.viewModel=viewModel
    }
    override fun cbrWifi(wifi: Barcode.WiFi) {
        val texto = "Wifi:\n\npassword:${wifi.password}\nTipo encripción:${wifi.encryptionType}\nSsid:${wifi.ssid}"
        if(texto!==viewModel.resultado.value){viewModel.resultado.value=texto}
    }
    override fun cbrUrl(url: Barcode.UrlBookmark) {
        val texto = "Url:\n\nNombre:${url.title}\nUrl:${url.url}"
        if(texto!==viewModel.resultado.value){viewModel.resultado.value=texto}
    }
    override fun cbrEmail(email: Barcode.Email) {
        val texto = "Email:\n\nTipo:${email.type}\nDireccion:${email.address}\nAsunto:${email.subject}\nContenido:${email.body}"
        if(texto!==viewModel.resultado.value){viewModel.resultado.value=texto}
    }
    override fun cbrContacto(contacto: Barcode.ContactInfo) {
        val texto = "Contacto:\n\nTitulo:${contacto.title}\nOrganización:${contacto.organization}\n" +
                "Nombre:${contacto.name!!.first} ${contacto.name!!.middle}${contacto.name!!.last}\n" +
                "Direcciones:${contacto.addresses}\nEmails:${contacto.emails}\n" +
                "Telefonos:${contacto.phones}\nUrls:${contacto.urls}}"
        if(texto!==viewModel.resultado.value){viewModel.resultado.value=texto}
    }
    override fun cbrLicenciaConducir(licencia: Barcode.DriverLicense) {
        val texto = "Licencia de conducir:\n\nNombre:${licencia.firstName} ${licencia.middleName} ${licencia.lastName}"+
                "Genero:${licencia.gender}\nFecha de nacimiento:${licencia.birthDate}"+
                "Numero de licencia:${licencia.licenseNumber}\nTipo de documento:${licencia.documentType}" +
                "\nFecha de emisión:${licencia.issueDate}\nFecha de expiracion:\n${licencia.expiryDate}\nPais emisor:${licencia.issuingCountry}"
                "Genero:${licencia.gender}\nFecha de nacimiento:${licencia.birthDate}"
        if(texto!==viewModel.resultado.value){viewModel.resultado.value=texto}
    }
    override fun cbrGeoLocacion(locacion: Barcode.GeoPoint) {
        val texto = "Geolocalización:\n\nLatitud:${locacion.lat}\nLongitud:${locacion.lng}"
        if(texto!==viewModel.resultado.value){viewModel.resultado.value=texto}
    }
    override fun cbrEvCalendario(evCal: Barcode.CalendarEvent) {
        val texto = "Evento:\n\nEstatus:${evCal.status}\nOrganizador:${evCal.organizer}\n" +
                "Descripcion:${evCal.description}\nLugar:${evCal.location}\n" +
                "Inicia:${evCal.start!!.day}/${evCal.start!!.month}/${evCal.start!!.year}" +
                "${evCal.start!!.hours}:${evCal.start!!.minutes}\n"+
                "Termina:${evCal.end!!.day}/${evCal.end!!.month}/${evCal.end!!.year}" +
                "${evCal.end!!.hours}:${evCal.end!!.minutes}\n" +
                "Resumen:${evCal.summary}"
        if(texto!==viewModel.resultado.value){viewModel.resultado.value=texto}
    }
    override fun cbrRaw(raw: String) {
        val texto = "Contenido: $raw"
        if(texto!==viewModel.resultado.value){viewModel.resultado.value=texto}
    }
}