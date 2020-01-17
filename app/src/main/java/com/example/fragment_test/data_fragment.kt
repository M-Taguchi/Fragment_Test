package com.example.fragment_test

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_test2.*
import android.app.Activity
import android.content.Intent
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_data_fragment.*


class data_fragment: Fragment() {

    var pawapuro : Hero = Hero()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        pawapuro = bundle!!.getSerializable("pawapuro") as Hero
        stamina.text = "体力　" + pawapuro.stamina.toString()
        power.text = "筋力　" +  pawapuro.power.toString()
        speed.text = "敏捷　" +  pawapuro.speed.toString()
        tech.text = "技術　" +  pawapuro.tech.toString()
        breaking.text = "変化　" +  pawapuro.breaking.toString()
        mental.text = "精神　" +  pawapuro.mental.toString()
    }
    companion object {
        @JvmStatic
        fun newInstance(pawapuro: Hero) : data_fragment =
            data_fragment().apply {
                arguments = Bundle().apply {
                    putSerializable("pawapuro", pawapuro)
                }
            }
    }

}
