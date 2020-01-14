package com.example.fragment_test

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val requestcode : Int = 111

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.container, Test1.newInstance())
            //transaction.addToBackStack(null)
            transaction.commit()
        }
        stamina_bar.setText("体力")
        progressBar.max = 100
        progressBar.progress = 100
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            requestcode -> {
                if (resultCode != Activity.RESULT_OK) {
                    return
                }
                //Toast.makeText(activity, "test", Toast.LENGTH_LONG).show()
                //Thread.sleep(10000)
                //result = data!!.getIntArrayExtra(Intent.EXTRA_TEXT)

                //result_reflect(pawapuro, result)
                return
            }
        }
        super.onActivityResult(requestCode, resultCode, data)

    }

    fun setparam(pawapuro : Hero){
        progressBar.max = pawapuro.max_stamina
        progressBar.progress = pawapuro.stamina

        return
    }

}
