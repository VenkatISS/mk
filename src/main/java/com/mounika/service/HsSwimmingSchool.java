package com.mounika.service;
import com.mounika.entities.Beginner;
import com.mounika.entities.Mentor;
import com.mounika.entities.SwimmingClass;
import com.mounika.entities.TeachingDay;
import com.mounika.exceptions.*;

public interface HsSwimmingSchool {

    void viewTimetableByDay(TeachingDay day);
    void viewTimetableByGrade(int grade);
    void viewTimetableByMentor(Mentor mentor);

    void bookClass(Beginner beginner, SwimmingClass lesson) throws NotEligibleForClassException, ClassAlreadyBookedException, ClassIsFullException;
    void cancelClass(Beginner beginner, SwimmingClass lesson) throws ClassNotBookedException, ClassIsAttendedException;
    void attendClass(Beginner beginner, SwimmingClass lesson) throws ClassNotBookedException;

    void assignMentorToClass(Mentor mentor, SwimmingClass lesson) throws SwimmingClassAlreadyHasMentorException;

    void addBeginner(Beginner beginner) throws BeginnerAlreadyExistsException;
    void removeBeginner(Beginner beginner);

    void addMentor(Mentor mentor) throws MentorAlreadyExistsException;
    void removeMentor(Mentor mentor);

    void addClass(SwimmingClass lesson);
    void removeClass(SwimmingClass lesson);

    void writeAnalysis(Beginner beginner,SwimmingClass lesson, int rating) throws RatingOutOfRangeException, WriteAnalysisException;

    void generateBeginnersReport();
    void generateMentorsReport();
}
