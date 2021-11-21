package com.example.fakecallingapp;

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
    int CallType;

    public ContactAdapter(List<Contact> contacts,int CallType) {
        this.contacts = contacts;
        this.CallType=CallType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.select_contact_design,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(CallType==CONTACTS)
        {
            String name = contacts.get(position).getNamee();
            String number = contacts.get(position).getContactt();

            ((ContactViewHolder) holder).setContactDetails(name, number);
        }

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
        }
    }
}
