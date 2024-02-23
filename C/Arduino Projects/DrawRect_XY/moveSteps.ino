void moveSteps(byte axis_pin, boolean dir, int nsteps) {

  /*
    Main File    : moveSteps
    Source Files : none
    Libraries    : none
    Description  : this function moves the carriage of the xy table along the
                   input axis, in the input direction, for the number of inputted
                   steps.
    Input Param  : axis - the axis along which the carraige will be moved
                   direction - direction in which the carraige will be moved
                   nsteps - the number of steps the carraige will move
    Output Param : none
    Author       : Cody Mott
    Date         : 05/14/2022
  */




  byte dir_pin;
  int i;

  //define the derection pin correlated with the axis step pin
  if (axis_pin == X_STEP_PIN) {

    //x axis direction pin
    dir_pin = X_DIR_PIN;
  }
  else {
    //y axis direction pin
    dir_pin = Y_DIR_PIN;
  }

  //set direction
  digitalWrite(dir_pin, dir);

  //move xy table carriage
  for (i = 1; i <= nsteps; i++) {
   
    // move 1 step
    digitalWrite(axis_pin, HIGH);
    digitalWrite(axis_pin, LOW);

    delay(20);
  }


}
