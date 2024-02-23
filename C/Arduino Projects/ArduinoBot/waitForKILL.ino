void waitForKILL() {

  /*
    Main File    : waitForKILL
    Source Files : none
    Libraries    : none
    Description  : This function reads the kill switch pin continously until
                   the kill switch is depressed and reads high. Then there is
                   a 0.5 second delay before the function returns. This function
                   does not return any value
    Author       : Cody Mott
    Date         : 05/14/2022
  */




  //read kill switch
 switchVal = digitalRead(kill_PIN); //kill switch value

  //loop while kill switch reads low
  while (switchVal == LOW) {

    //read kill switch
    switchVal = digitalRead(kill_PIN);

  } //end while

  //pause for 1/2 second
  delay(500);

  return; //return nothing

} //end waitForKILL
