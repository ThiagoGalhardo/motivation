package com.galhardo.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.galhardo.motivation.R
import com.galhardo.motivation.infra.MotivationConstants
import com.galhardo.motivation.infra.SecurityPreferences
import com.galhardo.motivation.repository.Mock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurerityPreferences: SecurityPreferences
    private var mPhraseFilter : Int = MotivationConstants.PHRASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar !=null){
            supportActionBar!!.hide()
        }

        mSecurerityPreferences = SecurityPreferences(this)
        val name = mSecurerityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        textName.text = "Olá, $name!"

        // Logica inicial de seleção
        imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
        handleNewPhrase()


        buttonNewPhrase.setOnClickListener(this)
        imageAll.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        imageSun.setOnClickListener(this)


    }

    override fun onClick(view: View) {

        val id = view.id
        val listFilter = listOf(R.id.imageAll, R.id.imageHappy, R.id.imageSun)

        if (id == R.id.buttonNewPhrase) {
            handleNewPhrase()

        } else if (id in  listFilter) {
                handleFilter(id)
        }

    }

    private fun handleFilter(id: Int) {

        imageAll.setColorFilter(resources.getColor(R.color.white))
        imageHappy.setColorFilter(resources.getColor(R.color.white))
        imageSun.setColorFilter(resources.getColor(R.color.white))

        when (id) {
            R.id.imageAll -> {
                imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.ALL
            }
            R.id.imageHappy -> {
                imageHappy.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.HAPPY
            }
            R.id.imageSun -> {
                imageSun.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.MORNING

            }

        }

    }

    private fun handleNewPhrase() {
        textFrase.text = Mock().getPhrase(mPhraseFilter)
    }

}