package com.mounika;


import com.mounika.entities.*;
import com.mounika.exceptions.*;
import com.mounika.service.HsSwimSchool;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class HsSwimSchoolTest {

    private HsSwimSchool hsSwimSchool;
    private Beginner beginner;
    private SwimmingClass lesson;
    @Before
    public void setUp() throws BeginnerAgeException {
        hsSwimSchool = new HsSwimSchool();
        beginner = new Beginner("Manu","Male", 10,"+94771234567",1);
        lesson = new SwimmingClass(1234,2);
    }


    @Test
    public void testAddBeginner_Successful() throws BeginnerAlreadyExistsException, BeginnerAgeException {
        hsSwimSchool.addBeginner(beginner);
        assertTrue(hsSwimSchool.getBeginners().contains(beginner));
    }

    @Test(expected = BeginnerAlreadyExistsException.class)
    public void testAddBeginner_AlreadyExists() throws BeginnerAlreadyExistsException, BeginnerAgeException {
        hsSwimSchool.addBeginner(beginner); // Adding the beginner once
        hsSwimSchool.addBeginner(beginner); // Trying to add the same beginner again should throw an exception
    }

    @Test
    public void testRemoveBeginner_Successful() throws BeginnerAlreadyExistsException, BeginnerAgeException {
        hsSwimSchool.addBeginner(beginner);
        assertTrue(hsSwimSchool.getBeginners().contains(beginner));
        hsSwimSchool.removeBeginner(beginner);
        assertFalse(hsSwimSchool.getBeginners().contains(beginner));
    }

    @Test
    public void testAddMentor_Successful() throws MentorAlreadyExistsException {
        Mentor mentor = new Mentor("Mentor John");
        hsSwimSchool.addMentor(mentor);
        assertTrue(hsSwimSchool.getMentors().contains(mentor));
    }

    @Test(expected = MentorAlreadyExistsException.class)
    public void testAddMenor_AlreadyExists() throws MentorAlreadyExistsException {
        Mentor mentor = new Mentor("Mentor Jane");
        hsSwimSchool.addMentor(mentor); // Adding the Mentor once
        hsSwimSchool.addMentor(mentor); // Trying to add the same Mentor again should throw an exception
    }

    @Test
    public void testRemoveMentor_Successful() throws MentorAlreadyExistsException {
        Mentor mentor = new Mentor("Mentor Alice");
        hsSwimSchool.addMentor(mentor);
        assertTrue(hsSwimSchool.getMentors().contains(mentor));
        hsSwimSchool.removeMentor(mentor);
        assertFalse(hsSwimSchool.getMentors().contains(mentor));
    }

    @Test
    public void testAssignMentorToLesson_Successful() throws SwimmingClassAlreadyHasMentorException {
        Mentor mentor = new Mentor("Mentor John");
        SwimmingClass lesson = new SwimmingClass(1);
        hsSwimSchool.assignMentorToClass(mentor, lesson);
        assertEquals(mentor, lesson.getMentor());
    }

    @Test(expected = SwimmingClassAlreadyHasMentorException.class)
    public void testAssignMentorToLesson_AlreadyHasMentor() throws SwimmingClassAlreadyHasMentorException {
        Mentor mentor1 = new Mentor("Mentor Alice");
        Mentor mentor2 = new Mentor("Mentor Bob");
        SwimmingClass lesson = new SwimmingClass(2);
        hsSwimSchool.assignMentorToClass(mentor1, lesson); // Assigning the first Mentor
        hsSwimSchool.assignMentorToClass(mentor2, lesson); // Trying to assign the another Mentor should throw an exception
    }

    @Test
    public void testBookLesson_Successful() throws NotEligibleForClassException, ClassAlreadyBookedException, ClassIsFullException, BeginnerAlreadyExistsException {
        hsSwimSchool.addBeginner(beginner);
        hsSwimSchool.addClass(lesson);
        hsSwimSchool.bookClass(beginner, lesson);
        assertTrue(beginner.isClassBooked(lesson));
        assertTrue(lesson.getAttendees().contains(beginner));
    }

    @Test(expected = ClassAlreadyBookedException.class)
    public void testBookLesson_AlreadyBooked() throws NotEligibleForClassException, ClassAlreadyBookedException, ClassIsFullException, BeginnerAlreadyExistsException {
        hsSwimSchool.addBeginner(beginner);
        hsSwimSchool.addClass(lesson);
        hsSwimSchool.bookClass(beginner, lesson);
        hsSwimSchool.bookClass(beginner, lesson);
    }

    @Test
    public void testCancelLesson_Successful() throws NotEligibleForClassException, ClassAlreadyBookedException, ClassIsFullException, ClassNotBookedException, ClassIsAttendedException, BeginnerAlreadyExistsException {
        hsSwimSchool.addBeginner(beginner);
        hsSwimSchool.addClass(lesson);
        hsSwimSchool.bookClass(beginner, lesson);
        hsSwimSchool.cancelClass(beginner, lesson);
        assertFalse(beginner.isClassBooked(lesson));
        assertFalse(lesson.getAttendees().contains(beginner));
    }

    @Test(expected = ClassNotBookedException.class)
    public void testCancelLesson_NotBooked() throws ClassNotBookedException, ClassIsAttendedException {
        hsSwimSchool.cancelClass(beginner, lesson);
    }

    @Test
    public void testAttendLesson_Successful() throws NotEligibleForClassException, ClassAlreadyBookedException, ClassIsFullException, ClassNotBookedException, BeginnerAlreadyExistsException {
        hsSwimSchool.addBeginner(beginner);
        hsSwimSchool.addClass(lesson);
        hsSwimSchool.bookClass(beginner, lesson);
        hsSwimSchool.attendClass(beginner, lesson);
        assertTrue(beginner.isClassAttended(lesson));
    }

    @Test(expected = ClassNotBookedException.class)
    public void testAttendLesson_NotBooked() throws ClassNotBookedException {
        hsSwimSchool.attendClass(beginner, lesson);
    }

    @Test
    public void testWriteAnalysis_Successful() throws NotEligibleForClassException, RatingOutOfRangeException, WriteAnalysisException, BeginnerAlreadyExistsException, ClassIsFullException, ClassAlreadyBookedException, ClassNotBookedException {
        hsSwimSchool.addBeginner(beginner);
        hsSwimSchool.addClass(lesson);
        hsSwimSchool.bookClass(beginner, lesson);
        hsSwimSchool.attendClass(beginner, lesson);

        int rating = 5;
        hsSwimSchool.writeAnalysis(beginner, lesson, rating);

        Analysis analysis = hsSwimSchool.getAnalysis().get(0);
        assertEquals(beginner, analysis.getBeginner());
        assertEquals(lesson, analysis.getLesson());
        assertEquals(rating, analysis.getRating());
    }

    @Test(expected = NotEligibleForClassException.class)
    public void testWriteAnalysis_NotEligibleForLesson() throws NotEligibleForClassException, RatingOutOfRangeException, WriteAnalysisException, BeginnerAlreadyExistsException, ClassIsFullException, ClassAlreadyBookedException {
        SwimmingClass lesson = new SwimmingClass(1234, 3);
        hsSwimSchool.addBeginner(beginner);
        hsSwimSchool.addClass(lesson);

        hsSwimSchool.bookClass(beginner, lesson);
        hsSwimSchool.writeAnalysis(beginner, lesson, 5); // Beginner hasn't booked or attended the lesson
    }

    @Test(expected = WriteAnalysisException.class)
    public void testWriteAnalysis_NotAttendedLesson() throws  RatingOutOfRangeException, WriteAnalysisException, BeginnerAlreadyExistsException, ClassIsFullException, ClassAlreadyBookedException {
        hsSwimSchool.addBeginner(beginner);
        hsSwimSchool.addClass(lesson);
        hsSwimSchool.writeAnalysis(beginner, lesson, 5); // Beginner hasn't attended the lesson
    }

    @Test(expected = RatingOutOfRangeException.class)
    public void testWriteAnalysis_RatingOutOfRange() throws NotEligibleForClassException, RatingOutOfRangeException, WriteAnalysisException, BeginnerAlreadyExistsException, ClassIsFullException, ClassAlreadyBookedException, ClassNotBookedException {
        hsSwimSchool.addBeginner(beginner);
        hsSwimSchool.addClass(lesson);
        hsSwimSchool.bookClass(beginner, lesson);
        hsSwimSchool.attendClass(beginner, lesson);
        hsSwimSchool.writeAnalysis(beginner, lesson, 6); // Rating out of range
    }

    // Helper method to capture System.out
    private String captureOutput(Runnable code) {
        // Redirect System.out to a string
        var outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the code
        code.run();

        // Reset System.out
        System.setOut(System.out);

        // Return the captured output
        return outContent.toString();
    }
}