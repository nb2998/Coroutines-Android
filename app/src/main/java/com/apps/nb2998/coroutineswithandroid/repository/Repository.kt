package com.apps.nb2998.coroutineswithandroid.repository

import androidx.lifecycle.LiveData
import com.apps.nb2998.coroutineswithandroid.JokeResponse
import com.apps.nb2998.coroutineswithandroid.api.RetrofitFactory
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {

    var job: CompletableJob? = null

    fun getJoke(): LiveData<JokeResponse> {
        job = Job()
        return object : LiveData<JokeResponse>() {
            override fun onActive() {
                super.onActive()
                job?.let { thisJob ->
                    CoroutineScope(IO + thisJob).launch {
                         val joke = RetrofitFactory.makeChuckNorrisApi().fetchJoke()
                        withContext(Main) {
                            value = joke
                            thisJob.complete()
                        }
                    }

                }
            }
        }
    }

    fun cancelJobs() = job?.cancel()
}