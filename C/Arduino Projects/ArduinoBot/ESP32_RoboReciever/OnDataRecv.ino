/**
   ESPNOW - Basic communication - Slave
   Date: 26th September 2017
   Author: Arvind Ravulavaru <https://github.com/arvindr21>
   Purpose: ESPNow Communication between a Master ESP32 and a Slave ESP32
   Description: This sketch consists of the code for the Slave module.
   Resources: (A bit outdated)
   a. https://espressif.com/sites/default/files/documentation/esp-now_user_guide_en.pdf
   b. http://www.esploradores.com/practica-6-conexion-esp-now/

   << This Device Slave >>

   Flow: Master
   Step 1 : ESPNow Init on Master and set it in STA mode
   Step 2 : Start scanning for Slave ESP32 (we have added a prefix of `slave` to the SSID of slave for an easy setup)
   Step 3 : Once found, add Slave as peer
   Step 4 : Register for send callback
   Step 5 : Start Transmitting data from Master to Slave

   Flow: Slave
   Step 1 : ESPNow Init on Slave
   Step 2 : Update the SSID of Slave with a prefix of `slave`
   Step 3 : Set Slave in AP mode
   Step 4 : Register for receive callback and wait for data
   Step 5 : Once data arrives, print it in the serial monitor

   Note: Master and Slave have been defined to easily understand the setup.
         Based on the ESPNOW API, there is no concept of Master and Slave.
         Any devices can act as master or salve.
*/

////callback function that will be executed when data is received
void OnDataRecv(const uint8_t * mac, const uint8_t *incomingData, int len) {
  memcpy(&dataIN, incomingData, sizeof(dataIN));
  
  //serial communication for debuggin purposes
  Serial.print("Bytes received: ");
  Serial.println(len);
  // Serial.print("kill switch value: ");
  // Serial.println(dataIN.killSwitch);
  // // Serial.print("Toggle: ");
  // Serial.print("LMP: ");
  // Serial.println(dataIN.left_MotPot);
  // Serial.print("RMP: ");
  // Serial.println(dataIN.right_MotPot);
  // Serial.println();

  /* Below is what I have customized for this project */

  //write killswitch value to killswitch pin
  digitalWrite(kill_PIN, dataIN.killSwitch);

// Map incoming joystick data
  L_motPot_OUT = map(dataIN.left_MotPot, 0, 4095, 0, 255); //left joystick
  R_motPot_OUT = map(dataIN.right_MotPot, 0, 4095, 0, 255); //right joystick

//write mapped joystick values to output pins
  dacWrite(L_mPOT_PIN, L_motPot_OUT); //left joystick
  dacWrite(R_mPOT_PIN, R_motPot_OUT); //right joystick


  //paired = dataIN.esp_paired; //Uncomment if including LED


} //end OnDataRecv
