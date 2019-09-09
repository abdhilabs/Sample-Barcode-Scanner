package com.abdhilabs.barcodescanner

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Penyimpanan sementara
    var scannedResult = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnScan.setOnClickListener {
            //will launch new Intent where the actual barcode scanning process is happening
            run {
                IntentIntegrator(this@MainActivity).initiateScan()
            }
        }
    }

    //Untuk menangkap hasil Scannya
    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result: IntentResult? =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result != null) {
            if (result.contents != null) {
                scannedResult = result.contents
                txtValue.text = scannedResult
            } else {
                txtValue.text = "Scan Failed"
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    /**
     * @onSaveInstanceState & @onRestoreInstanceState
     * adalah untuk menghandle saat rotasi layar
     */

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putString("scannedResult", scannedResult)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.let {
            scannedResult = it.getString("scannedResult")!!
            txtValue.text = scannedResult
        }
    }
}
