package vn.edu.tlu.cse.ht2.ngovanphat.qlpk.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.R;
import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.controller.UserController;
import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmail, edtPassword;
    private Button btnLogin;
    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Khởi tạo UserController
        userController = new UserController(this);

        // Ánh xạ view
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Xử lý sự kiện click nút đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                // Kiểm tra dữ liệu nhập
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this,
                            "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Thực hiện đăng nhập
                User user = userController.login(email, password);
                if (user != null) {
                    // Đăng nhập thành công
                    if (userController.isDoctor(user)) {
                        // Chuyển đến màn hình bác sĩ
                        Intent intent = new Intent(LoginActivity.this, DoctorMainActivity.class);
                        intent.putExtra("USER_EMAIL", user.getEmail());
                        startActivity(intent);
                    } else {
                        // Chuyển đến màn hình bệnh nhân
                        Intent intent = new Intent(LoginActivity.this, PatientMainActivity.class);
                        intent.putExtra("USER_EMAIL", user.getEmail());
                        startActivity(intent);
                    }
                    finish();
                } else {
                    // Đăng nhập thất bại
                    Toast.makeText(LoginActivity.this,
                            "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}