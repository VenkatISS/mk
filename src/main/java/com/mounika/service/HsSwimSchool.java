package com.mounika.service;

import com.mounika.entities.*;
import com.mounika.exceptions.*;
import com.mounika.strategies.GenerateAnalysisStrategy;
import com.mounika.strategies.GenerateBeginnersAnalysisStrategy;
import com.mounika.strategies.GenerateMentorsAnalysisStrategy;

import java.util.ArrayList;
import java.util.List;

public class HsSwimSchool implements HsSwimmingSchool {

    private List<SwimmingClass> lessons;
    private List<Mentor> mentors;
    private List<Beginner> beginners;
    private List<Analysis> analysis;
    private GenerateAnalysisStrategy generateAnalysisStrategy;

    public HsSwimSchool(){
        this.lessons= new ArrayList<>();
        this.mentors = new ArrayList<>();
        this.beginners = new ArrayList<>();
        this.analysis = new ArrayList<>();
    }

    @Override
    public void assignMentorToClass(Mentor mentor,SwimmingClass lesson) throws SwimmingClassAlreadyHasMentorException {
        if(lesson.getMentor() != null){
            throw new SwimmingClassAlreadyHasMentorException("This class already has a mentor");
        }
        lesson.setMentor(mentor);
    }

    @Override
    public void addBeginner(Beginner beginner) throws BeginnerAlreadyExistsException {
        if(beginners.contains(beginner)){
            throw new BeginnerAlreadyExistsException("Beginner already exists");
        }
        beginners.add(beginner);
    }

    @Override
    public void removeBeginner(Beginner beginner) {
        beginners.remove(beginner);
    }

    @Override
    public void addMentor(Mentor mentor) throws MentorAlreadyExistsException {
        if(mentors.contains(mentor)){
           throw new MentorAlreadyExistsException("mentor already exists");
        }
        mentors.add(mentor);
    }

    @Override
    public void removeMentor(Mentor mentor) {
        mentors.remove(mentor);
    }

    @Override
    public void addClass(SwimmingClass lesson) {
        if(!lessons.contains(lesson)){
            lessons.add(lesson);
        }
        lessons.add(lesson);
    }

    @Override
    public void removeClass(SwimmingClass lesson) {
        lessons.remove(lesson);
    }

    @Override
    public void viewTimetableByDay(TeachingDay day) {
        List<SwimmingClass> lessonsByDay = lessons.stream()
                .filter(lesson -> lesson.getDay() == day)
                .toList();
        printSeparator();
        System.out.println("Swimming Class on " + day + ":");
        printSeparator();
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Class ID", "Day", "Time", "Grade", "Mentor");
        for(SwimmingClass lesson : lessonsByDay){
            System.out.printf("%-20s%-20s%-20s%-20s%-20s\n",
                    lesson.getId(),
                    lesson.getDay(),
                    lesson.getAvailability(),
                    lesson.getGrade(),
                    lesson.getMentor().getName()
            );
        }
        printSeparator();
    }

    @Override
    public void viewTimetableByGrade(int grade) {
        List<SwimmingClass> lessonsByGrade = lessons.stream()
                .filter(lesson -> lesson.getGrade() == grade)
                .toList();
        printSeparator();
        System.out.println("Swimming Classes for Grade " + grade + ":");
        printSeparator();
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Class ID", "Day", "Time", "Grade", "Mentor");
        for(SwimmingClass lesson : lessonsByGrade){
            System.out.printf("%-20s%-20s%-20s%-20s%-20s\n",
                    lesson.getId(),
                    lesson.getDay(),
                    lesson.getAvailability(),
                    lesson.getGrade(),
                    lesson.getMentor().getName()
            );
        }
        printSeparator();
    }

    @Override
    public void viewTimetableByMentor(Mentor mentor) {
        List<SwimmingClass> lessonsByMentor = lessons.stream()
                .filter(lesson -> lesson.getMentor().equals(mentor))
                .toList();
        printSeparator();
        System.out.println("Swimming Class by " + mentor.getName() + ":");
        printSeparator();
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Class ID", "Day", "Time", "Grade", "Mentor");
        for(SwimmingClass lesson : lessonsByMentor){
            System.out.printf("%-20s%-20s%-20s%-20s%-20s\n",
                    lesson.getId(),
                    lesson.getDay(),
                    lesson.getAvailability(),
                    lesson.getGrade(),
                    lesson.getMentor().getName()
            );
        }
        printSeparator();
    }

    @Override
    public void bookClass(Beginner beginner, SwimmingClass lesson) throws NotEligibleForClassException, ClassAlreadyBookedException, ClassIsFullException {
        if(!beginner.isEligibleForClass(lesson)){
            throw new NotEligibleForClassException("You are not eligible for this class");
        }
        if(beginner.isClassBooked(lesson)){
           throw new ClassAlreadyBookedException("You are already attending this class");
        }
        if(lesson.isFull()){
           throw new ClassIsFullException("This class is full");
        }
        lesson.addAttendee(beginner);
        beginner.bookClass(lesson);
    }

    @Override
    public void cancelClass(Beginner beginner, SwimmingClass lesson) throws ClassNotBookedException, ClassIsAttendedException {
        if(!beginner.isClassBooked(lesson)){
            throw new ClassNotBookedException("You are not attending this class");
        }
        if(beginner.isClassAttended(lesson)){
            throw new ClassIsAttendedException("You cannot cancel a class you have already attended");
        }
        lesson.removeAttendee(beginner);
        beginner.cancelClass(lesson);
    }

    @Override
    public void attendClass(Beginner beginner, SwimmingClass lesson) throws ClassNotBookedException {
        if(!beginner.isClassBooked(lesson)){
            throw new ClassNotBookedException("You can't attend a class you haven't booked");
        }
        beginner.attendClass(lesson);
    }

    @Override
    public void writeAnalysis(Beginner beginner, SwimmingClass lesson, int rating) throws RatingOutOfRangeException, WriteAnalysisException {
        if(rating < 1 || rating > 5){
            throw new RatingOutOfRangeException("Rating must be between 1 and 5");
        }
        System.out.println("lesson.getAttendees().contains(beginner)");
        System.out.println(lesson.getAttendees().contains(beginner));
        if(!lesson.getAttendees().contains(beginner)){
           throw new WriteAnalysisException("You can't write a analysis for a class you haven't attended");
        }
        analysis.add(
                new Analysis(beginner, rating, lesson)
        );
    }

    @Override
    public void generateBeginnersReport() {
        generateAnalysisStrategy = new GenerateBeginnersAnalysisStrategy();
        generateAnalysisStrategy.generateReport(this);
    }

    @Override
    public void generateMentorsReport() {
        generateAnalysisStrategy = new GenerateMentorsAnalysisStrategy();
        generateAnalysisStrategy.generateReport(this);
    }
    public List<SwimmingClass> getClasses() {
        return lessons;
    }

    public List<Mentor> getMentors() {
        return mentors;
    }

    public List<Beginner> getBeginners() {
        return beginners;
    }

    public List<Analysis> getAnalysis() {
        return analysis;
    }

    private void printSeparator(){
        System.out.println("---------------------------------------------");
    }
}
