/*
 * CS1011 - 051
 * Fall 2020
 * File header contains class District
 * Name: Cody Mott
 * Created: 11/1/2022
 */
package mootpuppet;

/**
 * Course CS1011-051
 * Fall 2022-2023
 * District purpose: Manages parking lots within a district.
 *
 * @author mottc
 * @version created on 11/1/2022 at 12:10 pm
 */
public class District {
    private ParkingLot lot1;
    private ParkingLot lot2;
    private ParkingLot lot3;

    private int minutesClosed=0;     //total minutes closed
    private int entryTime=0;         //most recent entry time
    private boolean closed;          //track whether district is closed
    private int timeClosed=0;        //index the timestamp at which the district closed



    /**
     * Set up a district with three parking lots.
     * @param name1 Name of the first parking lot
     * @param capacity1 Maximum number of vehicles for the first parking lot
     * @param name2 Name of the second parking lot
     * @param capacity2 Maximum number of vehicles for the second parking lot
     * @param name3 Name of the third parking lot
     * @param capacity3 Maximum number of vehicles for the third parking lot
     */
    public District(String name1, int capacity1, String name2, int capacity2,
                    String name3, int capacity3) {

        lot1 = new ParkingLot(name1, capacity1);
        lot2 = new ParkingLot(name2, capacity2);
        lot3 = new ParkingLot(name3, capacity3);
    }//end District

    /**
     * Display status information for all three lots.
     * @see ParkingLot#displayStatus() for the format for each.
     */
    public void displayStatus() {
        System.out.println("District status:");
        System.out.print("  ");
        lot1.displayStatus();
        System.out.print("  ");
        lot2.displayStatus();
        System.out.print("  ");
        lot3.displayStatus();
        System.out.println();
    }//end displayStatus

    /**
     * Returns the number of remaining parking spots in the district
     * @return the number of remaining parking spots in the district
     */
    public int getNumberOfSpotsRemaining() {

        //uncomment code for testing
        /*int spotsRemaining = lot1.getNumberOfSpotsRemaining() + lot2.getNumberOfSpotsRemaining()
                + lot3.getNumberOfSpotsRemaining();
        System.out.println("Spots Remaining: " + spotsRemaining);*/

        return lot1.getNumberOfSpotsRemaining() + lot2.getNumberOfSpotsRemaining()
                + lot3.getNumberOfSpotsRemaining();

    }//end getNumberOfSpotsRemaining

    /**
     * Returns the amount of time all three lots have been
     * simultaneously closed.
     * @return number of minutes all three lots have been closed
     */
    public int getMinutesClosed() {return minutesClosed;}//end getMinutesClosed

    /**
     * Checks the status of all three lots in the district and
     * returns true if they are all closed and false otherwise.
     * @return whether all three lots in the district are closed
     */
    public boolean isClosed() {

        return lot1.isClosed() && lot2.isClosed() && lot3.isClosed();

    }//end isClosed

    /**
     * Record a vehicle entering a lot at a specified timestamp.
     * <p></p>
     * This calls ParkingLot.markVehicleEntry for the lot corresponding
     * to lotNumber (e.g., if lotNumber==1, call markVehicleEntry on lot1).
     * <p></p>
     * If lotNumber is out of range, the method should return without
     * doing anything else.
     * @param lotNumber Number of lot (should be 1, 2, or 3)
     * @param timestamp Entry timestamp in minutes since all lots were opened.
     */
    public void markVehicleEntry(int lotNumber, int timestamp) {


        if(lotNumber==1){
            lot1.markVehicleEntry(timestamp);
        }//end if

        else if(lotNumber==2){
            lot2.markVehicleEntry(timestamp);
        }//end else if

        else if(lotNumber==3){
            lot3.markVehicleEntry(timestamp);
        }//end else if

        //error message for testing code
        else {
            System.out.println("Error in District.markVehicleEntry()");
        }//end else

        //index most recent entry time
        if(timestamp>=entryTime){
            entryTime=timestamp;
        }//end if

        //track closed status and index time closed
        if(isClosed()&& !closed){

            closed=true; //district closed

            timeClosed = timestamp;
            //System.out.println("Time when Closed: " + timeClosed);

        }//end if


    }//end markVehicleEntry

    /**
     * Record a vehicle exiting a lot at a specified timestamp.
     * <p></p>
     * This calls ParkingLot.markVehicleExit for the lot corresponding
     * to lotNumber (e.g., if lotNumber==1, call markVehicleExit on lot1).
     * <p></p>
     * If lotNumber is out of range, the method should return without
     * doing anything else.
     * @param lotNumber Number of lot (should be 1, 2, or 3)
     * @param timestamp Exit timestamp in minutes since all lots were opened.
     */
    public void markVehicleExit(int lotNumber, int timestamp) {

        if(lotNumber==1){
            lot1.markVehicleExit(timestamp);
        }//end if

        else if(lotNumber==2){
            lot2.markVehicleExit(timestamp);
        }//end else if

        else if(lotNumber==3){
            lot3.markVehicleExit(timestamp);
        }//end else if

        //error message for testing code
        else {
            System.out.println("Error in District.markVehicleExit()");
        }//end else if

        //track closed status
        if(!isClosed() && closed) {

            closed=false; //district open

            //System.out.println("Time @ open: "+ timestamp);

            //each instance of being closed
            int currentMinClosed = timestamp - timeClosed;

            minutesClosed+= currentMinClosed; //calculate total minutes closed

            //uncomment for testing
           // System.out.println("currentMinClosed: "+ currentMinClosed);
            //System.out.println("minutesClosed: "+minutesClosed);

        }//end if

    }//end markVehicleExit

}//end District
