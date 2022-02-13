package neuroark.appsytutoriales.pruebadigitalcoaster.lectorcodigos.componentes

import android.app.Activity
import android.content.Context
import android.content.Context.CAMERA_SERVICE
import android.graphics.Bitmap
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.media.Image
import android.net.Uri
import android.os.Build
import android.util.SparseIntArray
import android.view.Surface
import androidx.annotation.RequiresApi
import com.google.mlkit.vision.common.InputImage
import java.io.IOException
import java.nio.ByteBuffer

class MlKitVisionImage {
    /**
     * Get the angle by which an image must be rotated given the device's current
     * orientation.
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Throws(CameraAccessException::class)
    fun getRotationCompensation(activity: Activity, isFrontFacing: Boolean): Int {
        // Get the device's current rotation relative to its "native" orientation.
        // Then, from the ORIENTATIONS table, look up the angle the image must be
        // rotated to compensate for the device's rotation.
        val deviceRotation: Int
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            deviceRotation = activity.getDisplay()?.rotation!!
        }else{
            @Suppress("DEPRECATION")
            deviceRotation = activity.windowManager.defaultDisplay.rotation
        }
        var rotationCompensation = ORIENTATIONS.get(deviceRotation)

        // Get the device's sensor orientation.
        val cameraManager = activity.getSystemService(CAMERA_SERVICE) as CameraManager
        MY_CAMERA_ID = cameraManager.cameraIdList[0]
        val sensorOrientation = cameraManager
            .getCameraCharacteristics(MY_CAMERA_ID)
            .get(CameraCharacteristics.SENSOR_ORIENTATION)!!
        if (isFrontFacing) {
            rotationCompensation = (sensorOrientation + rotationCompensation) % 360
        } else { // back-facing
            rotationCompensation = (sensorOrientation - rotationCompensation + 360) % 360
        }
        return rotationCompensation
    }

    fun crearInputImagenConUri(context: Context, uri: Uri): InputImage? {
        val image: InputImage
        try {
            image = InputImage.fromFilePath(context, uri)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
    fun crearInputImagenConBiteBuffer(byteBuffer:ByteBuffer, rotationDegrees:Int): InputImage? {
        return InputImage.fromByteBuffer(
            byteBuffer,
            /* image width */ 480,
            /* image height */ 360,
            rotationDegrees,
            InputImage.IMAGE_FORMAT_NV21 // or IMAGE_FORMAT_YV12
        )
    }
    fun crearInputImagenConBiteArray(byteArray:ByteArray, rotationDegrees:Int): InputImage? {
        return InputImage.fromByteArray(
            byteArray,
            /* image width */ 480,
            /* image height */ 360,
            rotationDegrees,
            InputImage.IMAGE_FORMAT_NV21 // or IMAGE_FORMAT_YV12
        )
    }
    fun crearInputImagenConBitmap(bitmap: Bitmap, rotationDegree:Int):InputImage{
        return InputImage.fromBitmap(bitmap, rotationDegree)
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    fun crearImagenInputConMediaImage(imagen: Image, rotation: Int):InputImage {
        return InputImage.fromMediaImage(imagen, rotation)
    }

    companion object {

        private val TAG = "MLKIT"
        private var MY_CAMERA_ID = "0"

        // [START camera_orientations]
        private val ORIENTATIONS = SparseIntArray()

        init {
            ORIENTATIONS.append(Surface.ROTATION_0, 0)
            ORIENTATIONS.append(Surface.ROTATION_90, 90)
            ORIENTATIONS.append(Surface.ROTATION_180, 180)
            ORIENTATIONS.append(Surface.ROTATION_270, 270)
        }
        // [END camera_orientations]
    }
}