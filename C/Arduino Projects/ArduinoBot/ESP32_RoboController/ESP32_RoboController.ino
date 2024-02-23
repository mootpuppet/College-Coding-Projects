/*****************************************************************
  Main File    : ESP32_RoboController.ino
  Source Files : InitESPNow.ino, OnDataSent.ino, ScanForSlave.ino,
                 deletePeer.ino,manageSlave.ino, sendData.ino
  Libraries    : esp_now.h, Wifi.h
  Description  : This program uses code written by Arvind Ravulavaru
                 and has been customized for my particular project.
                 Using ESPNow protocol, this program searches for 
                 and pairs a slave ESP-32 with the specified channel.
                 Once paired, this ESP-32, which is the master, will
                 read the input values of the joysticks and
                 the killswitch, and send those values to the paired
                 slave.
                 1) libraries are included
                 2) Custome definitions are defined
                 3) Input pins are defined
                 4) Structure for outgoing data is created
                 5) Variable created to define structure
                 6) Device to be paired is named slave
                 7) Pinmodes are defined
                 8) Serial communications begines
                 9) Device set up as a WiFi station
                 10) ESPNow initialized
                 11) Callback function registered
                 12) Scan for Slave
                 13) Continously read and send data
  Author       : Cody Mott
  Date         : 05/14/2022
*****************************************************************/

/*
  This program was copied and custimized using code uploaded on github by the user
  youmebangbang. That code can be found at the following web address 
  https://github.com/youmebangbang/bugbot. The person who uploaded this code
  failed to give credit to the person who originally wrote the ESPNow code, so I have.
  Their header can be found at the bottom of this tab and at the top of every other tab.
*/

//included libraries
#include <esp_now.h>
#include <WiFi.h>

//Uncomnent for lcd display
//#include <Wire.h>

//custom definitions
#define CHANNEL 1
#define PRINTSCANRESULTS 0
#define DELETEBEFOREPAIR 0

//define pins
byte kill_PIN = 21;    //kill switch pin
byte L_mPOT_PIN = 36;  //left joystick poteniometer pin
byte R_mPOT_PIN = 35;  //right joystick poteniometer pin


//create structure that contains data to be sent
typedef struct data_outStruct {
  bool killSwitch;   //kill switch
  int left_MotPot;   //Left Joystick
  int right_MotPot;  //Right Joystick
  //bool esp_paired; //uncomment if including LED on reciever
} data_outStruct;

//create variable for structure
data_outStruct outgoingData;

//name ESP peer
esp_now_peer_info_t slave;


void setup() {


  /*  UNCOMMENT AND CUSTOMIZE FOR LCD DISPLAY
  // Wire.begin(21, 22);
  //  display.begin(SSD1306_SWITCHCAPVCC, 0x3c);
  //  display.setTextSize(2);
  //  display.setTextColor(WHITE);
  //  display.setCursor(0, 0);
  //  display.clearDisplay();
  //  display.setRotation(2);
  //  display.println("BangBang\nController\nwith\nESP-NOW");
  //  display.display();
*/


  //Set pin modes
  pinMode(kill_PIN, INPUT_PULLDOWN);  //Kill switch
  pinMode(L_mPOT_PIN, INPUT);         //left joystick
  pinMode(R_mPOT_PIN, INPUT);         //right joystick

  //begin serial communication
  Serial.begin(115200);

  //set wifi mode
  WiFi.mode(WIFI_STA);

  //initialize espNOW
  InitESPNow();

  //register call back funtion for when data is sent
  esp_now_register_send_cb(OnDataSent);


    //scan for slave
  ScanForSlave();
  if (slave.channel == CHANNEL) {  // check if slave channel is defined
    bool isPaired = manageSlave();
    if (isPaired) {
      //      display.clearDisplay();
      //      display.setCursor(0, 0);
      Serial.println("Slave paired!");
      //      display.display();


    } else {
      Serial.println("Slave pair failed!");
    }
  } else {
    Serial.println("No slave found to process!");
  }
  delay(3000);  //pause for 3 seconds


}  // end setup

//start main program to excute continuously
void loop() {


  //assign values to outgoing data
  outgoingData.killSwitch = digitalRead(kill_PIN);     //read kill switch
  outgoingData.left_MotPot = analogRead(L_mPOT_PIN);   //read left joystick potentiometer
  outgoingData.right_MotPot = analogRead(R_mPOT_PIN);  //read right joystick potentiometer

  //send outgoing data
  sendData();

  delay(100);  //pause for 0.1 second

}  //end loop


/**
   ESPNOW - Basic communication - Master
   Date: 26th September 2017
   Author: Arvind Ravulavaru <https://github.com/arvindr21>
   Purpose: ESPNow Communication between a Master ESP32 and a Slave ESP32
   Description: This sketch consists of the code for the Master module.
   Resources: (A bit outdated)
   a. https://espressif.com/sites/default/files/documentation/esp-now_user_guide_en.pdf
   b. http://www.esploradores.com/practica-6-conexion-esp-now/

   << This Device Master >>

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