/*
 * Course: CS1011-051
 * Fall 2022-2023
 * File header contains class Warrior
 * Name: mottc
 * Created 10/20/2022
 */

package mootpuppet;

/**
 * Course CS1011-051
 * Fall 2022-2023
 * Warrior purpose: Driven method that constructs a warrior object
 * that has hit points, can perform attacks, and deals damage
 *
 * @author mottc
 * @version created on 10/20/2022 at 12:49 PM
 */
public class Warrior {

    private int hitPoints;                        //warrior health

    private  Die d20 = new Die(20);
    private  Die d10 = new Die(10);
    private  Die d8 = new Die(8);
    private  Die d4 = new Die(4);
    private final int hitDice =4;             //number of dice used to calculate starting Hit Points


    /**
     * when called, this constructor creates a Warrior object with
     * randomly generated starting hit points
     */
    public Warrior(){

        hitPoints=setInitialHitPoints();

    } //end Warrior constructor

    /**
     * when called, this method returns current hit point value
     * @return current hit points
     */
    public int getHitPoints(){


        return hitPoints;
    } //end getHitPoints

    /**
     * this method updates the warriors current hit point
     * value by subtraction the input damage
     * it also does not allow the hit points to drop below 0
     * @param damage - damage taken
     */
    public void takeDamage(int damage){

        hitPoints=hitPoints-damage;       //take damage

        if(hitPoints<0){hitPoints=0;}     //hitPoints floor

        //System.out.println("W_HP: "+hitPoints);

    }//end takeDamage

    /**
     * performs an attack action based on input and returns
     * the amount of damage dealt
     * @param type - attack type
     * @return damage dealt
     */
    public int attack(int type){

        int damage =0;                                 //damage dealt

        String hitOrMiss = "miss";                     //determine whether attack hits or misses
        String attackAction = "do nothing";            //description action being performed

        d20.roll();                                    //roll attack die
        int attackRoll = d20.getCurrentValue();        //value face up on attack die

        //attack type 1: Trusty Sword
        if(type==1){

            attackAction = "swing your trusty sword";

            //if attack roll hits
            if(attackRoll>=12){

                hitOrMiss="hit";

                //roll damage dice (2 d8)
                for(int i = 1; i<=2; i++){

                    d8.roll();
                    damage=damage+ d8.getCurrentValue();    //calculate damage

                }//end for

            }//end if

        }//end if

        //Attack type 2: Shield of Light
        else if(type==2){

            attackAction = "attack with your shield of light";

            //if attack hits
            if(attackRoll>=6){

                hitOrMiss = "hit";


                d4.roll();                           //roll damage die
                damage=d4.getCurrentValue();         //get damage

            }//end if

        }//end else if

        //describe attack
        System.out.println("You "+attackAction +" and "+hitOrMiss
                +" the Mugwump, dealing "+damage+" points of damage");

        return damage; //damage dealt

    }//end attack

    /**
     * this method sets the starting hit point value
     * by rolling 4 hit dice and adding their values
     * together
     * @return - starting hit point value
     */
    private int setInitialHitPoints(){

        int initialHitPoints=0;

        //roll hit dice
        for(int i=1 ; i<=hitDice ; i++){

            d10.roll();

            initialHitPoints=initialHitPoints +                 //calculate starting hit points
                    d10.getCurrentValue();

        } //end for

        return initialHitPoints;
    }//end setInitialHitPoints

}//end Warrior Class
