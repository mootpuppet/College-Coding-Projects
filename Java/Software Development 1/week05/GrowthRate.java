/*
 * Course: CS1011-051
 * Fall 2022-2023
 * File header contains class GrowthRate
 * Name: mottc
 * Created 10/5/2022
 */
package Mott.week05;

import java.util.Scanner;

/**
 * Course CS1011-051
 * Fall 2022-2023
 * GrowthRate purpose: this a game where the user is given two options.
 * a randomly generate amount to be added to their account on a weekly
 * basis or doubling the amount in their account starting with $0.01
 *
 * @author mottc
 * @version created on 10/5/2022 at 1:46 PM
 */
public class GrowthRate {

    //GLOBAL VARIABLES//
    private static Scanner userInput = new Scanner(System.in);    //Scanner user input variable
    private static final int maxDollarAmount = 5000;              //maximum dollar amount for random weekly earnings
    private static final int maxWeek = 40;                        //maximum number of weeks
    private static final double startingValue = 0.01;             //exponential growth starting value
    private static int playerChoice;                             //option player chose
    private static int linearGrowthAccount;                       //linear growth account
    private static double exponentialGrowthAccount;               //exponential growth account
    private static double differenceInAccounts;                   //difference between account balances
    private static boolean playerWon;                             //keeps track of whether player won or not
    private static int randomizedWeeklyEarnings;                  //randomized weekly earnings
    private static int randomWeeksTilSunExplodes;                 //randomized number of weeks
    private static boolean playAgain;                             //true if player wants to play again


    /**
     * This main method calls worker methods to display the story, display
     * the user's options, display the win conditions, get the players choice,
     * generate and display weekly growth rates, determine if the player won,
     * and display a message telling the player whether they won or lost
     * @param args
     */
    public static void main(String[] args) {


            displayStory();

        do {
            randomWeeksTilSunExplodes = generateRandomNumber(maxWeek);            //generate random number of weeks
            randomizedWeeklyEarnings = generateRandomNumber(maxDollarAmount);     //generate random weekly earnings
            displayOptions(randomizedWeeklyEarnings);
            displayWinConditions();
            getPlayerChoice();
            accountGrowth(randomWeeksTilSunExplodes, randomizedWeeklyEarnings);
            didPlayerWin();
            displayWinLose(randomWeeksTilSunExplodes);
            playGameAgain();

        }while(playAgain);

    } //end main

    /**
     * This method takes an upper limit and returns a random number
     * between 0 and that upper limit as an integer
     * @param upperLimit
     * @return random number between 0 & upperLimit
     */
    public static int generateRandomNumber(int upperLimit){

        return (int)(upperLimit*Math.random());   //random number between 0 and upperLimit

    } //end generateRandomNumber

    /**
     * This method displays the story for the game
     */
    public static void displayStory(){

        System.out.println("Sometime within the next " + maxWeek +" weeks, the sun is going to explode and" +
                " all life on our planet will cease to exist. \nScientists have found a distant solar system " +
                "that has a planet (Planet Java) with similar conditions to Earth. \nHumans have built a giant " +
                "space shuttle named \"A.R.K. 2.0\" which can comfortably hold 1/10th of the human population" +
                " \nand whose mission is to safely deliver as many people as possible to Planet Java. Human " +
                "scientists are not exactly \nsure when the sun is going to explode but they have sensors in place " +
                "that will tell us exactly 24 hrs before it does, \nwhich is just enough time for the A.R.K. to " +
                "launch and safely leave our solar system. The problem is corporate capitalism still \nreigns " +
                "supreme and, since space is limited, tickets are crazy expensive. You need to make enough money " +
                "to buy a ticket \nand have plenty left over to start your new life on Planet Java.");

    } //end displayStory

    /**
     * this method displays the player's options
     * @param dollars
     */
    public static void displayOptions(int dollars){

        System.out.println("\nHere are your options: \n");
        System.out.println("Option 1: \nGet a job cleaning toilets at NASA and make $"
                + dollars +" a week.\n");
        System.out.println("Option 2: \nApply for a government grant which will add $"+
                startingValue +" to your account and double it every week until the A.R.K." +
                " launches\n");

    } //end displayOptions

    /**
     * this method tells the player how they win the game
     */
    public static void displayWinConditions(){
        System.out.println("How you win:\nYou win if, when the world ends, " +
                "the option you chose made you more money\n");
    } //end displayWinConditions

