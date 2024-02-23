/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class Player
 * Name: mottc
 * Created 12/14/2022
 */
package Mottc;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * Player purpose: Super class for different player classes,
 * allows different types of players to be grouped together
 * and referenced using the Player class
 *
 * @author mottc
 * @version created on 12/14/2022 at 8:16 AM
 */
public abstract class Player {

    private final String name;
    private int score;

    /**
     * Constructs a Player and assigns it a name
     * @param name - the name of the player
     */
    public Player(String name){

        this.name=name;

    }//end Player


    /**
     * returns the name of the player
     * @return - name of player
     */
    public String getName(){

        return name;
    }//end getName

    /**
     * returns player score
     * @return - player score
     */
    public int getScore(){

        return score;
    }//end getScore

    /**
     * adds the players turn score to their total score
     * @param turnScore - turn score
     */
    public void addToScore(int turnScore){
        score+=turnScore;
    }//end addToScore

    /**
     * returns false
     * @param turnScore - player turn score
     * @return - false
     */
    public boolean chooseToHold(int turnScore){

        return false;
    }//end chooseToHold

    /**
     * returns null
     * @return - null
     */
    @Override
    public String toString(){

        return null;
    }//end toString

}//end Player