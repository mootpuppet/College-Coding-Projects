float getNum() {
 /*
 * Main File: getNum
 * Source Files: None
 * Libraries : None
 * Description: This function helps retrive numbers from the serial port 
 * Input param: None
 * Output param: num - number read from the serial port
 * Author: Dr. Luis A. Rodriguez
 * Date: 4/02/2019
*/

  float num;
  char junk = ' ';
  while (Serial.available() == 0) ;  // Wait here until input buffer has a character
  {

    num = Serial.parseFloat();     

    while (Serial.available() > 0)  // .parseFloat() can leave non-numeric characters
    {
      junk = Serial.read() ;  // clear the keyboard buffer
    }
  }
  return num;
}//end getNum
