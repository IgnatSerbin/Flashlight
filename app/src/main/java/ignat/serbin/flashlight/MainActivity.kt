package ignat.serbin.flashlight

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    var CAMERA_REQUEST:Int = 123


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var flash_status = false

        val btn_flash = findViewById<Button>(R.id.flash_btn)
        val status_info = findViewById<TextView>(R.id.msg_status)

        btn_flash.setOnClickListener {

                if (flash_status == false){
                    flash_on()
                    status_info.setText("Фонарик включен")
                    flash_status = true
                } else {
                    flash_off()
                    status_info.setText("Фонарик выключен")
                    flash_status = false
                }
        }

    }

     private fun flash_off() {
        val myCameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        try {
            val CameraId: String = myCameraManager.cameraIdList[0]
            myCameraManager.setTorchMode(CameraId, false)
        } catch (e: CameraAccessException){}
    }

     private fun flash_on() {
       val myCameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        try {
            val CameraId: String = myCameraManager.cameraIdList[0]
            myCameraManager.setTorchMode(CameraId, true)
        } catch (e: CameraAccessException){}
    }

    fun sendMsg(msg: String){

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    }
}