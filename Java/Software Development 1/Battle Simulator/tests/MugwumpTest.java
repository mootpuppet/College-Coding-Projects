/*
This class should be used to help in your development process
by ensuring that the Mugwump class is working as expected before
integrating it into the overall program.

These tests assume that you set the seed for the Random object
in the Die class to 939.

TODO: Make sure that you remove the seed from the Random object
TODO: before submitting your code to be graded.
 */
package tests;

import mootpuppet.Mugwump;

public class MugwumpTest {
    public static void main(String[] args) {
        // Create a Mugwump object
         Mugwump mugwumpTest = new Mugwump(24);

        // Use the debugger to ensure that when setInitialHitPoints() is called, that
        //   it assigns 24 to both hitPoints and
        System.out.println("Mugwump hit points: " +mugwumpTest.getHitPoints());


        // Ensure getHitPoints() returns 24
        System.out.println(mugwumpTest.getHitPoints());


        // Call takeDamage(8)
        mugwumpTest.takeDamage(8);

        // Ensure getHitPoints() returns 16
        System.out.println(mugwumpTest.getHitPoints());

        // Call takeDamage(-1)
        mugwumpTest.takeDamage(-1);
        // Ensure getHitPoints() returns 17
        System.out.println(mugwumpTest.getHitPoints());


        for (int i =1 ; i<20;i++){
        mugwumpTest.takeDamage(mugwumpTest.attack());

        System.out.println(mugwumpTest.getHitPoints());
        }

        // Ensure getHitPoints() returns 17

        // Call attack()
        // Use the debugger to ensure that ai() gets called and does the following:
        //   1. Produces 95 by rolling d100
        //   2. returns 3
        // Ensure the call to attack() returns -5 (5 healing points)

        // Ensure getHitPoints() returns 22

        // Call attack()
        // Use the debugger to ensure that ai() gets called and does the following:
        //   1. Produces 23 by rolling d100
        //   2. returns 1
        // Ensure the call to attack() returns 2 (2 hit points of damage)

        // Ensure getHitPoints() returns 20

        // Roll d20 six times

        // Call attack and ensure it returns 7 (7 hit points damage)
        // Call attack (again) and ensure it returns 7 (7 hit points damage)

        // Ensure getHitPoints() returns 4

        // Call attack()
        // Use the debugger to ensure that ai() gets called and does the following:
        //   1. Produces 82 by rolling d100
        //   2. returns 2
        // Ensure the call to attack() returns 10 (10 hit points damage)

        // Ensure getHitPoints() returns -6


    }
}
