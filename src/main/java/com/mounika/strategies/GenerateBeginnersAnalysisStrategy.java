package com.mounika.strategies;

import com.mounika.entities.Beginner;
import com.mounika.service.HsSwimSchool;

import java.util.List;

public class GenerateBeginnersAnalysisStrategy implements GenerateAnalysisStrategy {
    @Override
    public void generateReport(HsSwimSchool hsSwimSchool) {
        List<Beginner> beginners = hsSwimSchool.getBeginners();
        System.out.println("---------------------------------------------");
        System.out.println("Beginners Report");
        System.out.println("---------------------------------------------");
        for (Beginner beginner : beginners) {
            System.out.println(beginner);
        }
        System.out.println("---------------------------------------------");
    }
}
