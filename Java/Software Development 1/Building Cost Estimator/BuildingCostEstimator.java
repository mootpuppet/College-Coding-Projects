/*
 * Course: CS1011-051
 * Fall 2022-2023
 * File header contains class BuildingCostEstimator
 * Name: mottc
 * Created 10/12/2022
 */

package mootpuppet;

/**
 * Course CS1011-051
 * Fall 2022-2023
 * This is a driven class, it does nothing on its own.
 * It takes inputs from a driver class to calculate
 * the cost to build a house.
 *
 * @author mottc
 * @version created on 10/12/2022 at 1:15 PM
 */

public class BuildingCostEstimator {

    //GLOBAL VARIABLES

    //data
    private int squareFeet;
    private int numFullBaths;
    private int numHalfBaths;
    private int numBedrooms;
    private int numWindows;
    private double numGarages;   //number of garage stalls


    //constructors

    /**
     * This is the default constructor. It does nothing
     */
    public BuildingCostEstimator(){

    }

    //setters

    /**
     * this method assigns an input value of square feet
     * to the global variable squareFeet
     *
     * @param squareFeet
     * square feet
     */
    public void setSquareFeet(int squareFeet){

        this.squareFeet = squareFeet;

    } //end setSquareFeet

    /**
     * this method assigns an input value of
     * the number of full baths to the global variable
     * numFullBaths
     *
     * @param numFullBaths
     * number of full baths
     */
    public void setNumFullBaths(int numFullBaths){

        this.numFullBaths=numFullBaths;

    } //end setNumFullBaths

    /**
     * this method assigns an input value of the number
     * of half baths to the global variable numHalfBaths
     *
     * @param numHalfBaths
     * number of half baths
     */
    public void setNumHalfBaths(int numHalfBaths){

        this.numHalfBaths=numHalfBaths;
    } //end setNumHalfBaths

    /**
     * this method assigns an input value of the number
     * of bedrooms to the global variable numBedrooms
     *
     * @param numBedrooms
     * number of bedrooms
     */
    public void setNumBedrooms(int numBedrooms){

        this.numBedrooms=numBedrooms;

    } //end setNumBedrooms

    /**
     * this method assigns an input value of the number
     * of windows to the global variable numWindows
     *
     * @param numWindows
     * number of windows
     */
    public void setNumWindows(int numWindows){

        this.numWindows=numWindows;

    } //end setNumWindows

    /**
     * this method assigns an input value of the number
     * of garage stalls to the global variable numGarages
     *
     * @param numGarages
     * number of garage stalls
     */
    public void setNumGarages(double numGarages){

        this.numGarages=numGarages;

    } //end setNumGarages

    //getters

    /**
     * this method returns the value assigned
     * to the global variable squareFeet
     *
     * @return squareFeet
     */
    public int getSquareFeet(){return squareFeet;} //end getSquareFeet

    /**
     * this method returns the value assigned
     * to the global variable numFullBaths
     *
     * @return numFullBaths
     */
    public int getNumFullBaths() {return numFullBaths;}

    /**
     * this method returns the value assigned
     * to the global variable numHalfBaths
     *
     * @return numHalfBaths
     */
    public int getNumHalfBaths() {return numHalfBaths;} //end getNumHalfBaths

    /**
     * this method returns the value assigned
     * to the global variable numBedrooms
     *
     * @return numBedrooms
     */
    public int getNumBedrooms() {return numBedrooms;} //end getNumBedrooms

    /**
     * this method returns the value assigned
     * to the global variable numWindows
     *
     * @return numWindows
     */
    public int getNumWindows() {return numWindows;} //end getNumWindows

    /**
     * this method returns the value assigned
     * to the global variable numGarages
     *
     * @return numGarages
     */
    public double getNumGarages() {return numGarages;} //end getNumGarages


    //mutators

    /**
     * this method calculates the cost to build
     * and returns that cost
     *
     * @return the estimated cost to build
     */
    public double costToBuild(){

        //Costs in USD
        final int costPerSquareFoot = 185;
        final int costPerFullBath = 30000;
        final int costPerHalfBath = 12000;
        final int costPerBedroom = 5000;
        final int costPerWindow = 1300;
        final int costPerGarageStall = 12000;

        //calculate estimated cost to build
        return (costPerSquareFoot *squareFeet)+(numFullBaths* costPerFullBath)
                +(numHalfBaths* costPerHalfBath)+(numBedrooms* costPerBedroom)
                +(numWindows* costPerWindow)+(numGarages* costPerGarageStall);

    } //end costToBuild

} //end BuildingCostEstimator
