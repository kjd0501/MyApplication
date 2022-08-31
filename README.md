# Remote Control of Robot using Android App
This app is used to manipulate a robot to perform certain tasks. It supports translation and rotational movement of the robot and linear manipulation of the end effector.
This app allows communication using Wi-Fi or bluetooth. 
## Landing Page
### Page Layout: [activity_main.xml](https://github.com/kjd0501/MyApplication/blob/main/app/src/main/res/layout/activity_main.xml)
This page consists of two main buttons: Wi-Fi and Bluetooth. These refer to the method used to connect to the robot. 
When bluetooth is clicked, [activity_device_network_list.xml](https://github.com/kjd0501/MyApplication/blob/main/app/src/main/res/layout/activity_device_network_list.xml) is opened.
## Main Manipulation Page
### Page Layout: [activity_main2.xml](https://github.com/kjd0501/MyApplication/blob/main/app/src/main/res/layout/activity_main2.xml)
This page consists of all the main controls required for movement of the robot. 
   * fw1 and bw1: refer to translational movement in first axis
   * fw2 and bw2: refer to translational movement in second axis
   * fw3 and bw3: refer to translational movement in third axis
   
By double tapping fw1, fw2 or fw3, the user will be able to access the commands for rotational movement in the selected axis. 
There are two buttons available to go to default parameters: One of them is to return to the home orientation and the other is to completely raise the end effector tool.
