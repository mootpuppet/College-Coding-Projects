/*
 * Course: CS1021 - FIX
 * Winter 2022-23
 * Lab 3 - Pig Game
 * Name: FIX
 * Created: 11/04/2022
 * Modified: 11/07/2022
 */
package Mottc;

import java.util.Scanner;

/**
 * Driver for playing a game of Pig, with a mix of human
 * players and bots.
 */
public class Pig {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        PigGame theGame = new PigGame();
        addPlayers(theGame, in);

        Player winner = theGame.playGame();

        System.out.println(winner.getName() + " is the winner!");
    }

    /**
     * Add all the players for the game.
     * @param theGame The game of Pig!
     * @param in Input source for player info.
     */
    private static void addPlayers(PigGame theGame, Scanner in){
        System.out.print("How many players? ");
        int numberOfPlayers = Integer.parseInt(in.nextLine());
        for(int i = 0; i < numberOfPlayers; ++i){
            addPlayer(theGame, in);
        }
    }

    /**
     * Add one player to the game
     *
     * Prompts for info depending on the player type.
     *
     * @param theGame The game of Pig!
     * @param in Input source for player info.
     */
    private static void addPlayer(PigGame theGame, Scanner in) {
        String input;
        do {
            System.out.print("1) Human\n2) Dumb AI\n3) Threshold AI\n-> ");
            input = in.nextLine();
        } while(!(input.equals("1") || input.equals("2") || input.equals("3")));

        Player player = null;
        if(input.equals("1")){
            System.out.print("Name? ");
            String name = in.nextLine();
            player = new HumanPlayer(name, in);
        } else if(input.equals("2")){
            player = new DumbAIPlayer();
        } else {
            System.out.print("What threshold? ");
            int threshold = Integer.parseInt(in.nextLine());
            player = new ThresholdAIPlayer(threshold);
        }
        theGame.addPlayer(player);
    }

}