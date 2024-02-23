/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class DumbAI
 * Name: mottc
 * Created 12/14/2022
 */
package Mottc;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * DumbAI purpose: defines the behavior of an AIPlayer that uses a D2
 * to determine if they will hold or keep rolling
 *
 * @author mottc
 * @version created on 12/14/2022 at 8:19 AM
 */
public class DumbAIPlayer extends AIPlayer {
    private final Die coin;

    /**
     * DumbAIPlayer constructor. Instantiates a 2 sided die named coin
     */
    public DumbAIPlayer(){
        coin = new Die(2);
    }//end DumbAIPLayer

    /**
     * rolls coin to determine if DumbAIPlayer will hold. Returns true if 1
     * is rolled, returns false if 2 is rolled
     * @param turnScore - player turn score
     * @return - true or false
     */
    public boolean chooseToHold(int turnScore){

        coin.roll();

        return coin.getSideUp()==1;
    }//end chooseToHold

}//end DumbAIPlayer