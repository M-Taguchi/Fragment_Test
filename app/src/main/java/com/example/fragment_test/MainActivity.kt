package com.example.fragment_test

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

//これがパワプロ君
class Hero: Serializable {
    var max_stamina: Int = 100
    var stamina: Int = 100
    var power: Int = 0
    var speed: Int = 0
    var tech: Int = 0
    var breaking: Int = 0
    var mental: Int = 0


//    constructor(max_stamina: Int, stamina: Int, power: Int, speed: Int, tech: Int, breaking: Int, mental: Int) {
//        this.max_stamina = max_stamina
//        this.stamina = stamina
//        this.power = power
//        this.speed = speed
//        this.tech = tech
//        this.breaking = breaking
//        this.mental = mental
//    }
}

class MainActivity : AppCompatActivity() {

    var turn: Int = 0
    var calendar: IntArray = intArrayOf(4, 1)

    val requestcode: Int = 111
    val pawapuro: Hero = Hero()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.container, Test1.newInstance(pawapuro))
            //transaction.addToBackStack(null)
            transaction.commit()
        }
        stamina_bar.setText("体力")
        progressBar.max = 100
        progressBar.progress = 100
        calendarView.setText(calendar[0].toString() + "月" + calendar[1].toString() + "週")
    }

    //fragmentから
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

    //得られた結果を反映する
    fun setparam(pawapuro: Hero){
        progressBar.max = pawapuro.max_stamina
        progressBar.progress = pawapuro.stamina

        turn++
        calendar[1] += 1
        if (calendar[1] == 5){
            calendar[0] += 1
            calendar[1] = 1
        }
        if (calendar[0] == 13){
            calendar[0] = 1
        }

        calendarView.setText(calendar[0].toString() + "月" + calendar[1].toString() + "週")

        return
    }

}
