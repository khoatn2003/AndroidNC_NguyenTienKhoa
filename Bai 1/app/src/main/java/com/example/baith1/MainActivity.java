package com.example.bai1th1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.baith1.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {

    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView =findViewById(R.id.TextViewoi);
    }

    public void readwebpage(View view){
        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(new String[] { "http://www.mobipro.vn" });
    }
    class DownloadWebPageTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls) {
            String response = "", s = "";
            for (String url : urls) {
                try {
                    // Tạo kết nối HttpURLConnection
                    URL urlObj = new URL(url);
                    HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();

                    // Lấy luồng dữ liệu InputStream từ kết nối
                    InputStream content = urlConnection.getInputStream();
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));

                    // Đọc dữ liệu từ buffer
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                    // Đóng kết nối sau khi đọc dữ liệu
                    buffer.close();
                    urlConnection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace(); // Hiển thị lỗi nếu có
                }
            }
            return response;
        }

        protected void onPostExecute(String result) {
            textView.setText(result); // Cập nhật kết quả lên TextView
        }
    }

}