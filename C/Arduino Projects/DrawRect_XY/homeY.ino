int homeY() {
  /*
     Main File: homeY
     Source Files: None
     Libraries : None
     Description: homes the Y axis and counts steps between end stops
     Input param: None
     Output param: returns the Y axis dimension in steps
     Author: Cody Mott
     Date: 5/14/2022
  */

  //set y axis direction toward y axis limit switch A
  digitalWrite(Y_DIR_PIN, AWAY);

  //move carriage along y axis until it hits limit y axis switch A
  while (digitalRead(Y_SWA_PIN) == HIGH) {

    //move 1 step
    digitalWrite(Y_STEP_PIN, HIGH);
    digitalWrite(Y_STEP_PIN, LOW);

    delay(20);// delay for 20 miliseconds

  }

  //set y direction towards y axis limit switch B
  digitalWrite(Y_DIR_PIN, BACK);

  //start counting number of y steps
  int nYsteps = 0;

  //move carriage along the y axis until it hits y axis limit switch B
  while (digitalRead(Y_SWB_PIN) == HIGH) {

    //increase y steps count by 1
    nYsteps = nYsteps + 1;

    //move 1 step
    digitalWrite(Y_STEP_PIN, HIGH);
    digitalWrite(Y_STEP_PIN, LOW);

    delay(20);// delay for 20 miliseconds

  }

  //set y axis direction towards y axislimit switch A
  digitalWrite(Y_DIR_PIN, AWAY);

  //center carriage along the y axis
  for (int steps = 1; steps <= nYsteps / 2; steps++) {

    //move 1 step
    digitalWrite(Y_STEP_PIN, HIGH);
    digitalWrite(Y_STEP_PIN, LOW);

    delay(20);// delay for 20 miliseconds

  }

  return nYsteps;
}
