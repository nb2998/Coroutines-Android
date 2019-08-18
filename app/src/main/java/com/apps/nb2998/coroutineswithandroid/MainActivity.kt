package com.apps.nb2998.coroutineswithandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val RESULT1 = "Result #1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            CoroutineScope(IO).launch {
                fakeApiCall()
            }
        }
    }

    private suspend fun fakeApiCall() {
        val result1 = getResult1FromApi()
        logThread("Log: $result1")
    }

    //    can be called from within a coroutine
    private suspend fun getResult1FromApi(): String {
        logThread("getResult1FromApi")
        delay(1000)
        return RESULT1
    }

    private fun logThread(methodName: String) {
        println("Log: $methodName : ${Thread.currentThread().name}")
    }
}
