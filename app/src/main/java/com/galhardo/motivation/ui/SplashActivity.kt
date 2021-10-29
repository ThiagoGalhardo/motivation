package com.galhardo.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.galhardo.motivation.R
import com.galhardo.motivation.infra.MotivationConstants
import com.galhardo.motivation.infra.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurerityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurerityPreferences = SecurityPreferences(this)

        if (supportActionBar !=null){
            supportActionBar!!.hide()
        }

        buttonSave.setOnClickListener(this)

        verifyname()

    }

    override fun onClick(view: View) {

        val id = view.id
        if (id == R.id.buttonSave){
            handleSave()

        }
    }

    private fun verifyname(){
       val name = mSecurerityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)

        if (name != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }


    private fun handleSave(){
        val name = editTextName.text.toString()

        if (name != ""){
            mSecurerityPreferences.storeString(MotivationConstants.KEY.PERSON_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Informe seu nome", Toast.LENGTH_SHORT).show()
        }

    }
}