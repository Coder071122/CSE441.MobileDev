package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnParse;
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter adapter;
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
        btnParse = findViewById(R.id.btn_parse);
        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(adapter);
        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseJson();
            }

            private void parseJson() {
                String json = null;
                try {
                    InputStream is = getAssets().open("computer.json");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    json = new String(buffer, "UTF-8");
                    JSONObject jsonObject = new JSONObject(json);
                    mylist.add(jsonObject.getString("MaDM"));
                    mylist.add(jsonObject.getString("TenDM"));
                    JSONArray jsonArray = jsonObject.getJSONArray("Sanphams");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject product = jsonArray.getJSONObject(i);
                        mylist.add(product.getString("MaSP")+" - "+product.getString("TenSP"));
                        mylist.add(product.getString("SoLuong")+" - "+product.getString("DonGia"));
                        mylist.add(product.getString("ThanhTien")+" - "+product.getString("Hinh"));
                    }
                    adapter.notifyDataSetChanged();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e1) {
                    throw new RuntimeException(e1);
                }
            }
        });
    }
}