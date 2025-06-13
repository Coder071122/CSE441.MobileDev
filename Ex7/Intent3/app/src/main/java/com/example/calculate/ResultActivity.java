package com.example.calculate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {
    EditText edtGetA, edtGetB;
    Button btnSum, btnDif;
    @SuppressLint("MissingInflatedId")
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
        edtGetA = findViewById(R.id.edt_geta);
        edtGetB = findViewById(R.id.edt_getb);
        btnSum = findViewById(R.id.btn_sum);
        btnDif = findViewById(R.id.btn_difference);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("package");
        int a = bundle.getInt("a");
        int b = bundle.getInt("b");
        edtGetA.setText(a+"");
        edtGetB.setText(b+"");
        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = a+b;
                intent.putExtra("result", sum);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        btnDif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dif = a-b;
                intent.putExtra("result", dif);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}