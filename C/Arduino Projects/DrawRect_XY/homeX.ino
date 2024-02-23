int homeX() {
  /*
    Main File: homeX
    Source Files: None
    Libraries : None
    Description: homes the X axis and counts steps between end stops
    Input param: None
    Output param: returns the X axis dimension in steps
    Author: Cody Mott
    Date: 5/14/2022
  */


  //set x axis direction towards x axis limit switch A
  digitalWrite(X_DIR_PIN, AWAY);

  //move carriage along the x axis until it hits the x axis limit switch A
  while (digitalRead(X_SWA_PIN) == HIGH) {

    //move 1 step
    digitalWrite(X_STEP_PIN, HIGH);
    digitalWrite(X_STEP_PIN, LOW);

    delay(20);// delay for 20 miliseconds

  }

  //set x axis direction toward x axis limit switch B
  digitalWrite(X_DIR_PIN, BACK);

  //start counting number of x stpes
  int nXsteps = 0;

  //move carriage along x axis towards x axis limit switch B
  while (digitalRead(X_SWB_PIN) == HIGH) {

    //increase x steps count by 1
    nXsteps = nXsteps + 1;

    //move 1 step
    digitalWrite(X_STEP_PIN, HIGH);
    digitalWrite(X_STEP_PIN, LOW);

    delay(20);// delay for 20 miliseconds

  }

  //set x axis direction towards x axis limit switch A
  digitalWrite(X_DIR_PIN, AWAY);

  //center carriage along the x axis
  for (int steps = 1; steps <= nXsteps / 2; steps++) {

    // move 1 step
    digitalWrite(X_STEP_PIN, HIGH);
    digitalWrite(X_STEP_PIN, LOW);

    delay(20);// delay for 20 miliseconds


  }

  // return axis dimension in steps
  return nXsteps;

}
