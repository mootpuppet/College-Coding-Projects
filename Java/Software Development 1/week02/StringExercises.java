/*
 * Course: CS1011-051
 * Fall 2022-2023
 * File header contains class StringExercises
 * Name: mottc
 * Created 9/15/2022
 */
package Mott.week02;

/**
 * Course CS1011-051
 * Fall 2022-2023
 * StringExercises purpose: experiment with strings and their properties
 *
 * @author mottc
 * @version created on 9/15/2022 at 12:37 PM
 */
public class StringExercises {
    public static void main(String[] args) {
        //code for unit testing of class StringExercises
        String input = "Hello WorlD";
        String output = input.toLowerCase();
        int length;
        length=input.length();
        int newLength = length/2;
        output = output.substring(newLength);
        System.out.println(output +"\n" +"length is " +newLength);
    }
}