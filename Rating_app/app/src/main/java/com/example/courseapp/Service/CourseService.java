package com.example.courseapp.Service;

import com.example.courseapp.Model.Course;

import java.util.ArrayList;

public class CourseService {
    private static ArrayList<Course> courses = new ArrayList<>();

    //This method is called, when a course has been rated. The rating attribute is set as "true"
    public static void UpdateCourses(Course course){
        course.setRating(true);
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getName().equals(course.getName())){
                courses.set(i, course);
            }
        }
    }

    //This method creates the courses and adds them to an array
    public static void loadCourses(){
        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(new Course("Construction"));
        courseList.add(new Course("Design"));
        courseList.add(new Course("Tech"));
        courseList.add(new Course("Python"));
        courses = courseList;
        System.out.println("printing courses");
    }

    public static ArrayList<Course> getCourses() {
        return courses;
    }

    public static void setCourses(ArrayList<Course> courses) {
        CourseService.courses = courses;
    }
}
