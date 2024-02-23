/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class Calendar
 * Name: mottc
 * Created 12/7/2022
 */
package mottc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * Calendar purpose: A calendar object that holds a collection of sections.
 *
 * @author mottc
 * @version created on 12/7/2022 at 8:59 AM
 */
public class Calendar implements Schedulable {

    public final int DEFAULT_START_OF_DAY=8;
    public final int DEFAULT_END_OF_DAY=17;
    private final List<Schedulable> items = new ArrayList<>();
    private final String name;
    private final int startOfDay;
    private final int endOfDay;

    /**
     * constructs a calendar object with a name, a start time for each day, an end time for each day
     * and any sections to be added to the calendar
     * @param name - name of calendar
     * @param startOfDay - time of day the calendar starts
     * @param endOfDay - time of day the calendar ends
     * @param items - section object(s)
     */
    public Calendar(String name, int startOfDay,int endOfDay, Schedulable... items){

        this.name=name;
        this.startOfDay=startOfDay;
        this.endOfDay=endOfDay;
        this.items.addAll(Arrays.asList(items));
    }//end Calendar

    /**
     * Constructs a calendar object with the default start of day and default end of day
     * @param name - name of calendar
     * @param items - section object(s)
     */
    public Calendar(String name, Schedulable... items){

        this.name=name;
        startOfDay=DEFAULT_START_OF_DAY;
        endOfDay=DEFAULT_END_OF_DAY;
        this.items.addAll(Arrays.asList(items));
    }//end Calendar

    /**
     * Creates a String that is a visual representation of this calendar object
     * with the name of the calendar, day and times, and an x, wherever there is
     * a meeting scheduled
     * @return - calendar String
     */
    @Override
    public String toString(){

        String scheduleName = "**"+name+"**";

        int[][] scheduleMatrix= getScheduleMatrix();

        //first line
        String schedule = "| "+scheduleName+" | Mon | Tue | Wed | Thu | Fri |\n" +
                "| ";

        //dashes to go under name at beginning of second line
        for(int i=0;i< scheduleName.length();i++){
            schedule+="-";

        }//end for


        //second line, repeating pattern for each day
        for(int i=0;i<=FRIDAY;i++) {
            schedule += " | ---";
        }//end for

        //end of second line
        schedule+=" |\n";

        //new line for each hour
        for(int row = 0; row < scheduleMatrix.length; row++){

            String hour=(row+startOfDay)+":00";

            schedule+="| ";

            //whitespace before hour
            for(int i=0;i<scheduleName.length()-hour.length();i++){
                schedule+="\s";

            }//end for

            schedule+=hour+" |";



            //for each day
            for (int col = 0; col < scheduleMatrix[0].length; col++) {

                //whitespace for formatting purposes
                schedule+="\s\s";

                //"X" if there is something scheduled at this day and time
                if(scheduleMatrix[row][col]==1) {
                    schedule += "X";

                }//end if

                else{
                    schedule+="\s";

                }//end else

                //whitespace for formatting purposes
                schedule+="\s\s|";

            }//end for

            schedule+="\n";

        }//end for

        return schedule;

    }//end toString

    /**
     * this method calls the isBusy method for each section for
     * each day and time and a creates an integer matrix, called
     * sectionMatrix, of 0s and 1s where 1 represents a scheduled
     * meeting and 0 represents nothing. Each time, the sectionMatrix
     * is overwritten and added to another matrix called scheduleMatrix
     * which represents an entire schedule
     * @return - scheduleMatrix
     */
    private int[][] getScheduleMatrix(){

        int[][] scheduleMatrix= new int[endOfDay-startOfDay][FRIDAY+1];
        int[][] sectionMatrix= new int[endOfDay-startOfDay][FRIDAY+1];

       for(Schedulable item: items) {

            for(int row = 0; row < scheduleMatrix.length; row++) {
                for(int col = 0; col < scheduleMatrix[0].length; col++) {

                    if (item.isBusy(col,row+startOfDay)) {
                        sectionMatrix[row][col]=1;
                    }//end if
                    else {
                        sectionMatrix[row][col]=0;
                    }//end else

                }//end for

            }//end for

           //add sectionMatrix to scheduleMatrix
           for(int row = 0; row < scheduleMatrix.length; row++) {
               for (int col = 0; col < scheduleMatrix[0].length; col++) {
                   scheduleMatrix[row][col]+=sectionMatrix[row][col];
               }//end for
           }//end for

        }//end for

        return scheduleMatrix;

    }//end getScheduleMatrix


    /**
     * returns false if called
     * @param day - day
     * @param hour - time
     * @return false
     */
    @Override
    public boolean isBusy(int day, int hour) {

        return false;
    }//end isBusy

    /**
     * adds section object to items list
     * @param item - section
     * @return - true
     */
    @Override
    public boolean add(Schedulable item) {
        items.add(item);
        return true;
    }//end add

    /**
     * remove section object from items list
     * @param item - section
     * @return - true if successfully removed, otherwise false
     */
    @Override
    public boolean remove(Schedulable item) {

        return items.remove(item);
    }//end remove

    /**
     * returns name of Calendar
     * @return - name
     */
    @Override
    public String getName() {

        return name;
    }//end getName

}//end Calendar