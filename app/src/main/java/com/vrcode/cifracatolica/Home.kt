package com.vrcode.cifracatolica

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.vrcode.cifracatolica.R.id.bt_cifras
import com.vrcode.cifracatolica.R.id.bt_listas

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        val botaoListas: ImageButton = findViewById(bt_listas)

        botaoListas.setOnClickListener {
            val intent = Intent(this, Listas::class.java)
            startActivity(intent)
        }

        val botaoCifra: ImageButton = findViewById(bt_cifras)

        botaoCifra.setOnClickListener {
            val intent = Intent(this, CifraPage::class.java)
            startActivity(intent)
        }



    }


}