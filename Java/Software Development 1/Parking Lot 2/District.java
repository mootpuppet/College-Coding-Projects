/*
 * Course: CS1011-051
 * Fall 2022-2023
 * File header contains class District
 * Name: mottc
 * Created 11/3/2022
 */
package mootpuppet;

/**
 * Course CS1011-051
 * Fall 2022-2023
 * District purpose: Manages parking lots within a district.
 *
 * @author mottc
 * @version created on 11/3/2022 at 12:38 PM
 */

public class District {

    private ParkingLot[] lots;
    public static final int MAX_LOTS =20;
    private int numLots;

    private int minutesClosed=0;     //total minutes closed
    private boolean closed;
    private int timeClosed=0;




    public District() {lots = new ParkingLot[MAX_LOTS];}//end district

    /**
     * adds lot to parking lot array
     * @param name name of district
     * @param capacity number of total spots in district
     * @return index number of lot
     */
    public int addLot(String name, int capacity){

        int newIndex = numLots;

        if(newIndex<MAX_LOTS) {
            lots[newIndex] = new ParkingLot(name, capacity);
            numLots++;
        }//end if

        // return the index of the new lot or -1 if the lot was not added.
        return newIndex<MAX_LOTS ? newIndex : -1;

    }//end adLot

    /**
     * this getter gets the requested lot
     * @param index lot number
     * @return the requested lot
     */
    public ParkingLot getLot(int index){
        return lots[index];
    }//end getLot

    /**
     * Returns the number of remaining parking spots in the district
     * @return the number of remaining parking spots in the district
     */
    public int getNumberOfSpotsRemaining() {

       int spotsRemaining =0;

       for(int i=0; i<numLots;i++){
           spotsRemaining+= lots[i].getNumberOfSpotsRemaining();
       }

        //System.out.println("Spots Remaining: " + spotsRemaining);

        return spotsRemaining;

    }//end

    /**
     * Returns the amount of time all three lots have been
     * simultaneously closed.
     * @return number of minutes all three lots have been closed
     */
    public int getMinutesClosed() {return

            minutesClosed;
    }//end getMinutesClosed

    /**
     * Checks the status of all three lots in the district and
     * returns true if they are all closed and false otherwise.
     * @return whether all three lots in the district are closed
     */
    public boolean isClosed() {

        int numLotsClosed=0;

        for(int i=0;i<numLots;i++){

            if(lots[i].isClosed()){
                numLotsClosed++;
            }//end if

        }//end for


        return numLotsClosed==numLots;
    }

    /**
     * Record a vehicle entering a lot at a specified timestamp.
     * <p></p>
     * This calls ParkingLot.markVehicleEntry for the lot corresponding
     * to lotNumber (e.g., if lotNumber==1, call markVehicleEntry on lot1).
     * <p></p>
     * If lotNumber is out of range, the method should return without
     * doing anything else.
     * @param lotNumber of lot (should be 1, 2, or 3)
     * @param timestamp Entry timestamp in minutes since all lots were opened.
     */
    public void markVehicleEntry(int lotNumber, int timestamp) {


        //System.out.println(lotNumber+": "+timestamp);

        lots[lotNumber].markVehicleEntry(timestamp);


        if(isClosed()&& !closed){

            closed=true;

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

        lots[lotNumber].markVehicleExit(timestamp);

        if(!isClosed() && closed) {

            closed=false;

            //System.out.println("Time @ open: "+ timestamp);

            //each instance of being closed
            int currentMinClosed = timestamp - timeClosed;

            minutesClosed+= currentMinClosed;

            // System.out.println("currentMinClosed: "+ currentMinClosed);
            //System.out.println("minutesClosed: "+minutesClosed);

        }//end if
    }//end markVehicleExit

    /**
     * this method replaces the default toString and returns a display message
     * @return display message: status of district
     */

    public String toString(){

        String displayMessage = "District status:\n";

        for(int i=0;i<numLots;i++) {
            //System.out.println("i: "+i);
            //System.out.println("numLots: "+numLots);
            displayMessage+="  "+lots[i].toString()+"\n";
        }//end for

        //System.out.println(displayMessage);
        return displayMessage;
    }//end toString
}//end District
