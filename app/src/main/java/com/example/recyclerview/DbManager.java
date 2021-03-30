package com.example.recyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DbManager {
    private DataBaseHelper dbHelper;
    private Context context;
    private NoteModel model;
    private SQLiteDatabase database;
    private static final String TAG=DbManager.class.getSimpleName();

    public DbManager (Context c){
        context = c;

    }

    public DbManager open() throws Exception{
      dbHelper = new DataBaseHelper(context);
      database = dbHelper.getWritableDatabase();
      return this;
    }

    public void close(){
     dbHelper.close();
    }

    public void insert(String note){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.NOTE,note);
        database.insert(DataBaseHelper.TABLE_NAME,null, contentValues);
        close();
    }
    public Cursor fetch(){
       String [] column = new String[]{DataBaseHelper.NOTE};
       Cursor cursor = database.query(DataBaseHelper.TABLE_NAME,column,null,null,null,null,null);
        if(cursor!=null){
           cursor.moveToFirst();
        } return cursor;
    }
    public int update(long id,String note){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.NOTE,note);
        int i = database.update(DataBaseHelper.TABLE_NAME,contentValues, DataBaseHelper._ID + "=" + id,null);
        close();
        return i;
    }
    public void delete(NoteModel model){
        int rows=database.delete(DataBaseHelper.TABLE_NAME,DataBaseHelper._ID + "=?",new String[]{String.valueOf(model.getID())});
        Log.d(TAG,"Number of rows Affected : "+rows);
//        dbHelper.close();
    }

    public ArrayList<NoteModel> getNotes(){
        ArrayList<NoteModel> arrayList = new ArrayList<>();

        String select_query = "SELECT *FROM " + DataBaseHelper.TABLE_NAME;
        Cursor cursor = database.rawQuery(select_query,null);
        if (cursor.moveToFirst()){
            do {
                NoteModel noteModel = new NoteModel();
                noteModel.setID(cursor.getInt(0));
                noteModel.setNote(cursor.getString(1));
                arrayList.add(noteModel);
            }while (cursor.moveToNext());
            }
          return arrayList;
        }

    }

