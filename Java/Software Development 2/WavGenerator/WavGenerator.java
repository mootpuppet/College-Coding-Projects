/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class WavGenerator
 * Name: mottc
 * Created 11/30/2022
 */

package mottc;


import us.msoe.csse.taylor.audio.WavFile;
import us.msoe.csse.taylor.audio.WavPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * WavGenerator purpose: to allow the user to either create a reversed
 * copy of an audio file or to generate an audio file that plays a user
 * defined frequency.
 *
 * @author mottc
 * @version created on 11/30/2022 at 9:01 AM
 */
public class WavGenerator {

    private static int chosenOperation;      //operation the user chooses to perform
    public static Scanner userInput = new Scanner(System.in);

    private static final ArrayList<String> sounds = new ArrayList<>(Arrays.asList("baa",
            "bark","cluck","cymbal","gobble","moo","neigh","oink",
            "ominous"));

    /**
     * runs the logic of the program at the highest level
     * @param args
     */
    public static void main(String[] args) {

        welcome();
        pause();
        optionLoop();
        pause();
        goodBye();

    }//end main

    /**
     * this method asks the user which operation they would like to perform
     * the prompt is repeated until a valid entry is entered this method
     * calls the executeOperation method to perform the user specified action
     * this repeats until the user enters '0' to exit the program
     */
    private static void optionLoop(){

        do{
            do {

                boolean flag;

                do {
                    System.out.println("Please choose one of the following options by" +
                            "entering the corresponding number:\n0: Exit Program"+
                            "\n1: Reverse audio\n2: Add a frequency");

                    flag=false;

                    try {
                        chosenOperation = Integer.parseInt(userInput.nextLine());
                    }//end try
                    catch(Exception e){
                        flag=true;
                    }//end catch

                }while(flag);


                if (chosenOperation != 0 && chosenOperation < 3) {
                    pause();
                    executeOperation();
                }//end if
            }while(chosenOperation <0 || chosenOperation >3);


       } while(chosenOperation !=0);

    }//end optionLoop

    /**
     * this method calls other methods to perform the user specified
     * actions.
     */
    private static void executeOperation(){



        if(chosenOperation ==1){

            String fileName=selectFile();
            playAudio(fileName);
            reverseAudio(fileName);
        }//end if

        else if(chosenOperation ==2){

            generateFreq();
        }//end else if

        else{
            System.out.println("THIS SHOULD NEVER PRINT!");
        }//end else



    }//end executeOperation

    /**
     * generates a .wav file that is the reverse of the input wav file
     * @param fileName - name of file to be copied and reversed
     */
    private static void reverseAudio(String fileName){

        WavFile audioToBeReversed = new WavFile("sounds/"+fileName+".wav");

        ArrayList<Double> audioSampleList = new ArrayList<>(audioToBeReversed.getSamples());


        ArrayList<Double> reversedAudioSampleList = new ArrayList<>();

        for(int i =audioSampleList.size()-1 ; i>=0; i-- ){

            reversedAudioSampleList.add(audioSampleList.get(i));
        }//end for

        String newFileName= fileName+"Rev";

        WavFile reversedAudio = new WavFile("sounds/"+newFileName+".wav",
                audioToBeReversed.getNumChannels(), audioToBeReversed.getNumFrames(),
                audioToBeReversed.getValidBits(), audioToBeReversed.getSampleRate());



        reversedAudio.setSamples(reversedAudioSampleList);


        reversedAudio.close();

        pause();

        System.out.println("Here's what "+fileName+".wav sounds like reversed");
        pause();
        playAudio(newFileName);

    }//end reverse audio

    /**
     * generates a .wav file that produces a sine wave at
     * a user defined frequency
     */
    private static void generateFreq(){

        int sampleRate = 8000;

        System.out.println("What would you like to name your file?");
        String fileName=userInput.nextLine();



        WavFile  audioWithFreq = new WavFile("sounds/"+fileName+".wav",
                1, sampleRate,
                8, sampleRate);

        String freqString;

        double frequency;

        do {
            do {
                System.out.println("Please enter a frequency between 20 and 20,000");

                freqString = userInput.nextLine();

            } while (!isValidDouble(freqString));

            frequency = Double.parseDouble(freqString);

        }while(frequency<20 || frequency>20000);

        ArrayList<Double> frequencySamples = new ArrayList<>();

        for(int i=0 ; i<sampleRate; i ++){

            frequencySamples.add(Math.sin(2*Math.PI*i*(frequency/sampleRate)));

        }//end for

        audioWithFreq.setSamples(frequencySamples);

        audioWithFreq.close();



        System.out.println("Here's what "+fileName+".wav sounds like");
        pause();
        playAudio(fileName);
        pause();
    }//end generateFreq

    /**
     * asks user to select a .wav file from a list
     * @return name of selected .wav file
     */
    private static String selectFile(){

        String fileName;

        do {
            displaySoundFiles();

            fileName = userInput.nextLine();

            System.out.println("You entered: "+fileName);

        }while(!isValidFile(fileName));

        return fileName;
    }//end selectFile

    /**
     * displays a list of sound files to choose from
     */
    private static void displaySoundFiles(){

        System.out.println("Please enter the name of a wave file from the following list:");

        for (String sound: sounds) {

            System.out.println("\s*\t"+sound);

        }//end for
        
    } //end displaySoundFiles

    /**
     * Checks to see if the entered file name is valid
     * @param fileName - name of file entered by user
     * @return true or false
     */
    private static boolean isValidFile(String fileName){

        boolean flag = false;

        for (String sound : sounds) {

            if (fileName.equalsIgnoreCase(sound)){
                flag=true;
            }//end if
        }//end for

        if(!flag){
            System.out.println("Invalid File Name");
        }//end if

        return flag;
    }

    /**
     * calls WavPlayer.play() to play input file name
     * @param fileName - name of file to be played
     */
    private static void playAudio(String fileName){

        WavPlayer.play("sounds/"+fileName+".wav");
    }//end fileName

    /**
     * checks to see if an input String is a double
     * @param numString - number in string form
     * @return - true or false
     */
    private static boolean isValidDouble(String numString){

        boolean flag= true;

        try{
            Double.parseDouble(numString);
        }//end try

        catch(Exception e){
            flag=false;
        }//end catch

        return flag;

    }//end isValidDouble

    /**
     * This method pauses the program for 500 milliseconds
     */
    private static void pause(){


        try {
            Thread.sleep(500);      //pause for 500 milliseconds
        } //end try
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }//end catch

    }//end pause

    /**
     * Friendly little welcome message
     */
    private static void welcome(){
        System.out.println("Welcome to WavGenerator!");
    }//end welcome

    /**
     * Friendly Farewell
     */
    private static void goodBye(){
        System.out.println("Thank you for using WavGenerator! Have a wonderful day, goodbye.");
    }//end goodbye

}//end WavGenerator