package neuroark.appsytutoriales.pruebadigitalcoaster.lectorcodigos.componentes
import android.util.Log
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

class EscanerCodigoDeBarras{
    interface ResultadoEscaneo{
        fun cbrWifi(wifi:Barcode.WiFi)
        fun cbrUrl(url:Barcode.UrlBookmark)
//        fun cbrNombrePersona(nombres: Barcode.PersonName)
//        fun cbrDireccion(direccion: Barcode.Address)
//        fun cbrTelefono(telefono: Barcode.Phone)
//        fun cbrSms(sms: Barcode.Sms)
        fun cbrEmail(email:Barcode.Email)
        fun cbrContacto(contacto: Barcode.ContactInfo)
        fun cbrLicenciaConducir(licencia: Barcode.DriverLicense)
        fun cbrGeoLocacion(locacion: Barcode.GeoPoint)
        fun cbrEvCalendario(evCal: Barcode.CalendarEvent)
//      fun cbrFechaHora(cal: Barcode.CalendarDateTime)
        fun cbrRaw(raw: String)
    }
    fun scanBarcodes(inputImage: InputImage, imageProxy:ImageProxy, resEscaneo: ResultadoEscaneo) {
        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
            .build()
        val scanner = BarcodeScanning.getClient(options)
        val result = scanner.process(inputImage).addOnSuccessListener { barcodes ->
            procesarCodigosDeBarras(barcodes, resEscaneo)
            }
            .addOnFailureListener {
            Log.d("ResultadoScan","Error al escanear codigo de barras")
            }.addOnCompleteListener{
                imageProxy.close()
            Log.d("ResultadoScan","Escaneo de codigo de barras completo")
            }
    }

    private fun procesarCodigosDeBarras(barcodes: List<Barcode>, resEscaneo: ResultadoEscaneo) {
        Log.d("ResultadoScan","Escaneo de codigo de barras exitoso")
        for (barcode in barcodes) {
            val bounds = barcode.boundingBox
            val corners = barcode.cornerPoints
            val rawValue = barcode.rawValue
            when (barcode.valueType) {
                Barcode.TYPE_WIFI -> { resEscaneo.cbrWifi(barcode.wifi!!) }
                Barcode.TYPE_URL -> { resEscaneo.cbrUrl(barcode.url!!) }
                Barcode.TYPE_EMAIL -> { resEscaneo.cbrEmail(barcode.email!!) }
                Barcode.TYPE_CONTACT_INFO -> { resEscaneo.cbrContacto(barcode.contactInfo!!) }
                Barcode.TYPE_DRIVER_LICENSE -> { resEscaneo.cbrLicenciaConducir(barcode.driverLicense!!) }
                Barcode.TYPE_GEO -> { resEscaneo.cbrGeoLocacion(barcode.geoPoint!!) }
                Barcode.TYPE_CALENDAR_EVENT -> { resEscaneo.cbrEvCalendario(barcode.calendarEvent!!) }
                Barcode.TYPE_ISBN -> { resEscaneo.cbrRaw(barcode.rawValue!!) }
                Barcode.TYPE_TEXT -> { resEscaneo.cbrRaw(barcode.rawValue!!) }
                Barcode.TYPE_PRODUCT -> { resEscaneo.cbrRaw(barcode.rawValue!!) }
            }
        }
    }

}