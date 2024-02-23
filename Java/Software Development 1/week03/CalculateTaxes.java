/*
 * Course: CS1011-051
 * Fall 2022-2023
 * File header contains class CalculateTaxes
 * Name: mottc
 * Created 9/22/2022
 */
package Mott.week03;

import java.util.Scanner;

/**
 * Course CS1011-051
 * Fall 2022-2023
 * CalculateTaxes purpose: calculate taxes owed based on filing status and income
 *
 * @author mottc
 * @version created on 9/22/2022 at 12:09 AM
 */

public class CalculateTaxes {

    // GLOBAL VARIABLES //
    public static double income;
    public static String filingStatus;
    public static double taxesOwed;

    //tax bracket percentages

    //public static double[] taxBracketPercent =new double[]{0.1,0.12,0.22,0.24,0.32,0.35,0.37};
    
    public static double bracketPercentOne = 0.10;
    public static double bracketPercentTwo = 0.12;
    public static double bracketPercentThree = 0.22;
    public static double bracketPercentFour = 0.24;
    public static double bracketPercentFive = 0.32;
    public static double bracketPercentSix = 0.35;
    public static double bracketPercentSeven = 0.37;

    public static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {


        getFilingStatus();
        getIncome();
        calculateTaxesOwed();
        displayTaxes();

    }

    public static void getFilingStatus() {
        System.out.print("Are you a single filer or " +
                "a married joint filer (enter 's' or 'j'): ");
        filingStatus = userInput.next();
    }

    public static void getIncome() {
        System.out.print("Enter an estimate of your " +
                "earned income for 2022: $");
        income= userInput.nextDouble();
    }


    public static void calculateTaxesOwed() {
        if(filingStatus.equals("s")){
            singleFilerTaxes();
        }
        else {
            jointFilerTaxes();
        }
    }

    public static void displayTaxes() {

        System.out.println("\nYour estimated taxes for 2022 are: $" +Math.round(taxesOwed));
        System.out.println("This results in an " +(taxesOwed/income*100) +"% effective tax rate.");
    }

    public static void singleFilerTaxes() {

        //LOCAL VARIABLES//

        //single filer tax brackets
        double singleBracketOne = 10275;
        double singleBracketTwo = 41775;
        double singleBracketThree =89075;
        double singleBracketFour = 170050;
        double singleBracketFive = 215950;
        double singleBracketSix = 539900;

        //maximum taxes per bracket for single filers
        double singleMaxTaxLvlOne = singleBracketOne * bracketPercentOne;
        double singleMaxTaxLvlTwo = singleMaxTaxLvlOne+
               (singleBracketTwo-singleBracketOne)*bracketPercentTwo;
        double singleMaxTaxLvlThree = singleMaxTaxLvlTwo+
               (singleBracketThree-singleBracketTwo)*bracketPercentThree;
        double singleMaxTaxLvlFour = singleMaxTaxLvlThree+
               (singleBracketFour-singleBracketThree)*bracketPercentFour;
        double singleMaxTaxLvlFive = singleMaxTaxLvlFour+
               (singleBracketFive-singleBracketFour)*bracketPercentFive;
        double singleMaxTaxLvlSix = singleMaxTaxLvlFive+
               (singleBracketSix-singleBracketFive)*bracketPercentSix;

        //calculate taxes owed for single filers based on income
        if(income <= singleBracketOne){
            taxesOwed = bracketPercentOne *income;
        }
        else if(income <= singleBracketTwo){
            taxesOwed =  singleMaxTaxLvlOne+
                    (income-singleBracketOne)*bracketPercentTwo;
        }
        else if(income <= singleBracketThree){
            taxesOwed = singleMaxTaxLvlTwo+
                    (income-singleBracketTwo)*bracketPercentThree;
        }
        else if(income <= singleBracketFour){
            taxesOwed = singleMaxTaxLvlThree+
                    (income-singleBracketThree)*bracketPercentFour;
        }
        else if(income <= singleBracketFive){
            taxesOwed = singleMaxTaxLvlFour+
                    (income-singleBracketFour)*bracketPercentFive;
        }
        else if(income <= singleBracketSix){
            taxesOwed = singleMaxTaxLvlFive+
                    (income-singleBracketFive)*bracketPercentSix;
        }
        else {
            taxesOwed = singleMaxTaxLvlSix+
                    (income-singleBracketSix)*bracketPercentSeven;
        }

    }

    public static void jointFilerTaxes() {


        //LOCAL VARIABLES//

        //single filer tax brackets
        double jointBracketOne = 20550;
        double jointBracketTwo = 83550;
        double jointBracketThree = 178150;
        double jointBracketFour = 340100;
        double jointBracketFive = 431900;
        double jointBracketSix = 647850;

        //maximum taxes per bracket for single filers
        double jointMaxTaxLvlOne = jointBracketOne * bracketPercentOne;
        double jointMaxTaxLvlTwo = jointMaxTaxLvlOne+
                (jointBracketTwo-jointBracketOne)*bracketPercentTwo;
        double singleMaxTaxLvlThree = jointMaxTaxLvlTwo+
                (jointBracketThree-jointBracketTwo)*bracketPercentThree;
        double singleMaxTaxLvlFour = singleMaxTaxLvlThree+
                (jointBracketFour-jointBracketThree)*bracketPercentFour;
        double singleMaxTaxLvlFive = singleMaxTaxLvlFour+
                (jointBracketFive-jointBracketFour)*bracketPercentFive;
        double singleMaxTaxLvlSix = singleMaxTaxLvlFive+
                (jointBracketSix-jointBracketFive)*bracketPercentSix;

        //calculate taxes owed for single filers based on income
        if(income <= jointBracketOne){
            taxesOwed = bracketPercentOne *income;
        }
        else if(income <= jointBracketTwo){
            taxesOwed =  jointMaxTaxLvlOne+
                    (income-jointBracketOne)*bracketPercentTwo;
        }
        else if(income <= jointBracketThree){
            taxesOwed = jointMaxTaxLvlTwo+
                    (income-jointBracketTwo)*bracketPercentThree;
        }
        else if(income <= jointBracketFour){
            taxesOwed = singleMaxTaxLvlThree+
                    (income-jointBracketThree)*bracketPercentFour;
        }
        else if(income <= jointBracketFive){
            taxesOwed = singleMaxTaxLvlFour+
                    (income-jointBracketFour)*bracketPercentFive;
        }
        else if(income <= jointBracketSix){
            taxesOwed = singleMaxTaxLvlFive+
                    (income-jointBracketFive)*bracketPercentSix;
        }
        else {
            taxesOwed = singleMaxTaxLvlSix+
                    (income-jointBracketSix)*bracketPercentSeven;
        }

    }
}