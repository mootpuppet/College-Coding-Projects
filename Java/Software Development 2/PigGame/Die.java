/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class Die
 * Name: mottc
 * Created 12/14/2022
 */
package Mottc;

import java.util.Random;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * Die purpose: to simulate rolling a die
 *
 * @author mottc
 * @version created on 12/14/2022 at 8:15 AM
 */
public class Die {
    private static final int MIN_SIDES=2;
    private static final int MAX_SIDES=100;
    private static final int DEFAULT_SIDES =6;
    private static final Random generator = new Random();
    private final int numSides;
    private int currentValue;

    /**
     * Default constructor. Instantiates Die object with default number of sides
     */
    public Die(){

        numSides= DEFAULT_SIDES;
        roll();

    }//end Die

    /**
     * Instantiates Die object with the number of sides equal to the input parameter.
     * If numSides is greater than MAX_SIDES, then the number sides is set to MAX_SIDES.
     * If numSides is less than MIN_SIDES, then the number of sides is set ot MIN_SIDES
     * @param numSides - number of sides
     */
    public Die(int numSides){


        if(numSides<MIN_SIDES) {
            this.numSides = MIN_SIDES;
        }//end if

        else if(numSides>MAX_SIDES){
            this.numSides=MAX_SIDES;
        }//end else if

        else {
            this.numSides=numSides;
        }//end else

        roll();

    }//end Die

    /**
     * returns the side facing up
     * @return - currentValue
     */
    public int getSideUp(){

        return currentValue;

    }//end getSideUp

    /**
     * simulates rolling a die by randomly generating a number between 1
     * and the number of sides. Sets currentValue.
     */
    public void roll(){

        currentValue=generator.nextInt(numSides)+1;
    }//end roll

}//end Die