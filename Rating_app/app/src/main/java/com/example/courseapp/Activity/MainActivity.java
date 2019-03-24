package com.example.courseapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.courseapp.R;
import com.example.courseapp.Service.CourseService;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        if (savedInstanceState != null) {
            username.setText(savedInstanceState.getString("username"));
            password.setText(savedInstanceState.getString("password"));
        }
    }

    //Validation method, Initializes new intent if the username and password is correct, displays toast if it isn't
    public void validateLogin(View v){
        if (username.getText().toString().equals("Student") && password.getText().toString().equals("123456")){
            CourseService.loadCourses();
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.Invalid_username), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
        protected void onSaveInstanceState(Bundle outState){
            super.onSaveInstanceState(outState);

            outState.putString("username", username.getText().toString());
            outState.putString("password", password.getText().toString());
    }
}
