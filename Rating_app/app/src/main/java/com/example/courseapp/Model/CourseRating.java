package com.example.courseapp.Model;

//This class handles the progress values from the seekbars and turns them into a grade
public class CourseRating {
    private int subject_relevancy;
    private int performance;
    private int preparation;
    private int feedback;
    private int examples;
    private int job_opportunities;
    private String rating;

    public CourseRating(){
    }

    public String getSubject_relevancy() {
        return gradeConversion(subject_relevancy);
    }

    public void setSubject_relevancy(int subject_relevancy) {
        this.subject_relevancy = subject_relevancy;
    }

    public String getPerformance() {
        return gradeConversion(performance);
    }

    public void setPerformance(int performance) {
        this.performance = performance;
    }

    public String getPreparation() {
        return gradeConversion(preparation);
    }

    public void setPreparation(int preparation) {
        this.preparation = preparation;
    }

    public String getFeedback() {
        return gradeConversion(feedback);
    }

    public void setFeedback(int feedback) {
        this.feedback = feedback;
    }

    public String getExamples() {
        return gradeConversion(examples);
    }

    public void setExamples(int examples) {
        this.examples = examples;
    }

    public String getJob_opportunities() {
        return gradeConversion(job_opportunities);
    }

    public void setJob_opportunities(int job_opportunities) {
        this.job_opportunities = job_opportunities;
    }

    public String gradeConversion(int grade){
        if (grade > 90){
            rating = "A";
        } else if (grade > 80 && grade < 90){
            rating = "B";
        } else if (grade > 70 && grade < 80){
            rating = "C";
        } else if (grade > 60 && grade < 70){
            rating = "D";
        } else if (grade > 50 && grade < 60){
            rating = "E";
        } else if (grade < 50){
            rating = "F - This area is in need of improvement!";
        }
        return rating;
    }

    @Override
    public String toString() {
        return "CourseRating{" +
                "subject_relevancy=" + getSubject_relevancy() +
                ", performance=" + getPerformance() +
                ", preparation=" + getPreparation() +
                ", feedback=" + getFeedback() +
                ", examples=" + getExamples() +
                ", job_opportunities=" + getJob_opportunities() +
                '}';
    }
}
