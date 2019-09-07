package com.apps.nb2998.coroutineswithandroid

import androidx.lifecycle.ViewModel
import com.apps.nb2998.coroutineswithandroid.repository.Repository

// not singleton as bound to the activity anyway
class MainViewModel: ViewModel() {

    val joke = Repository.getJoke()

    fun cancelJobs() = Repository.cancelJobs()
}