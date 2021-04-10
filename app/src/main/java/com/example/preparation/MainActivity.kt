package com.example.preparation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.Exception
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class MainActivity : AppCompatActivity() {
    var leaderboard : List<SoldierFragment> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var text_field : TextView = findViewById(R.id.text)

//        var mainScroll = (findViewById<ScrollView>(R.id.mainScroll))


        (findViewById<Button>(R.id.btnMain)).setOnClickListener{
            try {
                val url = URL("https://heroesandgenerals.com/leaderboard/overall/?gamertag=kira2001")
                var string : String = ""
                val httpsURLConnection :  HttpsURLConnection = url.openConnection() as HttpsURLConnection
                httpsURLConnection.requestMethod = "GET"  // optional default is GET

                string = "\nSent 'GET' request to URL : $url; Response Code : $httpsURLConnection.responseCode"
                text_field.text = string

                httpsURLConnection.inputStream.bufferedReader().use {
                    it.lines().forEach { line ->
                        string += line
                        text_field.text = string
                    }
                }

                //       for (v in 1..5) {

                //       }
            }
            catch (e : Exception) {
                text_field.text = "ERROR happened"
            }
        }

    }
}