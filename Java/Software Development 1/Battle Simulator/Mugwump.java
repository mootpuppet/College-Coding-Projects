/*
 * Course: CS1011-051
 * Fall 2022-2023
 * File header contains class Mugwump
 * Name: mottc
 * Created 10/20/2022
 */
package mootpuppet;

/**
 * Course CS1011-051
 * Fall 2022-2023
 * Mugwump purpose: a driven class that constructs a Mugwump object
 * to fight a warrior object in a battle simulator
 *
 * @author mottc
 * @version created on 10/20/2022 at 12:49 PM
 */

public class Mugwump {

    private int hitPoints;                                   //health
    private int maxHitPoints;                                //max health
    private final int numHitDice = 6;                        //number of hit dice
    private Die d100 = new Die(100);                //100 sided die
    private Die d20 = new Die(20);                  //20 sided die
    private Die d10 = new Die(10);                  //10 sided die
    private  Die d6 = new Die(6);                   //6 sided die


    /**
     * Mugwump constructor. Constructs a mugwump object and
     * sets its starting hit point value and its max hit point
     * value
     */
    public Mugwump() {

        int initialHitPoints = setInitialHitPoints(); //initial hit points

        hitPoints = initialHitPoints;    //starting hit points
        maxHitPoints = initialHitPoints; //maximum hit points

    }//end Mugwump constructor

    /**
     * this is a tester constructor meant to eliminate the randomness of
     * the setInitialHitPoints() method to make tracing/testing easier
     * @param initialHitPoints defined by the MugwumpTest class
     */
    public Mugwump(int initialHitPoints){

        hitPoints = initialHitPoints;
        maxHitPoints = initialHitPoints;

        System.out.println("Max hit points: " +maxHitPoints);

    }//end Mugwump test constructor


    /**
     * getter of hit points
     * @return current hit point value
     */
    public int getHitPoints() {return hitPoints;}//end getHitPoints

    /**
     * applies damage taken to Mugwump's current hit point value
     * @param damage - damage taken
     */
    public void takeDamage(int damage){

        hitPoints=hitPoints-damage;    //take damage

        //hit points celing
        if(hitPoints>maxHitPoints){

            hitPoints=maxHitPoints;

        }//end if

        //hit points floor
        else if(hitPoints<0){
            hitPoints=0;
        }//end else if

        //System.out.println("MW_HP: " +hitPoints);

    }//end takeDamage

    /**
     * This method handles the attack logic
     * @return the amount of damage an attack has caused, 0 if the attack misses or
     *         a negative amount of damage if the Mugwump heals itself
     */
    public int attack() {

        int healing=0;                           //amount healed
        int damage=0;                            //damage dealt
        int attackType = ai();                   //get attack from AI

        String hitOrMiss = " misses";            //declaration of whether attack hits or misses

        d20.roll();                              //roll attack die

        int attackRoll = d20.getCurrentValue();  //attack die value

        //Attack type 1: Razor Sharp Claws
        if(attackType ==1){

            //attack hits if attack die rolls 13 or higher
            if(attackRoll>=13){

                hitOrMiss=" hits";   //attack hits

                //roll for damage (2 d6)
                for(int i = 1; i<=2;i++){

                    d6.roll();
                    damage=damage+ d6.getCurrentValue(); //calculate damage

                }//end for
            }//end if

        }//end if

        //Attack Type 2: Fangs of Death
        else if(attackType ==2){

            //attack hits if 16 or higher is rolled
            if(attackRoll>=16){

                hitOrMiss = " hits";  //attack hits

                //roll 3 damage dice
                for(int i = 1; i<=3;i++){

                    d6.roll();

                    damage=damage+ d6.getCurrentValue(); //calculate damage

                }//end for

            }//end if

        }//end else if

        //Attack Type 3: Lick Wounds
        else if(attackType ==3){

            hitOrMiss = null;

            d6.roll();                             //roll for healing
            healing =damage-d6.getCurrentValue();  //calculate amount healed

            takeDamage(healing);                   //Mugwump Heals


        }//end else if

        //error message for programmer
        else{

            System.out.println("\nIf you're getting this message, you done messed up\n");

        }//end else

        //print message if Mugwump uses Lick Wounds

        if(hitOrMiss == null){
            System.out.print(" and heals "+(healing*-1)+" hit points.\n");
        }//end if

        //display message if Mugwump uses other attacks
        else {
            System.out.print(" and"+hitOrMiss +", dealing " +damage +" points of damage.\n");
        }//end else


        return damage; //damage dealt
    }//end attack

    /**
     * rolls 6 d10 to set initial hit points
     * @return starting/max hit point value
     */
    private int setInitialHitPoints(){

        int initialHitPoints=0;

        for(int i=1 ; i<=numHitDice ; i++){

            d10.roll();                             //roll hit die

            initialHitPoints=initialHitPoints +     //calculate initial hit points
                    d10.getCurrentValue();

        } //end for


        return initialHitPoints;  //starting hit points

    }//end setInitialHitPoints

    /**
     * This method determines what action the Mugwump performs
     * @return 1 for a Claw attack, 2 for a Bite, and 3 if the Mugwump licks its wounds
     */
    private int ai() {

        int attackType;        //attack type
        d100.roll();           //roll d100

        int d100Value =d100.getCurrentValue(); //d100 value

        String attackName;  //name of attack

        //Attack 1: Razor Sharp Claws
        if(d100Value <= 60){

            attackType = 1;
            attackName= "Razor Sharp Claws";

        }//end if

        //Attack 2: Fangs of Death
        else if (d100Value<=85) {

            attackType=2;
            attackName = "Fangs of Death";

        }//end else if

        //Attack 3: Lick Wounds
        else {

            attackType =3;
            attackName = "Lick Wounds";

        }//end else

        //display attack name
        System.out.print("The Mugwump uses "+attackName);

        return attackType; //attack being used

    }//end ai

}//end Mugwump Class
