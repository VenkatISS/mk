package com.mounika.entities;

import com.mounika.exceptions.BeginnerAgeException;

import java.util.ArrayList;
import java.util.List;

public class Beginner {
    private String name;
    private String gender;
    private int agegroup;
    private String emergencyContactnumber;
    private int classification;
    private List<SwimmingClass> classesBooked;
    private List<SwimmingClass> classesAttended;
    private List<SwimmingClass> classesCancelled;

    public Beginner(String name, String gender , int age, String emergencyContact, int grade) throws BeginnerAgeException {
        if(age < 4 || age > 11) {
            throw new BeginnerAgeException("Age group should be between 4 and 11");
        }
        this.name = name;
        this.gender = gender;
        this.agegroup = age;
        this.emergencyContactnumber = emergencyContact;
        this.classification = grade;
        this.classesBooked = new ArrayList<>();
        this.classesAttended = new ArrayList<>();
        this.classesCancelled = new ArrayList<>();
    }

    public boolean isClassAttended(SwimmingClass lesson) {
        return classesAttended.contains(lesson);
    }

    public boolean isClassBooked(SwimmingClass lesson) {
        return classesBooked.contains(lesson);
    }

    public boolean isEligibleForClass(SwimmingClass lesson){
        return classification == lesson.getGrade() || classification == lesson.getGrade() - 1;
    }

    public void bookClass(SwimmingClass lesson) {
        classesBooked.add(lesson);
    }

    public void attendClass(SwimmingClass lesson) {
        classesBooked.remove(lesson);
        classesAttended.add(lesson);
        setGrade(lesson.getGrade());
    }

    public void cancelClass(SwimmingClass lesson) {
        classesBooked.remove(lesson);
        classesCancelled.add(lesson);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return agegroup;
    }

    public void setAge(int age) {
        this.agegroup = age;
    }

    public String getEmergencyContact() {
        return emergencyContactnumber;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContactnumber = emergencyContact;
    }

    public int getGrade() {
        return classification;
    }

    public void setGrade(int grade) {
        this.classification = grade;
    }

    public List<SwimmingClass> getClassesBooked() {
        return classesBooked;
    }

    public List<SwimmingClass> getClassesAttended() {
        return classesAttended;
    }


    public List<SwimmingClass> getClassesCancelled() {
        return classesCancelled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beginner beginner = (Beginner) o;
        return name.equals(beginner.name);
    }


    @Override
    public String toString() {
        String sb = "Name: " + name + "\n" +
                "Gender: " + gender + "\n" +
                "Age: " + agegroup + "\n" +
                "Emergency Contact: " + emergencyContactnumber + "\n" +
                "Grade: " + classification + "\n" +
                "Classes Booked: " + classesBooked.size() + "\n" +
                "Classes Attended: " + classesAttended.size() + "\n" +
                "Classes Cancelled: " + classesCancelled.size() + "\n";
        return sb;
    }
}
