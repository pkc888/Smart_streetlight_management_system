# Smart_streetlight_management_system
This is an IOT project where LED streetlights are monitored and controlled with the help of sensors(LDR , IR/PIR sensors) connected to micro-controller (here Node MCU) and an Android Application. The code for micro-controller (node MCU) is written in Arduino IDE.

![image](https://user-images.githubusercontent.com/44221142/214034965-5fb096ff-49c1-4c27-b989-500bbfea5b40.png)

LDR senses the intensity of sunlight which is falling on it and passes this information to the Microcontroller Node MCU. Then Node MCU will process this value and compare this value with the predefined threshold value. If the sensed value by LDR is less than predefined threshold then Node MCU switches the relay to a closed switch and the LED will glow, when the sensed value is greater than the threshold then the Node MCU switches the relay to off state and the LED will turn off automatically.

If a person, object/vehicle appears nearby the IR sensors, it will capture the signals and turn ON the particular street lights as well as LEDs of one preceding and succeeding streetlights. This will be useful for the person driving the vehicle to be able to see further objects.
An android application will give user access to control the functionality of street lights from any remote location though Internet. User can choose which type of setup or functionality will be suitable for the particular area and roads according to surrounding.

![image](https://user-images.githubusercontent.com/44221142/214035142-a39224ad-1aef-42ea-95bf-056b860bf7ed.png)


An android application will give user access to control the functionality of street lights from any remote location though Internet

## This system can be controlled in two ways manually and automatically

### In manual mode the LEDs can be directly turn ON & OFF through mobile application.

![image](https://user-images.githubusercontent.com/44221142/214035282-24e8fe64-b93f-4b5f-bb46-406829885210.png)

### The automatic mode is divided into certain areas where street lights are supposed to be installed.

## Residential areas: 
In this mode, when the LDR sensor detects low light in the evening it will glow with full intensity until midnight. After midnight the intensity of LEDs will start decreasing gradually with time and the IR sensor will activate and start to detect objects/vehicles. If it detects any vehicle, it will trigger the LED to glow with maximum intensity and after few second it will back to previous state

## Rural area: 
In this mode, when LDR sensor detects low light in the surrounding it will glow with low intensity as vehicle movements are very less there as compared to urban cities at night. The led will glow with high intensity only if IR detects any vehicle movement.

## Industrial areas: 
in this mode, LDR sensors will be activated all the night due to continuous mobility of vehicles in these areas throughout the night.

![image](https://user-images.githubusercontent.com/44221142/214035460-f13b149b-7eed-4ff0-9107-39a53689b5b4.png)
![image](https://user-images.githubusercontent.com/44221142/214035476-7e663b73-7f88-49e7-afc5-93f19dc9e60c.png)

All user settings will be sent to the database. The database used is firebase database by Google. 

![image](https://user-images.githubusercontent.com/44221142/214035605-313d84d0-f4ba-4fd9-8a78-8c0a1fb0d2e5.png)

