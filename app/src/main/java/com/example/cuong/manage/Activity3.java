package com.example.cuong.manage;

import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


public class Activity3 extends AppCompatActivity {

    private EditText edDay;
    private EditText edMonth;
    private EditText edYear;
    private TextView tvTest;
    private Spinner spinnerCategory;
    private EditText edAmount;
    private RadioButton rdThu;
    private RadioButton rdChi;
    private Button btnInsertActivity;
    private List<String> outcomeCategories;
    private List<String> incomeCategories;
    private ArrayAdapter<String> arrayAdapterIncome;
    private ArrayAdapter<String> arrayAdapterOutcome;
    private String itemName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        edDay=findViewById(R.id.edDay);
        edMonth=findViewById(R.id.edMonth);
        edYear=findViewById(R.id.edYear);
       spinnerCategory=findViewById(R.id.spinnerCategory);
       edAmount=findViewById(R.id.edAmount);
       rdThu=findViewById(R.id.rdThu);
       rdChi=findViewById(R.id.rdChi);
       tvTest=findViewById(R.id.tvTest);
       btnInsertActivity=findViewById(R.id.btnInsertActivity);
       outcomeCategories=new ArrayList<>();
       incomeCategories=new ArrayList<>();


       outcomeCategories.add("ĂnUống"); outcomeCategories.add("SinhHoạt"); outcomeCategories.add("MuaSắm");
       outcomeCategories.add("Tặng");
       incomeCategories.add("Lương");
       incomeCategories.add("Thưởng");

       arrayAdapterIncome=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,incomeCategories);
       arrayAdapterOutcome=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,outcomeCategories);

       spinnerCategory.setAdapter(arrayAdapterIncome);

       rdChi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if(rdChi.isChecked()==true) spinnerCategory.setAdapter(arrayAdapterOutcome);
               else spinnerCategory.setAdapter(arrayAdapterIncome);
           }
       });



        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemName=spinnerCategory.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        btnInsertActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(edDay.length()==0||edMonth.length()==0||edYear.length()==0||edAmount.length()==0)
                    Toast.makeText(Activity3.this,"Xin nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
               else {
                     String type;
                    if(rdThu.isChecked()==true) type="Thu";
                    else type="Chi";
                    String newday=edDay.getText().toString().trim();
                    String newmonth=edMonth.getText().toString().trim();
                    String newyear=edYear.getText().toString().trim();
                    String newamount=edAmount.getText().toString().trim();
                    String url=Url.url+
                            "?newday="+newday+
                            "&newmonth="+newmonth+
                            "&newyear="+newyear+
                            "&newtype="+type+
                            "&newcategory="+itemName.trim()+
                            "&newamount="+newamount;
                    //Toast.makeText(Activity3.this,url,Toast.LENGTH_SHORT).show();



                   new Read().execute(url);

                }


            }
        });
    }











    private class Read extends AsyncTask<String,Void,String>{
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

                httpURLConnection.setRequestMethod("POST");
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
            //tvTest.setText(s);
            Toast.makeText(Activity3.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }

}



