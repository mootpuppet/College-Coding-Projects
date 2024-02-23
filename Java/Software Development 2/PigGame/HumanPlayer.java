/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class HumanPlayer
 * Name: mottc
 * Created 12/14/2022
 */
package Mottc;

import java.util.Scanner;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * HumanPlayer purpose: Child of Player Class, defines the behavior
 * and choices  of a Human Player
 *
 * @author mottc
 * @version created on 12/14/2022 at 8:19 AM
 */
public class HumanPlayer extends Player{

    private final Scanner in;

    /**
     * Constructs a HumanPlayer with the given name and Scanner
     * @param name - name of HumanPlayer
     * @param in - input Scanner
     */
    public HumanPlayer(String name, Scanner in) {

        super(name);

        this.in = in;

    }//end HumanPlayer

    /**
     * asks the player whether they would like to hold true or false
     * @param turnScore - player turn score
     * @return -true or false (true: player would like to hold)
     *                        (false: player does not wish to hold)
     */
    public boolean chooseToHold(int turnScore){

        String playerChoice;
        String yesOrNo;


        do {

            System.out.println("Hold? [y/n]");

            playerChoice = in.next();

            yesOrNo=""+playerChoice.charAt(0);

        }while(!yesOrNo.equalsIgnoreCase("y") && !yesOrNo.equalsIgnoreCase("n"));

        return yesOrNo.equalsIgnoreCase("y");
    }//end chooseToHold

}//end HumanPlayer