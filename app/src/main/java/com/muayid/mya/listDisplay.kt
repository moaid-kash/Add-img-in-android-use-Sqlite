package com.muayid.mya

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.myapplication.DBMmanager
import kotlinx.android.synthetic.main.activity_list_display.*
import kotlinx.android.synthetic.main.noet.view.*

class listDisplay : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_display)
        queryTitle("%")
    }



    var listNotes=ArrayList<img>()



    inner class MyNotesAdpater: BaseAdapter {
        @SuppressLint("ViewHolder")
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val myView=layoutInflater .inflate(R.layout .noet ,null)
            var note=listN [p0]
            myView .textView.text  = note.id.toString()
            val bmp= BitmapFactory.decodeByteArray(note.img,0,note.img.size)
            myView.imageView.setImageBitmap(bmp)


            // myView .imageView  =note .

            return myView


        }

        override fun getItem(p0: Int): Any {
            return listN [p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return listN .size

        }

        var listN=ArrayList<img>()
        var content: Context?=null
        constructor(content: Context, listN:ArrayList<img>):super()
        {
            this.content=content
            this.listN=listN


        }


    }




    fun queryTitle(tilt:String){
        var dbMmanager= DBMmanager(this)
        val p = arrayOf("ID","img")
        val selec= arrayOf(tilt)
        var cursor=dbMmanager.query(p,"ID like ?" ,selec,"ID")
        if (cursor.moveToFirst()){
            listNotes.clear()
            do {
                val id =cursor.getInt(cursor.getColumnIndex("ID"))
                val des =cursor.getBlob(cursor.getColumnIndex("img"))
                listNotes.add(img(des ,id))
            }while (cursor.moveToNext())

        }
        var myAdapter=MyNotesAdpater(this,listNotes)
        lstkash.adapter=myAdapter

    }


}
