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

public class Activity6 extends AppCompatActivity {
    private ArrayAdapter<Blog> blogArrayAdapter6;
    private ListView listviewBlog6;
    private List<Blog> arrayblog6;
    private EditText edMonth6;
    private EditText edYear6;
    private Button btnThongKe6;
    private TextView tvInCome6;
    private TextView tvOutCome6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);

        listviewBlog6=findViewById(R.id.listviewBlog6);
        edMonth6=findViewById(R.id.edMonth6);
        edYear6=findViewById(R.id.edYear6);
        btnThongKe6=findViewById(R.id.btnThongKe6);
        tvInCome6=findViewById(R.id.tvInCome6);
        tvOutCome6=findViewById(R.id.tvOutCome6);

        btnThongKe6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayblog6=new ArrayList<>();
                String url=Url.url+"?gmonth="+edMonth6.getText().toString()+
                        "&gyear="+edYear6.getText().toString();
                new ReadMonth().execute(url);
            }
        });


    }
    private class ReadMonth extends AsyncTask<String,Void,String> {
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
            int totalincome6=0;
            int totaloutcome6=0;

            try{
                Log.d("Json","start");
                JSONArray array=new JSONArray(s) ;
                for(int i=0;i<array.length();i++){
                    JSONObject activity=array.getJSONObject(i);
                    String day=activity.getString("day");
                    String month=activity.getString("month");
                    String year=activity.getString("year");
                    String type=activity.getString("type");
                    String category=activity.getString("category");
                    String amount=activity.getString("amount");
                    arrayblog6.add(new Blog(day,month,year,type,category,amount));
                    if(type.equals("Thu")==true||type.equals("thu")==true)
                        totalincome6+=Integer.parseInt(amount);
                    else totaloutcome6+=Integer.parseInt(amount);
                    //  Toast.makeText(Activity5.this,blog.getDay()+blog.getMonth()+blog.getYear()+blog.getType()+blog.getCategory()+blog.getAmount(),Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("AddBlog","Done");
            tvInCome6.setText("Tổng thu: "+totalincome6);
            tvOutCome6.setText("Tổng chi: "+totaloutcome6);
            blogArrayAdapter6=new BlogAdapter(Activity6.this,R.layout.listblog,arrayblog6);
            listviewBlog6.setAdapter(blogArrayAdapter6);

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

    }

}
