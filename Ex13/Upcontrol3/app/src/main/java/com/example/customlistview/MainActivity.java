package com.example.customlistview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String a[] = {"Điện thoại iphone 12", "Điện thoại samsung s20", "Điện thoại Nokia", "Điện thoại Oppo"};
    int imagePhone[] = {R.drawable.android, R.drawable.android, R.drawable.android, R.drawable.android};
    ArrayList<Phone> list;
    MyArrayAdapter adapter;
    ListView lv;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lv = findViewById(R.id.lv);
        list = new ArrayList<>();
        for(int i=0; i<a.length; i++){
            list.add(new Phone(imagePhone[i],a[i]));
        }
        adapter = new MyArrayAdapter(this, R.layout.layout_listview, list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("name", a[position]);
                startActivity(intent);
            }
        });
    }
}