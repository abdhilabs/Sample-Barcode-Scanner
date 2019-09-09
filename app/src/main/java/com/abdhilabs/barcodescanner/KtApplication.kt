package com.abdhilabs.barcodescanner

import androidx.multidex.MultiDexApplication

/**
 * Karena kita mengkatifkan Mutidex, maka juga membuat Application yang mengextends MultidexApplication
 * Septerti di bawah
 */

class KtApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()


    }
}