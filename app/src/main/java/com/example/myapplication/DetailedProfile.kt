package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailedProfile.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailedProfile : Fragment() {
    // TODO: Rename and change types of parameters
    private var bookImageResource: Int? = null
    private var bookTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            bookImageResource = it.getInt(ARG_PARAM1)
            bookTitle = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detailed_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val act = activity
        if (act != null) {
            val bookName = act.intent.getStringExtra("bookTitle") ?: "Empty Title"
            val bookResource =
                act.intent.getIntExtra("bookImageResource", R.drawable.ic_launcher_background)
            displayProfile(bookName, bookResource)
        }
    }

    fun displayProfile(titleName: String, imageResource: Int) {
        val act = activity
        if (act?.intent != null) {
            val titleTv = act.findViewById<TextView>(R.id.bookTitleTV)
            val bookIv = act.findViewById<ImageView>(R.id.detailedBookIV)
            titleTv.text = titleName
            bookIv.setImageResource(imageResource)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailedProfile.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) = DetailedProfile().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }
}