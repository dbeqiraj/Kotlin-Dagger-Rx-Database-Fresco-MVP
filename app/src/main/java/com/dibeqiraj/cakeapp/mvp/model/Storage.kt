package com.dibeqiraj.cakeapp.mvp.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import javax.inject.Inject

class Storage @Inject constructor(context: Context?) : SQLiteOpenHelper(context, "cakes_db", null, 1) {

    val TAG: String? = Storage::class.simpleName
    val ID: String = "id"
    val TITLE: String = "title"
    val PREVIEW_DESCRIPTION: String = "previewDescription"
    val DETAIL_DESCRIPTION: String = "detailDescription"
    val IMAGE_URL: String = "imageUrl"
    val TABLE_NAME: String = "cakes"
    val DROP_TABLE: String = "DROP TABLE IF EXISTS ${TABLE_NAME}"
    val SELECT_QUERY: String = "SELECT * FROM ${TABLE_NAME}"
    val CREATE_TABLE: String = "create table ${TABLE_NAME}(" +
            ID + " integer primary key autoincrement not null," +
            TITLE + " text not null," +
            PREVIEW_DESCRIPTION + " text not null," +
            DETAIL_DESCRIPTION + " text not null," +
            IMAGE_URL + " text not null)"

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            db?.execSQL(CREATE_TABLE)
        } catch (e: SQLException) {
            Log.d(TAG, e.message)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun addCake(cake: Cake) {
        val db: SQLiteDatabase = writableDatabase
        val values = ContentValues()

        values.put(TITLE, cake.title)
        values.put(PREVIEW_DESCRIPTION, cake.previewDescription)
        values.put(DETAIL_DESCRIPTION, cake.detailDescription)
        values.put(IMAGE_URL, cake.imageUrl)

        try {
            db.insert(TABLE_NAME, null, values)
        } catch (e: SQLException) {
            Log.d(TAG, e.message)
        }

        db.close()
    }

    fun getSavedCakes(): MutableList<Cake> {
        val cakeList: MutableList<Cake> = ArrayList()

        val db: SQLiteDatabase = writableDatabase

        try {
            val cursor: Cursor = db.rawQuery(SELECT_QUERY, null)

            if (cursor.count > 0 && cursor.moveToFirst()) {
                do {
                    val cake = Cake(
                            cursor.getInt(cursor.getColumnIndex(ID)),
                            cursor.getString(cursor.getColumnIndex(TITLE)),
                            cursor.getString(cursor.getColumnIndex(PREVIEW_DESCRIPTION)),
                            cursor.getString(cursor.getColumnIndex(DETAIL_DESCRIPTION)),
                            cursor.getString(cursor.getColumnIndex(IMAGE_URL))
                    )
                    cakeList.add(cake)

                } while (cursor.moveToNext())
            }

            cursor.close()
        } catch (e: SQLException) {
            Log.d(TAG, e.message)
        }

        return cakeList
    }
}