package com.apps.nb2998.coroutineswithandroid

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apps.nb2998.coroutineswithandroid.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// not singleton as bound to the activity anyway
class MainViewModel: ViewModel() {

    var joke: MutableLiveData<JokeResponse>

    init {
        joke= MutableLiveData()
        CoroutineScope(Dispatchers.IO).launch {
            joke.postValue(Repository.getJoke())
        }
    }

    fun fetchNewJoke() {
        CoroutineScope(Dispatchers.IO).launch {
            joke.postValue(Repository.getJoke())
        }
    }

//    fun cancelJobs() = Repository.cancelJobs()
}