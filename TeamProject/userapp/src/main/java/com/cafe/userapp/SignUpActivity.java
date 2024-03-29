package com.cafe.userapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cafe.userapp.Http.HttpRequest;
import com.cafe.userapp.Http.ModelTeam;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtemail, edtpw, edtphone, edtaddr;
    private CheckBox checkemail, checkall, checkBox1, checkBox2;
    private RadioButton rtn1, rtn2;
    private RadioGroup rtng;
    private Button btn_final;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtemail = (EditText) findViewById(R.id.edt_input_email);
        edtpw = (EditText) findViewById(R.id.edt_input_pw);
        edtphone = (EditText) findViewById(R.id.edt_input_num);
        edtaddr = (EditText) findViewById(R.id.edt_input_addr);

        checkemail = (CheckBox) findViewById(R.id.checkBox);
        checkall = (CheckBox) findViewById(R.id.checkALL);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);

        rtn1 = (RadioButton) findViewById(R.id.radioButton1);
        rtn2 = (RadioButton) findViewById(R.id.radioButton2);
        rtng = (RadioGroup) findViewById(R.id.radioGroup);

        btn_final = (Button) findViewById(R.id.btn_final);

        checkall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    checkBox1.setChecked(true);
                    checkBox2.setChecked(true);
                } else {
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                }
            }
        });

        btn_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox1.isChecked() == false || (rtn1.isChecked() == rtn2.isChecked())) {
                    return;
                } else {
                    new HttpLogin().execute(edtemail.getText().toString(), edtpw.getText().toString(), edtphone.getText().toString(), edtaddr.getText().toString(), String.valueOf(checkemail.isChecked()), String.valueOf(rtn1.isChecked()));
                }
            }
        });


    }

    public class HttpLogin extends AsyncTask<String, Integer, String> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(getApplicationContext());
            waitDlg.setMessage(" 버전 확인 중");
            waitDlg.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            String email = strings[0];
            String pw = strings[1];
            String phone = strings[2];
            String addr = strings[3];
            boolean checkemail = Boolean.valueOf(strings[4]);
            boolean sex = Boolean.valueOf(strings[5]);

            String result = insert(email, pw, phone, addr, checkemail, sex);

            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
            if (s.equals("1")) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }


    }

    public String insert(String email, String pw, String phone, String addr, Boolean checkemail, Boolean sex) {
        String weburl = "http://192.168.0.44:8080/team/insertteam";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            // ModelPerson을 json으로 변환
            ModelTeam obj = new ModelTeam(email, pw, phone, addr, checkemail, sex);
            String data = new Gson().toJson(obj);

            request = new HttpRequest(weburl).addHeader("charset", "utf-8")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json");
            httpCode = request.post(data);

            if (httpCode == HttpURLConnection.HTTP_OK) { // HttpURLConnection.HTTP_OK == 200
                try {
                    response = request.getStringResponse(); // 서버값이 리턴된다
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }
        return response;
    }
}
