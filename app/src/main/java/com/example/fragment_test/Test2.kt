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

        return_button.setOnClickListener{
            fragmentManager?.popBackStack()
        }
        button2.setOnClickListener{
            submit(intArrayOf(-20, 15, 0, 3, -1, 0))
            fragmentManager?.popBackStack()
        }

    }
    companion object {
        @JvmStatic
        fun newInstance(target: Fragment?, requestCode: Int) : Test2 =
            Test2().apply {
                this.setTargetFragment(target, requestCode)

                val args = Bundle()
                this.setArguments(args)
            }
    }

    fun submit(result: IntArray) {
        val target = this.getTargetFragment()

        val data = Intent()
        data.putExtra(Intent.EXTRA_TEXT, result)
        //Toast.makeText(activity, Integer.toString(targetRequestCode), Toast.LENGTH_LONG).show()
        target?.onActivityResult(targetRequestCode, Activity.RESULT_OK, data)
    }

}
