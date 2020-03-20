package com.example.login_facebook

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.fragment.fragment_list_view
import com.example.fragment.fragment_list_view_detail
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        debugHashKey()

        val authen = authen()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.layout, authen,"fragment_authen")
        transaction.addToBackStack("fragment_authen")
        transaction.commit()

//        val authen = Recycler_view()
//        val manager = supportFragmentManager
//        val transaction = manager.beginTransaction()
//        transaction.replace(R.id.layout, Recycler_view(),"fragment_RecyclerView")
//        transaction.addToBackStack("fragment_list_view")
//        transaction.commit()


    }



@RequiresApi(Build.VERSION_CODES.O)
private fun debugHashKey() {
    try {
        val info = packageManager.getPackageInfo(
            "com.example.login_facebook",
            PackageManager.GET_SIGNATURES
        )
        for (signature in info.signatures) {
            val md: MessageDigest = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            Log.d("KeyHash:", Base64.getEncoder().encodeToString(md.digest()))
        }
    } catch (e: PackageManager.NameNotFoundException) {
    } catch (e: NoSuchAlgorithmException) {
    }
}

}
