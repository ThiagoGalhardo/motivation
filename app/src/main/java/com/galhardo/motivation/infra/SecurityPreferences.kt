package com.galhardo.motivation.infra

import android.content.Context

class SecurityPreferences (contex : Context) {

    private val mSharedPreferences = contex.getSharedPreferences("motivation", Context.MODE_PRIVATE)

    fun storeString(key: String, value: String){
        mSharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String) : String {
       return mSharedPreferences.getString(key, "") ?: ""
    }


}