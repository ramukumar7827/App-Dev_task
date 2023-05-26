package com.example.introductionapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val github: ImageView =findViewById(R.id.et1)

        val linkdin:ImageView=findViewById(R.id.et2)
        val intsa:ImageView=findViewById(R.id.et3)

        github.setOnClickListener {
            val openURL=Intent(Intent.ACTION_VIEW)
            openURL.data= Uri.parse("https://github.com/ramukumar7827")
            startActivity(openURL)


        }
      linkdin.setOnClickListener {
          val openURL=Intent(Intent.ACTION_VIEW)
          openURL.data= Uri.parse("https://www.linkedin.com/in/ramu--kumar/")
          startActivity(openURL)

        }
        intsa.setOnClickListener(){
            val openURL=Intent(Intent.ACTION_VIEW)
            openURL.data= Uri.parse("https://instagram.com/___ram__u?igshid=NGExMmI2YTkyZg==")
            startActivity(openURL)


        }




    }
}