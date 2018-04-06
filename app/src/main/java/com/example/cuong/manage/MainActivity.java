package com.example.cuong.manage;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {


    private Button btnCapNhat;
    private Button btnThongKe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCapNhat = findViewById(R.id.btnCapNhat);
        btnThongKe = findViewById(R.id.btnThongKe);

        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.thongke);
                Button btnDay=dialog.findViewById(R.id.btnDay);
                Button btnMonth=dialog.findViewById(R.id.btnMonth);
                Button btnWeek=dialog.findViewById(R.id.btnWeek);
                Button btnYear=dialog.findViewById(R.id.btnYear);

                btnMonth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"Chức năng hiện chưa có",Toast.LENGTH_SHORT).show();
                    }
                });

                btnYear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent4=new Intent(MainActivity.this,Activity7.class);
                        startActivity(intent4);
                    }
                });

                btnMonth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent3=new Intent(MainActivity.this,Activity6.class);
                        startActivity(intent3);
                    }
                });
                btnDay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent2=new Intent(MainActivity.this,Activity5.class);
                        startActivity(intent2);
                    }
                });
                dialog.show();

            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(MainActivity.this,Activity3.class);
                startActivity(intent1);
            }
        });
    }
}
