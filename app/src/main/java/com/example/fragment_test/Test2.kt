package com.example.fragment_test

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_test2.*

class Test2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        if (args != null) {
            val str = args.getString("Message")
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(str: String) : Test2 =
            Test2()?.apply {
                arguments = Bundle().apply {
                    putString("Message", str)
                }
            }
    }

}
