package com.example.courseapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

//Parcelable implementation in this class is used to be able to send the course object between activites
public class Course implements Parcelable {
    private String name;
    //This rating value becomes true when a course has been rated
    private boolean rating = false;

    public Course(String name) {
        this.name = name;
    }

    public Course(String name, boolean rating) {
        this.name = name;
        this.rating = rating;
    }

    public boolean getRating() {
        return rating;
    }

    public void setRating(boolean rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }

    public Course(Parcel source){
        name = source.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}


