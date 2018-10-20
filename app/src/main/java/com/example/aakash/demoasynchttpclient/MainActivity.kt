package com.example.aakash.demoasynchttpclient

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var herokuResponse: TextView
    lateinit var hitHeroku: Button
    var response:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        herokuResponse = findViewById(R.id.herokuResponse)
        hitHeroku = findViewById(R.id.hitHeroku)

        hitHeroku.setOnClickListener(View.OnClickListener{
            HerokuTask().execute()
        })
    }

    inner class HerokuTask: AsyncTask<String, String, String>() {
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            herokuResponse.text = result
        }

        override fun doInBackground(vararg params: String?): String {
            var response = ""
            try {
                var herokuUrl = "https://noderesthelloworld.herokuapp.com/"

                var url = URL(herokuUrl)
                var conn = url.openConnection()
                conn.connect()

                var bf = BufferedReader(InputStreamReader(conn.getInputStream()))

                response = bf.readLine().toString()

                 System.out.println(response)
            } catch (e: Exception) {
                System.out.println(e.printStackTrace())
            }

            return response
        }
    }
}
