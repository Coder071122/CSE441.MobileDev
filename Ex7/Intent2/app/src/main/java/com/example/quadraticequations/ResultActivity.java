package com.example.quadraticequations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {
    TextView txtResult;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtResult = findViewById(R.id.txt_result);
        Intent myintent = getIntent();
        Bundle myBundle = myintent.getBundleExtra("mybackage");
        int a = myBundle.getInt("a");
        int b = myBundle.getInt("b");
        String result = "";
        if(a==0 && b==0){
            result = "Vô số nghiệm";
        }
        else if(a==0 && b!= 0){
            result = "Vô nghiệm";
        }
        else{
            DecimalFormat dcf = new DecimalFormat("0.##");
            result = dcf.format(-b*1.0/a);
        }
        txtResult.setText(result+"");
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}