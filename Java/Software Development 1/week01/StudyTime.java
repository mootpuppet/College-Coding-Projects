/*
 * Course: CS1011-051
 * Fall 2022-2023
 * File header contains class Lab1_FirstProgram
 * Name: mottc
 * Created 9/8/2022
 */
package Mott.week01;

import java.util.Scanner;

/**
 * Course CS1011-051
 * Fall 2022-2023
 * Lab1_FirstProgram purpose: to help set study expectations for new college students
 *
 * @author mottc
 * @version created on 9/8/2022 at 1:42 PM
 */
public class StudyTime {
    /**
     * Simple program to help set study expectations for
     * new college students
     * @param args ignored
     */
    public static void main(String[] args){
        //create a "reference variable" / object to gather data
        //from the keyboard
        Scanner in = new Scanner(System.in);

        // Request data from the user
        System.out.print("Enter the number of credits you are taking this term: ");
        int creditsTaken = in.nextInt();
        System.out.print("Enter the number of hours you plan to spend studying each week: ");
        int plannedHours = in.nextInt();

        // Calculate recommended hours of study
        int recommendedHoursLow = creditsTaken * 2;
        int recommendedHoursHigh = creditsTaken * 3;

        System.out.println("You are taking " +creditsTaken
                + " credits this term and plan to study " +plannedHours
                + " hours per week.");

        System.out.print("You plan to study ");
        if(plannedHours < recommendedHoursLow) {
            System.out.print("less than");
        } else if(plannedHours > recommendedHoursHigh) {
            System.out.print("more than");
        } else {
            System.out.print("in the range of");
        }
        System.out.println(" the recommended " +recommendedHoursLow +" - "
                +recommendedHoursHigh +" hours per week.");
    }
}