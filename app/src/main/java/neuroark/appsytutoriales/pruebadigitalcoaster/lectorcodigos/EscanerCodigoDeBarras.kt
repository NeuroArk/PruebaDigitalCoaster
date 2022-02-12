package neuroark.appsytutoriales.pruebadigitalcoaster.lectorcodigos
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import neuroark.appsytutoriales.pruebadigitalcoaster.lectorcodigos.datos.CodBarEmail
import neuroark.appsytutoriales.pruebadigitalcoaster.lectorcodigos.datos.CodBarError
import neuroark.appsytutoriales.pruebadigitalcoaster.lectorcodigos.datos.CodBarUrl
import neuroark.appsytutoriales.pruebadigitalcoaster.lectorcodigos.datos.ResultadoCodBar

class EscanerCodigoDeBarras{
    private fun <T> scanBarcodes(image: InputImage): T where T: ResultadoCodBar {
        var respuesta: T = CodBarError(-1000) as T
        // [START set_detector_options]
        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_AZTEC,
                Barcode.FORMAT_QR_CODE,
                Barcode.FORMAT_AZTEC)
            .build()
        // [END set_detector_options]

        // [START get_detector]
        val scanner = BarcodeScanning.getClient()
        // Or, to specify the formats to recognize:
        // val scanner = BarcodeScanning.getClient(options)
        // [END get_detector]

        // [START run_detector]
        val result = scanner.process(image)
            .addOnSuccessListener { barcodes ->
                // Task completed successfully
                // [START_EXCLUDE]
                // [START get_barcodes]
                for (barcode in barcodes) {
                    val bounds = barcode.boundingBox
                    val corners = barcode.cornerPoints
                    val rawValue = barcode.rawValue
                    val valueType = barcode.valueType
                    // See API reference for complete list of supported types
                    when (valueType) {
                        Barcode.TYPE_WIFI -> {
                            val ssid = barcode.wifi!!.ssid
                            val contrasena = barcode.wifi!!.password
                            val tipoEncripcion = barcode.wifi!!.encryptionType
                        }
                        Barcode.TYPE_URL -> {
                            val titulo = barcode.url!!.title
                            val url = barcode.url!!.url
                            respuesta = CodBarUrl(Barcode.TYPE_EMAIL,titulo!!,url!!) as T
                        }
                        Barcode.TYPE_CALENDAR_EVENT -> {
                            val inicia = barcode.calendarEvent!!.start
                            val termina = barcode.calendarEvent!!.end
                            val descripcion = barcode.calendarEvent!!.description
                            val lugar = barcode.calendarEvent!!.location
                            val organizador = barcode.calendarEvent!!.organizer
                            val estado = barcode.calendarEvent!!.status
                            val resumen = barcode.calendarEvent!!.summary
                        }
                        Barcode.TYPE_EMAIL -> {
                            val tipo = barcode.email!!.type
                            val asunto = barcode.email!!.subject
                            val cuerpo = barcode.email!!.body
                            val direccion = barcode.email!!.address
                            respuesta = CodBarEmail(Barcode.TYPE_EMAIL,tipo,direccion!!,asunto!!,cuerpo!!) as T
                        }
                        Barcode.TYPE_GEO -> {
                            val latitud = barcode.geoPoint!!.lat
                            val longitud = barcode.geoPoint!!.lng
                        }
                        Barcode.TYPE_CONTACT_INFO -> {
                            val titulo = barcode.contactInfo!!.title
                            val urls = barcode.contactInfo!!.urls
                            val nombre = barcode.contactInfo!!.name
                            val organizacion = barcode.contactInfo!!.organization
                            val telefonos = barcode.contactInfo!!.phones
                            val direcciones = barcode.contactInfo!!.addresses
                            val emails = barcode.contactInfo!!.emails
                        }
                        Barcode.TYPE_ISBN -> {
                            val inicia = barcode.rawValue!!.toString()
                        }
                        Barcode.TYPE_TEXT -> {
                            val inicia = barcode.rawValue!!.toString()
                        }
                        Barcode.TYPE_PRODUCT -> {
                            val producto = barcode.rawValue!!.toString()
                        }
                        Barcode.TYPE_DRIVER_LICENSE -> {
                            val nombres = barcode.driverLicense!!.firstName
                            val coletillasNombres = barcode.driverLicense!!.middleName
                            val apellidos = barcode.driverLicense!!.lastName
                            val genero = barcode.driverLicense!!.gender
                            val fechaDeNacimiento = barcode.driverLicense!!.birthDate
                            val dirCiudad = barcode.driverLicense!!.addressCity
                            val dirEstado = barcode.driverLicense!!.addressState
                            val dirCalle = barcode.driverLicense!!.addressStreet
                            val dirCodigoPostal = barcode.driverLicense!!.addressZip
                            val tipoDocumento = barcode.driverLicense!!.documentType
                            val fechaDeEmision = barcode.driverLicense!!.issueDate
                            val fechaDeExpiracion = barcode.driverLicense!!.expiryDate
                            val paisEmisor = barcode.driverLicense!!.issuingCountry
                            val numeroDeLicencia = barcode.driverLicense!!.licenseNumber
                        }
                    }
                }
                // [END get_barcodes]
                // [END_EXCLUDE]
            }
            .addOnFailureListener {
                // Task failed with an exception
                // ...
            }
        // [END run_detector]
        return respuesta
    }
}