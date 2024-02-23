/*
 * Course: CS1011 - 051
 * Fall 2022
 * Lab 7 - Battle Simulator 3000
 * Name: Mottc
 * Created: 10/25/2022
 */
package mootpuppet;

import java.util.Scanner;

/**
 * Course CS1011-051
 * Fall 2022-2023
 * BattleSim Driver for Battle Simulator 3000
 *
 * @author mottc
 * @version created on 10/25/2022 at 12:49 PM
 */
public class BattleSim {
    /**
     * Ten-sided die to be used for checking initiative by all classes
     */
    public static final Die INITIATIVE_DIE = new Die(10);


    public static void main(String[] args) {

        // sentinel value for the game loop
        boolean play;


        // game loop
        do {
            // print the introduction and rules
            intro();

            String victor="none";

            // initialize game
            // Initialize the Warrior and Mugwump classes, set the current victor to "none"
            Warrior warrior = new Warrior();
            Mugwump mugwump = new Mugwump();
            Scanner in = new Scanner(System.in);




            // while neither combatant has lost all of their hit points, report status and battle!
            while (victor.equalsIgnoreCase("none")) {
                report(warrior, mugwump);
                victor = battle(warrior, mugwump, in);

                //uncomment for testing
                //victor="mugwump";
                //victor="warrior";
                //System.out.println(victor);

            }//end while

            // declare the winner

            victory(victor);


            // ask to play again
            play=playAgain(in);

           // System.out.println(play);


        } while(play);  //end do while
        // Thank the user for playing your game

        System.out.println("\nThanks for playing Battle Simulator 3000!");

    }//end main

    /**
     * This method displays the introduction to the game and gives a description of the rules.
     */
    private static void intro() {
        System.out.println("\nWelcome to Battle Simulator 3000! The world's more low tech battle simulator!\n" +
                "You are a Valiant Warrior defending your humble village from an evil Mugwump!" +
                "\nFight bravely, or the citizens of your town will be the Mugwump's dinner!\n " +
                "\nYou have your Trusty Sword, which deals decent damage, but can be tough to hit with sometimes.\n" +
                "You also have your Shield of Light, which is not as strong as your sword, but is easier to deal" +
                "\ndamage with. \n\nLet the epic battle begin!\n");

    }

    /**
     * This method handles the battle logic for the game.
     * @param warrior The Warrior of Light!
     * @param mugwump The Evil Mugwump!
     * @return The name of the victor, or "none", if the battle is still raging on
     */
    private static String battle(Warrior warrior, Mugwump mugwump, Scanner in) {
        // determine who attacks first (Roll! For! Initiative!) and store the result
        int initiative =initiative();

        String victor="none";


        // If the Warrior attacks first
        if(initiative==1){
            System.out.println("\nThe Valiant Warrior attacks first!");

            //the warrior attacks the mugwump
            mugwump.takeDamage(warrior.attack(attackChoice(in)));

           // System.out.println("BatSim_MW_HP: "+mugwump.getHitPoints()); //uncomment for testing

            //mugwump attacks if its still alive
            if(mugwump.getHitPoints()>0) {
                //the mugwump attacks the warrior
                warrior.takeDamage(mugwump.attack());

                if(warrior.getHitPoints()==0){

                    victor="Mugwump";

                }//end if
            }//end if

            //warrior wins when mugwump dies
            else if(mugwump.getHitPoints()==0){
                victor="Warrior";
            }

            else{

                System.out.println("The Mugwump's HP is below 0. That shouldn't happen.");
            }


        } //end if

        //mugwump attacks first
        else if (initiative == 2) {

            System.out.println("\nThe grotesque Mugwump attacks first!");

            //the mugwump attacks the warrior and deals damage
            warrior.takeDamage(mugwump.attack());

            //System.out.println("BatSim_WHP: "+warrior.getHitPoints()); //uncomment for testing

            //warrior attacks if it's still alive
            if(warrior.getHitPoints()>0) {

                //the warrior attacks the mugwump
                mugwump.takeDamage(warrior.attack(attackChoice(in)));

                if(mugwump.getHitPoints()==0){
                    victor="Warrior";
                }// end if

            }//end if

            //mugwump wins if warrior dies
            else if(warrior.getHitPoints()==0){

                victor="Mugwump";

            }//end else if

            //error message for programmer
            else{

                System.out.println("The Warrior's HP is below 0. That shouldn't happen.");
            }//end else

        } //end else if

        //error message
        else{System.out.println("Something went wrong!");}


        //System.out.println(victor); //uncomment for testing

        return victor;

    }//end battle

