package neuroark.appsytutoriales.pruebadigitalcoaster.lectorcodigos

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import neuroark.appsytutoriales.pruebadigitalcoaster.lectorcodigos.componentes.EscanerCodigoDeBarras
import neuroark.appsytutoriales.pruebadigitalcoaster.lectorcodigos.componentes.MlKitVisionImage

class AnalizadorDeCodigos(private var resCan:EscanerCodigoDeBarras.ResultadoEscaneo): ImageAnalysis.Analyzer {
    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val escaner = EscanerCodigoDeBarras()
        val imagen = imageProxy.image
        val vision = MlKitVisionImage()
        if (imagen != null) {
          val inputImage = vision.crearImagenInputConMediaImage(imagen, imageProxy.imageInfo.rotationDegrees)
          escaner.scanBarcodes(inputImage,imageProxy,resCan)
        }
    }

}