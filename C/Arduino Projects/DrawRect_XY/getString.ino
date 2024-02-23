String getString() {
  /*
    Main File: getString
    Source Files: None
    Libraries : None
    Description: This function helps retrive stringss from the serial port
    Input param: None
    Output param: num - String read from the serial port
    Author: Dr. Cody Mott
    Date: 5/14/2022
  */

// initialize user input
  String userInput = "";


  while (Serial.available() == LOW) {
    /*Wait for user input */
  } //end while

//read serial until enter is pressed
  userInput = Serial.readStringUntil('\n');


// return user input string
  return userInput;

}// end loop()
