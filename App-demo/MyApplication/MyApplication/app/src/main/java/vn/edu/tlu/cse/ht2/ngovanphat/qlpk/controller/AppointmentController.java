package vn.edu.tlu.cse.ht2.ngovanphat.qlpk.controller;
import android.content.Context;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.database.DatabaseHelper;
import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.model.Appointment;
public class AppointmentController {
    private DatabaseHelper dbHelper;

    public AppointmentController(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public boolean createAppointment(Appointment appointment) {
        if (appointment == null) {
            return false;
        }
        return dbHelper.createAppointment(appointment);
    }

    // Lấy danh sách lịch hẹn của bệnh nhân
    public List<Appointment> getPatientAppointments(String patientEmail) {
        if (patientEmail == null || patientEmail.isEmpty()) {
            return null;
        }
        return dbHelper.getPatientAppointments(patientEmail);
    }

    // Cập nhật trạng thái lịch hẹn
    public boolean updateAppointmentStatus(String appointmentId, String status) {
        if (appointmentId == null || status == null) {
            return false;
        }
        return dbHelper.updateAppointmentStatus(appointmentId, status) > 0;
    }

    // Kiểm tra lịch hẹn có phải trong tương lai không
    public boolean isFutureAppointment(Appointment appointment) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date appointmentDate = dateFormat.parse(appointment.getDate() + " " + appointment.getTime());
            Date currentDate = new Date();
            return appointmentDate.after(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Appointment> getDoctorAppointmentsByDate(String doctorEmail, String date) {
        return dbHelper.getDoctorAppointmentsByDate(doctorEmail, date);
    }

    public boolean updateAppointmentNotes(String appointmentId, String notes) {
        return dbHelper.updateAppointmentNotes(appointmentId, notes);
    }

    // Kiểm tra trạng thái lịch hẹn
    public boolean canCancelAppointment(Appointment appointment) {
        return isFutureAppointment(appointment) &&
                !"Đã hủy".equals(appointment.getStatus());
    }

    public boolean isConfirmedAppointment(Appointment appointment) {
        return "Đã xác nhận".equals(appointment.getStatus());
    }

    public boolean isPendingAppointment(Appointment appointment) {
        return "Chờ xác nhận".equals(appointment.getStatus());
    }
}