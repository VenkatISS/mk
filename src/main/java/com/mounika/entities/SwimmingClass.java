package com.mounika.entities;

import java.util.ArrayList;
import java.util.List;

public class SwimmingClass {
    private int idNumber;
    private Availability convenience;
    private Mentor mentor;
    private TeachingDay day;
    private int grade;
    private List<Beginner> participants;

    // only for testing
    public SwimmingClass(int id) {
        this.idNumber = id;
        this.grade = 2;
        this.participants = new ArrayList<>();
    }
    // only for testing
    public SwimmingClass(int id, int grade) {
        this.idNumber = id;
        this.grade = grade;
        this.participants = new ArrayList<>();
    }

    public SwimmingClass(int id, Availability availability, TeachingDay day, int grade) {
        this.idNumber = id;
        this.convenience = availability;
        this.day = day;
        this.grade = grade;
        this.participants = new ArrayList<>();
    }

    public SwimmingClass(int id, Availability availability, Mentor mentor, TeachingDay day, int grade) {
        this.idNumber = id;
        this.convenience = availability;
        this.mentor = mentor;
        this.day = day;
        this.grade = grade;
        this.participants = new ArrayList<>();
    }

    public int getId() {
        return idNumber;
    }

    public void setId(int id) {
        this.idNumber = id;
    }

    public boolean isFull() {
        return this.participants.size() == 4;
    }

    public void addAttendee(Beginner beginner) {
        this.participants.add(beginner);
    }

    public void removeAttendee(Beginner beginner) {
        this.participants.remove(beginner);
    }

    public Availability getAvailability() {
        return convenience;
    }

    public void setAvailability(Availability avalability) {
        this.convenience = avalability;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public TeachingDay getDay() {
        return day;
    }

    public void setDay(TeachingDay day) {
        this.day = day;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public List<Beginner> getAttendees() {
        return participants;
    }

    public void setAttendees(List<Beginner> attendees) {
        this.participants = attendees;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SwimmingClass other = (SwimmingClass) obj;
        return idNumber == other.idNumber;
    }
}
