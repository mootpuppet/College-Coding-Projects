/*
  Main File    : DrawRect_XYtableSIM.m
  Source Files : homeY, homeX, moveSteps, getString, getNum
  Libraries    : String.h
  Description  : This program to draws a rectangle with the XY-Table. The
  pins are defined for the x axis, y axis, and limit switches. Each limit
  switch is configured with a pullup resistor. Values are assigned to the
  directions Back and Away, and values are assigned to Low and High. the
  homeXY.m function is called, which returns the values for the maximum
  number of steps for both the x and y axises. those max values are then
  displayed for the user. Then the carriage is moved to the bottom left
  corner of the table. It then executes a while loop % that allows the
  user to keep drawing rectangles if they wish. The user is asked for the
  desired height and width of the rectangle they would like to draw, then
  the program uses the moveSteps() function to move the carriage and draw
  the requested rectangle, by first moving along the y axis away from the
  motor for the desired number of steps, then along the x axis away from
  the motor for a number of steps equal to the width, then back for both
  axises, with first the y, then the x axis. then the user is asked if
  they would like to draw another rectangle, if they reply with anything
  that starts with a y, the loop repeats, if they reply with anything else,
  the loop ends. if the user ever requests a rectangle that exceeds the
  maximum dimensions of the xy table, an error message will be displayed
  in the command window, and they will be asked again for the height and
  width of the rectangle they would like to draw.

  Author       : Cody Mott
  Date         : 05/14/2022
*/

//libraries
#include <String.h>

// GLOBAL VARIABLES

// Define X-axis pins
const byte X_STEP_PIN = 6; // step pin
const byte X_DIR_PIN  = 7; //direction pin
const byte X_SWA_PIN = 10; //Limit switch A
const byte X_SWB_PIN = 9; //limit switch B

// Define Y-axis pins
const byte Y_STEP_PIN = 5; //step pin
const byte Y_DIR_PIN  = 4; //direction pin
const byte Y_SWA_PIN = 3; //limit switch A
const byte Y_SWB_PIN = 2; //limit switch B

// Direction options
boolean AWAY = 0; // Away from motor or towards SW A
boolean BACK = 1; // Back to motor or towards SW B

//Variabls
byte stepsPerInch = 320 / 3; //step to inch coversion factor

void setup() {


  //configure pins
  pinMode(X_STEP_PIN, OUTPUT);
  pinMode(X_DIR_PIN, OUTPUT);
  pinMode(Y_STEP_PIN, OUTPUT);
  pinMode(Y_DIR_PIN, OUTPUT);

  // Configure switches with a pullup resistor
  pinMode(X_SWA_PIN, INPUT_PULLUP);
  pinMode(X_SWB_PIN, INPUT_PULLUP);
  pinMode(Y_SWA_PIN, INPUT_PULLUP);
  pinMode(Y_SWB_PIN, INPUT_PULLUP);

  //initialize serial port
  int baudrate = 9600;      // serial port baudrate
  Serial.begin(baudrate);     // Create Serial port object at 9600 bps


}

void loop() {


  //home x and y axises and caluclate max dimensions
  int max_Ydim = homeY();
  int max_Xdim = homeX();

  // convet max dimesions to inches
  int Ydim_inces = max_Ydim / stepsPerInch;
  int Xdim_inces = max_Xdim / stepsPerInch;


  //display maximum dimesensions of XY table in inches
  Serial.println("Max rectangle height: ");
  Serial.println(Ydim_inces);
  Serial.println(" inches");
  Serial.print("");
  Serial.println("Max rectangle width: ");
  Serial.println(Xdim_inces);
  Serial.println(" inches");

  //Move carriage to bottom left corner
  moveSteps(Y_STEP_PIN, BACK, max_Ydim / 2);
  moveSteps(X_STEP_PIN, BACK, max_Xdim / 2);

  String repeat = "yes"; //lets loop begin

  //repeat as long as user wishes to draw another rectangle
  while (repeat.equalsIgnoreCase("yes")) {

    //ask user dimensions of rectangle they would like to draw
    Serial.print("How tall would you like the rectangle to be? ");
    int rectHeight_inches = getNum();
    Serial.print('How wide would you like the rectangle to be? ');
    int rectWidth_inches = getNum();


    // convert requested dimensions in inches to steps
    int rectSteps_H = rectHeight_inches * stepsPerInch;
    int rectSteps_W = rectWidth_inches * stepsPerInch;


    //Draw requested rectangle if it does not exceed the maximum dimensions
    if (rectSteps_H <= max_Ydim && rectSteps_W <= max_Xdim) {

      //move carriage along y axis away from y lim switch A for a number
      // of steps equal to the user defined height
      moveSteps(Y_STEP_PIN, AWAY, rectSteps_H);

      //move carriage along x axis away from x lim switch A for a number
      //of steps equal to the user defined width
      moveSteps(X_STEP_PIN, AWAY, rectSteps_W);

      //move carriage along y axis toward y lim switch A for a number of
      //steps equal to the user defined height
      moveSteps(Y_STEP_PIN, BACK, rectSteps_H);

      //move carriage along x axis toward x lim switch A for a number of
      //steps equal to the user defined width
      moveSteps(X_STEP_PIN, BACK, rectSteps_W);

      //ask user if they would like to draw another rectangle
      Serial.print("Would you like to draw another rectangle? yes/no? ");
      repeat = getString();
      
    } // end if()
    
    else {

      // error message displayed if requested rectangle exceeds XY table
      // dimensions
      Serial.write("Requested rectangle exceeds max dimensions");

    } //end else
    
  } //end while() 

} // end loop()
