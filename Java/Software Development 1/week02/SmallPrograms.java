/*
 * Course: CS1011-051
 * Fall 2022-2023
 * File header contains class SmallPrograms
 * Name: mottc
 * Created 9/14/2022
 */
package Mott.week02;

import java.util.Scanner; //import scanner

/**
 * Course CS1011-051
 * Fall 2022-2023
 * SmallPrograms purpose: run multiple different methods in one program
 *
 * @author mottc
 * @version created on 9/14/2022 at 1:13 PM
 */

public class SmallPrograms { //start SmallPrograms
    public static Scanner input = new Scanner(System.in);  //initialize scanner

    public static void main(String[] args) {   // start main

       negate();              // call negate
       pennies();             // call pennies
       lastHalf();            // call lastHalf
       makeInitialization();  // call makeInitialization
       fraction();            // call fraction

    } //end main

    /**
     * get integer from user and negate it
     * and print to console
     */
    public static void negate(){  //start negate

        System.out.println("1. Negate");                //print title to console
        int inputNumberToNegate;                            //declare input variable
        System.out.print("Please enter an integer: ");  //ask user for an integer
        inputNumberToNegate=input.nextInt();                //assign user input to input variable
        int outputNumber = -inputNumberToNegate;            //initialize output variable as negated input
        System.out.println("Result: " +outputNumber);   //print output variable to the console
        System.out.println("\n");                       //blank space for ease of reading

    }  //end negate

    /**
     * get dollar amount from user then convert to
     * number of pennies and print to console
     */
    public static void pennies(){   //start pennies

        System.out.println("2. How many pennies");           //print title to console
        float dollarAmount;                                  //declare input variable
        System.out.print("Please enter a dollar amount: ");  //ask user to input a dollar amount
        dollarAmount=input.nextFloat();                      //assign input variable as user input
        int cents = (int)(dollarAmount*100);                 //convert dollars to cents & initialize output
        System.out.println("Result: " +cents );              //print conversion result to console
        System.out.println("\n");                            //blank space for ease or reading

    }  //end pennies

    /**
     * get string of characters from user then print the last half
     * of the string to the console
    */
    public static void lastHalf(){  //start lastHalf

        System.out.println("3. Last Half");         //print title to console
        String inputString;                         //declare input variable
        System.out.print("Please enter a word: ");  //ask user to enter a word
        inputString=input.next();                   //assign user input to input variable

        System.out.println("Result: "               //print last half of input string to console
                + inputString.substring
                (inputString.length()/2));

        System.out.println("\n");                   //blank space for ease of reading
    }  //end lastHalf

    /**
     * get variable name from user, get an integer from user,
     * then, using the user inputs, print an initialization statement
     * to the console
     */
    public static void makeInitialization(){  //start makeInitialization

        System.out.println("4. Make Initialization");        //print title to console
        String variableName;                                 //declare input string variable
        System.out.print("Please enter a variable name: ");  //ask user to enter a variable name
        variableName=input.next();                           //get input and assign it to string variable
        int variableValue;                                   //declare input integer variable
        System.out.print("Please enter an integer: ");       //ask user to enter an integer
        variableValue=input.nextInt();                       //get input and assign it to integer variable

        System.out.println("Result: int " +variableName      //print initialization statement to console
                +" = " +variableValue +";");

        System.out.println("\n");                            //bank space for ease of reading

    }  //end makeInitialization

    /**
     * get a numerator from the user, get a denominator from the user,
     * convert each integer to a float and perform division,
     * then print the quotient to the console.
     */
    public static void fraction(){  //start fraction

        System.out.println("5. Fraction");                     //print title to console
        int numerator;                                         //declare numerator variable
        System.out.print("Please enter a numerator: ");        //ask user to enter a numerator
        numerator=input.nextInt();                             //get input and assign it to numerator variable
        int denominator;                                       //declare denominator variable
        System.out.print("Please enter a denominator: ");      //ask user to enter a denominator
        denominator=input.nextInt();                           //get input and assign it to denominator

        float quotient = (float)numerator/(float)denominator;  /* convert input integers to float          *
                                                                * perform division and initialize quotient */

        System.out.println("Result: " +quotient);              //print quotient to console

    }  //end fraction

} //end SmallPrograms



