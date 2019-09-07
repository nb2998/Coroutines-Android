package com.apps.nb2998.coroutineswithandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.apps.nb2998.coroutineswithandroid.api.RetrofitFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_jokes.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JokesActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)

//        val chuckNorrisApi = RetrofitFactory.makeChuckNorrisApi()
//
//        btnNewJoke.setOnClickListener {
//            CoroutineScope(IO).launch {
//                chuckNorrisApi.fetchJoke().apply {
//                    withContext(Dispatchers.Main) {
//                            textJoke.text = value
//
//                            Picasso.get()
//                                .load(icon_url)
//                                .placeholder(R.mipmap.ic_launcher)
//                                .error(R.mipmap.ic_launcher)
//                                .into(imageJoke)
//
//                            imageJoke.visibility = View.VISIBLE
//                    }
//                }
//            }
//        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.joke.observe(this, Observer {
            with(it) {
                textJoke.text = value
                Picasso.get()
                    .load(icon_url)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageJoke)

                imageJoke.visibility = View.VISIBLE
            }
        })

    }
}
