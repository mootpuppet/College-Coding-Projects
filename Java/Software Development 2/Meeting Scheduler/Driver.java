/*
 * Course: CS1021 - FIX
 * Winter 2022-23
 * Lab 2 - Interfaces
 * Name: mottc
 * Created: 11/04/2022
 * Modified: 12/7/2022
 */
package mottc;

import static mottc.Schedulable.MONDAY;
import static mottc.Schedulable.TUESDAY;
import static mottc.Schedulable.WEDNESDAY;
import static mottc.Schedulable.THURSDAY;
import static mottc.Schedulable.FRIDAY;

/**
 * Driver class to test the code required to be written for the CS1021 lab 2 assignment.
 * The program creates three calendars, adds schedulable items to the calendars, and
 * then displays each calendar.
 */
public class Driver {
    public static void main(String[] args) {
        Calendar bukowy = getBukowyCalendar();
        Calendar jones = getJonesCalendar();
        Calendar student = getStudentCalendar();
        System.out.println(bukowy);
        System.out.println(jones);
        System.out.println(student);

    }

    /**
     * Generates a calendar with the classes for the student completing this assignment.
     * @return The student's calendar
     */
    private static Calendar getStudentCalendar() {

        Calendar mott = new Calendar("Cody Mott",7,15,
                new Section("CS1021-021",
                        new Meeting(9, 1, MONDAY),
                        new Meeting(9, 1, TUESDAY),
                        new Meeting(8, 2, WEDNESDAY),
                        new Meeting(9, 1, FRIDAY)),
                new Section("CE1911 021",
                        new Meeting(11, 1, TUESDAY),
                        new Meeting(11, 1, WEDNESDAY),
                        new Meeting(10, 2, THURSDAY),
                        new Meeting(11, 1, FRIDAY)),
                new Section("EE2060-021",
                        new Meeting(12, 3, TUESDAY),
                        new Meeting(12, 2, THURSDAY))
        );
        return mott;

    }

    /**
     * Generates a calendar with the classes for Dr. Bukowy.
     * @return Dr. Bukowy's calendar
     */
    private static Calendar getBukowyCalendar() {
        Meeting lec1 = new Meeting(8, 1, MONDAY);
        Meeting lec2 = new Meeting(8, 1, TUESDAY);
        Meeting lec3 = new Meeting(8, 1, FRIDAY);
        Meeting lab = new Meeting(8, 2, THURSDAY);
        Section cs1021_011 = new Section("CS1021-011", lec1, lec2, lec3, lab);
        Section cs1021_021 = new Section("CS1021-021",
                new Meeting(9, 1, MONDAY),
                new Meeting(9, 1, TUESDAY),
                new Meeting(9, 1, FRIDAY),
                new Meeting(8, 2, WEDNESDAY));
        Section cs1021_061 = new Section("CS1021-061",
                new Meeting(13, 1, TUESDAY),
                new Meeting(13, 1, WEDNESDAY),
                new Meeting(13, 1, FRIDAY),
                new Meeting(12, 2, THURSDAY));
        Calendar bukowy = new Calendar("Dr. Bukowy", 7, 14, cs1021_011, cs1021_021, cs1021_061);
        return bukowy;
    }

    /**
     * Generates a calendar with the classes for Prof. Jones.
     * @return Prof. Jones's calendar
     */
    private static Calendar getJonesCalendar() {
        Calendar jones = new Calendar("Prof. Jones",
                new Section("CS2711-003",
                        new Meeting(13, 1, TUESDAY),
                        new Meeting(13, 1, WEDNESDAY),
                        new Meeting(13, 1, THURSDAY),
                        new Meeting(13, 1, FRIDAY)),
                new Section("CS2711-004",
                        new Meeting(14, 1, MONDAY),
                        new Meeting(14, 1, TUESDAY),
                        new Meeting(14, 1, THURSDAY),
                        new Meeting(14, 1, FRIDAY)),
                new Section("SE2840-011",
                        new Meeting(8, 1, MONDAY),
                        new Meeting(8, 1, TUESDAY),
                        new Meeting(8, 1, FRIDAY),
                        new Meeting(8, 2, WEDNESDAY)),
                new Section("SE2840-021",
                        new Meeting(9, 1, MONDAY),
                        new Meeting(9, 1, TUESDAY),
                        new Meeting(9, 1, FRIDAY),
                        new Meeting(9, 2, THURSDAY))
                );
        return jones;
    }

}
