package com.example.fakecallingapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {
    List<Contact> ContactList=new ArrayList<>();
    RecyclerView contactRecyclerView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_contact);
        contactRecyclerView=(RecyclerView) findViewById(R.id.contact_list);
        progressBar=findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        getContactList();

    }


    private void getContactList() {
        Uri uri= ContactsContract.Contacts.CONTENT_URI;
        String sort =ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
        Cursor cursor=getContentResolver().query(uri,null,null,null,sort);
        if(cursor.getCount()>0)
        {
            while (cursor.moveToNext()){
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                @SuppressLint("Range") String name =cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                Uri phoneUri=ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String selection=ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" =?";
                Cursor PhoneCursor=getContentResolver().query(
                        phoneUri,null,selection,new String[]{id},null);
                if(PhoneCursor.moveToNext())
                {
                 @SuppressLint("Range") String number=PhoneCursor.getString(PhoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                 Contact contact=new Contact(name,number);
                    ContactList.add(contact);
                    PhoneCursor.close();
                }
            }
            cursor.close();
            progressBar.setVisibility(View.GONE);
        }
        ContactAdapter adapterr=new ContactAdapter(ContactList,getApplicationContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        contactRecyclerView.setLayoutManager(layoutManager);
        contactRecyclerView.setAdapter(adapterr);
    }

}