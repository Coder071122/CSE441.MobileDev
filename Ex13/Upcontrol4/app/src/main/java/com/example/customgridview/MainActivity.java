package com.example.customgridview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static String[] arrayName = {"Ảnh 1", "Ảnh 2", "Ảnh 3", "Ảnh 4","Ảnh 5","Ảnh 6"};
    public static int[] imageName = {R.drawable.travel, R.drawable.travel,R.drawable.travel, R.drawable.travel, R.drawable.travel, R.drawable.travel};
    GridView gr;
    MyArrayAdapter adapter;
    ArrayList<Image> arrimg;
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
        gr = findViewById(R.id.gridl);
        arrimg = new ArrayList<Image>();
        adapter = new MyArrayAdapter(this, R.layout.listitem, arrimg);
        gr.setAdapter(adapter);

        for(int i=0; i<imageName.length; i++){
            Image myimg = new Image(imageName[i], arrayName[i]);
            arrimg.add(myimg);
            adapter.notifyDataSetChanged();

        }
        gr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("title", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}