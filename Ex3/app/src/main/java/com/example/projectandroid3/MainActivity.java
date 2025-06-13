package com.example.projectandroid3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity { EditText edt1, edt2, edt3;
    Button btncong, btntru, btnnhan, btnchia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1 = findViewById(R.id.edta);
        edt2 = findViewById(R.id.edtb);
        edt3 = findViewById(R.id.edtc);
        btncong = findViewById(R.id.btncong);
        btntru = findViewById(R.id.btntru);
        btnchia = findViewById(R.id.btnchia);
        btnnhan = findViewById(R.id.btnnhan);
        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            int a = Integer.parseInt("0"+edt1.getText());
            int b = Integer.parseInt("0"+edt2.getText());
        }
        });
        btntru.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            int a = Integer.parseInt("0"+edt1.getText());
            int b = Integer.parseInt("0"+edt2.getText());
        }
        });
        btnnhan.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            int a = Integer.parseInt("0"+edt1.getText());
            int b = Integer.parseInt("0"+edt2.getText());
        }
        });
        btnchia.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0"+edt1.getText()); int b = Integer.parseInt("0"+edt2.getText()); if (b == 0)
                {
                    edt3.setText("B phai khac 0");
                }
            }
        });
    }
}
