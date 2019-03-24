package com.example.courseapp.Model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.courseapp.R;
import com.example.courseapp.Activity.RatingActivity;

import java.util.ArrayList;

//This adapter has the responsibility to create ViewHolders that hold the inflated views.
//Once a ViewHolder is created, it's put in a cache, where it can be reused if required
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {

    Context context;
    public ArrayList<Course> courses;

    public CourseAdapter(ArrayList<Course> courses, Context context){
        this.courses = courses;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
        }
    }

    //This method handles ViewHolder creation, recycle and view inflation based on viewType
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    //This method binds data to child views of the ViewHolder.
    //It will be called everytime a view has to appear on screen
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Course course = courses.get(position);
        Log.d("test", course.getName());
        holder.name.setText(course.getName());
        holder.name.setOnClickListener((View) -> {
            Intent intent = new Intent(context, RatingActivity.class);
            intent.putExtra("Course", course);
            context.startActivity(intent);
        });
    }

    //This method returns the size of the dataset used for the adapter
    @Override
    public int getItemCount() {
        return courses.size();
    }
}