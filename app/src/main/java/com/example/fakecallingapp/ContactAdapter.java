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
public class ContactAdapter extends RecyclerView.Adapter {
    public static int CONTACTS=1;
    List<Contact> contacts;
    Context context;

    public ContactAdapter(List<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.select_contact_design,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            String name = contacts.get(position).getNamee();
            String number = contacts.get(position).getContactt();

            ((ContactViewHolder) holder).setContactDetails(name, number);


    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    private class ContactViewHolder extends RecyclerView.ViewHolder {

        private TextView Name;
        private TextView Number;
        public ContactViewHolder(View view) {
            super(view);

            Name=(TextView) view.findViewById(R.id.contact_name);
            Number=(TextView) view.findViewById(R.id.contact_number);
        }

        public void setContactDetails(String name,String number){
            Name.setText(name);
            Number.setText(number);
            itemView.findViewById(R.id.select_contact_layout).setOnClickListener(new View.OnClickListener() {
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