    /**
     * this method utilizes the scanner to get the player's input
     * when choosing option 1 or 2
     */
    public static void getPlayerChoice(){

    boolean flagError;                         //error flag


        do { // will loop if user inputs anything other than 1 or 2 as integers.

            try {

                //ask user to choose option 1 or 2
                System.out.print("Please choose option 1 or option 2 (enter 1 or 2): ");

                playerChoice = userInput.nextInt();  //get input

                flagError = false;                    //flag false, no error

            } //end try

            //catches if user inputs anything other than an integer
            catch(Exception e){

                System.out.println("\nPlease enter an integer\n"); //error message for user

                userInput.nextLine();                              //clear Scanner

                flagError=true;                                    //flag true, error

            } //end catch

            //flag is true if player inputs an integer that is not 1 or 2
            if (playerChoice != 1 && playerChoice != 2){

                flagError = true; //flag true, error

            } //end if

        }while(flagError);     //loop while flagError is true


    } //end getPlayerChoice

    /**
     * This method calculates and displays the total weekly account balances of
     * the exponential growth account and linear growth account every week from
     * 1 - weeksLeft
     * @param weeksLeft
     * @param dollars
     */
    public static void accountGrowth(int weeksLeft, int dollars){


        linearGrowthAccount=dollars;                       //linear growth account starting balance
        exponentialGrowthAccount=startingValue;            //exponential growth account starting balance


        if (weeksLeft>0) {


            for (int currentWeek = 1; currentWeek < weeksLeft; currentWeek++) {

                System.out.format("Week %2d Linear: $%d,"
                        ,currentWeek,linearGrowthAccount);              //display linear account balance

                System.out.format(" Week %2d Exponential: $%.2f\n",      //display exponential account balance
                        currentWeek, exponentialGrowthAccount);

                //update account balances
                linearGrowthAccount += dollars;                         //add dollar amount to linear account balance
                exponentialGrowthAccount *= 2;                          //double exponential account balance

            } //end for loop

        } //end if

    } //end accountGrowth

    /**
     * this method determines whether the player won or lost
     */
    public static void didPlayerWin(){

        differenceInAccounts = Math.abs((linearGrowthAccount-            //difference between accounts
                randomizedWeeklyEarnings)-exponentialGrowthAccount/2);


        //player wins if they chose option 1 and linear account balance is greater than exponential account balance
        if (playerChoice == 1 && linearGrowthAccount-
                randomizedWeeklyEarnings >=exponentialGrowthAccount/2){

            playerWon = true;         //player won

        } //if ends

        //player wins if they chose option 2 and exponential account balance is greater than linear account balance
        else if (playerChoice ==2 && exponentialGrowthAccount/2>=linearGrowthAccount- randomizedWeeklyEarnings){

            playerWon=true;          //player won

        } //else if


        else{

            playerWon=false;        //player lost

        } //end else


    }  //end didPlayerWin


    /**
     * This method lets the player know if they won or lost. It tells the user
     * what the difference in account balances was, and when the sun exploded.
     * @param weekOfExplosion
     */
    public static void displayWinLose(int weekOfExplosion){

        //display message if input parameter is less than 1
        if(weekOfExplosion<1){

            System.out.println("Sorry the scientists messed up their calculations and " +
                    "the sun exploded before the end of the first day. Everyone dies ");
        } //end if

        //display message if player won
        else if(playerWon){

            System.out.format("Congratulations! You won! The sun exploded at the beginning of week %2d " +
                    "but you made \nenough money for a ticket and made out with an extra $%.2f\n",
                    weekOfExplosion, differenceInAccounts);

        } //end else if

        //display message if player lost
        else{

            System.out.format("You died! The sun exploded at a the beginning of week %2d, " +
                    "you didn't have \nenough money for a ticket and you missed out on $%.2f\n"
                    ,weekOfExplosion, differenceInAccounts);

        } //end else

    } //end displayWinLose

    /**
     * this method asks the user if they would like to play again
     * and assigns a value of true or false to the variable
     * playAgain
     */
    public static void playGameAgain(){

        String again;     //declare String variable again

        boolean inputError;  //input error variable

        do {

            try {
                System.out.print("Do you want to play again? (y/n): ");  //ask user if they want to play again
                again = userInput.next();                                //get user input
                inputError = false;                                      // no error

            } //end try


            catch (Exception e) {        //cat error if input is not String

                System.out.println("Please enter y or n");   //error message
                inputError = true;                           // error
                again ="w";                                  //allows following if statement to execute
                userInput.nextLine();                        //clear buffer

            } //end catch

            //execute if again does not equal y and again does not equal n
            if (!again.equals("y") && !again.equals("n")){

                inputError = true;       //error
                userInput.nextLine();    //clear buffer

            } //end if

        }while (inputError);

        if(again.equals("y")){

            playAgain = true;       //player wants to play again

        } //end if

        else{

            playAgain = false;      //player does not want to play again

            System.out.println("\nThanks for playing! Have a great day!");  //friendly fairwell message
        } //end else


    } //end playGameAgain

} //end GrowthRate