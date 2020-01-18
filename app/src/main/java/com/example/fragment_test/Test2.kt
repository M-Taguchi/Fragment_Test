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
import kotlinx.android.synthetic.main.fragment_test1.*

//練習内容をまとめたクラス
class Test2: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test2, container, false)
    }

    //ここから処理を開始
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        train_image.visibility = View.INVISIBLE

        //戻るボタンを押したとき
        return_button.setOnClickListener{
            fragmentManager?.popBackStack()
        }

        //練習1のボタンを押したとき(トス)
        button2.setOnClickListener{
            submit(intArrayOf(-20, 15, 0, 3, -1, 0))
            fragmentManager?.popBackStack()
        }
        button2.setOnLongClickListener{
            train_name.setText("トスバッティング")
            result_1.setText("筋力　　+15")
            result_2.setText("技術　　+3")
            result_3.setText("変化　　-1")
            result_4.setText("怪我率　0%")
            train_image.setImageResource(R.drawable.baseball_batter_man)
            train_image.visibility = View.VISIBLE
            true
        }

        //練習2のボタンを押したとき(ノック)
        button3.setOnClickListener{
            submit(intArrayOf(-15, 0, 15, 3, -1, 3))
            fragmentManager?.popBackStack()
        }
        button3.setOnLongClickListener{
            train_name.setText("ノック")
            result_1.setText("敏捷　　+15")
            result_2.setText("技術　　+3")
            result_3.setText("変化　　-1")
            result_4.setText("怪我率　0%")
            train_image.setImageResource(R.drawable.baseball_goro)
            train_image.visibility = View.VISIBLE
            true
        }
    }

    //fragmentのインスタンス生成時に呼ばれる
    companion object {
        @JvmStatic
        fun newInstance(target: Fragment?, requestCode: Int) : Test2 =
            Test2().apply {
                this.setTargetFragment(target, requestCode)

                val args = Bundle()
                this.setArguments(args)
            }
    }

    //練習の結果をfragment1に結果を返す
    fun submit(result: IntArray) {
        val target = this.getTargetFragment()

        val data = Intent()
        data.putExtra(Intent.EXTRA_TEXT, result)
        //Toast.makeText(activity, Integer.toString(targetRequestCode), Toast.LENGTH_LONG).show()
        target?.onActivityResult(targetRequestCode, Activity.RESULT_OK, data)
    }

}
