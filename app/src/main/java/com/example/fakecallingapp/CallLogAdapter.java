package com.example.fakecallingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CallLogAdapter extends RecyclerView.Adapter {
    public static int CALL_LOG=1;
    List<CallLog> callLogs;
    int CallType;
    Context context;

    public CallLogAdapter(List<CallLog> callLogs, int CallType, Context context) {
        this.callLogs = callLogs;
        this.CallType=CallType;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.call_log_design,parent,false);
        return new CallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (CallType == CALL_LOG) {
            String name = callLogs.get(position).getName();
            String number = callLogs.get(position).getContact();
            String date = callLogs.get(position).getDate();
            String time = callLogs.get(position).getTime();

            ((CallViewHolder) holder).setLogDetails(name, number, date, time);
        }
    }

    @Override
    public int getItemCount() {
        return callLogs.size();
    }


    private class CallViewHolder extends RecyclerView.ViewHolder {

        private TextView Name;
        private TextView Number;
        private TextView Date;
        private TextView Time;
        public CallViewHolder(View view) {
            super(view);

            Name=(TextView) view.findViewById(R.id.call_log_name);
            Number=(TextView) view.findViewById(R.id.call_log_number);
            Date=(TextView) view.findViewById(R.id.call_log_date);
            Time=(TextView) view.findViewById(R.id.call_log_time);
        }

        public void setLogDetails(String name,String number,String date,String time){
            Name.setText(name);
            Number.setText(number);
            Date.setText(date);
            Time.setText(time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,CallActivity.class);
                    intent.putExtra("name",name);
                    intent.putExtra("number",number);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);

                }
            });
        }
    }
}
