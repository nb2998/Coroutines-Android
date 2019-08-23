package com.apps.nb2998.coroutineswithandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_jokes.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class JokesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)

        val chuckNorrisApi = RetrofitFactory.makeChuckNorrisApi()

        btnNewJoke.setOnClickListener {
            CoroutineScope(IO).launch {
                val jokeResponse = chuckNorrisApi.fetchJoke()
                withContext(Dispatchers.Main) {
                    with(jokeResponse ) {
                        textJoke.text = value

                        Picasso.get()
                            .load(icon_url)
                            .placeholder(R.mipmap.ic_launcher)
                            .error(R.mipmap.ic_launcher)
                            .into(imageJoke)
                    }
                }
            }
        }
    }
}
