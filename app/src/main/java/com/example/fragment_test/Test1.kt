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
import java.io.Serializable
import android.view.KeyEvent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking


//行動選択をまとめたクラス
class Test1: Fragment() {
    //val pawapuro = Hero( 100,100, 0, 0, 0, 0, 0)
    var pawapuro: Hero = Hero()

    //var pawapuro: Hero = Hero( 100,100, 0, 0, 0, 0, 0)
    var result: IntArray = intArrayOf(0, 0, 0, 0 , 0, 0)
    val requestcode: Int = 110
    val request_code_pre_event = 112
    val request_code_after_event = 113
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle : Bundle? = arguments
        pawapuro = bundle!!.getSerializable("pawapuro") as Hero

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
        walk_button.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.addToBackStack(null)
            transaction?.replace(R.id.container, ChatFragment.newInstance(this, request_code_pre_event))
            transaction?.commit()
        }

        view.setOnKeyListener { v, keyCode, event ->
            (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN).apply {
                // as you like :)
            }
        }
        view.setFocusableInTouchMode(true)
        view.requestFocus()


    }
    companion object {
        @JvmStatic
        fun newInstance(pawapuro: Hero) : Test1 =
            Test1().apply {
                arguments = Bundle().apply {
                    putSerializable("pawapuro", pawapuro)
                }
            }
    }

    //練習の結果をMainActivityに反映させる
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
                //ここでMainActivityに反映
                activity?.setparam(pawapuro)

                //後イベ発生
                var after_event = true
                if (after_event == true) {
                    val transaction = activity?.supportFragmentManager?.beginTransaction()
                    transaction?.addToBackStack(null)
                    transaction?.replace(R.id.container, ChatFragment.newInstance(this, request_code_after_event))
                    transaction?.commit()
                }else{
                    activity!!.turn++
                    activity.go_calendar()

                    //前イベ発生
                    var pre_event = true
                    if (pre_event == true) {
                        val transaction = activity?.supportFragmentManager?.beginTransaction()
                        transaction?.addToBackStack(null)
                        transaction?.replace(R.id.container, ChatFragment.newInstance(this, request_code_pre_event))
                        transaction?.commit()
                    }
                }
                return
            }
            request_code_pre_event -> {
                if (resultCode != Activity.RESULT_OK) {
                    return
                }

                result = data!!.getIntArrayExtra(Intent.EXTRA_TEXT)

                result_reflect(pawapuro, result)
            }
            request_code_after_event -> {
                if (resultCode != Activity.RESULT_OK) {
                    return
                }

                result = data!!.getIntArrayExtra(Intent.EXTRA_TEXT)

                result_reflect(pawapuro, result)

                val activity = activity as MainActivity?

                activity!!.turn++
                activity.go_calendar()

                //前イベ発生
                var pre_event = true
                if (pre_event == true) {
                    val transaction = activity?.supportFragmentManager?.beginTransaction()
                    transaction?.addToBackStack(null)
                    transaction?.replace(R.id.container, ChatFragment.newInstance(this, request_code_pre_event))
                    transaction?.commit()
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    //結果を取得
    fun result_reflect(pawapuro: Hero, result: IntArray){
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
