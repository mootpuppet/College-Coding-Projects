/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class ThresholdAIPlayer
 * Name: mottc
 * Created 12/14/2022
 */
package Mottc;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * ThresholdAIPlayer purpose: defines the behavior of a ThresholdAIPlayer.
 * the thresholdAIPlayer is instantiated with a turnScore threshold in which
 * will dictated whether they hold. If the turnScore is greater than or equal
 * to their threshold, then the hold, otherwise they keep rolling
 *
 * @author mottc
 * @version created on 12/14/2022 at 8:23 AM
 */
public class ThresholdAIPlayer extends AIPlayer {

   private final int threshold;

    /**
     * Constructs a ThresholdAIPlayer and sets their threshold
     * @param threshold - turn score at which the ThresholdAIPlayer
     *                    will hold
     */
   public ThresholdAIPlayer(int threshold){

       this.threshold=threshold;
   }//end ThresholdAIPlayer

    /**
     * returns true if turnScore is greater than or equal to
     * threshold
     * @param turnScore - player turn score
     * @return - true or false
     */
   public boolean chooseToHold(int turnScore){

       return turnScore >= threshold;
   }//end chooseToHold

}//end ThreshHoldAIPlayer