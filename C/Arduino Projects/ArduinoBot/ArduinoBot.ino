/***************************************************************************
  Main File    : ArduinoBot
  Source Files : avoidanceManuver, driveMotor_left, driveMotor_right,
                 readDistance,readJoy_left, readJoy_right, waitForKill
  Libraries    : none
  Description  : This program calls on several functions to control a 
                 custom built dual motor robot with an arduino uno. 
                 First, the global variables are defined, these 
                 mainly being the arduino uno pins and a few definitions
                 used for logic. In the setup, the pin modes are defined
                 and serial communication begun. The main loop starts by 
                 calling the waitForKill() function, then enables the 
                 motor driver, reads the kill switch pin, and while the 
                 kill switch is reading low, the current distance is defined
                 by calling the readDistance function and returning a distance
                 value in inches, the current distance is then sent through
                 the avoidanceManuver function, then the readJoy_left function 
                 is called, the readJoy_right function is called, and the 
                 kill switch pin is read again. Once the while loop is exited,
                 which would be due to the kill switch reading HIGH, the motor 
                 driver is disabled and there is a 1 second delay before the 
                 main loop starts over.
  Author       : Cody Mott
  Date         : 05/14/2022
*****************************************************************************/                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              //global variables

//GLOBAL VARIALBLES

//Define Pins
const byte leftMotVolt_PIN = A0; // left motor voltage  input
const byte rightMotVolt_PIN = A3; // right motor voltage input
const byte kill_PIN = 12; //kill switch
const byte L_mot_PWM  = 6; //left motor PWM output
const byte R_mot_PWM = 3; //right motor PWM output
const byte STANDBY = 7; //motor driver standby pin

//left motor direction pins
const byte L_mot_DIR1 = 8;
const byte L_mot_DIR2 = 9;

//right motor direciton pins
const byte R_mot_DIR1 = 5;
const byte R_mot_DIR2 = 4;

//ultrasonic sensor pins
const byte trigPin = 10;//trigger
const byte echoPin = 11;//echo

//define constants
const bool ENABLE = 1;
const bool DISABLE = 0;

//motor directions
const bool CW = 1; //clowckwise
const bool CCW = 0; //counter clockwise

//avoidance system values
const byte dist_threshold = 6;//min distance in inches to object
const byte moveawayfromOBJ_dist = 3; //distance in inches to move away from object
const byte avoidance_speed = 255 / 2; //speed at which to move away from object


//define variables
float current_dist; //current distance from nearest object
bool switchVal; //killswitch value


void setup() {


  //pinModes
  pinMode(L_mot_PWM, OUTPUT);
  pinMode(R_mot_PWM, OUTPUT);
  pinMode(STANDBY, OUTPUT);
  pinMode(L_mot_DIR1, OUTPUT);
  pinMode(L_mot_DIR2, OUTPUT);
  pinMode(R_mot_DIR1, OUTPUT);
  pinMode(R_mot_DIR2, OUTPUT);
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);
  pinMode(kill_PIN, INPUT);
  pinMode(leftMotVolt_PIN, INPUT);
  pinMode(rightMotVolt_PIN, INPUT);

  //setup serial for debugging purposes
  Serial.begin(9600);


} //end setup



void loop() {


  //wait for kill switch to be pressed before continuing
  waitForKILL();

  //Enable motor driver
  digitalWrite(STANDBY, ENABLE);

  //read kill switch value
  switchVal = digitalRead(kill_PIN);

  //run throught loop until kill switch is pressed again
  while (switchVal == LOW) {

    //read current distance
    current_dist = readDistance();

    // perform avoidance manuver
    avoidanceManuver(current_dist);

    // read left joysitck and drive left motor
    readJoy_left();

    //read right joy stick and drive right motor
    readJoy_right();

    //read switch value
    switchVal = digitalRead(kill_PIN);

  } // end while loop

  //disable motor driver
  digitalWrite(STANDBY, DISABLE);

  //wait 1 second
  delay(1000);


} //end loop