    /**
     * This method reports the status of the combatants
     * @param warrior The Warrior of Light!
     * @param mugwump The Evil Mugwump!
     */
    private static void report(Warrior warrior, Mugwump mugwump) {

        System.out.println("Warrior HP: " +warrior.getHitPoints());
        System.out.println("Mugwump HP: "+mugwump.getHitPoints());


    } //end report

    /**
     * This method asks the user what attack type they want to use and returns the result
     * @return 1 for sword, 2 for shield
     */
    private static int attackChoice(Scanner in) {

        boolean flag; //sentinel value for error detection

        int choice=3; //player's attack choice

        do {

            try {

                flag=false; //no error

                System.out.print("How would you like to attack?" +
                        "\n1. Your Trusty Sword" +
                        "\n2. Your Shield of Light" +
                        "\nEnter choice: ");

                choice = in.nextInt();

            }//end try

            //catches exception if player doesn't enter an integer
            catch(Exception e){

                flag=true;             //error
                in.nextLine();         //clear scanner

            }//end catch

            //if player doesn't choose 1 or 2
            if(choice!=1 && choice != 2){

                flag=true; //error

            }//end if

           // System.out.println(flag); //uncomment for testing

        }while(flag); //repeat if flag is true

        return choice;

    }//end attackChoice

    /**
     * Determines which combatant attacks first and returns the result. In the case of a tie,
     * re-roll.
     * @return 1 if the warrior goes first, 2 if the mugwump goes first
     */
    private static int initiative() {
        int warriorInitiative;
        int mugwumpInitiative;
        int initiative;

        do{
            INITIATIVE_DIE.roll();
            warriorInitiative= INITIATIVE_DIE.getCurrentValue();

            INITIATIVE_DIE.roll();
            mugwumpInitiative= INITIATIVE_DIE.getCurrentValue();

        } while(warriorInitiative==mugwumpInitiative);

        if(warriorInitiative>mugwumpInitiative){

            initiative=1;
        }
        else {
            initiative=2;
        }

        //uncomment for testing
       /* System.out.println(warriorInitiative);
        System.out.println(mugwumpInitiative);
        System.out.println(initiative); */

        return initiative;
    }

    /**
     * This method declares the victor of the epic battle
     * @param victor the name of the victor of the epic battle
     */
    private static void victory(String victor) {

        //mugwump wins
        if(victor.equalsIgnoreCase("mugwump")){
            System.out.println("\nAlas, Valiant Warrior, you have been slain. And, as you take your final breath " +
                    "\nand your vision starts to fade, you see the Mugwump in the distance,\n" +
                    "feasting upon the flesh of the townsfolk.\n");
        }//end if

        //warrior wins
        else if (victor.equalsIgnoreCase("warrior")) {
            System.out.println("\nRejoice Hero! For you have defeated the mighty and fearsome Mugwump!\n" +
                    "In doing so you have saved the townsfolk. The local bard has already " +
                    "\nstarted composing a ballad recounting the epic battle and the town " +
                    "\nofficials have decided to erect a statue in your honor." +
                    "\n\nCongratulations on your victory!" +
                    "\nMay your blade stay ever-sharp and your sword arm as true as your heart.\n");
        }//end else if

        //error message for programmer
        else{
            System.out.println("There is no victor. That should not be possible");
        }//end else

    } //end victory

    /**
     * This method asks the user if they would like to play again
     * @param in Scanner
     * @return true if yes, false otherwise
     */
    private static boolean playAgain(Scanner in) {

        boolean play=false;

        boolean flag;

        do {

            flag=false;

            System.out.println("Do you want to play again (yes/no)? ");
            String playAgain = in.next();                                          //get input

            if (playAgain.equalsIgnoreCase("yes") ||
                    playAgain.equalsIgnoreCase("y")) {
                play = true;

            }//end if

            else if (!playAgain.equalsIgnoreCase("No") &&
                    !playAgain.equalsIgnoreCase("N")) {

                flag=true;


            }//end else if

            //System.out.println(play);

        }while(flag);

        return play;
    }//end playAgain


}//end BattleSim