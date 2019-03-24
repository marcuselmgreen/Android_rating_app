package com.example.courseapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courseapp.Model.Course;
import com.example.courseapp.Model.CourseRating;
import com.example.courseapp.R;
import com.example.courseapp.Service.CourseService;

public class RatingActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    TextView courseTitle;
    SeekBar sb1, sb2, sb3, sb4, sb5, sb6;
    Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        //Get intent from the CourseAdapter class, which includes the chosen course object
        Intent intent = getIntent();
        course = intent.getParcelableExtra("Course");
        courseTitle = findViewById(R.id.course);
        courseTitle.setText(course.getName());

        sb1 = findViewById(R.id.subject_relevance);
        sb2 = findViewById(R.id.Teacher_performance);
        sb3 = findViewById(R.id.Teacher_preparation);
        sb4 = findViewById(R.id.Amount_of_feedback);
        sb5 = findViewById(R.id.Good_examples);
        sb6 = findViewById(R.id.Job_opportunities);

        if (savedInstanceState != null) {
            courseTitle.setText(savedInstanceState.getString("title"));
            course = savedInstanceState.getParcelable("course");
            sb1.setProgress(savedInstanceState.getInt("sb1"));
            sb2.setProgress(savedInstanceState.getInt("sb2"));
            sb3.setProgress(savedInstanceState.getInt("sb3"));
            sb4.setProgress(savedInstanceState.getInt("sb4"));
            sb5.setProgress(savedInstanceState.getInt("sb5"));
            sb6.setProgress(savedInstanceState.getInt("sb6"));
        }
    }

    //Methods from the implemented interface Seekbar.onSeekBarChangeListener
    //The methods included notifies the client when progress level has changed on the seekbars

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /* This method fills the initialized CourseRating object with the progress values
    from the seekbars. The specific course includes a rating attribute which has been marked
    with a boolean value - true, which identifies the course as having been rated once already
    so that it can't be rated anymore */
    public void rating(View v) {
        CourseRating finalRating = new CourseRating();
        finalRating.setSubject_relevancy(this.sb1.getProgress());
        finalRating.setPerformance(this.sb2.getProgress());
        finalRating.setPreparation(this.sb3.getProgress());
        finalRating.setFeedback(this.sb4.getProgress());
        finalRating.setExamples(this.sb5.getProgress());
        finalRating.setJob_opportunities(this.sb6.getProgress());

        Log.d(finalRating.toString(), "Final rating: ");

        course.setRating(true);
        CourseService.UpdateCourses(course);

        Log.d("DEBUG", CourseService.getCourses().toString());

        //This intent is being initialized so that the user will be redirected to the courselist
        //page, when the mail has been sent
        Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
        startActivity(intent);
        sendMail(finalRating);
    }

    //This method creates a mail with all the progress values from the seekbars
    public void sendMail(CourseRating finalRating) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"marc1967@stud.kea.dk"});
        i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.Mail_subject) + course.getName());
        i.putExtra(Intent.EXTRA_TEXT, getString(R.string.Mail_heading) + "\n\n" +
                getString(R.string.Relevancy_rating, finalRating.getSubject_relevancy()) + "\n" +
                getString(R.string.Performance_rating, finalRating.getPerformance()) + "\n" +
                getString(R.string.Preparation_rating, finalRating.getPreparation()) + "\n" +
                getString(R.string.Feedback_rating, finalRating.getFeedback()) + "\n" +
                getString(R.string.Examples_rating, finalRating.getExamples()) + "\n" +
                getString(R.string.Job_rating, finalRating.getJob_opportunities()));
        try {
            startActivity(Intent.createChooser(i, getString(R.string.Send_mail_chooser)));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(RatingActivity.this, getString(R.string.No_email_clients), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putString("title", courseTitle.getText().toString());
        outState.putParcelable("course", course);
        outState.putInt("sb1", sb1.getProgress());
        outState.putInt("sb2", sb2.getProgress());
        outState.putInt("sb3", sb3.getProgress());
        outState.putInt("sb4", sb4.getProgress());
        outState.putInt("sb5", sb5.getProgress());
        outState.putInt("sb6", sb6.getProgress());
    }
}
