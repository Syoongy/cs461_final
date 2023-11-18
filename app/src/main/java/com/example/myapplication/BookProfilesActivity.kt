package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Scanner

class BookProfilesActivity : AppCompatActivity() {

    private val booksMap = HashMap<String, ArrayList<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_profiles)
//        loadBooks()
//        val genreSpinner = findViewById<Spinner>(R.id.genreSpinner)
//        val genres = arrayListOf<String>()
//        genres.addAll(booksMap.keys)
//        val spinnerAdapter =
//            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, genres)
//        genreSpinner.adapter = spinnerAdapter
//        genreSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            // If an items is selected
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                val selectedGenre = p0!!.getItemAtPosition(p2).toString()
//                val books = booksMap[selectedGenre]
//                val firstBookIv = findViewById<ImageView>(R.id.firstBookIV)
//                val firstBookTv = findViewById<TextView>(R.id.firstBookTV)\
//                val firstBookLayout = findViewById<LinearLayout>(R.id.firstBookLayout)
//                val firstBook = books?.get(0)!!
//                val firstBookImageResource = bookResourceSelector(firstBook)
//                firstBookTv.text = firstBook
//                firstBookIv.setImageResource(firstBookImageResource)
//                firstBookLayout.setOnClickListener{it -> {
//
//                }}
//                val secondBookIv = findViewById<ImageView>(R.id.secondBookIV)
//                val secondBookTv = findViewById<TextView>(R.id.secondBookTV)
//                val secondBook = books?.get(1)!!
//                secondBookTv.text = secondBook
//                secondBookIv.setImageResource(bookResourceSelector(secondBook))
//
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//
//        }
//        val firstLayout = findViewById<LinearLayout>(R.id.firstBookLayout)
    }

    private fun bookResourceSelector(bookTitle: String): Int {
        when (bookTitle) {
            "Pride and Prejudice" -> return R.drawable.pride_and_prejudice
            "To Paradise" -> return R.drawable.to_paradise
            "Atomic habits" -> return R.drawable.atomic_habits
            "Educated" -> return R.drawable.educated
            "The Maid" -> return R.drawable.the_maid
            "Murder on the orient express" -> return R.drawable.the_murder_on_the_orient_express
        }
        return R.drawable.ic_launcher_background
    }

    private fun loadBooks() {
        // Retrieve our user-pw files
        val scanners = ArrayList<Scanner>()
        val bookScanner = Scanner(resources.openRawResource(R.raw.books))
        scanners.add(bookScanner)
        // Iterate through and add them to the existing usernames list
        for (scanner in scanners) {
            while (scanner.hasNextLine()) {
                val line = scanner.nextLine()
                val lineVals = line.split("\t")
                var genre = lineVals[1].lowercase()
                var bookArr = booksMap[genre]
                if (bookArr.isNullOrEmpty()) {
                    booksMap[genre] = arrayListOf<String>()
                }
                booksMap[genre]!!.add(lineVals[0])
            }
            scanner.close()
        }
    }


    fun launchExercise(view: View) {
        val idString = view.resources.getResourceName(view.id).split("/")[1]
//        val intent = Intent(this, ExerciseActivity::class.java)
//        when (idString) {
//            "japanLayout" -> intent.putExtra("countryName", "japan")
//            "hindiLayout" -> intent.putExtra("countryName", "india")
//            "koreaLayout" -> intent.putExtra("countryName", "korea")
//            "thaiLayout" -> intent.putExtra("countryName", "thai")
//            else -> {
//                return
//            }
//        }
//        startActivity(intent)
    }
}