package com.example.fragment


import CustomAdapter
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.login_facebook.R
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 */
class fragment_list_view : Fragment() {

    private lateinit var listView: ListView
    private var username : String? = null
    private var password : String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragment_list_view, container, false)

        listView = view.findViewById(R.id.listview)

        val jsonString : String = loadJsonFromAsset("recipes.json", activity!!.baseContext).toString()
        val json = JSONObject(jsonString)
        val jsonArray = json.getJSONArray("recipes")

        val adapter = CustomAdapter(activity!!.baseContext, jsonArray)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            var titleTextView = jsonArray.getJSONObject(position).getString("title").toString()
            var detailTextView = jsonArray.getJSONObject(position).getString("description").toString()
            var image = jsonArray.getJSONObject(position).getString("image").toString()

            val fragmentListViewDetail = fragment_list_view_detail().newInstance(titleTextView,detailTextView,image)

            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragmentListViewDetail,"fragment_list_view_detail")
            transaction.addToBackStack("fragment_list_view_detail")
            transaction.commit()
        }

        return view
    }

    private fun loadJsonFromAsset(filename: String, context: Context): String? {
        val json: String?

        try {
            val inputStream = context.assets.open(filename)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: java.io.IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }

    fun newInstance(username: String,password: String): fragment_list_view {
        val fragment = fragment_list_view()
        val bundle = Bundle()
        bundle.putString("username", username)
        bundle.putString("password", password)
        fragment.setArguments(bundle)
        return fragment
    }

}