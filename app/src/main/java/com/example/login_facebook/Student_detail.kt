package com.example.login_facebook

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.facebook.FacebookSdk

/**
 * A simple [Fragment] subclass.
 */

class Student_detail : Fragment() {

    private var title:String?=null
    private var detail:String?=null
    private var image:String?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(com.example.login_facebook.R.layout.fragment_student_detail, container, false)
        val layout_title =view?.findViewById<TextView>(com.example.login_facebook.R.id.title)
        val layout_detail =view?.findViewById<TextView>(com.example.login_facebook.R.id.detail)
        val layout_image =view.findViewById<ImageView>(com.example.login_facebook.R.id.imgV)

        layout_title?.text = this.title
        layout_detail?.text = this.detail


        Glide.with(this)
            .load(image)
            .into(layout_image)


        val button: Button = view.findViewById(R.id.button2);
        // Inflate the layout for this fragment
        button.setOnClickListener {

            // Toast.makeText(context, "Add to cart Success!!", Toast.LENGTH_LONG).show()

            val builder = AlertDialog.Builder(context)
            builder.setMessage("ยืนยันความถูกต้องของสมาชิกภายในทีม?")
            builder.setPositiveButton("ตกลง", DialogInterface.OnClickListener { dialog, id ->
                Toast.makeText(
                    FacebookSdk.getApplicationContext(),
                    "ทำการยืนยันสำเร็จ", Toast.LENGTH_SHORT
                ).show()
            })
            builder.setNegativeButton("ยกเลิก", DialogInterface.OnClickListener { dialog, which ->
                //dialog.dismiss();
            })
            builder.show()
        }

        return view
    }

    fun newInstance(title: String,detail: String,image:String): Student_detail {
        val fragment = Student_detail()
        val bundle = Bundle()
        bundle.putString("title", title)
        bundle.putString("detail", detail)
        bundle.putString("image", image)

        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var bundle = arguments
        if (bundle != null) {
            title = bundle.getString("title").toString()
            detail = bundle.getString("detail").toString()
            image = bundle.getString("image").toString()

        }
    }

}

