package com.example.fragment_test

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_test1.*
import android.widget.Toast
import android.content.Intent
import android.app.Activity
import android.R.attr.data
import android.os.Parcel
import android.os.Parcelable

class Hero {
    var max_stamina : Int = 100
    var stamina : Int = 100
    var power : Int = 0
    var speed : Int = 0
    var tech : Int = 0
    var breaking : Int = 0
    var mental : Int = 0

    constructor(max_stamina: Int, stamina: Int, power: Int, speed: Int, tech: Int, breaking: Int, mental: Int) {
        this.max_stamina = max_stamina
        this.stamina = stamina
        this.power = power
        this.speed = speed
        this.tech = tech
        this.breaking = breaking
        this.mental = mental
    }
}

class Test1 : Fragment() {
    val pawapuro = Hero( 100,100, 0, 0, 0, 0, 0)
    var result : IntArray = intArrayOf(0, 0, 0, 0 , 0, 0)
    val requestcode : Int = 110
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        train_button.setOnClickListener{
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.addToBackStack(null)
            transaction?.replace(R.id.container, Test2.newInstance(this, requestcode))
            transaction?.commit()
        }
        data_button.setOnClickListener{
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.addToBackStack(null)
            transaction?.replace(R.id.container, data_fragment.newInstance(pawapuro))
            transaction?.commit()
            //val test = pawapuro.stamina
            //Toast.makeText(activity, test.toString(), Toast.LENGTH_LONG).show()
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() : Test1 =
            Test1().apply {
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            requestcode -> {
                if (resultCode != Activity.RESULT_OK) {
                    return
                }
                //Toast.makeText(activity, "test", Toast.LENGTH_LONG).show()
                //Thread.sleep(10000)
                result = data!!.getIntArrayExtra(Intent.EXTRA_TEXT)

                result_reflect(pawapuro, result)

                val activity = activity as MainActivity?

                activity?.setparam(pawapuro)

                return
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun result_reflect(pawapuro : Hero, result : IntArray){
        pawapuro.stamina += result[0]
        pawapuro.power += result[1]
        pawapuro.speed += result[2]
        pawapuro.tech += result[3]
        pawapuro.breaking += result[4]
        pawapuro.mental += result[5]

        //上限値設定
        if(pawapuro.stamina > pawapuro.max_stamina){ pawapuro.stamina = pawapuro.max_stamina}

        // 下限値設定
        if(pawapuro.stamina < 0){ pawapuro.stamina = 0}
        if(pawapuro.power < 0){ pawapuro.power = 0}
        if(pawapuro.speed < 0){ pawapuro.speed = 0}
        if(pawapuro.tech < 0){ pawapuro.tech = 0}
        if(pawapuro.breaking < 0){ pawapuro.breaking = 0}
        if(pawapuro.mental < 0){ pawapuro.mental = 0}
    }

}
