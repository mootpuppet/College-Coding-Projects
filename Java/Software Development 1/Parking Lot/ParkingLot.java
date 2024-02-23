/*
 * Course: CS1011-051
 * Fall 2022-2023
 * File header contains class ParkingLot
 * Name: mottc
 * Created 11/1/2022
 */
package mootpuppet;

/**
 * Course CS1011-051
 * Fall 2022-2023
 * ParkingLot purpose: Manages a single parking lot
 *
 * @author mottc
 * @version created on 11/1/2022 at 12:10 pM
 */

public class ParkingLot {
    public static final double CLOSED_THRESHOLD=80; //percentage of capacity when lot closes
    private final String name;    //name of lot
    private final int capacity;
    private int spotsRemaining;
    private int carsInLot=0;
    private int timeClosed =0;       //timestamp when lot closed
    private int minutesClosed=0;     //total minutes closed
    private boolean closed;         //keeps track of whether the lot is closed
    private int entryTime=0;         //time when last vehicle entered the lot

    /**
     * this is the default constructor method.
     * when instantiated, it creates a lot named test
     * and assigns the input parameter to the global
     * capacity varialbe
     * @param capacity - number of total parking spots in lot
     */
    public ParkingLot(int capacity){

        name="test";
        this.capacity=capacity;
        spotsRemaining=capacity;

        //uncomment for tracing
       /* System.out.println("\nname: "+ name);
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
     * This method displays the status of the parking lot
     */
    public void displayStatus(){



        //if closed
        if(isClosed()){

            System.out.println(name+" parking lot status: CLOSED");

        }//end if

        //if not closed
        else if (!isClosed()) {

            System.out.print(name+" parking lot status: %");
            System.out.printf("%3.2f%n", getPercentFull());

        }//end else if


        else {
            System.out.println("STATUS ERROR!"); //error message for debugging
        }//end else


    }//end displayStatus

    /**
     * a getter for total minutes closed
     * @return total minutes lot closed
     */
    public int getMinutesClosed(){


        //uncomment for debugging
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

        /* //UNCOMMENT FOR TESTING
        double percentFull = carsInLot/(double)capacity*100.00;
        System.out.println("Percent Full: " +percentFull+"%");
        return percentFull;
        */

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

        /*This if statement prevents timestamps in
        * past from affecting current data */
        if(timestamp>=entryTime){

            entryTime=timestamp;   //current timestamp is now the most recent entry time
            spotsRemaining--;      //one less spot remaining
            carsInLot++;           //add car to lot

        }//end if


        //prevents lot from having negative spots
        if(spotsRemaining<0){

            spotsRemaining=0;

        }//end if



        //Uncomment for debugging
        /*System.out.println("Vehicle Entered lot!");
        System.out.println("Entry Time: " + timestamp);
        System.out.println("Cars in lot: "+carsInLot);
        System.out.println("Spots remaining: "+spotsRemaining);
        displayStatus();*/

        //check if lot is closed and if the closed variable is false
        if(isClosed() && !closed){

            closed=true;              //lot is closed

            timeClosed = timestamp;  //index time at which lot closed

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

        /*this if statement prevents timestamps in past
         *from affecting current data*/
        if(timestamp>=entryTime) {

            spotsRemaining++; //+1 spot remaining
            carsInLot--;      //-1 car in lot
        }//end if

        //prevents spots remaining from exceeding capacity
        if (spotsRemaining> capacity){
            spotsRemaining=capacity;
        }//end if



        //uncomment for Debugging
        /*System.out.println("Vehicle Left lot!");
        System.out.println("Time of Exit: " + timestamp);
        System.out.println("Cars in lot: "+carsInLot);
        System.out.println("Spots remaining: "+spotsRemaining);
        displayStatus();*/


        //checks if lot is open and if closed variable is true
        if(!isClosed() && closed) {

            closed=false;      //lot not closed

            //System.out.println("Time @ open: "+ timestamp);

            //calculate most recent closure duration
            int currentMinClosed = timestamp - timeClosed;

            //calculate total minutes closed
            minutesClosed+= currentMinClosed;

           //System.out.println("currentMinClosed: "+ currentMinClosed);
            //System.out.println("minutesClosed: "+minutesClosed);

        }//end if

        //System.out.println("CLOSED: "+closed);


    }//end markVehicleExit




}//end Class ParkingLot
