package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Random
import java.util.Scanner

class ExerciseActivity : AppCompatActivity() {
    private var langWords = ArrayList<String>()
    private var langSyn = ArrayList<String>()
    private var langAll = ArrayList<String>()

    private var randomWords = ArrayList<String>()
    private var wordSelections = ArrayList<String>()
    private var wordToSyn = HashMap<String, String>()


    //Adapter
    private lateinit var myAdapter: ArrayAdapter<String>

    //read language file
    private fun readLangFile(scanner: Scanner) {
        while (scanner.hasNextLine()) {
            val line = scanner.nextLine()
            val linesplit = line.split("\t")
            langWords.add(linesplit[0])
            val wordList = linesplit[1] //string of words

            // split word list, get right synonym
            val wordsplit = wordList.split(",")
            langSyn.add(wordsplit[0])
            langAll.add(wordsplit[1])
//          Toast.makeText(this,"$langWords : $langSyn", Toast.LENGTH_SHORT).show()
        }
    }

    private fun wordToGuess() {
        val randomize = Random()
        val index = randomize.nextInt(langWords.size)
        val theWord = langWords[index]
        val theSyn = langSyn[index]

        val wordTv = findViewById<TextView>(R.id.theWord)
        wordTv.text = theWord

        // set syn
        val synTv = findViewById<TextView>(R.id.synonym)
//        synTv.text = theSyn

        //put the words to listview
        myAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, langAll)
        val wordList = findViewById<ListView>(R.id.wordsList)
        wordList.adapter = myAdapter

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        //read file
        val japanese = Scanner(resources.openRawResource(R.raw.japanese))
        readLangFile(japanese)

        // display random lang word + synonym
        wordToGuess()

    }
}