package com.vrcode.cifracatolica

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class CifraPage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cifra_page)

        val btnNovaCifra = findViewById<Button>(R.id.bt_novaCifra)
        btnNovaCifra.setOnClickListener {
            val intent = Intent(this, NewCifra::class.java)
            startActivity(intent)
        }

        val btnExibirTodasCifras = findViewById<Button>(R.id.bt_exibirCifras)
        btnExibirTodasCifras.setOnClickListener {
            val intent = Intent(this, AllCifras::class.java)
            startActivity(intent)
        }

    }
}