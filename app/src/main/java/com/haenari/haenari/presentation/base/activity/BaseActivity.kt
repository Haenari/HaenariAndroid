package com.haenari.haenari.presentation.base.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun startActivity(activity: Class<out BaseActivity>, init: ((Intent) -> Unit)? = null) {
        val intent = Intent(this, activity).apply {
            init?.invoke(this)
        }
        startActivity(intent)
    }
}