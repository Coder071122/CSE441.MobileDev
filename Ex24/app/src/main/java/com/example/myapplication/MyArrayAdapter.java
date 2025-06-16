package com.example.myapplication;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<Tygia> {
    Activity context;
    int resource;
    List<Tygia> objects;
    public MyArrayAdapter(Activity context, int resource, List<Tygia> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = context.getLayoutInflater().inflate(this.resource, null);
        }
        Tygia tygia = this.objects.get(position);
        // Assuming you have TextViews and ImageView in your layout to display the data
        TextView txtType = convertView.findViewById(R.id.txt_type);
        ImageView img = convertView.findViewById(R.id.img_h1);
        TextView txtBuyTM = convertView.findViewById(R.id.txt_buytm);
        TextView txtBuyCK = convertView.findViewById(R.id.txt_buyck);
        TextView txtSellTM = convertView.findViewById(R.id.txt_selltm);
        TextView txtSellCK = convertView.findViewById(R.id.txt_sellck);
        img.setImageBitmap(tygia.getBitmap());
        txtType.setText(tygia.getType());
        txtBuyTM.setText(tygia.getBuycash());
        txtBuyCK.setText(tygia.getBuyck());

        txtSellTM.setText(tygia.getSellcash());
        txtSellCK.setText(tygia.getSellck());

        return convertView;
    }
}
