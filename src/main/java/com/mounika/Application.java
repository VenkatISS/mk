package com.mounika;


import com.mounika.entities.*;
import com.mounika.exceptions.BeginnerAgeException;
import com.mounika.exceptions.BeginnerAlreadyExistsException;
import com.mounika.exceptions.MentorAlreadyExistsException;
import com.mounika.service.HsSwimSchool;

import java.util.Scanner;

public class Application {
    private static final HsSwimSchool swimmingSchool = new HsSwimSchool();
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            clearConsole();
            separator();
            System.out.println("Welcome to the HJSS Bookings Management System");
            separator();
            System.out.println("1. Add Beginner");
            System.out.println("2. Remove Beginner");
            System.out.println("3. Add Mentor");
            System.out.println("4. Remove Mentor");
            System.out.println("5. Add Class");
            System.out.println("6. Remove Class");
            System.out.println("7. View Availability by Day");
            System.out.println("8. View Availability by Grade");
            System.out.println("9. View Availability by Beginner");
            System.out.println("10. Book Class");
            System.out.println("11. Cancel Class");
            System.out.println("12. Attend Class");
            System.out.println("13. Write Analysis");
            System.out.println("14. Generate Beginners Analysis");
            System.out.println("15. Generate Mentors Analysis");
            System.out.println("0. Exit");
            separator();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    clearConsole();
                    separator();
                    System.out.println("Add Beginner");
                    separator();
                    addBeginner();
                    break;
                case 2:
                    clearConsole();
                    separator();
                    System.out.println("Remove Beginner");
                    separator();
                    removeBeginner();
                    break;
                case 3:
                    clearConsole();
                    separator();
                    System.out.println("Add Mentor");
                    separator();
                    addMentor();
                    break;
                case 4:
                    clearConsole();
                    separator();
                    System.out.println("Remove Mentor");
                    separator();
                    removeMentor();
                    break;
                case 5:
                    clearConsole();
                    separator();
                    System.out.println("Add Class");
                    separator();
                    addClass();
                    break;
                case 6:
                    clearConsole();
                    separator();
                    System.out.println("Remove Class");
                    separator();
                    removeClass();
                    break;
                case 7:
                    clearConsole();
                    separator();
                    System.out.println("Availability by Day");
                    separator();
                    viewAvailabilityByDay();
                    break;
                case 8:
                    clearConsole();
                    separator();
                    System.out.println("Availability by Grade");
                    separator();
                    viewAvailabilityByGrade();
                    break;
                case 9:
                    clearConsole();
                    separator();
                    System.out.println("Availability by Mentors");
                    separator();
                    viewAvailabilityByMentor();
                    break;
                case 10:
                    clearConsole();
                    separator();
                    System.out.println("Book Class");
                    separator();
                    bookClass();
                    break;
                case 11:
                    clearConsole();
                    separator();
                    System.out.println("Cancel Class");
                    separator();
                    cancelClass();
                    break;
                case 12:
                    clearConsole();
                    separator();
                    System.out.println("Attend Class");
                    separator();
                    attendClass();
                    break;
                case 13:
                    clearConsole();
                    separator();
                    System.out.println("Write Analysis");
                    separator();
                    writeAnalysis();
                    break;
                case 14:
                    swimmingSchool.generateBeginnersReport();
                    break;
                case 15:
                    swimmingSchool.generateMentorsReport();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    scanner.nextLine();
            }
        }
    }

    private static void addBeginner() {
        System.out.print("Enter beginner name: ");
        String beginnerName = scanner.nextLine();
        System.out.print("Enter beginner gender: ");
        String beginnerGender = scanner.nextLine();
        System.out.print("Enter beginner age: ");
        int beginnerAge = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter beginner emergency contact: ");
        String beginnerEmergencyContact = scanner.nextLine();
        System.out.print("Enter beginner grade: ");
        int beginnerGrade = scanner.nextInt();
        scanner.nextLine();
        try {
            swimmingSchool.addBeginner(
                    new Beginner(
                            beginnerName,
                            beginnerGender,
                            beginnerAge,
                            beginnerEmergencyContact,
                            beginnerGrade
                    )
            );
            System.out.println("Beginner added successfully!");
            scanner.nextLine();
        } catch (BeginnerAlreadyExistsException | BeginnerAgeException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
    }

    private static void removeBeginner() {
        System.out.print("Enter beginner name: ");
        Beginner beginner = swimmingSchool.getBeginners().stream().filter(
                l -> l.getName().equals(scanner.nextLine())
        ).findFirst().orElse(null);
        if (beginner == null) {
            System.out.println("Beginner not found!");
            scanner.nextLine();
            return;
        }
        swimmingSchool.removeBeginner(beginner);
        System.out.println("Beginner removed successfully!");
        scanner.nextLine();
    }

    private static void addMentor() {
        System.out.print("Enter Mentor name: ");
        String mentorName = scanner.nextLine();
        try {
            swimmingSchool.addMentor(
                    new Mentor(
                            mentorName
                    )
            );
            System.out.println("Mentor added successfully!");
            scanner.nextLine();
        } catch (MentorAlreadyExistsException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
    }

    private static void removeMentor() {
        System.out.print("Enter mentor name: ");
        Mentor mentor = swimmingSchool.getMentors().stream().filter(
                c -> c.getName().equals(scanner.nextLine())
        ).findFirst().orElse(null);
        if (mentor == null) {
            System.out.println("Mentor not found!");
            scanner.nextLine();
            return;
        }
        swimmingSchool.removeMentor(mentor);
        System.out.println("Mentor removed successfully!");
        scanner.nextLine();
    }

    private static void addClass() {
        System.out.print("Days");
        System.out.println("1. "+ TeachingDay.MONDAY);
        System.out.println("2. "+ TeachingDay.WEDNESDAY);
        System.out.println("3. "+ TeachingDay.FRIDAY);
        System.out.println("4. "+ TeachingDay.SATURDAY);
        System.out.println("0. Exit");
        TeachingDay day = null;
        System.out.print("Enter day: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                day = TeachingDay.MONDAY;
                break;
            case 2:
                day = TeachingDay.WEDNESDAY;
                break;
            case 3:
                day = TeachingDay.FRIDAY;
                break;
            case 4:
                day = TeachingDay.SATURDAY;
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice!");
                scanner.nextLine();
                return;
        }
        System.out.print("Times");
        System.out.println("1. "+ Availability.From2To3);
        System.out.println("2. "+ Availability.From3To4);
        System.out.println("3. "+ Availability.From4To5);
        System.out.println("4. "+ Availability.From5To6);
        System.out.println("5. "+ Availability.From6To7);
        System.out.println("0. Exit");
        Availability time = null;
        System.out.print("Enter time: ");
        choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                time = Availability.From2To3;
                break;
            case 2:
                time = Availability.From3To4;
                break;
            case 3:
                time = Availability.From4To5;
                break;
            case 4:
                time = Availability.From5To6;
                break;
            case 5:
                time = Availability.From6To7;
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice!");
                scanner.nextLine();
                return;
        }
        System.out.print("Enter grade: ");
        int grade = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter mentor name: ");
        String mentorName = scanner.nextLine();
        Mentor mentor = swimmingSchool.getMentors().stream().filter(
                c -> c.getName().equals(mentorName)
        ).findFirst().orElse(null);
        if (mentor == null) {
            System.out.println("<Mentor> not found!");
            scanner.nextLine();
            return;
        }
        scanner.nextLine();
        swimmingSchool.addClass(
                new SwimmingClass(
                        (int) Math.random()*1000,
                        time,
                        mentor,
                        day,
                        grade
                )
        );
        System.out.println("Class added successfully!");
        scanner.nextLine();
    }

    private static void removeClass() {
        System.out.print("Enter class id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SwimmingClass session = swimmingSchool.getClasses().stream().filter(
                l -> l.getId() == id
        ).findFirst().orElse(null);
        if (session == null) {
            System.out.println("Session not found!");
            scanner.nextLine();
            return;
        }
        swimmingSchool.removeClass(
                session
        );
        System.out.println("Class removed successfully!");
        scanner.nextLine();
    }

    private static void viewAvailabilityByDay() {
        System.out.print("Days");
        System.out.println("1. "+ TeachingDay.MONDAY);
        System.out.println("2. "+ TeachingDay.WEDNESDAY);
        System.out.println("3. "+ TeachingDay.FRIDAY);
        System.out.println("4. "+ TeachingDay.SATURDAY);
        System.out.println("0. Exit");
        TeachingDay day = null;
        System.out.print("Enter day: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                day = TeachingDay.MONDAY;
                break;
            case 2:
                day = TeachingDay.WEDNESDAY;
                break;
            case 3:
                day = TeachingDay.FRIDAY;
                break;
            case 4:
                day = TeachingDay.SATURDAY;
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice!");
                scanner.nextLine();
                return;
        }
        swimmingSchool.viewTimetableByDay(day);
        scanner.nextLine();
    }

    private static void viewAvailabilityByGrade() {
        System.out.print("Enter grade: ");
        int grade = scanner.nextInt();
        scanner.nextLine();
        swimmingSchool.viewTimetableByGrade(grade);
        scanner.nextLine();
    }

    private static void viewAvailabilityByMentor() {
        System.out.print("Enter mentor name: ");
        String mentorName = scanner.nextLine();
        Mentor mentor = swimmingSchool.getMentors().stream().filter(
                c -> c.getName().equals(mentorName)
        ).findFirst().orElse(null);

        if (mentor == null) {
            System.out.println("Mentor not found!");
            scanner.nextLine();
            return;
        }

        swimmingSchool.viewTimetableByMentor(
                mentor
        );
        scanner.nextLine();
    }

    private static void bookClass() {
        System.out.print("Enter beginner name: ");
        String beginnerName = scanner.nextLine();
        Beginner beginner = swimmingSchool.getBeginners().stream().filter(
                l -> l.getName().equals(beginnerName)
        ).findFirst().orElse(null);
        if (beginner == null) {
            System.out.println("Beginner not found!");
            scanner.nextLine();
            return;
        }
        System.out.print("Enter class id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SwimmingClass session = swimmingSchool.getClasses().stream().filter(
                l -> l.getId() == id
        ).findFirst().orElse(null);
        if (session == null) {
            System.out.println("Session not found!");
            scanner.nextLine();
            return;
        }
        try {
            swimmingSchool.bookClass(
                    beginner,
                    session
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        System.out.println("Session booked successfully!");
        scanner.nextLine();
    }

    private static void cancelClass() {
        System.out.print("Enter beginner name: ");
        String beginnerName = scanner.nextLine();
        Beginner beginner = swimmingSchool.getBeginners().stream().filter(
                l -> l.getName().equals(beginnerName)
        ).findFirst().orElse(null);
        if (beginner == null) {
            System.out.println("Beginner not found!");
            scanner.nextLine();
            return;
        }
        System.out.print("Enter class id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SwimmingClass session = swimmingSchool.getClasses().stream().filter(
                l -> l.getId() == id
        ).findFirst().orElse(null);
        if (session == null) {
            System.out.println("Session not found!");
            scanner.nextLine();
            return;
        }
        try {
            swimmingSchool.cancelClass(
                    beginner,
                    session
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        System.out.println("Class cancelled successfully!");
        scanner.nextLine();
    }

    private static void attendClass() {
        System.out.print("Enter beginner name: ");
        String beginnerName = scanner.nextLine();
        Beginner beginner = swimmingSchool.getBeginners().stream().filter(
                l -> l.getName().equals(beginnerName)
        ).findFirst().orElse(null);
        if (beginner == null) {
            System.out.println("Beginner not found!");
            scanner.nextLine();
            return;
        }
        System.out.print("Enter class id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SwimmingClass session = swimmingSchool.getClasses().stream().filter(
                l -> l.getId() == id
        ).findFirst().orElse(null);
        if (session == null) {
            System.out.println("Class not found!");
            return;
        }
        try {
            swimmingSchool.attendClass(
                    beginner,
                    session
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        System.out.println("Class attended successfully!");
        scanner.nextLine();
    }

    private static void writeAnalysis() {
        System.out.print("Enter beginner name: ");
        String beginnerName = scanner.nextLine();
        Beginner beginner = swimmingSchool.getBeginners().stream().filter(
                l -> l.getName().equals(beginnerName)
        ).findFirst().orElse(null);
        if (beginner == null) {
            System.out.println("Beginner not found!");
            scanner.nextLine();
            return;
        }
        System.out.println("Enter Class ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SwimmingClass session = swimmingSchool.getClasses().stream().filter(
                l -> l.getId() == id
        ).findFirst().orElse(null);
        if (session == null) {
            System.out.println("Class not found!");
            scanner.nextLine();
            return;
        }
        System.out.print("Enter analysis: 1-5");
        int analysis = scanner.nextInt();
        scanner.nextLine();
        try {
            swimmingSchool.writeAnalysis(
                    beginner,
                            session,
                            analysis
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        System.out.println("Analysis written successfully!");
        scanner.nextLine();
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    private static void separator() {
        System.out.println("------------------------------------------------");
    }
}
