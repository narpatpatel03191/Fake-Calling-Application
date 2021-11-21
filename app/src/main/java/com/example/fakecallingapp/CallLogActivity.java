package com.example.fakecallingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CallLogActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);

        RecyclerView callLogRecyclerView = findViewById(R.id.log_contact_list);
        List<CallLog> callLogList=new ArrayList<>();

        for (int i=0;i<10;i++){
            callLogList.add(new CallLog("Himanshu Pandey","+91 6378791396","12:40 AM","11 Nov 2021"));
            callLogList.add(new CallLog("Narpat Patel","+91 8949473540","12:10 AM","12 Nov 2021"));
        }
        CallLogAdapter adapter=new CallLogAdapter(callLogList,CallLogAdapter.CALL_LOG);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        callLogRecyclerView.setLayoutManager(layoutManager);
        callLogRecyclerView.setAdapter(adapter);
    }
}