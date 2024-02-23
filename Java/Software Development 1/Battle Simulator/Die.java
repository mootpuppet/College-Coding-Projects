/*
 * Course: CS1011-051
 * Fall 2022-2023
 * File header contains class Die
 * Name: mottc
 * Created 10/20/2022
 */

package mootpuppet;

import java.util.Random;

/**
 * Course CS1011-051
 * Fall 2022-2023
 * Die purpose: a driven class that can create a Die object that will
 * be rolled gives a random number
 *
 * @author mottc
 * @version created on 10/20/2022 at 12:49 PM
 */
public class Die {

    private int numSides;         //number of sides
    private int currentValue;     //number facing up on die

    private Random generator = new Random(); //random number generator

    /**
     * Die constructor. Constructs die object with number of
     * side equal to the input
     * @param numSides - number of sides on the die
     */
    public Die (int numSides){

        this.numSides=numSides;    //number of sides
        roll();                   //roll die to set starting value

    }//end Die

    /**
     * default Die if number of sides are not specified
     */
    public Die(){
        this(6);         //set number of sides to 6
        roll();                   //roll die

    }//end Die

    /**
     * getter that gets the number facing up a Die object
     * @return current number facing up
     */
    public int getCurrentValue(){


        return currentValue;

    } //end get currentValue

    /**
     * roll die and generate random number between 1 and
     * number of sides
     */
    public void roll(){

        currentValue= generator.nextInt(numSides)+1;
    } //end roll


    /**
     * this method tests the other methods in this class
     * by creating two test dice and rolling them 100 times.
     * it also tests whether there is and even distribution
     * of odd and even numbers.
     * @param args
     */
    public static void main(String[] args) {

        Die testDie = new Die(100); //100 sided die
        Die testDie2 = new Die();            //default die


        int sumEven1=0; //testDie1 even numbers rolled
        int sumOdd1=0;  //testDie1 odd numbers rolled

        int sumEven2=0; //testDie2 even numbers rolled
        int sumOdd2=0; //testDie2 odd numbers rolled

        //roll test dice 100 times
        for(int k=0;k<100;k++){
            testDie.roll();
            testDie2.roll();

            //print test dice values
            System.out.println("testDie: " +testDie.getCurrentValue()
                    +"  testDie2: " +testDie2.getCurrentValue());


            if (testDie.getCurrentValue()%2 ==0){
                sumEven1++;
            }
            else {
                sumOdd1++;
            }

            if (testDie2.getCurrentValue()%2 ==0){
                sumEven2++;
            }
            else {
                sumOdd2++;
            }
        }
        System.out.println(sumEven1+"\t"+sumOdd1);
        System.out.println(sumEven2+"\t"+sumOdd2);

    }//end main

}//end Die class