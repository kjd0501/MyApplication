package com.example.myapplication;


import static android.content.ContentValues.TAG;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.DataOutputStream;
import java.net.Socket;

/**
 * This class is used to declare the methods for the page activity_main.xml.
 * In this class, connection to the robot using wifi and bluetooth is allowed.
 */
public class MainActivity extends AppCompatActivity {
    //declare the variables to be used for the buttons
    private Button bt; //bluetooth button
    private Button wf; //wifi button

    // s & dout are public and accessible from any activity so that the bluetooth
    // connection is retained across the whole app
    public static DataOutputStream dout;
    public static Socket s;

    // declare the variables for the bluetooth adapter for connection with the robot
    BluetoothAdapter BA;
    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;

    /**
     * This method is used to initialise the buttons available on the page.
     * It is also used to define the actions triggered for the buttons when they
     * are tapped.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the page to activity_main
        setContentView(R.layout.activity_main);

        //initialise the buttons
        bt = (Button) findViewById(R.id.bluetooth);  // bluetooth button
        wf = (Button) findViewById(R.id.wifi);  // wifi button
        BA = BluetoothAdapter.getDefaultAdapter(); //bluetooth adapter

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // check if device supports bluetooth
        if (BA == null) {
            showToast("bluetooth not supported");
            finish();
            return;
        }

        // when wifi button is clicked
        wf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WifiManager wifi = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);

                // make sure wifi is turned on
                if (wifi.isWifiEnabled()){
                    //String ipAddress = Formatter.formatIpAddress(wifi.getConnectionInfo().getIpAddress());
                    //showToast(ipAddress);
                    try{
                        //create a client socket to connect to a server socket
                        //change ip address, this is just for testing
                        s = new Socket("10.248.128.125",6666);
                        dout=new DataOutputStream(s.getOutputStream());

                        // open home page
                        openActivity2();
                        //dout.flush();
                        //dout.close();
                        //s.close();
                    }catch(Exception e){
                        Log.e(TAG, "failed", e);
                        showToast("not sent");
                    }
                }
                else
                    showToast("Wifi is not enabled");   // if wifi is not on

            }
        });

        // if bluetooth button is clicked
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // turn on bluetooth
                if (!BA.isEnabled()) {
                    //showToast("Turning ON Bluetooth...");
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.BLUETOOTH_CONNECT }, REQUEST_ENABLE_BT);

                        return;
                    }
                    //request to turn on bluetooth
                    startActivityForResult(intent,REQUEST_ENABLE_BT);

                }

                //bluetooth already on, //call function openActivity2
                else{
                    openActivity2();
                }
            }
        });
    }

    /**
     * This method is used to check if bluetooth is enabled on a device.
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case REQUEST_ENABLE_BT:
                if (resultCode== RESULT_OK){
                    // bluetooth ON
                    showToast("Bluetooth is ON");
                    openActivity2();   //call function openActivity2
                }else{
                    showToast("Bluetooth is not on");
                }
                break;
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

    /**
     * This method is used to print the message msg on the screen.
     * @param msg is the message to be printed
     */
    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    /**
     * This method is used to go to Device_NetworkList Activity.
     */
    public void openActivity2() {
        Intent A2 = new Intent(this, Device_NetworkList.class);
        startActivity(A2);
    }
}









