package com.example.cuong.manage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class Activity8 extends AppCompatActivity {

    private ArrayAdapter<Blog> blogAdapter8;
    private EditText edDay8;
    private EditText edMonth8;
    private EditText edYear8;
    private Button btnThongKe8;;
    private List<Blog> blogs8;
    private ListView listviewBlog8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_8);

        edDay8=findViewById(R.id.edDay8);
    }
}
