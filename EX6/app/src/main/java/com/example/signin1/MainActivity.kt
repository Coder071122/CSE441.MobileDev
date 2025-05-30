package com.example.signin1.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.signin.R

class MainActivity : AppCompatActivity() {
    private lateinit var edtTen: EditText
    private lateinit var edtCMND: EditText
    private lateinit var edtBosung: EditText
    private lateinit var chkDocbao: CheckBox
    private lateinit var chkDocsach: CheckBox
    private lateinit var chkDoccode: CheckBox
    private lateinit var btnSend: Button
    private lateinit var group: RadioGroup

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ view
        edtTen = findViewById(R.id.edtten)
        edtCMND = findViewById(R.id.editCMND)
        edtBosung = findViewById(R.id.editBosung)
        chkDocbao = findViewById(R.id.chkdocbao)
        chkDocsach = findViewById(R.id.chkdocsach)
        chkDoccode = findViewById(R.id.chkdoccode)
        btnSend = findViewById(R.id.btnsend)
        group = findViewById(R.id.idgroup)

        btnSend.setOnClickListener {
            doShowInformation()
        }
    }

    private fun doShowInformation() {
        val ten = edtTen.text.toString().trim()
        if (ten.length < 3) {
            edtTen.requestFocus()
            edtTen.selectAll()
            Toast.makeText(this, "Tên phải >= 3 ký tự", Toast.LENGTH_LONG).show()
            return
        }

        val cmnd = edtCMND.text.toString().trim()
        if (cmnd.length != 9) {
            edtCMND.requestFocus()
            edtCMND.selectAll()
            Toast.makeText(this, "CMND phải đúng 9 ký tự", Toast.LENGTH_LONG).show()
            return
        }

        val id = group.checkedRadioButtonId
        if (id == -1) {
            Toast.makeText(this, "Phải chọn bằng cấp", Toast.LENGTH_LONG).show()
            return
        }

        // Lấy bằng cấp
        val rad = findViewById<RadioButton>(id)
        val bangcap = rad.text.toString()

        // Sở thích
        val sothich = StringBuilder()
        if (chkDocbao.isChecked) sothich.append("Đọc báo\n")
        if (chkDocsach.isChecked) sothich.append("Đọc sách\n")
        if (chkDoccode.isChecked) sothich.append("Đọc code\n")

        val bosung = edtBosung.text.toString()

        // Tạo nội dung thông tin
        val msg = """
            Họ tên: $ten
            CMND: $cmnd
            Bằng cấp: $bangcap
            Sở thích:
            $sothich
            Thông tin bổ sung: $bosung
        """.trimIndent()

        // Tạo dialog
        AlertDialog.Builder(this)
            .setTitle("Thông tin cá nhân")
            .setMessage(msg)
            .setNegativeButton("Đóng") { dialog, _ -> dialog.cancel() }
            .show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        AlertDialog.Builder(this)
            .setTitle("Question")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes") { _, _ -> finish() }
            .setNegativeButton("No") { dialog, _ -> dialog.cancel() }
            .show()
    }
}
