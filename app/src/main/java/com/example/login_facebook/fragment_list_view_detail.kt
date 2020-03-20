package com.example.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.login_facebook.R

/**
 * A simple [Fragment] subclass.
 */
class fragment_list_view_detail : Fragment() {
    private var titleTextView : String? = null
    private var detailTextView : String? = null
    private var image : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_fragment_list_view_detail, container, false)
        // Inflate the layout for this fragment

        var layout_titleTextView : TextView = view.findViewById<TextView>(R.id.titleTextView)
        var layout_detailTextView : TextView = view.findViewById<TextView>(R.id.detailTextView)
        var layout_image : ImageView = view.findViewById<ImageView>(R.id.imageView)
        layout_titleTextView.setText(titleTextView)
        layout_detailTextView.setText(detailTextView)
        Glide.with(this)
            .load(image)
            .into(layout_image);

        return view

    }

    fun newInstance(title: String,detail: String,image:String): fragment_list_view_detail {
        val fragment = fragment_list_view_detail()
        val bundle = Bundle()
        bundle.putString("titleTextView", title)
        bundle.putString("detailTextView", detail)
        bundle.putString("image", image)
        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            titleTextView = bundle.getString("titleTextView").toString()
            detailTextView = bundle.getString("detailTextView").toString()
            image = bundle.getString("image").toString()

        }
    }

}