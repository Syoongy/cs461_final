package com.example.myapplication

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.Scanner

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BookProfiles.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookProfiles : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val booksMap = HashMap<String, ArrayList<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_profiles, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val act = activity
        if (act != null) {
            loadBooks()
            val genreSpinner = act.findViewById<Spinner>(R.id.genreSpinner)
            val genres = arrayListOf<String>()
            genres.addAll(booksMap.keys)
            val spinnerAdapter =
                ArrayAdapter<String>(act, android.R.layout.simple_spinner_dropdown_item, genres)
            genreSpinner.adapter = spinnerAdapter
            genreSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                // If an items is selected
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val selectedGenre = p0!!.getItemAtPosition(p2).toString()
                    val books = booksMap[selectedGenre]
                    val firstBookIv = act.findViewById<ImageView>(R.id.firstBookIV)
                    val firstBookTv = act.findViewById<TextView>(R.id.firstBookTV)
                    val firstBookLayout = act.findViewById<LinearLayout>(R.id.firstBookLayout)
                    val firstBook = books?.get(0)!!
                    val firstBookImageResource = bookResourceSelector(firstBook)
                    firstBookTv.text = firstBook
                    firstBookIv.setImageResource(firstBookImageResource)
                    firstBookLayout.setOnClickListener { it ->
                        bookClick(it, firstBookImageResource, firstBook)
                    }
                    val secondBookIv = act.findViewById<ImageView>(R.id.secondBookIV)
                    val secondBookTv = act.findViewById<TextView>(R.id.secondBookTV)
                    val secondBook = books?.get(1)!!
                    secondBookTv.text = secondBook
                    secondBookIv.setImageResource(bookResourceSelector(secondBook))

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }
    }

    private fun bookClick(view: View, imageResource: Int, title: String) {

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val myIntent = Intent(activity, DetailsActivity::class.java)
            myIntent.putExtra("bookImageResource", imageResource)
            myIntent.putExtra("bookTitle", title)
            startActivity(myIntent)
        } else {
            val detFrag = fragmentManager!!.findFragmentById(R.id.bookProfilesFragment) as DetailedProfile
//            detFrag.displayPokemon(tag)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BookProfiles.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BookProfiles().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}