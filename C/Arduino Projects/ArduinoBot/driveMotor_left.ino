void driveMotor_left(float motorSpeed, byte dir ) {

/*
  Main File    : driveMotor_left
  Source Files : none
  Libraries    : none
  Description  : This function takes the left motor speed input and left
                 motor direction input then drives the left motor in that
                 direction at that speed. This function does not return 
                 any value
  Author       : Cody Mott
  Date         : 05/14/2022
*/


  // Determine motor direction based on input
  if (dir == CW) {

    //set motor direction to clockwise
    digitalWrite(L_mot_DIR1, LOW);
    digitalWrite(L_mot_DIR2, HIGH);

  } //end if

  else if (dir == CCW) {

    //set motor direction to counterclockwise
    digitalWrite(L_mot_DIR1, HIGH);
    digitalWrite(L_mot_DIR2, LOW);
    
  } //end elseif



  //generating Voltage to Motor
  analogWrite (L_mot_PWM, motorSpeed);

  return; //return nothing

} //end driveMotor_left
