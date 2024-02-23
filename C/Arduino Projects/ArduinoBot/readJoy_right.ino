void readJoy_right() {

  /*
    Main File    : readJoy_right
    Source Files : none
    Libraries    : none
    Description  : This function reads the analog input from the right joysitck,
                   determines direction and speed of right motor, then calls the
                   driveMotor_right function to drive the right motor. This function
                   does not return any value
    Author       : Cody Mott
    Date         : 05/14/2022
  */


  //local varialbes
  int volts2motor; //motor speed
  int joystickVolts = analogRead(rightMotVolt_PIN); //analog input from right joystick



  //determine motor speed and direction
  if (joystickVolts <= 200) {

    //map motor speed
    volts2motor = map(joystickVolts, 200, 0, 0, 255);

    //drive right motor in clockwise direction
    driveMotor_right(volts2motor, CCW );

  } //end if
  
  else if (joystickVolts >= 300) {

    //map motor speed
    volts2motor = map(joystickVolts, 300, 665, 0, 255);

    //drive right motor in counter clockwise direction
    driveMotor_right(volts2motor, CW );

  } //end else if




  //Uncomment for debugging
  //Serial.println("JR");
  //Serial.println(joystickVolts);
  //Serial.println(volts2motor);

  return; //return nothing

} //end readJoy_right
