package com.example.cuong.manage;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Activity7 extends AppCompatActivity {

    private ListView lv7;
    private List<Blog> blogs7;
    private ArrayAdapter<Blog> adapter7;
    private Button btnThongKe7;
    private EditText edYear7;
    private TextView tvInCome7;
    private TextView tvOutCome7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7);

        lv7=findViewById(R.id.lv7);
        btnThongKe7=findViewById(R.id.btnThongKe7);
        edYear7=findViewById(R.id.edYear7);
        tvInCome7=findViewById(R.id.tvInCome7);
        tvOutCome7=findViewById(R.id.tvOutCome7);


        btnThongKe7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blogs7=new ArrayList<>();
                String url=Url.url+"?gmonth=0&gyear="+edYear7.getText().toString();
                new ReadYear().execute(url);
            }
        });

    }

    private class ReadYear extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            StringBuilder stringBuilder=new StringBuilder(); //gan du lieu doc
            try {
                URL url=new URL(strings[0]);
                //mo connection
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                // lay du lieu tu url connection
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                InputStream inputStream=httpURLConnection.getInputStream();

                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);

                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

                String line="";

                while((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line + "\n");
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            int totalincome7=0;
            int totaloutcome7=0;
            try{
                JSONArray array=new JSONArray(s) ;
                for(int i=0;i<array.length();i++){
                    JSONObject activity=array.getJSONObject(i);
                    String day=activity.getString("day");
                    String month=activity.getString("month");
                    String year=activity.getString("year");
                    String type=activity.getString("type");
                    String category=activity.getString("category");
                    String amount=activity.getString("amount");
                    blogs7.add(new Blog(day,month,year,type,category,amount));
                    if(type.equals("Thu")==true||type.equals("thu")==true)
                        totalincome7+=Integer.parseInt(amount);
                    else totaloutcome7+=Integer.parseInt(amount);
                    //  Toast.makeText(Activity5.this,blog.getDay()+blog.getMonth()+blog.getYear()+blog.getType()+blog.getCategory()+blog.getAmount(),Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            tvInCome7.setText("Tổng thu: "+totalincome7);
            tvOutCome7.setText("Tổng chi: "+totaloutcome7);
            adapter7=new BlogAdapter(Activity7.this,R.layout.listblog,blogs7);
            lv7.setAdapter(adapter7);

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

    }
}
