package com.mounika.entities;

public class Analysis {
    private Beginner beginner;
    private int g;
    private SwimmingClass lesson;
    public Analysis(Beginner beginner, int rating, SwimmingClass lesson) {
        this.beginner = beginner;
        this.g = rating;
        this.lesson = lesson;
    }

    public Beginner getBeginner() {
        return beginner;
    }

    public void setBeginner(Beginner beginner) {
        this.beginner = beginner;
    }

    public int getRating() {
        return g;
    }

    public void setRating(int rating) {
        this.g = rating;
    }

    public SwimmingClass getLesson() {
        return lesson;
    }

    public void setLesson(SwimmingClass lesson) {
        this.lesson = lesson;
    }
}
