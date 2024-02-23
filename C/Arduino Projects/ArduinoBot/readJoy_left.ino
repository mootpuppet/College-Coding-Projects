void readJoy_left() {

  /*
    Main File    : readJoy_left
    Source Files : none
    Libraries    : none
    Description  : This function reads the analog input from the left joysitck,
                   determines direction and speed of left motor, then calls the
                   driveMotor_left function to drive the left motor. This function
                   does not return any value
    Author       : Cody Mott
    Date         : 05/14/2022
  */


  //local varialbes
  int volts2motor; //motor speed
  int joystickVolts = analogRead(leftMotVolt_PIN); //analog input from left joystick



  //determine motor speed and direction
  if (joystickVolts <= 200) {

    //map motor speed
    volts2motor = map(joystickVolts, 200, 0, 0, 255);

    //drive left motor in clockwise direction
    driveMotor_left(volts2motor, CW );

  } //end if

  else if (joystickVolts >= 300) {

    //map motor speed
    volts2motor = map(joystickVolts, 300, 665, 0, 255);

    //drive left motor in counter clockwise direction
    driveMotor_left(volts2motor, CCW );

  } //end else if


  //Uncomment for debugging
  //Serial.println("JL");
  //Serial.println(joystickVolts);
  //Serial.println(volts2motor);

  return; //return nothing

} //end readJoy_left
