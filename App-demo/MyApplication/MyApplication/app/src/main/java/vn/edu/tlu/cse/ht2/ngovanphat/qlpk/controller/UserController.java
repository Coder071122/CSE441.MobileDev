package vn.edu.tlu.cse.ht2.ngovanphat.qlpk.controller;
import android.content.Context;
import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.database.DatabaseHelper;
import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.model.User;
import java.util.List;
public class UserController {
    private DatabaseHelper dbHelper;

    public UserController(Context context) {
        dbHelper = new DatabaseHelper(context);
    }
    public List<User> getAllDoctors() {
        return dbHelper.getAllDoctors();
    }

    // Xử lý đăng nhập
    public User login(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return null;
        }
        return dbHelper.getUser(email, password);
    }

    // Kiểm tra vai trò người dùng
    public boolean isDoctor(User user) {
        return user != null && "Bác sĩ".equals(user.getRole());
    }

    public boolean isPatient(User user) {
        return user != null && "Bệnh nhân".equals(user.getRole());
    }
}
