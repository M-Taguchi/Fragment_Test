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





class Test1 : Fragment() {
    var name : String? = "test"
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
            transaction?.add(R.id.container, Test2.newInstance(this, requestcode))
            transaction?.commit()
        }
        data_button.setOnClickListener{
            Toast.makeText(activity, name, Toast.LENGTH_LONG).show()
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
                name = data?.getStringExtra(Intent.EXTRA_TEXT)
                return
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}
