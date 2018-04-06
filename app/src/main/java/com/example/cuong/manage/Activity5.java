package com.example.cuong.manage;

import android.app.TimePickerDialog;
import android.content.Intent;
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

public class Activity5 extends AppCompatActivity {

    private EditText edDay5;
    private EditText edMonth5;
    private EditText edYear5;
    private Button btnThongKe5;
    private TextView tvInCome5;
    private TextView tvOutCome5;
    private  List<Blog> blogs;
    private ListView listViewBlog;
    private ArrayAdapter<Blog> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        edDay5=findViewById(R.id.edDay5);
        edMonth5=findViewById(R.id.edMonth5);
        edYear5=findViewById(R.id.edYear5);
        btnThongKe5=findViewById(R.id.btnThongKe5);
        listViewBlog=findViewById(R.id.listviewBlog);
        tvInCome5=findViewById(R.id.tvInCome5);
        tvOutCome5=findViewById(R.id.tvOutCome5);


       // blogs.add(new Blog("4","10","1996","Thu","Lương","10000"));


        // blogs=new ArrayList<>();
        //  blogs.add(new Blog("4","10","1996","Thu","Chi","30000"));

        //blogs=new ArrayList<>();


        btnThongKe5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blogs=new ArrayList<>();
                if(edDay5.length()==0||edMonth5.length()==0||edYear5.length()==0)
                   Toast.makeText(Activity5.this,"Xin nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                 else {
                    String url=Url.url+
                             "?byday="+edDay5.getText().toString()+
                             "&bymonth="+edMonth5.getText().toString()+
                             "&byyear="+edYear5.getText().toString();
                     new ReadDay().execute(url);
                     Log.d("Link","Success");
                  //  arrayAdapter=new BlogAdapter(Activity5.this,R.layout.listblog,blogs);
                  //  listViewBlog.setAdapter(arrayAdapter);
                  //  listViewBlog.deferNotifyDataSetChanged();


                }
            }
        });
    }


    private class ReadDay extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            StringBuilder stringBuilder=new StringBuilder(); //gan du lieu doc
            try {
                URL url=new URL(strings[0]);
                //URLConnection urlConnection=url.openConnection(); //mo connection
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
            int totalincome=0;
            int totaloutcome=0;
            try{

                Log.d("Json","start");
                Log.d("Json2","start");
                JSONArray array=new JSONArray(s) ;
                Log.d("Json3","start");
                for(int i=0;i<array.length();i++){
                    JSONObject activity=array.getJSONObject(i);
                    String day=activity.getString("day");
                    String month=activity.getString("month");
                    String year=activity.getString("year");
                    String type=activity.getString("type");
                    String category=activity.getString("category");
                    String amount=activity.getString("amount");
                    if(type.equals("Thu")==true||type.equals("thu")==true)
                        totalincome+=Integer.parseInt(amount);
                    else totaloutcome+=Integer.parseInt(amount);
                  //  Blog blog=new Blog(day,month,year,type,category,amount);
                    blogs.add(new Blog(day,month,year,type,category,amount));
                  //  arrayAdapter.add(blog);
                    Log.d("AddBlog","Done");
                  //  Toast.makeText(Activity5.this,blog.getDay()+blog.getMonth()+blog.getYear()+blog.getType()+blog.getCategory()+blog.getAmount(),Toast.LENGTH_SHORT).show();
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }
            tvInCome5.setText("Tổng thu: "+totalincome);
            tvOutCome5.setText("Tổng chi: "+totaloutcome);
            arrayAdapter=new BlogAdapter(Activity5.this,R.layout.listblog,blogs);
            listViewBlog.setAdapter(arrayAdapter);

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

    }

}
