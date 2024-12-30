package com.vrcode.cifracatolica

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        val botaoListas: ImageButton = findViewById(R.id.bt_listas)

        botaoListas.setOnClickListener {
            val intent = Intent(this, Listas::class.java)
            startActivity(intent)
        }


    }


}