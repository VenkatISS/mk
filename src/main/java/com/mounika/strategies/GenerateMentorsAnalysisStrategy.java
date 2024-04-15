package com.mounika.strategies;

import com.mounika.entities.Mentor;
import com.mounika.entities.Analysis;
import com.mounika.service.HsSwimSchool;
import com.mounika.utils.Helper;

import java.util.List;

public class GenerateMentorsAnalysisStrategy implements GenerateAnalysisStrategy {
    @Override
    public void generateReport(HsSwimSchool hsSwimSchool) {
        List<Analysis> analyses = hsSwimSchool.getAnalysis();
        List<Mentor> mentors = hsSwimSchool.getMentors();
        System.out.println("---------------------------------------------");
        System.out.println("Mentors Report");
        System.out.println("---------------------------------------------");
        System.out.println("Number of Mentors: " + mentors.size());
        System.out.println("Mentors: ");
        System.out.printf("%-20s%-20s%-20s\n", "Name", "Average Analysis","Global Rating");
        for (Mentor mentor : mentors) {
            double rating = getAverageRating(analyses, mentor);
            System.out.printf("%-20s%-20s%-20s\n", mentor.getName(), rating, Helper.getRatingString((int) rating));
        }
        System.out.println("---------------------------------------------");
    }

    private double getAverageRating(List<Analysis> analyses, Mentor mentor) {
        double sum = 0;
        int count = 0;
        for (Analysis analysis : analyses) {
            if (analysis.getLesson().getMentor().equals(mentor)) {
                sum += analysis.getRating();
                count++;
            }
        }
        return sum / count;
    }
}
