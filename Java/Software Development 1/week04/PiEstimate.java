/*
 * Course: CS1011-051
 * Fall 2022-2023
 * File header contains class PiEstimate
 * Name: mottc
 * Created 10/3/2022
 */
package Mott.week04;

import java.util.Scanner;

/**
 * Course CS1011-051
 * Fall 2022-2023
 * PiEstimate purpose: generate a quarter circle image based on user input radius and use it to
 * estimate the value of PI
 *
 * @author mottc
 * @version created on 10/3/2022 at 11:30 PM
 */
public class PiEstimate {

    public static Scanner userInput = new Scanner(System.in);    //initialize scanner variable

    //GLOBAL VARIABLES//
    private static int radius;                 //semicircle radius
    private static int area =0;                //number of stars/pixels / area of semicircle
    private static double piTolerance;         //error tolerance for pi estimation

    public static void main(String[] args) {

        getRadius();                          //get radius from user
        generateImage();                      //generate semi-circle
        estimatePi();                         //estimate the value of pi using semicircle area and radius
        getErrorTolerance();                  //get error tolerance from user
        estimatePiWithErrorTolerance();       //estimate pi using error tolerance

    } //end main()

    /**
     * this method asks the user for a radius, if the user doesn't enter
     * an integer, they are asked to please enter and integer and then asked
     * for the radius again.
     */
    public static void getRadius(){

        boolean flag;     //flag error

        do {
            try {

                System.out.print("Enter the desired radius: ");   //ask user for radius
                radius = userInput.nextInt();                     //get radius
                flag = false;                                     //flag false if no exception is caught

            } //end try
            catch (Exception e) { //error protocol

                System.out.println("\nPlease enter an integer.");  //error message
                userInput.nextLine();                              //clear scanner
                flag=true;                                         //flag true if exception is caught

            }//end catch

        }while(flag); //repeat if exception is caught

    } //end getRadius()

    /**
     * This method uses the user input radius to generate an image
     * of a semicircle and indexes the area of the semicircle
     */
    public static void generateImage(){

        int row;                                    //current row
        int col;                                    //current column

        //generate semicircle image inside a box
        for(row=radius+1; row>=0; row--){

            //generates top row and bottom row of image
            if(row == 0 || row == radius +1){

                for(col=0; col<=radius+1; col++){

                    //print start of row
                    if(col==0){

                        System.out.print("+");

                    } //end if

                    //print end of row
                    else if(col==radius+1){
                        System.out.print("+\n");

                    } //end else if

                    //print the middle bits
                    else{

                        System.out.print("-");

                    } //end else

                } //end for loop

            } //end if

            //generate all other rows
            else {

                for (col = 0; col <= radius + 1; col++) {


                    if(col==0){                     //start of current row

                        System.out.print("|");

                    } //end if

                    else if(col == radius+1){       //end of current row

                        System.out.print("|\n");

                    } //end else if

                    //generate semicircle image
                    else if (Math.sqrt(Math.pow(row-1,2)
                            +Math.pow(col-1,2)) <= radius-0.5){    //if current row,col is within radius

                        System.out.print("*");                     //astrix represents semicircle pixels
                        area++;                                    //area is equal to the number of astrix

                    } //end else if


                    else{

                        System.out.print(" ");      //print space if current row,col is outside semicircle radius

                    } //end else

                } //end for loop

            } //end else

        } //end for loop

    } //end generateImage()

    /**
     * this method uses the user input radius and the area of the semicircle
     * to estimate the value of pi. That estimation is then printed to the console.
     */
    public static void estimatePi(){

        double pi = 4* area /Math.pow(radius,2);                              //estimation of pi
        System.out.println("4 * " + area +"/" +radius*radius + " = " +pi);    //print equation to console

    } //end estimatePi()

    /**
     * this method asks the user for an error tolerance in the
     * form of a double floating point decimal. if the user inputs
     * anything other than a double or an integer, they will repeatedly
     * be reminded to input a double until they do so.
     */
    public static void getErrorTolerance(){

        boolean flag;     //flag error

        do {
            try {

                System.out.print("Enter the minimum desired error " +
                        "for the estimate of PI: ");                   //ask user for error tolerance
                piTolerance = userInput.nextDouble();                  //get error tolerance
                flag = false;                                          //flag false if no exception is caught

            } //end try

            catch (Exception e) { //error protocol

                System.out.println("\nPlease enter a double floating point decimal.");  //error message
                userInput.nextLine();                              //clear scanner
                flag=true;                                         //flag true if exception is caught

            } //end catch

        }while(flag); //repeat if exception is caught


    } //end getErrorTolerance()

    /**
     * this method uses the user input error tolerance to estimate
     * the value of pi within that error tolerance
     */
    public static void estimatePiWithErrorTolerance(){

        //local variables
        int row;                                   //current row
        int col;                                   //current column
        int semicircleRadius =1;                   //radius of semicircle
        int semicircleArea=0;                      //area of semicircle
        double piEstimationWithinTolerance;        //estimation of pi within error tolerance

        //estimate pi
        do {

            for (row = 1; row <= semicircleRadius; row++) {

                for (col = 1; col <= semicircleRadius; col++) {

                    //add one to area of semicircle if current row, col is within radius
                    if (Math.sqrt(Math.pow(row - 1, 2) +
                            Math.pow(col - 1, 2)) <= semicircleRadius - 0.5) {

                        semicircleArea++; //add one to area

                    } //end if

                } //end inner for loop

            } //end outer for loop

           double rSquare=Math.pow(semicircleRadius,2);                  //square the radius
            piEstimationWithinTolerance= 4*semicircleArea/rSquare;       //estimate pi
            semicircleRadius++;                                          //add one to value of radius
            semicircleArea=0;                                            //reset area



        } while(piEstimationWithinTolerance-Math.PI > piTolerance);     //repeat if estimation is not within tolerance

        //display message once the estimation is within the error tolerance
        System.out.println("An estimate of "+piEstimationWithinTolerance
                +" was achieved with a radius of " +(semicircleRadius-1));

    } //end estimatePiWithErrorTolerance()

} //end PiEstimate()