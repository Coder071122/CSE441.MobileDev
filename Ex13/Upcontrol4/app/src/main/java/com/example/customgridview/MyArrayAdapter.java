package com.example.customgridview;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Image> {
    Activity context = null;
    int LayoutId;
    ArrayList<Image> myArray = null;
    public MyArrayAdapter(Activity context, int LayoutId, ArrayList<Image> a) {
        super(context, LayoutId, a);
        this.context = context;
        this.LayoutId = LayoutId;
        this.myArray = a;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(LayoutId, null);
        final  Image myimage = myArray.get(position);
        final ImageView imgitem = convertView.findViewById(R.id.imageView);
        final TextView myname = convertView.findViewById(R.id.textView);
        return convertView;
    }
}
