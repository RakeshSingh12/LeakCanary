package com.example.leakcanary

import android.content.Context
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var myAsyncTask: MyAsyncTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button?.setOnClickListener(View.OnClickListener {
            if (myAsyncTask != null) {
                finish()
            }
            myAsyncTask = MyAsyncTask(this@MainActivity)
            myAsyncTask!!.execute()
        })
    }

    class MyAsyncTask(private val mcontext: Context) :
        AsyncTask<Void?, Void?, Void?>() {

        override fun doInBackground(vararg p0: Void?): Void? {
            val bitmap = BitmapFactory.decodeResource(
                mcontext.resources,
                R.drawable.ic_launcher_background
            )
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return null
        }

    }

    override fun onDestroy() {
        myAsyncTask!!.cancel(false)
        super.onDestroy()
        Log.v("", "activity des")
    }
}