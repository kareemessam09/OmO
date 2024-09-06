package com.kotlinexample.moviesapp.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences


@SuppressLint("StaticFieldLeak")
object SharedPrefrenc {

        private const val PREFS_NAME = "sharedPref"
        private lateinit var context: Context

        val prefs: SharedPreferences
            get() = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        @SuppressLint("StaticFieldLeak")
        fun initalize(context: Context){
            SharedPrefrenc.context = context
        }

        fun setLoginState(state: Boolean) {
            val editor = prefs.edit()
            editor.putBoolean("loginState", state)
            editor.apply()
        }

        fun getLoginState() : Boolean {
            return prefs.getBoolean("loginState", false)
        }

        fun setUserName(userName: String) {
            val editor = prefs.edit()
            editor.putString("userName", userName)
            editor.apply()
        }

        fun getUserName() : String? {
            return prefs.getString("userName", "null")
        }


}