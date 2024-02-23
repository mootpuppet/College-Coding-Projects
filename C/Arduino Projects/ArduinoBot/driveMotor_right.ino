void driveMotor_right(float motorSpeed, byte dir ) {

  /*
    Main File    : driveMotor_right
    Source Files : none
    Libraries    : none
    Description  : This function takes the right motor speed input and left
                   motor direction input then drives the right motor in that
                   direction at that speed. This function does not return 
                   any value
    Author       : Cody Mott
    Date         : 05/14/2022
  */



  // Determine motor direction based on input
  if (dir == CW) {

    //set motor direction to clockwise
    digitalWrite(R_mot_DIR1, LOW);
    digitalWrite(R_mot_DIR2, HIGH);

  } //end if

  else if (dir == CCW) {

    //set motor direction to counterclockwise
    digitalWrite(R_mot_DIR1, HIGH);
    digitalWrite(R_mot_DIR2, LOW);

  } //end elseif



  //generating Voltage to Motor
  analogWrite (R_mot_PWM, motorSpeed);

  return; //return nothing

} //end driveMotor_right
