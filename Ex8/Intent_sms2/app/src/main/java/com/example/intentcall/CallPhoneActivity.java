package com.example.intentcall;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CallPhoneActivity extends AppCompatActivity {
    private static final int REQUEST_CALL_PHONE = 1;
    Button btnBack1;
    ImageButton ibtnCall;
    EditText edtCall;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_call_phone);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtCall = findViewById(R.id.edt_call);
        btnBack1 = findViewById(R.id.btn_back1);
        ibtnCall = findViewById(R.id.ibtn_call);
        ibtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+edtCall.getText().toString()));
                if (ContextCompat.checkSelfPermission(CallPhoneActivity.this, android.Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Yêu cầu quyền nếu chưa được cấp
                    ActivityCompat.requestPermissions(CallPhoneActivity.this,
                            new String[]{android.Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
                } else {
                    // Quyền đã được cấp, thực hiện cuộc gọi
                    startActivity(intent);
                }
            }
        });
        btnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, int deviceId) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PHONE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền được cấp, thực hiện lại cuộc gọi
                if (edtCall != null && !edtCall.getText().toString().isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + edtCall.getText().toString()));
                    startActivity(intent);
                }
            } else {
                // Quyền bị từ chối, thông báo cho người dùng
                // (Tùy chọn) Hiển thị Toast hoặc Dialog
            }
        }
    }
}