package com.example.myapplication

import android.app.DownloadManager
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.ContactsContract
import android.widget.Toast

class DBMmanager {
    val dbNmar="trtrt"
    val dbNotesTable ="Notes"
    val colID="ID"
    val colTitle="img"
    val dbV=1


    val sqlCeate="CREATE TABLE IF NOT EXISTS $dbNotesTable ($colID INTEGER PRIMARY KEY ,$colTitle blob );"

    var sqlBD: SQLiteDatabase?=null
    constructor(context: Context)
    {

        var db=DatabsseNotes(context)
        sqlBD =db .writableDatabase

    }



    inner class DatabsseNotes:SQLiteOpenHelper{
        override fun onCreate(p0: SQLiteDatabase?) {
            Toast .makeText(context,"Databaes is cretad  ",Toast .LENGTH_LONG).show()

            p0!!.execSQL(sqlCeate)


         }

        override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
            p0!!.execSQL("DROP TABLE IF EXISTS $dbNotesTable")

          }


        var context:Context?=null
        constructor(context:Context):super (context,dbNmar,null ,dbV ){

            this.context=context

        }





    }


    fun imsertNotes(valuer:ContentValues):Long{
        val id=sqlBD !!.insert (dbNotesTable ,null,valuer)

       return id


    }
    fun query(P:Array<String>,selec:String,seArgs:Array<String>,sort:String ):Cursor{

      val db =SQLiteQueryBuilder()
        db.tables=dbNotesTable
        val cursor=db.query(sqlBD,P,selec ,seArgs,null,null,sort)
      return cursor

    }

    fun delete(selec: String,seArgs: Array<String>):Int{
        val cont =sqlBD!!.delete(dbNotesTable,selec,seArgs)
        return cont



    }
}

