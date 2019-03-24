package com.example.courseapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.courseapp.Model.Course;
import com.example.courseapp.Model.CourseAdapter;
import com.example.courseapp.R;
import com.example.courseapp.Service.CourseService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class WelcomeActivity extends AppCompatActivity {

    private CourseAdapter Adapter;
    private ArrayList<Course> allCourses = new ArrayList<>();
    private Set<Course> coursesAwaitingRating = new HashSet<>();

    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        loadCourses();
    }

    //This method goes through all the courses in a list and inserts the courses which hasn't been
    //rated into another list, this way it will only show the user the non rated courses
    public void loadCourses(){
        recycler = findViewById(R.id.recycler_view);
        allCourses = CourseService.getCourses();
        for (int i = 0; i < allCourses.size(); i++) {
            if (allCourses.get(i).getRating()){
                Log.d(allCourses.get(i).getName(), "loadCourses: course already rated");
            } else {
                Log.d(allCourses.get(i).getName(), "Adding course");
                coursesAwaitingRating.add(allCourses.get(i));
            }
        }

        //To display the courses for the user, the recycler view is used. To show the list of non
        //rated courses in the recycler view, an adapter class is initialized
        Adapter = new CourseAdapter(new ArrayList<>(coursesAwaitingRating), this);
        //Measuring and positioning item views within a RecyclerView
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(LayoutManager);
        //Item animations if changes are made to the adapter
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(Adapter);
    }
}
