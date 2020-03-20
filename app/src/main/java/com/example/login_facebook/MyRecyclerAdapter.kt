package com.example.login_facebook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONArray

class MyRecyclerAdapter(fragmentActivity: FragmentActivity, val dataSource: JSONArray) : RecyclerView.Adapter<MyRecyclerAdapter.Holder>() {

    private val thiscontext : Context = fragmentActivity.baseContext
    private val thisActivity = fragmentActivity

    class Holder(view : View) : RecyclerView.ViewHolder(view) {

        private val View = view;

        lateinit var layout : LinearLayout
        lateinit var titleTextView: TextView
        lateinit var detailTextView: TextView
        lateinit var image: ImageView

        fun Holder(){

            layout = View.findViewById<View>(R.id.recyview_layout) as LinearLayout
            titleTextView = View.findViewById<View>(R.id.tv_name) as TextView
            detailTextView = View.findViewById<View>(R.id.tv_description) as TextView
            image = View.findViewById<View>(R.id.imgV) as ImageView

        }

    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.recy_layout, parent, false))
    }


    override fun getItemCount(): Int {
        return dataSource.length()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.Holder()

        holder.titleTextView.setText( dataSource.getJSONObject(position).getString("firstname").toString() )
        holder.detailTextView.setText( dataSource.getJSONObject(position).getString("lastname").toString() )

        Glide.with(thiscontext)
            .load(dataSource.getJSONObject(position).getString("image").toString())
            .into(holder.image)

        holder.layout.setOnClickListener{

            //Toast.makeText(thiscontext,holder.titleTextView.text.toString(),Toast.LENGTH_SHORT).show()


            val title:String = dataSource.getJSONObject(position).getString("firstname").toString()
            val detail:String = dataSource.getJSONObject(position).getString("lastname").toString()
            val img:String = dataSource.getJSONObject(position).getString("image").toString()

            // Toast.makeText(thiscontext,holder.titleTextView.text.toString(),Toast.LENGTH_SHORT).show()val
            val fragment_item_selected = Student_detail().newInstance(title,detail,img)
            val frgMng = thisActivity.supportFragmentManager
            val transaction : FragmentTransaction = frgMng.beginTransaction()
            transaction.replace(R.id.layout, fragment_item_selected,"Student_detail")
            transaction.addToBackStack("Student_detail")
            transaction.commit()


        }

    }


}
