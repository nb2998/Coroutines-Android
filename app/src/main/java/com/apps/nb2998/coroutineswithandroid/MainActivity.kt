package com.apps.nb2998.coroutineswithandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val RESULT1 = "Result #1"
    private val RESULT2 = "Result #2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            CoroutineScope(IO).launch {
                // scope is like grouping a bunch of jobs together (Job 1-5, Job 3 fails- cancel them all)
                fakeApiCall()
            }
        }
    }

    private suspend fun fakeApiCall() {
        val result1 = getResult1FromApi()
        logThread("Log: $result1")
        setTextOnTextView(result1)

        // using suspend keyword ensures that next line waits for the previous line to execute
        val result2 =  getResult2FromApi(result1)
        logThread("Log: $result2")
        setTextOnTextView(result2)
    }

    //    can be called from within a coroutine
    private suspend fun getResult1FromApi(): String {
        logThread("getResult1FromApi")
        delay(1000) // diff from Thread.sleep (Only delay this single coroutine and not the entire thread)
        return RESULT1
    }

    private suspend fun getResult2FromApi(result1: String): String {
        logThread("getResult2FromApi using result from 1: $result1")
        delay(1000) // diff from Thread.sleep (Only delay this single coroutine and not the entire thread)
        return RESULT2
    }

    private suspend fun setTextOnTextView(text: String) {
        // possible contexts: IO, Main, Default
        // IO- N/w requests, DB ; Main- Main thread (interacting with UI) ; Default- For heavy tasks (filter a large list)
        withContext(Main) { // switch context of coroutine
            //   are thread independent - can switch threads anytime
            // launch creates a new coroutine while with context just switches the context
            tvResult.text = tvResult.text.toString() + "\n$text"
        }
    }

    private fun logThread(methodName: String) {
        // Coroutine ≠ Thread (Coroutine is a job to be done and Thread is a place where job will be executed)
        // hence, multiple coroutines can run on the same thread
        println("Log: $methodName : ${Thread.currentThread().name}")
    }
}
