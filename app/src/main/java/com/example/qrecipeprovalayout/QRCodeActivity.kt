package com.example.qrecipeprovalayout

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import kotlinx.android.synthetic.main.activity_qrcode.*


class QRCodeActivity : AppCompatActivity() {
    private lateinit var codeScanner: CodeScanner
    val MY_CAMERA_PERMISSION_REQUEST = 1111
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode)


        //initialize code scanner
        codeScanner = CodeScanner(this, codeScannerView)
        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS
        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.scanMode = ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled = true
        codeScanner.isFlashEnabled = false

        //callback
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                val intent = Intent(this,RecipeActivity::class.java)
                startActivity(intent)
            }
        }

        //error callback
        codeScanner.errorCallback = ErrorCallback {
            runOnUiThread {
                Toast.makeText(this, "Scan Error: ${it.message}", Toast.LENGTH_LONG).show()
            }
        }

        //check permission and start code scanner if permission is granted
        checkPermission()
        Log.v(TAG, "onCreate finish")
    }

    override fun onResume() {
        super.onResume()
        Log.v(TAG, "onResume")
        //check permission and start code scanner if permission is granted
        checkPermission()
    }

    override fun onPause() {
        Log.v(TAG, "onPause")

        //release resources while Activity is not displayed
        codeScanner.releaseResources()
        super.onPause()
    }

    fun checkPermission() {
        Log.v(TAG, "checkPermission")

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //ask permission to the user
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), MY_CAMERA_PERMISSION_REQUEST)
        }else
            codeScanner.startPreview()
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        Log.v(TAG, "onRequestPermissionsResult")

        if(requestCode == MY_CAMERA_PERMISSION_REQUEST && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            codeScanner.startPreview()
        else
            Toast.makeText(this, "Cannot scan until you give the camera permission", Toast.LENGTH_LONG).show()
    }

}