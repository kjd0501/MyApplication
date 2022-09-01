# Remote Control of Robot using Android App
This app is used to manipulate a robot to perform certain tasks. It supports translation and rotational movement of the robot and linear manipulation of the end effector.This app allows communication using Wi-Fi or bluetooth. The communication with the robot is done through using ROS. Messages are sent in the form of numbers in String Format.

## Landing Page
### Page Layout: [activity_main.xml](https://github.com/kjd0501/MyApplication/blob/main/app/src/main/res/layout/activity_main.xml)

![image](https://user-images.githubusercontent.com/107410292/187661987-bb747f12-83d6-4da2-b135-ff4b236ba0a0.png)

This page consists of two main buttons: Wi-Fi and Bluetooth. These refer to the method used to connect to the robot. 
When bluetooth is clicked, [activity_device_network_list.xml](https://github.com/kjd0501/MyApplication/blob/main/app/src/main/res/layout/activity_device_network_list.xml) is opened.

## Main Manipulation Page
### Page Layout: [activity_main2.xml](https://github.com/kjd0501/MyApplication/blob/main/app/src/main/res/layout/activity_main2.xml)

![image](https://user-images.githubusercontent.com/107410292/187662738-0a1f2f14-c9fa-4d4b-a963-59d65f4e41e8.png)
![image](https://user-images.githubusercontent.com/107410292/187663199-14f15b88-072e-4de8-94bd-8b779ec90c99.png)

This page consists of all the main controls required for movement of the robot. 
   * fw1 and bw1: refer to translational movement in first axis
   * fw2 and bw2: refer to translational movement in second axis
   * fw3 and bw3: refer to translational movement in third axis
   
By double tapping fw1, fw2 or fw3, the user will be able to access the commands for rotational movement in the selected axis. 
There are two buttons available to go to default parameters: One of them is to return to the home orientation and the other is to completely raise the end effector tool.
There are two additional buttons at the bottom of the page in order to access the pages for drill mode and suction cup manipulation.

## Suction Cup Manipulation Page
### Page Layout: [activity_main3.xml](https://github.com/kjd0501/MyApplication/blob/main/app/src/main/res/layout/activity_main3.xml)

![image](https://user-images.githubusercontent.com/107410292/187664240-97bc15a6-3dc1-4326-b045-2b2392305c70.png)

The central red button is used to toggle the central three suction cups in the robot. 
The outer orange buttons toggle one suction cup at a time which is associated with the relevant button. 
There are two additional buttons at the bottom of the page to toggle all the suction cups on or off and another to toggle all the outer suction cups on or off.

## Drill Mode Manipulation Page
### Page Layout: [activity_main4.xml](https://github.com/kjd0501/MyApplication/blob/main/app/src/main/res/layout/activity_main4.xml)

![image](https://user-images.githubusercontent.com/107410292/187672927-3db7bed8-49d8-4dec-a835-769762516c66.png)

This page consists of all the main controls required for movement of the robot. 
   * fw1 and bw1: refer to translational movement in first axis
   * fw2 and bw2: refer to translational movement in second axis
   * fw3 and bw3: refer to translational movement in third axis

The two buttons at the top of the screen are used for circular and rectangular manipulation. There are buttons at the bottom of the screen for retraction of the end effector tool.

By pressing on the Add New button, the user will be redirected to [activity_main6.xml](https://github.com/kjd0501/MyApplication/blob/main/app/src/main/res/layout/activity_main6.xml).

![image](https://user-images.githubusercontent.com/107410292/187679527-1c7a04db-acb0-44bf-98ad-1ccfad7cf488.png)

## Additional Folders
The .idea folder holds the settings for the IDE the app is being developed on.

The app folder holds the main logic for the working of the app including the xml files regarding the layouts.

The graddle/wrappers folder is used for app distribution.

## Tool and Programming Language Used

The app is built using Java for the logic and xml to hold the layout of the app. Additionally, the IDE used is Android Studio as this app is meant to be used on Android devices. The commands are sent to the robot using ROS frameworks such as topic, service, publishers and subscribers.

## Testing

So far, the app has not been tested on the robot as the ROS nodes created have not been tested. The app is successfully able to send data across a bluetooth socket as this was tested by connecting two phones to each other and sending data across it. The app has also been simulated using the emulator on Android Studio. Additonal tests are yet to be run. For workflow test when no device is connected, the body for the send method in the script needs to be commented out as there is a check to see if the app is continually connected to a device via bluetooth present within that method.
