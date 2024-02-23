/*
 * Course: CS1011-051
 * Fall 2022-2023
 * File header contains class InputAndOutput
 * Name: mottc
 * Created 9/8/2022
 */

package Mott.week01;

import java.util.Scanner;

/**
 * Course CS1011-051
 * Fall 2022-2023
 * InputAndOutput purpose: get an int from user and print it on console
 *
 * @author mottc
 * @version created on 9/8/2022 at 1:04 PM
 */
public class InputAndOutput {
    public static void main(String[] args) {
//code for unit testing of class InputAndOutput
//or delete this main method
        int inputVariable;
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your number");
        inputVariable=input.nextInt();
        System.out.println("You entered the number "+inputVariable);


    }
}

