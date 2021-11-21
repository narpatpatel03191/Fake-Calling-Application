package com.example.fakecallingapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SelectContact extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_contect);

        RecyclerView callLogRecyclerView = findViewById(R.id.select_contact_list);
        List<CallLog> callLogList=new ArrayList<>();
        Cursor cursor_Android_Contacts = null;
        ContentResolver contentResolver = getContentResolver();

        try {
            cursor_Android_Contacts = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        } catch (Exception ex) {
            Log.e("Error on contact", ex.getMessage());
        }
        for (int i=0;i<10;i++){
            callLogList.add(new CallLog("Himanshu Pandey","+91 6378791396"));
            callLogList.add(new CallLog("Narpat Patel","+91 8949473540"));
        }
        CallLogAdapter adapter=new CallLogAdapter(callLogList,CallLogAdapter.CONTACTS);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        callLogRecyclerView.setLayoutManager(layoutManager);
        callLogRecyclerView.setAdapter(adapter);
    }
}
