
import android.R
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// MainActivity.java
class MainActivity : AppCompatActivity() {
    var edtten: EditText? = null
    var editCMND: EditText? = null
    var editBsong: EditText? = null
    var chkdobao: CheckBox? = null
    var chkdoscach: CheckBox? = null
    var chkdoccode: CheckBox? = null
    var btnsend: Button? = null
    var group: RadioGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtten = findViewById<EditText>(R.id.edtTen)
        editCMND = findViewById<EditText>(R.id.edtCMND)
        editBsong = findViewById<EditText>(R.id.edtBsong)
        chkdobao = findViewById<CheckBox>(R.id.chkdobao)
        chkdoscach = findViewById<CheckBox>(R.id.chkdoscach)
        chkdoccode = findViewById<CheckBox>(R.id.chkdoccode)
        btnsend = findViewById<Button>(R.id.btnsend)

        btnsend.setOnClickListener(View.OnClickListener { doShowInformation() })
    }

    fun doShowInformation() {
        // Kiểm tra tên hợp lệ
        val ten = edtten!!.text.toString()
        if (ten.length < 3) {
            edtten!!.requestFocus()
            edtten!!.selectAll()
            Toast.LENGTH_LONG.show() // Điều chỉnh
            return
        }

        // Kiểm tra CMND hợp lệ
        val cmd = editCMND!!.text.toString().trim { it <= ' ' }
        if (cmd.length != 9) {
            editCMND!!.requestFocus()
            editCMND!!.selectAll()
            Toast.LENGTH_LONG.show() // Điều chỉnh
            return
        }

        // Kiểm tra bảng cấp
        var bang = ""
        group = findViewById<RadioGroup>(R.id.idgroup)
        bang = group.getCheckedRadioButtonId() // Trả về Id

        Toast.makeText(this, "Phải chọn bảng cấp", Toast.LENGTH_LONG).show() // Điều chỉnh
    }

    // Hàm xử lý sự kiện quay lại
    override fun onBackPressed() {
        val b: AlertDialog.Builder = Builder(this@MainActivity)
        b.setTitle("Cảnh báo")
        b.setMessage("Bạn có chắc muốn thoát không?")
        b.setIcon(R.drawable.inform)
        b.setPositiveButton(
            "Có",
            DialogInterface.OnClickListener { dialog, which -> finish() })
        b.setNegativeButton(
            "Không",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        b.create().show()
    }
}