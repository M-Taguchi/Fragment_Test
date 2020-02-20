package com.example.fragment_test

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_test2.*
import android.app.Activity
import android.content.Intent
import android.text.method.Touch.onTouchEvent
import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_data_fragment.*
import android.view.MotionEvent
import android.view.View.OnTouchListener



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

        //戻るボタン無効化
        view.setOnKeyListener { v, keyCode, event ->
            (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN).apply {
            }
        }
        view.setFocusable(true);
        view.setFocusableInTouchMode(true)
        view.requestFocus()

        //画面タップでページ送り
        var count = 0
        view.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {

                if (event.action == MotionEvent.ACTION_UP) {
                    //do something
                    textView.setText("次ページ")
                    count++
                }
                if (count == 2){
                    fragmentManager?.popBackStack()
                    submit(intArrayOf(-5, 1, 1, 1, 1, 1))
                }

                return true
            }
        })

    }
    companion object {
        @JvmStatic
        fun newInstance(target: Fragment?, requestCode: Int) : ChatFragment =
            ChatFragment().apply {
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
