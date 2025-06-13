package com.example.customlistview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<Phone> {
    Activity context;
    int idlayout;
    ArrayList<Phone> list;


    public MyArrayAdapter( Activity context, int idlayout, ArrayList<Phone> list) {
        super(context, idlayout, list);
        this.context = context;
        this.idlayout = idlayout;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(idlayout, null);
        Phone phone = list.get(position);
        ImageView imgphone = convertView.findViewById(R.id.img_phone);
        imgphone.setImageResource(phone.getImagephone());
        TextView txtNamePhone = convertView.findViewById(R.id.txt_namephone);
        txtNamePhone.setText(phone.getNamephone());
        return convertView;
    }
}
