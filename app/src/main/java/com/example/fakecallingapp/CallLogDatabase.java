package com.example.fakecallingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class CallLogDatabase extends SQLiteOpenHelper {



    private static final int DB_VERSION=1;
    private static final String TAG="call db helper";
    private static  final String TABLE_NAME="callLogs";
    private static final String COL1="id";
    private static final String COL2="name";
    private static final String COL3="number";
    private static final String COL4="date";
    private static final String COL5="time";

    public CallLogDatabase(Context context){
        super(context,TAG,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable="CREATE TABLE "+TABLE_NAME+"( "+COL1+" INT PRIMARY KEY,"+COL2+" TEXT,"+COL3+" TEXT NOT NULL,"+COL4+" TEXT,"+COL5+" TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void addToLogs(CallLog callLog){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL2,callLog.getName());
        contentValues.put(COL3,callLog.getContact());
        contentValues.put(COL4,callLog.getDate());
        contentValues.put(COL5,callLog.getTime());
        long result=db.insert(TABLE_NAME,null,contentValues);
        db.close();

//        return result==-1?"Some erorr occured":"data added successfully";
    }

    public List<CallLog> getLogs(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);


        List<CallLog> list=new ArrayList<>();

            while (cursor.moveToNext()){
                list.add(new CallLog(cursor.getString(1),cursor.getString(2),
                        cursor.getString(4),cursor.getString(3)));
            }

        cursor.close();
        db.close();
        return list;
    }
}
