package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class LanguageLearningActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_learning)

        val jpnLayout = findViewById<LinearLayout>(R.id.japanLayout)
    }

    fun launchExercise(view: View) {
        val idString = view.resources.getResourceName(view.id).split("/")[1]
        val intent = Intent(this, ExerciseActivity::class.java)
        when (idString) {
            "japanLayout" -> intent.putExtra("countryName", "japan")
            "hindiLayout" -> intent.putExtra("countryName", "india")
            "koreaLayout" -> intent.putExtra("countryName", "korea")
            "thaiLayout" -> intent.putExtra("countryName", "thai")
            else -> {
                return
            }
        }
        startActivity(intent)
    }
}