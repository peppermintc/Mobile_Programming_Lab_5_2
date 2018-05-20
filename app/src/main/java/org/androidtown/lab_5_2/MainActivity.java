package org.androidtown.lab_5_2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //뷰 선언
    EditText editText;
    Button button;
    TextView textView1;
    TextView textView2;

    //곱셈 결과
    int result = 1;
    //곱셈에 도움이 되는
    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //액티비티와 연결
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText1);
        button = findViewById(R.id.button1);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        //버튼 리스너
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AsyncTask 계산 실행
                new CalculateTask().execute();
            }
        });

    }

    //AsyncTask의 계산 클래스
    private class CalculateTask extends AsyncTask<Integer, Integer, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //결과값 초기화
            result=1;
            textView1.setText("");

            //입력한 값 읽어오기
            try {
                n = Integer.parseInt(editText.getText().toString());
            } catch(NumberFormatException nfe) {
            }
        }


        @Override
        protected Void doInBackground(Integer... params) {

            //결과값계산 및 onProgressUpdate실행 
            while(n>0)
            {
                result *= n;
                publishProgress();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                n--;
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            //텍스트뷰 출력
            String print = textView1.getText().toString();
            textView1.setText(print + " " + String.valueOf(n));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //결과값 출력
            textView2.setText("= " + String.valueOf(result));
        }

    }
}