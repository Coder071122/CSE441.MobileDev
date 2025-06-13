package vn.edu.tlu.cse.ht2.ngovanphat.qlpk.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.model.User;
import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.model.Appointment;
public class DatabaseHelper  extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "ClinicDB";
    private static final int DATABASE_VERSION = 1;

    // Bảng Users
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ROLE = "role";
    private static final String COLUMN_FULL_NAME = "full_name";
    private static final String COLUMN_SPECIALTY = "specialty";

    // Bảng Appointments
    private static final String TABLE_APPOINTMENTS = "appointments";
    private static final String COLUMN_APPOINTMENT_ID = "id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_DOCTOR_EMAIL = "doctor_email";
    private static final String COLUMN_PATIENT_EMAIL = "patient_email";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_NOTES = "notes";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng Users
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_USER_ID + " TEXT PRIMARY KEY,"
                + COLUMN_EMAIL + " TEXT UNIQUE,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_ROLE + " TEXT,"
                + COLUMN_FULL_NAME + " TEXT,"
                + COLUMN_SPECIALTY + " TEXT"
                + ")";

        // Tạo bảng Appointments
        String CREATE_APPOINTMENTS_TABLE = "CREATE TABLE " + TABLE_APPOINTMENTS + "("
                + COLUMN_APPOINTMENT_ID + " TEXT PRIMARY KEY,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_TIME + " TEXT,"
                + COLUMN_DOCTOR_EMAIL + " TEXT,"
                + COLUMN_PATIENT_EMAIL + " TEXT,"
                + COLUMN_STATUS + " TEXT,"
                + COLUMN_NOTES + " TEXT"
                + ")";

        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_APPOINTMENTS_TABLE);

        // Thêm dữ liệu mẫu
        insertSampleUsers(db);
        insertSampleAppointments(db);
    }
    private void insertSampleUsers(SQLiteDatabase db) {
        // Thêm các bác sĩ
        String insertDoctor1 = "INSERT INTO " + TABLE_USERS + " VALUES("
                + "'1', 'doctor1@clinic.com', '123456', 'Bác sĩ', 'Dr. Nguyễn Văn A', 'Nội tổng quát')";
        String insertDoctor2 = "INSERT INTO " + TABLE_USERS + " VALUES("
                + "'2', 'doctor2@clinic.com', 'abc123', 'Bác sĩ', 'Dr. Trần Thị B', 'Nhi khoa')";
        String insertDoctor3 = "INSERT INTO " + TABLE_USERS + " VALUES("
                + "'D3', 'doctor3@clinic.com', 'qwerty', 'Bác sĩ', 'Dr. Lê Minh C', 'Tai - Mũi - Họng')";
        // Thêm các bệnh nhân
        String insertPatient1 = "INSERT INTO " + TABLE_USERS + " VALUES("
                + "'4', 'patient1@email.com', '987654', 'Bệnh nhân', 'Lê Văn D', NULL)";
        String insertPatient2 = "INSERT INTO " + TABLE_USERS + " VALUES("
                + "'5', 'patient2@email.com', '654321', 'Bệnh nhân', 'Trần Thị E', NULL)";
        String insertPatient3 = "INSERT INTO " + TABLE_USERS + " VALUES("
                + "'6', 'patient3@email.com', '321654', 'Bệnh nhân', 'Nguyễn Văn F', NULL)";

        db.execSQL(insertDoctor1);
        db.execSQL(insertDoctor2);
        db.execSQL(insertDoctor3);
        db.execSQL(insertPatient1);
        db.execSQL(insertPatient2);
        db.execSQL(insertPatient3);
    }
    private void insertSampleAppointments(SQLiteDatabase db) {
        // Thêm các lịch hẹn mẫu
        String insertAppointment1 = "INSERT INTO " + TABLE_APPOINTMENTS + " VALUES("
                + "'1', '2025-04-10', '08:00', 'doctor1@clinic.com', 'patient1@email.com', 'Chưa xác nhận', NULL)";
        String insertAppointment2 = "INSERT INTO " + TABLE_APPOINTMENTS + " VALUES("
                + "'2', '2025-04-10', '09:30', 'doctor2@clinic.com', 'patient2@email.com', 'Đã xác nhận', 'Kiểm tra sốt cao')";
        String insertAppointment3 = "INSERT INTO " + TABLE_APPOINTMENTS + " VALUES("
                + "'3', '2025-04-11', '10:00', 'doctor3@clinic.com', 'patient3@email.com', 'Chưa xác nhận', NULL)";
        String insertAppointment4 = "INSERT INTO " + TABLE_APPOINTMENTS + " VALUES("
                + "'4', '2025-04-11', '14:00', 'doctor1@clinic.com', 'patient2@email.com', 'Đã xác nhận', 'Theo dõi huyết áp')";
        String insertAppointment5 = "INSERT INTO " + TABLE_APPOINTMENTS + " VALUES("
                + "'5', '2025-04-12', '16:30', 'doctor2@clinic.com', 'patient1@email.com', 'Chưa xác nhận', NULL)";

        db.execSQL(insertAppointment1);
        db.execSQL(insertAppointment2);
        db.execSQL(insertAppointment3);
        db.execSQL(insertAppointment4);
        db.execSQL(insertAppointment5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPOINTMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
    // Kiểm tra đăng nhập
    public User getUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_EMAIL,
                COLUMN_PASSWORD,
                COLUMN_ROLE,
                COLUMN_FULL_NAME,
                COLUMN_SPECIALTY
        };

        String selection = COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            try {
                int idIndex = cursor.getColumnIndexOrThrow(COLUMN_USER_ID);
                int emailIndex = cursor.getColumnIndexOrThrow(COLUMN_EMAIL);
                int passwordIndex = cursor.getColumnIndexOrThrow(COLUMN_PASSWORD);
                int roleIndex = cursor.getColumnIndexOrThrow(COLUMN_ROLE);
                int fullNameIndex = cursor.getColumnIndexOrThrow(COLUMN_FULL_NAME);
                int specialtyIndex = cursor.getColumnIndexOrThrow(COLUMN_SPECIALTY);

                user = new User(
                        cursor.getString(idIndex),
                        cursor.getString(emailIndex),
                        cursor.getString(passwordIndex),
                        cursor.getString(roleIndex),
                        cursor.getString(fullNameIndex),
                        cursor.getString(specialtyIndex)
                );
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cursor.close();
            }
        }
        return user;
    }

    public List<Appointment> getDoctorAppointments(String doctorEmail) {
        List<Appointment> appointments = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {
                COLUMN_APPOINTMENT_ID,
                COLUMN_DATE,
                COLUMN_TIME,
                COLUMN_DOCTOR_EMAIL,
                COLUMN_PATIENT_EMAIL,
                COLUMN_STATUS,
                COLUMN_NOTES
        };

        String selection = COLUMN_DOCTOR_EMAIL + " = ?";
        String[] selectionArgs = {doctorEmail};
        String orderBy = COLUMN_DATE + " ASC, " + COLUMN_TIME + " ASC";

        Cursor cursor = db.query(TABLE_APPOINTMENTS, columns, selection, selectionArgs, null, null, orderBy);

        if (cursor.moveToFirst()) {
            do {
                try {
                    int idIndex = cursor.getColumnIndexOrThrow(COLUMN_APPOINTMENT_ID);
                    int dateIndex = cursor.getColumnIndexOrThrow(COLUMN_DATE);
                    int timeIndex = cursor.getColumnIndexOrThrow(COLUMN_TIME);
                    int doctorEmailIndex = cursor.getColumnIndexOrThrow(COLUMN_DOCTOR_EMAIL);
                    int patientEmailIndex = cursor.getColumnIndexOrThrow(COLUMN_PATIENT_EMAIL);
                    int statusIndex = cursor.getColumnIndexOrThrow(COLUMN_STATUS);
                    int notesIndex = cursor.getColumnIndexOrThrow(COLUMN_NOTES);

                    Appointment appointment = new Appointment(
                            cursor.getString(idIndex),
                            cursor.getString(dateIndex),
                            cursor.getString(timeIndex),
                            cursor.getString(doctorEmailIndex),
                            cursor.getString(patientEmailIndex),
                            cursor.getString(statusIndex),
                            cursor.getString(notesIndex)
                    );
                    appointments.add(appointment);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return appointments;
    }

    // Phương thức getPatientAppointments
    public List<Appointment> getPatientAppointments(String patientEmail) {
        List<Appointment> appointments = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {
                COLUMN_APPOINTMENT_ID,
                COLUMN_DATE,
                COLUMN_TIME,
                COLUMN_DOCTOR_EMAIL,
                COLUMN_PATIENT_EMAIL,
                COLUMN_STATUS,
                COLUMN_NOTES
        };

        String selection = COLUMN_PATIENT_EMAIL + " = ?";
        String[] selectionArgs = {patientEmail};
        String orderBy = COLUMN_DATE + " ASC, " + COLUMN_TIME + " ASC";

        Cursor cursor = db.query(TABLE_APPOINTMENTS, columns, selection, selectionArgs, null, null, orderBy);

        if (cursor.moveToFirst()) {
            do {
                try {
                    int idIndex = cursor.getColumnIndexOrThrow(COLUMN_APPOINTMENT_ID);
                    int dateIndex = cursor.getColumnIndexOrThrow(COLUMN_DATE);
                    int timeIndex = cursor.getColumnIndexOrThrow(COLUMN_TIME);
                    int doctorEmailIndex = cursor.getColumnIndexOrThrow(COLUMN_DOCTOR_EMAIL);
                    int patientEmailIndex = cursor.getColumnIndexOrThrow(COLUMN_PATIENT_EMAIL);
                    int statusIndex = cursor.getColumnIndexOrThrow(COLUMN_STATUS);
                    int notesIndex = cursor.getColumnIndexOrThrow(COLUMN_NOTES);

                    Appointment appointment = new Appointment(
                            cursor.getString(idIndex),
                            cursor.getString(dateIndex),
                            cursor.getString(timeIndex),
                            cursor.getString(doctorEmailIndex),
                            cursor.getString(patientEmailIndex),
                            cursor.getString(statusIndex),
                            cursor.getString(notesIndex)
                    );
                    appointments.add(appointment);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return appointments;
    }

    // Cập nhật trạng thái lịch hẹn
    public int updateAppointmentStatus(String appointmentId, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STATUS, status);

        return db.update(TABLE_APPOINTMENTS, values,
                COLUMN_APPOINTMENT_ID + " = ?",
                new String[]{appointmentId});
    }
    // Thêm phương thức getAllDoctors
    public List<User> getAllDoctors() {
        List<User> doctors = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_EMAIL,
                COLUMN_PASSWORD,
                COLUMN_ROLE,
                COLUMN_FULL_NAME,
                COLUMN_SPECIALTY
        };

        String selection = COLUMN_ROLE + " = ?";
        String[] selectionArgs = {"Bác sĩ"};

        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                try {
                    int idIndex = cursor.getColumnIndexOrThrow(COLUMN_USER_ID);
                    int emailIndex = cursor.getColumnIndexOrThrow(COLUMN_EMAIL);
                    int passwordIndex = cursor.getColumnIndexOrThrow(COLUMN_PASSWORD);
                    int roleIndex = cursor.getColumnIndexOrThrow(COLUMN_ROLE);
                    int fullNameIndex = cursor.getColumnIndexOrThrow(COLUMN_FULL_NAME);
                    int specialtyIndex = cursor.getColumnIndexOrThrow(COLUMN_SPECIALTY);

                    User doctor = new User(
                            cursor.getString(idIndex),
                            cursor.getString(emailIndex),
                            cursor.getString(passwordIndex),
                            cursor.getString(roleIndex),
                            cursor.getString(fullNameIndex),
                            cursor.getString(specialtyIndex)
                    );
                    doctors.add(doctor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return doctors;
    }

    public List<Appointment> getDoctorAppointmentsByDate(String doctorEmail, String date) {
        List<Appointment> appointments = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {
                COLUMN_APPOINTMENT_ID,
                COLUMN_DATE,
                COLUMN_TIME,
                COLUMN_DOCTOR_EMAIL,
                COLUMN_PATIENT_EMAIL,
                COLUMN_STATUS,
                COLUMN_NOTES
        };

        String selection = COLUMN_DOCTOR_EMAIL + " = ? AND " + COLUMN_DATE + " = ?";
        String[] selectionArgs = {doctorEmail, date};
        String orderBy = COLUMN_TIME + " ASC";

        Cursor cursor = db.query(TABLE_APPOINTMENTS, columns, selection, selectionArgs, null, null, orderBy);

        if (cursor.moveToFirst()) {
            do {
                try {
                    Appointment appointment = new Appointment(
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_APPOINTMENT_ID)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOCTOR_EMAIL)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PATIENT_EMAIL)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTES))
                    );
                    appointments.add(appointment);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return appointments;
    }

    public boolean updateAppointmentNotes(String appointmentId, String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTES, notes);

        int result = db.update(TABLE_APPOINTMENTS, values,
                COLUMN_APPOINTMENT_ID + " = ?",
                new String[]{appointmentId});
        return result > 0;
    }

    // Thêm phương thức createAppointment
    public boolean createAppointment(Appointment appointment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_APPOINTMENT_ID, appointment.getId());
        values.put(COLUMN_DATE, appointment.getDate());
        values.put(COLUMN_TIME, appointment.getTime());
        values.put(COLUMN_DOCTOR_EMAIL, appointment.getDoctorEmail());
        values.put(COLUMN_PATIENT_EMAIL, appointment.getPatientEmail());
        values.put(COLUMN_STATUS, appointment.getStatus());
        values.put(COLUMN_NOTES, appointment.getNotes());

        long result = db.insert(TABLE_APPOINTMENTS, null, values);
        return result != -1;
    }
}
