package com.example.billatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btSearch: Button = findViewById(R.id.btSearch)
        val etFrom: TextInputEditText = findViewById(R.id.etFrom)
        val etArrival: TextInputEditText = findViewById(R.id.etWhere)

        var userInputFrom: String
        var userInputDestination: String
        btSearch.setOnClickListener {
            userInputFrom = etFrom.text.toString().lowercase()
            userInputDestination = etArrival.text.toString().lowercase()

            val intent = Intent(this, SearchResultActivity::class.java).apply {
                val bundle = Bundle()
                bundle.putString("from", userInputFrom)
                bundle.putString("where", userInputDestination)
                this.putExtras(bundle)
            }
            startActivity(intent)
        }
    }
}