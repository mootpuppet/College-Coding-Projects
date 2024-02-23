/*
 * Course: CS1011-051
 * Fall 2022-2023
 * File header contains class ParkingLot
 * Name: mottc
 * Created 11/9/2022
 */
package mootpuppet;

import java.text.DecimalFormat;

/**
 * Course CS1011-051
 * Fall 2022-2023
 * ParkingLot purpose: Manages a single parking lot
 *
 * @author mottc
 * @version created on 11/9/2022 at 12:37 PM
 */
public class ParkingLot {
    public static final double CLOSED_THRESHOLD=80.0;
    private final String name;  //name of lot
    private final int capacity; //number of total parking spaces in lot
    private int spotsRemaining; //number of available spots remaining
    private int carsInLot=0;    //number of cars in lot
    private int timeClosed =0;  //time in minutes, at which the lot closed
    private int minutesClosed=0;  //total duration closed, in minutes
    private boolean closed;    //internally tracks whether the lot is closed
    private int entryTime=0;   //time at which most recent vehicle entered lot
    DecimalFormat formatter = new DecimalFormat("##.#%");  //decimal format object

    /**
     * this is the default constructor method.
     * when instantiated, it creates a lot named test
     * and assigns the input parameter to the global
     * capacity variable
     * @param capacity - number of total parking spots in lot
     */
    public ParkingLot(int capacity){

        name="test";
        this.capacity=capacity;
        spotsRemaining=capacity;

        //uncomment for tracing
       /* System.out.println("\name: "+ name);
        System.out.println("capacity: "+capacity);*/

    }//end ParkingLot

    /**
     * When this constructor is called, it creates a new lot with
     * and assigns the input parameters to the global variables name
     * and capacity.
     * @param name - name of parking lot
     * @param capacity - total number of parking spots in lot
     */
    public ParkingLot(String name, int capacity){

        this.name=name;
        this.capacity=capacity;
        spotsRemaining=capacity;

        //uncomment for tracing
         /*System.out.println("\nname: "+ name);
         System.out.println("capacity: "+capacity);*/

    }//end ParkingLot

    /**
     * a getter for total minutes closed
     * @return total minutes lot closed
     */
    public int getMinutesClosed(){

        //System.out.println("Minutes Closed: " + minutesClosed);

        return minutesClosed;

    }//end getMinutesClosed

    /**
     * getter to get name of parking lot
     * @return name of parking lot
     */
    public String getName(){

        return name;
    }//end getName

    /**
     * getter that returns the number of
     * spots remaining in the lot
     * @return -spots remaining in lot
     */
    public int getNumberOfSpotsRemaining(){

        return spotsRemaining;

    }//end getNumberOfSpotsRemaining

    /**
     * getter that returns a percentage of how full the lot is
     * @return percentage of lot filled
     */
    public double getPercentFull(){

        //UNCOMMENT FOR TESTING
        /*double percentFull = carsInLot/(double)capacity*100.00;
        System.out.println("Percent Full: " +percentFull+"%");
        return percentFull;*/


        //comment out when testing
        return carsInLot/(double)capacity*100.00;

    }//end getPercentFull

    /**
     * a getter that both determines and returns whether
     * the lot is closed.
     * @return closure status of the lot (true/false)
     */
    public boolean isClosed(){

        return getPercentFull()>=CLOSED_THRESHOLD;

    }//end isClosed

    /**
     * this method marks the time at which the most recent vehicle
     * entered the lot. It keeps track of how many cars are in the lot
     * and how many parking spots are remaining.
     * @param timestamp - time at which vehicle entered the lot
     */
    public void markVehicleEntry(int timestamp){

        if(timestamp>=entryTime){
            entryTime=timestamp;
            spotsRemaining--;
            carsInLot++;
        }//end if


        if(spotsRemaining<0){

            spotsRemaining=0;

        }//end if



        //Uncomment for debugging
        /*System.out.println("Vehicle Entered lot!");
        System.out.println("Entry Time: " + timestamp);
        System.out.println("Cars in lot: "+carsInLot);
        System.out.println("Spots remaining: "+spotsRemaining);
        displayStatus();*/

        if(isClosed()&& !closed){

            closed=true;  //lot closed

            timeClosed = timestamp;  //the time in minutes at which the lot closed down

            //System.out.println("Time when Closed: " + timeClosed);

        }//end if

        //System.out.println("CLOSED: "+closed);




    }//end markVehicleEntry

    /**
     * This method marks the time when a vehicle exits the lot
     * and sets the number of spots remaining and the number of
     * cars in lot accordingly
     * @param timestamp - time at which vehicle left lot
     */
    public void markVehicleExit(int timestamp){

        if(timestamp>=entryTime) {
            spotsRemaining++;
            carsInLot--;
        }

        if (spotsRemaining> capacity){
            spotsRemaining=capacity;
        }



        //uncomment for Debugging
        /*System.out.println("Vehicle Left lot!");
        System.out.println("Time of Exit: " + timestamp);
        System.out.println("Cars in lot: "+carsInLot);
        System.out.println("Spots remaining: "+spotsRemaining);
        displayStatus();*/


        if(!isClosed() && closed) {

            closed=false;

            //System.out.println("Time @ open: "+ timestamp);

            //each instance of being closed
            int currentMinClosed = timestamp - timeClosed;

            minutesClosed+= currentMinClosed;

            //System.out.println("currentMinClosed: "+ currentMinClosed);
            //System.out.println("minutesClosed: "+minutesClosed);
        }

        //System.out.println("CLOSED: "+closed);


    }//end markVehicleExit

    /**
     * this method replaces the default toString and returns a display message
     * @return display message, status of lot
     */
    public String toString(){

        String displayStatus;
        if(isClosed()) {

            displayStatus = "Status for " + name + " parking lot: " + carsInLot + " vehicles (CLOSED)";
        }
        else{
            //double percent = getPercentFull();

            displayStatus = "Status for " + name + " parking lot: " + carsInLot +
                    " vehicles ("+formatter.format(getPercentFull()/100)+")";
        }
        // System.out.println(displayStatus);
        return displayStatus;

    }//end toString

}//end ParkingLot