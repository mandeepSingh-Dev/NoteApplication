package com.mandeep.noteapplication.PractiseUTIL

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyReciever :BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d("39tf3wf",intent?.action.toString()+"d")
    }
}