package com.muayid.mya

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.myapplication.DBMmanager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {
    val  REQUEST_CODE_GALLERY =999

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val db= DBMmanager(this)
        val   pass= arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)


        setBDD.setOnClickListener {
            val vale= ContentValues()
            vale.put("img",img(imageView ))

            db.imsertNotes(vale)
            Toast.makeText(this,"Insert Iamag",Toast.LENGTH_LONG).show()

        }

       li.setOnClickListener{
           val intent = Intent(this, listDisplay ::class.java)
           startActivity(intent)

              }

        setimg.setOnClickListener {

            ActivityCompat.requestPermissions(
                this,
                pass,
                REQUEST_CODE_GALLERY
            )

        }
    }


    fun img(imag : ImageView): ByteArray {

        val bitmap=imag.drawable.toBitmap()
        val setram= ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,setram)
        val btArr=setram.toByteArray()

        return btArr

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode ==REQUEST_CODE_GALLERY)
        {
            if(grantResults.size > 0&& grantResults[0]== PackageManager.PERMISSION_GRANTED){

                val intent=Intent(Intent.ACTION_PICK)
                intent.setType("image/*")
                startActivityForResult(intent,999)


            }
            else
            {
                Toast.makeText(this,"die",Toast.LENGTH_LONG).show()

            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode ==REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data!=null) {

            val ut=data.data

            val inputStream=contentResolver.openInputStream(ut!!)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            imageView .setImageBitmap(bitmap)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }




}
