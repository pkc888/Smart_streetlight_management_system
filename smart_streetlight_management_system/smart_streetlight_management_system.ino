#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>


// Set these to run example.
#define FIREBASE_HOST "pankaj-5d37e.firebaseio.com"
#define FIREBASE_AUTH "7iF5J3LJyx1VLOIysqIvRMIcEyfEHszs94DVehTf"
#define WIFI_SSID "realme 7i"
#define WIFI_PASSWORD "123456778"

const int ledPin = D1;
const int ldrPin = A0;
const int irPin = D2;
int irState = 1;
int brightness=0;
int fadeAmount=5;

   
void setup() {
  
  Serial.begin(9600);

  pinMode(ledPin, OUTPUT);
  pinMode(ldrPin, INPUT);
  pinMode(irPin, INPUT);

  // connect to wifi.
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }

  Serial.println();
  Serial.print("connected: ");
  Serial.print("NodeMCU IP Address : ");
  Serial.println(WiFi.localIP());
  
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  delay(300);

  if (Firebase.failed()) { 
  Serial.println(Firebase.error()); 
  } 

  //SETTING VARIABLE IN DATABASE WITH INITAIAL VALUES
 
  Firebase.setInt("ldrstatuss/value",0);
  Firebase.setInt("irstatee/value",0);
  Firebase.setInt("valuee",5);        // setting initial value as 5


} 



//void firebasereconnect(){ 
//  Serial.println("Trying to reconnect"); 
//  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH); 
//  
//  } 


  int ldrst;
  int irst;
  
  int ldrstat;
  int irstat;
  int value; 

  
void loop() {

  
//  if (Firebase.failed()) {
//      Serial.print("setting /number failed:");
//      Serial.println(Firebase.error());  
//      return;
//  }
//  delay(1000);


  
  //READING SENSORS DATA
  int ldrStatus = analogRead(ldrPin);
  int irState = digitalRead(irPin);
  
  //ASSIGHNING TO VARIABLES
  ldrst=int(ldrStatus);
  irst=int(irState);

  //SENDING DATA TO FIREBASE 
  Firebase.setInt("ldrstatuss/value",ldrStatus);
  Firebase.setInt("irstatee/value",irState);

  // RECEIVING DATA FROM FIREBASE
  ldrstat=Firebase.getInt("ldrstatuss/value");
  irstat=Firebase.getInt("iirstatee/value");
  int value=Firebase.getInt("valuee");
  delay(1000);


 //manually CONDITION 1:-

 if(value==1)
   {

      int brightness=1023;
      analogWrite(ledPin,brightness);
      Serial.println("Manually turned ON");
   }


//manually CONDITION 2:-

else if (value==0)
   {
      int brightness=0;
      analogWrite(ledPin,brightness);
      Serial.println("manually turned OFF");
   }



// automatically through sensors Condition where street lights are installed in residential areas:-

 else if(value==2)
   {
      
       //before midnight
      if (ldrstat<=200)
         {
            brightness=255;
            analogWrite(ledPin,brightness);
            //digitalWrite(ledPin,HIGH);
            Serial.println(brightness);
            Serial.println(ldrstat);
            delay(60000);  // here delay of 1 minute  for checking 
         }
      else
         {
            brightness=0;
            analogWrite(ledPin,brightness);
            //digitalWrite(ledPin,LOW);
            Serial.println(brightness);
            Serial.println(ldrstat);
            delay(60000);
         }
          
      //after midnight lights begins to fade until detects any object

      if (ldrstat<300)
         {
            if (irstat ==1) 
              {
                  int brightness=1023;
                  analogWrite(ledPin,brightness);
                  brightness=brightness-fadeAmount;
                  if (brightness==0||brightness==1023)
                  {
                    fadeAmount=-fadeAmount;
                    }
                    delay(500);
                  Serial.println(brightness);
                  Serial.println("LDR sensor activated");
          
              } 
            else
              {
                   int brightness=1023;
                   analogWrite(ledPin,brightness);
                   Serial.println("ir sensor activated");
                   Serial.println("LDR sensor activated");
              }
          }
      
        else
          {
            int brightness=0;
            analogWrite(ledPin,brightness);
          }
    
    }



// automatically through sensors Condition where street lights are installed in rural areas:-
// due to low mobility in these area here lights will remain dim unless it detect any object whole night

 else if(value==3)
    {
       if (ldrstat<300)
          {
             if (irstat ==1) 
               {

                  brightness=255;
                  analogWrite(ledPin,brightness);
                  Serial.println(brightness);
                  Serial.println("LDR sensor activated");
               }
             else
               {
                  brightness=1023;
                  analogWrite(ledPin,brightness);
                  Serial.println(brightness);
                  Serial.println("LDR sensor activated");
                  Serial.println("ir sensor activated");
               }
          }
       else
          {
              brightness=0;
              analogWrite(ledPin,brightness);
              Serial.println(ldrstat);
          }
     }
      




// automatically through sensors Condition where street lights are installed in industrial areas:-
// here lights will be remain on with full intensity through out the dark period.

  else if(value==4)
    {
      if (ldrstat<300)
        {
          
          brightness=1023;
          analogWrite(ledPin,brightness);
          Serial.println("ldr sensor activated");
          Serial.println(ldrstat);
        }
      else{
          brightness=0;
          analogWrite(ledPin,brightness);
        }
    }
}
    
