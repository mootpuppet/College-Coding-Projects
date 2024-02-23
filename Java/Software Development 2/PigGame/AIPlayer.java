/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class AIPlayer
 * Name: mottc
 * Created 12/14/2022
 */
package Mottc;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * AIPlayer purpose: Super class for different AI player classes
 *
 * @author mottc
 * @version created on 12/14/2022 at 8:18 AM
 */
public abstract class AIPlayer extends Player {

    private static int numberOfAIPlayers=0;

    /**
     * constructs and AIPlayer and assigns it a name based on
     * the number of AI players playing
     */
    public AIPlayer(){


        super("Bot #"+numberOfAIPlayers++);

    }//end AIPlayer

}//end AIPlayer