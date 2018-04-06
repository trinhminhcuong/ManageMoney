package com.example.cuong.manage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Cuong on 4/6/2018.
 */

public class BlogAdapter extends ArrayAdapter<Blog> {

    private Context context;
    private int resource;
    private List<Blog> objects;


    public BlogAdapter(@NonNull Context context, int resource,List<Blog> objects) {
        super(context, resource,objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            convertView=layoutInflater.inflate(resource,null);
        }

        ImageView image=(ImageView)convertView.findViewById(R.id.image);
        TextView tvDate=(TextView)convertView.findViewById(R.id.tvDate);
        TextView tvCategory=(TextView)convertView.findViewById(R.id.tvCategory);
        TextView tvAmount=(TextView)convertView.findViewById(R.id.tvAmount);

        Blog blog=objects.get(position);

        if(blog.getType().equals("Thu"))
            image.setImageResource(R.drawable.income);
        else image.setImageResource(R.drawable.outcome);

        tvDate.setText(blog.getDay()+"-"+blog.getMonth()+"-"+blog.getYear());
        tvCategory.setText(blog.getCategory());
        tvAmount.setText(blog.getAmount());



        return convertView;
    }
}
