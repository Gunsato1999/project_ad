import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.example.login_facebook.R
import org.json.JSONArray

class CustomAdapter(context: Context, val dataSource: JSONArray) : BaseAdapter() {

    private val thiscontext: Context = context
    private val inflater: LayoutInflater = thiscontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.length()
    }

    override fun getItem(position: Int): Any {
        return dataSource.getJSONObject(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view: View
        val holder : ViewHolder

        // 1
        if (convertView == null) {

            // 2
            view = inflater.inflate(R.layout.layout_listview, parent, false)

            // 3
            holder = ViewHolder()

            holder.layout = view.findViewById(R.id.listview_layout)
            holder.titleTextView = view.findViewById(R.id.tv_name) as TextView
            holder.detailTextView = view.findViewById(R.id.tv_description) as TextView
            holder.image = view.findViewById(R.id.imgV) as ImageView
            // 4
            view.tag = holder
        } else {
            // 5
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        // 6
        val layout = holder.layout
        val titleTextView = holder.titleTextView
        val detailTextView = holder.detailTextView
        val image = holder.image

        titleTextView.setText( dataSource.getJSONObject(position).getString("title").toString() )
        detailTextView.setText( dataSource.getJSONObject(position).getString("description").toString() )

        Glide.with(thiscontext)
            .load(dataSource.getJSONObject(position).getString("image").toString())
            .into(image);

//        layout.setOnClickListener{
//            Toast.makeText(thiscontext,titleTextView.text.toString(), Toast.LENGTH_SHORT).show()
//        }

        return view
    }

    private class ViewHolder {
        lateinit var layout : LinearLayout
        lateinit var titleTextView: TextView
        lateinit var detailTextView: TextView
        lateinit var image: ImageView
    }

}