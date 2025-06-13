package vn.edu.tlu.cse.ht2.ngovanphat.qlpk.view.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.R;
import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.controller.AppointmentController;
import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.controller.UserController;
import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.model.Appointment;
import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.model.User;

public class BookAppointmentActivity extends AppCompatActivity {
    private Spinner spinnerDoctor;
    private Button btnSelectDate, btnSelectTime, btnBook;
    private TextView tvSelectedDoctor, tvSelectedDate, tvSelectedTime;
    private EditText edtNotes;

    private UserController userController;
    private AppointmentController appointmentController;
    private String selectedDate = "";
    private String selectedTime = "";
    private String patientEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        // Lấy email bệnh nhân từ Intent
        patientEmail = getIntent().getStringExtra("USER_EMAIL");
        if (patientEmail == null) {
            Toast.makeText(this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Khởi tạo controllers
        userController = new UserController(this);
        appointmentController = new AppointmentController(this);

        // Ánh xạ views
        initViews();
        // Load danh sách bác sĩ
        loadDoctors();
        // Set up listeners
        setupListeners();
    }

    private void initViews() {
        spinnerDoctor = findViewById(R.id.spinnerDoctor);
        btnSelectDate = findViewById(R.id.btnSelectDate);
        btnSelectTime = findViewById(R.id.btnSelectTime);
        btnBook = findViewById(R.id.btnBook);
        tvSelectedDoctor = findViewById(R.id.tvSelectedDoctor);
        tvSelectedDate = findViewById(R.id.tvSelectedDate);
        tvSelectedTime = findViewById(R.id.tvSelectedTime);
        edtNotes = findViewById(R.id.edtNotes);
    }

    private void loadDoctors() {
        List<User> doctors = userController.getAllDoctors();
        if (doctors == null || doctors.isEmpty()) {
            Toast.makeText(this, "Không có bác sĩ nào trong hệ thống", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo mảng String để hiển thị tên bác sĩ
        String[] doctorNames = new String[doctors.size()];
        for (int i = 0; i < doctors.size(); i++) {
            User doctor = doctors.get(i);
            doctorNames[i] = doctor.getFullName() + " - " + doctor.getSpecialty();
        }

        // Sử dụng ArrayAdapter với mảng String
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, doctorNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDoctor.setAdapter(adapter);
    }

    private void setupListeners() {
        btnSelectDate.setOnClickListener(v -> showDatePicker());
        btnSelectTime.setOnClickListener(v -> showTimePicker());
        btnBook.setOnClickListener(v -> bookAppointment());
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
                    tvSelectedDate.setText("Ngày khám: " + selectedDate);
                }, year, month, day);

        // Giới hạn chọn ngày từ hôm nay trở đi
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, selectedHour, selectedMinute) -> {
                    selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute);
                    tvSelectedTime.setText("Giờ khám: " + selectedTime);
                }, hour, minute, true);

        timePickerDialog.show();
    }

    private void bookAppointment() {
        // Kiểm tra đã chọn đủ thông tin
        if (selectedDate.isEmpty() || selectedTime.isEmpty()) {
            Toast.makeText(this, "Vui lòng chọn đầy đủ thông tin",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // Lấy vị trí được chọn từ spinner
        int selectedPosition = spinnerDoctor.getSelectedItemPosition();
        List<User> doctors = userController.getAllDoctors();
        User selectedDoctor = doctors.get(selectedPosition);

        String notes = edtNotes.getText().toString().trim();

        // Tạo lịch hẹn mới
        Appointment appointment = new Appointment(
                UUID.randomUUID().toString(),
                selectedDate,
                selectedTime,
                selectedDoctor.getEmail(),
                patientEmail,
                "Chưa xác nhận",
                notes
        );

        // Lưu lịch hẹn
        if (appointmentController.createAppointment(appointment)) {
            Toast.makeText(this, "Đặt lịch thành công", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Đặt lịch thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}