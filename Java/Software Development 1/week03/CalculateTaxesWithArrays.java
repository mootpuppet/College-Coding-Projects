/*
 * Course: CS1011-051
 * Fall 2022-2023
 * File header contains class CalculateTaxesWithArrays
 * Name: mottc
 * Created 9/22/2022
 */
package Mott.week03;

//import scanner library
import java.util.Scanner;

/**
 * Course CS1011-051
 * Fall 2022-2023
 * CalculateTaxes purpose: calculate taxes owed based on filing status and income using arrays
 *
 * @author mottc
 * @version created on 9/22/2022 at 12:09 AM
 */

public class CalculateTaxesWithArrays {

    // GLOBAL VARIABLES //
    public static double income;               //user income
    public static String filingStatus;         //user filing status
    public static double taxesOwed;            //estimated taxes owed


    //tax percentages for each tax bracket
    public static final double[] taxPercent =new double[]
            {0.1,0.12,0.22,0.24,0.32,0.35,0.37};


    //initialize new scanner
    public static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        getFilingStatus();         //get filing status from user
        getIncome();               //get income from user
        calculateTaxesOwed();      //calculate taxes owed based on filing status and income
        displayTaxes();            //display taxes owed

    } //end main

    /**
     * ask user for filing status and get input
     */
    public static void getFilingStatus() {

        filingStatus="m";     //allows loop to begin

        //repeat until user enters 's' or 'j'
        while(!filingStatus.equals("s") && !filingStatus.equals("j")) {

            System.out.print("Are you a single filer or " +          //ask user for filing status
                    "a married joint filer (enter 's' or 'j'): ");

            filingStatus = userInput.next();                        //get user input

        } //end while loop

    } //end getFilingStatus

    /**
     * get users estimated income
     */
    public static void getIncome() {

        System.out.print("Enter an estimate of your " +  //ask user to enter income
                "earned income for 2022: $");

        income= userInput.nextDouble();                  //get income

    } //end getIncome

    /**
     * calls singleFilerTaxes() method if user is a single filer,
     * calls jointFilerTaxes() method if user is a joint filer,
     * or prints an error message if filingStatus somehow becomes
     * something that is neither an 's' nor a 'j'.
     */
    public static void calculateTaxesOwed() {

        if(filingStatus.equals("s")){           //if user is a single filer

            singleFilerTaxes();                 //calculate taxes owed

        } //end if

        else if(filingStatus.equals("j")){      //if user is a joint filer

            jointFilerTaxes();                  //calculate taxes owed

        } //end else if

        else {

            System.out.print("How did you get this far?");  //this message should never print.

        } //end else

    } //end calculateTaxesOwed

    /**
     * displays estimated taxes owed and effective tax rate
     */
    public static void displayTaxes() {

        System.out.println("\nYour estimated taxes for 2022 are:" +    //estimated taxes
                " $" +Math.round(taxesOwed));

        System.out.println("This results in an "                        //effective tax rate
                +(taxesOwed/income*100) +"% effective tax rate.");
    } //end displayTaxes

    /**
     * calculates taxes for single filers
     */
    public static void singleFilerTaxes() {

        //LOCAL VARIABLES//

        final double[] taxBracketIncomeLevel = new double[]     //income levels for each tax bracket
                {10275,41775,89075,170050,215950,539900};

        double[] maxTax = new double[6];                        //allocate memory for maxTax array

        maxTax[0]= taxBracketIncomeLevel[0]*taxPercent[0];      //maximum tax for first tax bracket

        //calculate maximum tax for the rest of the tax brackets
        for (int i = 1; i < taxBracketIncomeLevel.length; i++) {

            maxTax[i] = maxTax[i - 1] + (taxBracketIncomeLevel[i]
                    - taxBracketIncomeLevel[i - 1]) * taxPercent[i];

        } //end for loop

        //System.out.println(Arrays.toString(maxTax));           //uncomment to check calculations


        if(income <=taxBracketIncomeLevel[0]){

            taxesOwed = income*taxPercent[0];                  //taxes owed if user is in first tax bracket

        }  //end if statement

        //calculate taxes owed for tax brackets 2-6
        else if(income<=taxBracketIncomeLevel[5]) {

            int i = 1;                                                          //index


            //this loop ends when income is less than the tax bracket threshold
            while (income > taxBracketIncomeLevel[i-1]) {

                taxesOwed = maxTax[i - 1]+
                        (income-taxBracketIncomeLevel[i-1])*taxPercent[i];      //calculate taxes owed

                i++;                                                            //increase index by 1

            } //end while loop

        } //end else if

        //calculate taxes for last tax bracket
        else{
            taxesOwed = maxTax[5]+
                    (income-taxBracketIncomeLevel[5])*taxPercent[6];

        } //end else


    } //end SingleFilerTaxes


    /**
     * This method calculates the taxes owed for joint filers
     */
    public static void jointFilerTaxes() {


        //LOCAL VARIABLES//
        final double[] taxBracketIncomeLevel = new double[]
                {20550,83550,178150,340100,431900,647850};      //income level for each tax bracket

        double[] maxTax = new double[6];                        //allocate memory for maxTax array

        maxTax[0]= taxBracketIncomeLevel[0]*taxPercent[0];      //maximum tax for first tax bracket

        //calculate maximum tax for the rest of the tax brackets
        for (int i = 1; i < taxBracketIncomeLevel.length; i++) {

            maxTax[i] = maxTax[i - 1] + (taxBracketIncomeLevel[i]
                    - taxBracketIncomeLevel[i - 1]) * taxPercent[i];

        } //end for loop

        //System.out.println(Arrays.toString(maxTax));           //uncomment to check calculations


        if(income <=taxBracketIncomeLevel[0]){

            taxesOwed = income*taxPercent[0];                  //taxes owed if user is in first tax bracket

        }  //end if statement

        //calculate taxes owed for tax brackets 2-6
        else if(income<=taxBracketIncomeLevel[5]) {

            int i = 1;                                                          //index


            //this loop ends when income is less than the tax bracket threshold
            while (income > taxBracketIncomeLevel[i-1]) {

                taxesOwed = maxTax[i - 1]+
                        (income-taxBracketIncomeLevel[i-1])*taxPercent[i];      //calculate taxes owed

                i++;                                                            //increase index by 1

            } //end while loop

        } //end else if


        //calculate taxes for last tax bracket
        else{

            taxesOwed = maxTax[5]+ (income-taxBracketIncomeLevel[5])*taxPercent[6];

        } //end else



    } //end jointFilerTaxes

} //end CalculateTaxesWithArrays