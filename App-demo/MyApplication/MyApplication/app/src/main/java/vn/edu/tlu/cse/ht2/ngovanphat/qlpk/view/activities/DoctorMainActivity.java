package vn.edu.tlu.cse.ht2.ngovanphat.qlpk.view.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.R;
import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.controller.AppointmentController;
import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.model.Appointment;
import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.view.adapters.AppointmentAdapter;

public class DoctorMainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AppointmentController appointmentController;
    private String doctorEmail;
    private TextView tvCurrentDate;
    private Button btnSelectDate, btnLogout;
    private String selectedDate = "";
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);

        doctorEmail = getIntent().getStringExtra("USER_EMAIL");
        if (doctorEmail == null) {
            Toast.makeText(this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        appointmentController = new AppointmentController(this);

        // Ánh xạ view
        recyclerView = findViewById(R.id.recyclerView);
        tvCurrentDate = findViewById(R.id.tvCurrentDate);
        btnSelectDate = findViewById(R.id.btnSelectDate);
        btnLogout = findViewById(R.id.btnLogout);
        swipeRefresh = findViewById(R.id.swipeRefresh);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Setup SwipeRefreshLayout
        swipeRefresh.setOnRefreshListener(() -> {
            loadAppointments();
            swipeRefresh.setRefreshing(false);
        });

        // Set up listeners
        btnSelectDate.setOnClickListener(v -> showDatePicker());
        btnLogout.setOnClickListener(v -> logout());

        // Load lịch hẹn của ngày hiện tại
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        selectedDate = sdf.format(new Date());
        tvCurrentDate.setText("Ngày: " + selectedDate);
        loadAppointments();
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    selectedDate = String.format("%d-%02d-%02d",
                            selectedYear, selectedMonth + 1, selectedDay);
                    tvCurrentDate.setText("Ngày: " + selectedDate);
                    loadAppointments();
                }, year, month, day);

        datePickerDialog.show();
    }

    private void loadAppointments() {
        List<Appointment> appointments = appointmentController.getDoctorAppointmentsByDate(doctorEmail, selectedDate);
        AppointmentAdapter adapter = new AppointmentAdapter(appointments, true,
                new AppointmentAdapter.AppointmentClickListener() {
                    @Override
                    public void onConfirmClick(Appointment appointment) {
                        showNoteDialog(appointment);
                    }

                    @Override
                    public void onCancelClick(Appointment appointment) {
                        appointmentController.updateAppointmentStatus(appointment.getId(), "Đã hủy");
                        loadAppointments();
                    }
                });
        recyclerView.setAdapter(adapter);
    }

    private void showNoteDialog(Appointment appointment) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ghi chú tình trạng bệnh nhân");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        input.setMinLines(3);
        input.setText(appointment.getNotes());
        builder.setView(input);

        builder.setPositiveButton("Lưu", (dialog, which) -> {
            String notes = input.getText().toString().trim();
            appointmentController.updateAppointmentNotes(appointment.getId(), notes);
            appointmentController.updateAppointmentStatus(appointment.getId(), "Đã xác nhận");
            loadAppointments();
        });
        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void logout() {
        // Xóa thông tin đăng nhập và quay về màn hình login
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}