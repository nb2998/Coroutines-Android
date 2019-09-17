package com.apps.nb2998.coroutineswithandroid.repository

import androidx.lifecycle.MutableLiveData
import com.apps.nb2998.coroutineswithandroid.JokeResponse
import com.apps.nb2998.coroutineswithandroid.api.RetrofitFactory
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {

//    var job: CompletableJob? = null

//    fun getJoke(): MutableLiveData<JokeResponse> {
//        job = Job()
//        return object : MutableLiveData<JokeResponse>() {
//            override fun onActive() {
//                super.onActive()
//                job?.let { thisJob ->
//                    CoroutineScope(IO + thisJob).launch {
//                         val joke = RetrofitFactory.makeChuckNorrisApi().fetchJoke()
//                        withContext(Main) {
//                            value = joke
//                            thisJob.complete()
//                        }
//                    }
//
//                }
//            }
//        }
//    }

    suspend fun getJoke() = RetrofitFactory.makeChuckNorrisApi().fetchJoke()

//    fun cancelJobs() = job?.cancel()
}