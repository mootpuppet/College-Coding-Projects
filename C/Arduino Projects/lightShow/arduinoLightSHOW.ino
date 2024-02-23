/*
   Main File: arduinoLightShow.ino
   Source Files: getNum
   Libraries : None
   Description: This program asks the user how manly times they would like to
                repeat the LED lighting sequence then proceeds to light the LEDs
                and will change direction if the momentary switch is depressed.
                the direction along with the status of the switch is displayed
                in the serial port
   Author: Cody Mott
   Date: 4/20/2022
*/

// GLOBAL Variables are define Here

// define constants
const int SWITCH_PIN = 12;              // momentary switch pin
const int LED_NUM = 5;                  // number of LEDs
const int LED_PIN[5] = {10, 8, 7, 5, 2}; // LED pins
const int DELAY_int = 1000;             //dealy interval 1 second

// define variables
int switch_val = 0;  //momentary switch value
int sequence_REPS = 0; // sequence repititions

void setup() {

  // set switch pin to input
  pinMode(SWITCH_PIN, INPUT);

  // set led pins to outputs
  for (int i = 0; i < LED_NUM; i++) {
    pinMode( LED_PIN[i], OUTPUT);
  }

  //initialize serial port
  int baudrate = 9600;      // serial port baudrate
  Serial.begin(baudrate);     // Create Serial port object at 9600 bps

}

void loop() {

  Serial.println("Enter the number of times you would like to repeat the lighting sequence then press ENTER");

  sequence_REPS = getNum(); // call getNum for user input

  for (int rep = 1; rep <= sequence_REPS ; rep++) {

    switch_val = digitalRead(SWITCH_PIN); // read switch value


    // Turns on each LED light one at a time in reverse sequence
    if (switch_val == HIGH) {


      // display status of switch and direction of lighting
      Serial.write("The switch is On and LEDs are lighting up in the REVERSE sequence");
      Serial.println("");

      //light LEDs
      for (int i = 0; i < LED_NUM; i++) {
        digitalWrite(LED_PIN[i], HIGH); //turn on LED light
        delay(DELAY_int);//pause for 1 second
        digitalWrite(LED_PIN[i], LOW); // turn off LED light
      }
    }

    // Turns on LEDs one at a time in initial sequence
    else {

      //display switch status and direction of lighting
      Serial.write("The switch is OFF and LEDs are lighting up in the INITIAL sequence");
      Serial.println("");

     // light LEDs
      for (int i = LED_NUM - 1; i >= 0; i--) {
        digitalWrite(LED_PIN[i], HIGH); //turn on LED light
        delay(DELAY_int);//pause for 1 second
        digitalWrite(LED_PIN[i], LOW); // turn off LED light
      }
    }
  }

  // make sure the LEDs are off
  for (int i = 0; i < LED_NUM; i++) {
    digitalWrite(LED_PIN[i], LOW);
  }

}
