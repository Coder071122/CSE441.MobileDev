package com.example.projectandroid4;
import java.text.DecimalFormat;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends Activity { Button btnChandoan;
    EditText editTen,editChieucao,editCannang,editBMI,editChandoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnChandoan= findViewById(R.id.btnBMI);
        editTen=findViewById(R.id.edtten);
        editChieucao= findViewById(R.id.edtchieucao);
        editCannang= findViewById(R.id.edtcannang);
        editBMI= findViewById(R.id.edtBMI);
        editChandoan= findViewById(R.id.edtChuanDoan);
        btnChandoan.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {

            double H=Double.parseDouble(editChieucao.getText()+"");
            double W=Double.parseDouble(editCannang.getText()+"");
            double BMI=W/Math.pow(H,2);
            String chandoan="";
            if(BMI<18)
            {
                chandoan="Bạn gầy";
            }
            else if(BMI<=24.9)
            {
                chandoan="Bạn bình thường";
            }
            else if(BMI<=29.9)
            {
                chandoan="Bạn béo phì độ 1";
            }
            else if(BMI<=34.9)
            {
                chandoan="Bạn béo phì độ 2";
            }
            else
            {
                chandoan="Bạn béo phì độ 3";
            }
        }
        });
    }
}
