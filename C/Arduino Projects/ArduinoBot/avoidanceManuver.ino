void avoidanceManuver(float distance) {

  /*
    Main File    : avoidanceManuver
    Source Files : none
    Libraries    : none
    Description  : This function takes the input of current distance and does
                   nothing if the current distance is greater than the distance
                   threshold. If the current distance is less than or equall to
                   the distnace threshold, this function with stop the motors,
                   then back away from the object a set number of inches equal
                   the moveawayfromOBJ_dist constant. This function does not
                   return any value
    Author       : Cody Mott
    Date         : 05/14/2022
  */


  //if current distance is less than or equal to distance threshold
  if (distance <= dist_threshold) {

    //stop motors
    driveMotor_left(0, CCW );
    driveMotor_right(0, CW);

    //pause for 0.5 seconds
    delay(500);

    //read current distance
    distance = readDistance();

    //move robot back away from object at half speed until it has moved
    //a distance in inches equal to moveawayfromOBJ_dist
    while (distance < dist_threshold + moveawayfromOBJ_dist) {

      driveMotor_left(avoidance_speed, CW );
      driveMotor_right(avoidance_speed, CCW);

      distance = readDistance();//read current distance from object

    }//end while


    //stop motors
    driveMotor_left(0, CCW );
    driveMotor_right(0, CW);

  } //end if

  return; //return nothing

} // end avoidanceManuver
