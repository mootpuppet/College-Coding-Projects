/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class PigGame
 * Name: mottc
 * Created 12/14/2022
 */
package Mottc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * PigGame purpose: A game to be instantiated and played, using a driver.
 * Each player rolls a die and after each roll, they decide to hold or not,
 * adding those points to their total points or to keep rolling to hopefully
 * score more points.If a player rolls a one, they bust and lose all accumulated
 * points for that turn. Winner is the first player to hit 100+ points.
 *
 * @author mottc
 * @version created on 12/14/2022 at 8:15 AM
 */
public class PigGame {

    private final Die die = new Die();
    private final List<Player> players = new ArrayList<>();
    private final List<Player> playOrder = new ArrayList<>(); //a list of players to help determine the turn order

    private Scanner in = new Scanner(System.in);

    /**
     * PigGame constructor. Instantiates a PigGame object
     */
    public PigGame(){


    }//end PigGame

    /**
     * adds a Player to an ArrayList of Players
     * @param player - player to be added to the game
     */
    public void addPlayer(Player player){

        players.add(player);

    }//end addPlayer

    /**
     * determines flow of game, calls playerInitiative to determine turn order,
     * calls takeTurn for each Player, and determines winner of the game
     * @return winner
     */
    public Player playGame(){

        playerInitiative();

        int winnerIndex=-1;
        int highScore=0;


        do {
            for (int i=0;i<players.size();i++) {

                if(highScore<100) {
                    System.out.println(this); //print score
                    takeTurn(players.get(i));

                    //sets new high score
                    if (players.get(i).getScore() > highScore) {
                        highScore = players.get(i).getScore();
                    }//end if

                }//end if

                //determine winner
                if(players.get(i).getScore() >=100){
                    winnerIndex= i;
                }//end if

            }//end for

        }while(highScore<100);

        System.out.println(this); //print score

        return players.get(winnerIndex);
    }//end play game

    /**
     * Creates and formats a String that shows the name and current score of
     * each player
     * @return displayScore
     */
    @Override
    public String toString(){

        String displayScore="";


        for(int i=0 ; i<players.size();i++){
            String playerName=players.get(i).getName();

            //format whitespace in before current player's name
            for(int j=0; j<10-playerName.length();j++){           //10 character spaces for whitespace and name
                displayScore+="\s";
            }//end for

            displayScore+= playerName+" : ";

            String playerScore=""+players.get(i).getScore();

            //format whitespace before current player's score
            for(int j=0;j<3-playerScore.length();j++){         //3 character spaces for score

                displayScore+="\s";
            }//end for

            displayScore += playerScore;

            //newline after every player except the last player
            if(players.get(i)!=players.get(players.size()-1)) {

                displayScore +="\n";
            }//end if

        }//end for

        return displayScore;

    }//end toString

    /**
     * determines flow of player turn. The player rolls a die, the value is displayed,
     * if they roll a 1, they bust, their turn score is set to 0 and their turn is over,
     * otherwise it is determined whether they hold. If the player does not hold,
     * the die is added to their turn score, and they keep rolling. If they hold, their
     * turn score is added to their total score.
     *
     * @param player - current player
     */
    private void takeTurn(Player player){


        int turnScore=0;

           do {
               die.roll();
               System.out.println(player.getName()+" rolled a "+die.getSideUp());

               if (die.getSideUp() == 1) {
                   System.out.println(player.getName()+" busts!");
                   turnScore=0;
               }//end if

               else{
                   turnScore+=(die.getSideUp());
               }//end else

           }while(die.getSideUp()!=1 && !player.chooseToHold(turnScore));


           if(die.getSideUp()!=1){
               System.out.println(player.getName()+" holds");

           }//end if

           player.addToScore(turnScore);

    }//end takeTurn

    /**
     * Determines the order of play. A starting player is randomly selected,
     * then each Player, starting with the starting Player, is reogranized
     * into a new arraylist called playOrder. All players are then removed
     * from the players list, then playOrder is copied into players list.
     */
    private void playerInitiative() {
        Die initiativeDie = new Die(players.size());
        initiativeDie.roll();
        int firstPlayerIndex = initiativeDie.getSideUp() - 1;


        int j;
        int k = 0;

        for (int i = 0; i < players.size(); i++) {

            j = firstPlayerIndex + i;

            if (!(j >= players.size())) {
                playOrder.add(players.get(j));
            }//end if

            else {
                playOrder.add(players.get(k));
                k++;

            }//end else

        }//end for

        players.removeAll(players);
        players.addAll(playOrder);

    }//end playerInitiative


}//end PigGame