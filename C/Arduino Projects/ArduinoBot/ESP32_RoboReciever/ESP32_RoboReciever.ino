/****************************************************************************
  Main File    : ESP32_RoboReciever.ino
  Source Files : InitESPNow.ino, OnDataRecv.ino, configDeviceAP.ino
  Libraries    : esp_now.h, Wifi.h
  Description  : This program uses code written by Arvind Ravulavaru
                 and has been customized for my particular project.
                 This program allows the ESP-32 it is uploaded onto,
                 connect to a MASTER ESP-32 utilizing the ESPNow
                 protocol that was originally developed by espressif.
                 ESPNow allows 2 or more ESP-32s or ESP-8226s communicate
                 over WI-FI. In this particular case, this ESP-32 is
                 configured in AP mode. After ESPNow is initialized,
                 It waits to receive data from the MASTER ESP-32. 
                 The data it is receiving is the value of the killswitch
                 and the values of the left and right joysticks. After 
                 this data is received, it written to the corresponding 
                 ouputs pins. The output pins are wired to an arduino uno
                 via jumper wires. The uno controls the Robot.Some code
                 has been written to incorporate an LED which would
                 act as way to tell if the two devices are paired or not.
  Author       : Cody Mott
  Date         : 05/14/2022
****************************************************************************/

/*
  This program was copied and custimized using code uploaded on github by the user
  youmebangbang. That code can be found at the following web address 
  https://github.com/youmebangbang/bugbot. The person who uploaded this code
  failed to give credit to the person who originally wrote the ESPNow code, so I have.
  Their header can be found at the bottom of this tab and at the top of every other tab.
*/

//GLOBAL VARIABLES AND DEFINITIONS BELOW

//Include libraries
#include <WiFi.h>
#include <esp_now.h>

// custom definitions
#define CHANNEL 1

// define pins
byte kill_PIN = 21;    //kill switch pin
byte L_mPOT_PIN = 25;  // right motor voltage control potentiometer pin
byte R_mPOT_PIN = 26;  // left motor voltage control potentiometer pin
//byte LED_PIN = 18; //uncomment if including LED

//allows the inital state of the LED to be off
//bool paired = false;  //uncomment if using LED

//create structure that contains data to be recieved
typedef struct dataIN_Struct {
  bool killSwitch;  //kill switch input
  int left_MotPot; //left joystick input
  int right_MotPot; //right joystick input
  //bool esp_paired; /uncomment if including LED
} dataIN_Struct;

//create variable for structure
dataIN_Struct dataIN;

//intialize output variables
int L_motPot_OUT; //left joystick output
int R_motPot_OUT; //right joystick output

void setup() {  //start setup

  // begin serial comunication
  Serial.begin(115200);

  //set Pin Modes
  pinMode(kill_PIN, OUTPUT);
  pinMode(L_mPOT_PIN, OUTPUT);
  pinMode(R_mPOT_PIN, OUTPUT);
  //pinMode(LED_PIN, OUTPUT); // uncomment if using LED

//Set device in AP mode to begin with
  WiFi.mode(WIFI_AP);

  // configure device AP mode
  configDeviceAP();

  // This is the mac address of the Slave in AP Mode
  Serial.print("AP MAC: ");
  Serial.println(WiFi.softAPmacAddress());

  // Initiallize ESPNow with a fallback logic
  InitESPNow();

  //register callback function
  esp_now_register_recv_cb(OnDataRecv);

}  // end setup


void loop() {  //start loop

  /* UNCOMMENT IF USING LED TO SHOW THAT THE DEVICES ARE PAIRED

 digitalWrite(LED_PIN,paired);

delay(500);

paired=false;
digitalWrite(LED_PIN,paired);

delay(500);

*/

}  //end loop

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