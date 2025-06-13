package vn.edu.tlu.cse.ht2.ngovanphat.qlpk.view.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.content.Intent;
import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.R;
import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.controller.AppointmentController;
import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.model.Appointment;
import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.view.adapters.AppointmentAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class PatientMainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AppointmentController appointmentController;
    private String patientEmail;
    private Button btnLogout;
    private SwipeRefreshLayout swipeRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main);

        // Lấy email bệnh nhân từ Intent
        patientEmail = getIntent().getStringExtra("USER_EMAIL");
        if (patientEmail == null) {
            Toast.makeText(this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Khởi tạo controller
        appointmentController = new AppointmentController(this);

        // Ánh xạ view
        recyclerView = findViewById(R.id.recyclerView);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fabBookAppointment = findViewById(R.id.fabBookAppointment);
        fabBookAppointment.setOnClickListener(v -> {
            Intent intent = new Intent(this, BookAppointmentActivity.class);
            intent.putExtra("USER_EMAIL", patientEmail);
            startActivity(intent);
        });

        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(v -> logout());
        // Setup SwipeRefreshLayout
        swipeRefresh.setOnRefreshListener(() -> {
            loadAppointments();
            swipeRefresh.setRefreshing(false);
        });

        // Load danh sách lịch hẹn
        loadAppointments();


    }


    private void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    private void loadAppointments() {
        List<Appointment> appointments = appointmentController.getPatientAppointments(patientEmail);
        AppointmentAdapter adapter = new AppointmentAdapter(appointments, false,
                new AppointmentAdapter.AppointmentClickListener() {
                    @Override
                    public void onConfirmClick(Appointment appointment) {
                        // Bệnh nhân không có quyền xác nhận
                    }

                    @Override
                    public void onCancelClick(Appointment appointment) {
                        // Xử lý hủy lịch hẹn
                        appointmentController.updateAppointmentStatus(appointment.getId(), "Đã hủy");
                        loadAppointments(); // Tải lại danh sách
                    }
                });
        recyclerView.setAdapter(adapter);
    }
}