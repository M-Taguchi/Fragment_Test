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

class Test1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        if (args != null) {
            val str = args.getString("Message")
        }

        train_button.setOnClickListener{
            Toast.makeText(activity, "トーストメッセージ", Toast.LENGTH_LONG).show()
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.addToBackStack(null)
            transaction?.add(R.id.container, Test2.newInstance("Fragment2"))
            transaction?.commit()
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(str: String) : Test1 =
            Test1().apply {
                arguments = Bundle().apply {
                    putString("Message", str)
                }
            }
    }

}
