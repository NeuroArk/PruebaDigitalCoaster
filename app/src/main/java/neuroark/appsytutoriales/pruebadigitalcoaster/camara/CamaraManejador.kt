package neuroark.appsytutoriales.pruebadigitalcoaster.camara

import android.content.ContentValues
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import neuroark.appsytutoriales.pruebadigitalcoaster.lectorcodigos.AnalizadorDeCodigos
import neuroark.appsytutoriales.pruebadigitalcoaster.lectorcodigos.componentes.EscanerCodigoDeBarras
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CamaraManejador (private val frag: Fragment, private val previewView:PreviewView, private val capturarImagen:Boolean=false, resCan: EscanerCodigoDeBarras.ResultadoEscaneo){
    lateinit var camara: Camera
    lateinit var imageCapture:ImageCapture
    private val cameraProviderFuture:ListenableFuture<ProcessCameraProvider> = ProcessCameraProvider.getInstance(frag.requireContext())
    val executorService: ExecutorService? = Executors.newSingleThreadExecutor()
    private val analizadorCoidgos:AnalizadorDeCodigos
    init{
        cameraProviderFuture.addListener(Runnable {
            bindPreview(cameraProviderFuture.get())
        }, ContextCompat.getMainExecutor(frag.requireContext()))
        analizadorCoidgos = AnalizadorDeCodigos(resCan)
    }
    private fun bindPreview(cameraProvider : ProcessCameraProvider) {
        Log.d("ResultadoScan","bindeando Preview...")
        val preview : Preview = Preview.Builder()
            .build()
        val cameraSelector : CameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()
        preview.setSurfaceProvider(this.previewView.getSurfaceProvider())
        cameraProvider.unbindAll()
        if(capturarImagen) initCapturadorImagen(cameraProvider,cameraSelector,preview)
            else initEscaner(cameraProvider,cameraSelector,preview)
    }
    private fun initEscaner(cameraProvider : ProcessCameraProvider, cameraSelector : CameraSelector, preview:Preview){
        val imageAnalysis = ImageAnalysis.Builder()
            // enable the following line if RGBA output is needed.
            // .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
            .setTargetResolution(Size(1280, 720))
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
        imageAnalysis.setAnalyzer(executorService!!,analizadorCoidgos)
        camara = cameraProvider.bindToLifecycle(frag as LifecycleOwner, cameraSelector, imageAnalysis, preview)
    }
    private fun initCapturadorImagen(cameraProvider : ProcessCameraProvider, cameraSelector : CameraSelector, preview:Preview){
        imageCapture = ImageCapture.Builder()
            .setTargetRotation(this.previewView.display.rotation)
            .build()
        camara = cameraProvider.bindToLifecycle(frag as LifecycleOwner, cameraSelector, preview, imageCapture)
    }


    private fun crearOpcionesTomarFoto():ImageCapture.OutputFileOptions{
        // Create time stamped name and MediaStore entry.
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
            }
        }
        // Create output options object which contains file + metadata
        return ImageCapture.OutputFileOptions
            .Builder(frag.requireContext().contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues)
            .build()
    }
    private fun crearAnalizadorDeImagen(){

    }
    ///////////////////// Controles camara /////////////////////
    fun zoom(){

    }
    fun tomarFoto() {
        imageCapture.takePicture(crearOpcionesTomarFoto(),
            ContextCompat.getMainExecutor(frag.requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(error: ImageCaptureException)
                {

                }
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {

                }
            })
    }
    ///////////////////// Herramientas /////////////////////////
    fun cambiarModoImplementacion(compatible:Boolean){
        if(compatible) previewView.implementationMode = PreviewView.ImplementationMode.COMPATIBLE
        else previewView.implementationMode = PreviewView.ImplementationMode.PERFORMANCE
    }
    fun cambiarModoImplementacion(modo:PreviewView.ImplementationMode = PreviewView.ImplementationMode.COMPATIBLE){
        previewView.implementationMode = modo
    }
    fun cambiarTipoEscalado(escala:PreviewView.ScaleType = PreviewView.ScaleType.FIT_CENTER){
        previewView.scaleType = escala
    }
    companion object{
        const val FILENAME_FORMAT = "png"
    }
}