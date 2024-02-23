float readDistance() {

  /*
    Main File    : readDistance
    Source Files : none
    Libraries    : none
    Description  : This function uses the ultrasonic sensor to determine
                   the distance to the nearest object. It first triggers
                   a pulse and measureses the duration, then converts that
                   to centimeters, converts centimeters to inches, and then
                   returns the distance in inches. The distance in inches
                   is also printed to the the serial monitor for debugging
                   purposes.
    Author       : Cody Mott
    Date         : 05/14/2022
  */



  //local variables
  float duration_us, distance_cm, distance_inches;

  // generate 10-microsecond pulse to TRIG pin
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);

  // measure duration of pulse from ECHO pin
  duration_us = pulseIn(echoPin, HIGH);

  // calculate the distance in centimeteres
  distance_cm = 0.017 * duration_us;

  // convert cm to inches
  distance_inches = distance_cm / 2.54;

  // print the value to Serial Monitor
  Serial.print("distance: ");
  Serial.print(distance_inches);
  Serial.println(" inches");

  return distance_inches; //return distance in inches

} //end readDistance
