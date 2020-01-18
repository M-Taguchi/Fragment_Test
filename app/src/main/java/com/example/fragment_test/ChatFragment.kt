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
import android.view.KeyEvent
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_data_fragment.*

//パワプロ君のデータを見るクラス
class ChatFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val bundle = arguments


        view.setOnKeyListener { v, keyCode, event ->
            (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN).apply {
                // as you like :)
                //fragmentManager?.popBackStack()
            }
        }
        view.setFocusableInTouchMode(true)
        view.requestFocus()

    }
    companion object {
        @JvmStatic
        fun newInstance() : ChatFragment =
            ChatFragment().apply {
            }
    }

}
