package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<List> {
    private Activity context;
    private ArrayList<List> arr;
    private int layoutID;

    public MyArrayAdapter(Activity context, int layoutID, ArrayList<List> arr) {
        super(context, layoutID, arr);
        this.arr = arr;
        this.context = context;
        this.layoutID = layoutID;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID, null);
        final List list = arr.get(position);
        ImageView imgitem = convertView.findViewById(R.id.img);
        imgitem.setImageBitmap(list.getImg());

        TextView txtTitle = convertView.findViewById(R.id.txt_title);
        txtTitle.setText(list.getTitle().toString());
        TextView txtInfo = convertView.findViewById(R.id.txt_info);
        txtInfo.setText(list.getInfo().toString());
        MainActivity.lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent inten = new Intent(Intent.ACTION_VIEW, Uri.parse(list.getLink()));
                context.startActivity(inten);
            }
        });

        return convertView;
    }
}
