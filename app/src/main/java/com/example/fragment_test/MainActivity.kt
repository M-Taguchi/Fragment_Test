package com.example.fragment_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            train_button.setOnClickListener {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.addToBackStack(null)
                transaction.replace(R.id.container, Test1.newInstance("Fragment"))
                transaction.commit()
            }
        }
    }
}
