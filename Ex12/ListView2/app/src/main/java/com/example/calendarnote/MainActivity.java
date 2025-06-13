package com.example.calendarnote;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView txtDate;
    EditText edtWork, edtHour, edtMinute;
    Button btnAddWork;
    ListView lv;
    ArrayList<String> arrayWork;
    ArrayAdapter<String> adapter;
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
        txtDate = findViewById(R.id.txt_date);
        edtHour = findViewById(R.id.edt_hour);
        edtWork = findViewById(R.id.btn_work);
        edtMinute = findViewById(R.id.edt_minute);
        btnAddWork = findViewById(R.id.btn_work);
        lv = findViewById(R.id.lv);
        arrayWork = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayWork);
        lv.setAdapter(adapter);
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtDate.setText("Hôm nay: "+ simpleDateFormat.format(currentDate));
        btnAddWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtWork.getText().toString().equals("")||edtHour.getText().toString().equals("")||
                edtMinute.getText().toString().equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Info missing");
                    builder.setMessage("Please enter all infomation of the work");
                    builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
                else {
                    String str = edtWork.getText().toString() + " - "
                            + edtHour.getText().toString()+":"
                            + edtMinute.getText().toString();
                    arrayWork.add(str);
                    adapter.notifyDataSetChanged();
                    edtMinute.setText("");
                    edtHour.setText("");
                    edtWork.setText("");
                }
            }
        });
    }
}