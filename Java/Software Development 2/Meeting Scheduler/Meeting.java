/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class Meeting
 * Name: mottc
 * Created 12/7/2022
 */
package mottc;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * Meeting purpose: a meeting object that can be added to a section,
 * implements the Schedulable interface
 *
 * @author mottc
 * @version created on 12/7/2022 at 8:47 AM
 */
public class Meeting implements Schedulable {

    private final int day;
    private final int duration;
    private final int hour;

    /**
     * Constructs a meeting object. Sets this objects day, hour,
     * and duration variables equal to the input parameters
     * @param hour - hour at which the meeting will take place
     * @param duration - length in hours of the meeting
     * @param day - the day the meeting takes place
     */
    public Meeting(int hour, int duration, int day){

        this.day=day;
        this.hour=hour;
        this.duration=duration;
    }//end Meeting


    /**
     * isBusy determines whether this specific meeting object
     * is busy at the input day and time
     * @param day - day being checked
     * @param hour - time being checked
     * @return true or false, true if this meeting instance
     * takes place on the input day and during the input time
     */
    @Override
    public boolean isBusy(int day, int hour) {

        //check the day and up to a three hour time period
        boolean busy= this.day == day && (this.hour == hour || this.hour+duration-1==hour ||
                (this.hour+duration-2==hour && duration-2>=0));

        return busy;


    }//end isBusy

    /**
     * items can not be added to meeting, will return false if ever called
     * @param item - a Schedulable object
     * @return - false
     */
    @Override
    public boolean add(Schedulable item) {
        return false;
    }//end add

    /**
     * items can not be removed from meeting, will return false if ever called
     * @param item - a Schedulable object
     * @return - false
     */
    @Override
    public boolean remove(Schedulable item) {
        return false;
    }//end remove

    /**
     * getName returns "X"
     * @return - "X"
     */
    @Override
    public String getName() {
        return "X";
    }//end getName

}//end Meeting