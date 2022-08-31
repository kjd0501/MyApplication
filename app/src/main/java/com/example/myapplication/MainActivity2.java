package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * This class is used to declare the methods for the page activity_main2.xml.
 * In this class, the commands sent for manipulation of the robot are defined.
 */
public class MainActivity2 extends AppCompatActivity {
    //declare the variables to be used for the buttons
    private Button drillMode; //button used to transition to drill mode manipulation page
    private Button suctionCups; //button used to transition to suction cup manipulation page
    private Button goToHomeOrientation; //button used to send command to go to home orientation
    private Button raiseEndEffector; //button used to send command to raise end effector tool
    private Button fw1; //button used to send command for forward translational motion along axis 1
    private Button fw2; //button used to send command for forward translational motion along axis 2
    private Button fw3; //button used to send command for forward translational motion along axis 3
    private Button bw1; //button used to send command for backward translational motion along axis 1
    private Button bw2; //button used to send command for backward translational motion along axis 2
    private Button bw3; //button used to send command for backward translational motion along axis 3
    private FloatingActionButton clk1; //button used to send command for clockwise rotation about axis 1
    private FloatingActionButton aclk1; //button used to send command for anticlockwise rotation about axis 1
    private FloatingActionButton clk2; //button used to send command for clockwise rotation about axis 2
    private FloatingActionButton aclk2; //button used to send command for anticlockwise rotation about axis 2
    private FloatingActionButton clk3; //button used to send command for clockwise rotation about axis 3
    private FloatingActionButton aclk3; //button used to send command for anticlockwise rotation about axis 3
    int rot; //variable used to toggle visibility of buttons for rotation about an axis
    private BluetoothSocket bs; //bluetooth socket
    //variables declared for use of the bluetooth socket
    private OutputStream out;
    private Socket sock;
    private DataOutputStream dout;
    //variables defined to enable double-tap
    private long lastTouchTime = 0;
    private long currentTouchTime = 0;

    /**
     * This method is used to initialise the buttons available on the page.
     * It is also used to define the actions triggered for the buttons when they
     * are tapped or double tapped.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the page to activity_main2
        setContentView(R.layout.activity_main2);

        //initialise the buttons
        //button used to transition to drill mode manipulation page
        drillMode = (Button) findViewById(R.id.drillMode);
        //button used to transition to suction cup manipulation page
        suctionCups = (Button) findViewById(R.id.suctionCups);
        //button used to send command to go to home orientation
        goToHomeOrientation = (Button) findViewById(R.id.Home);
        //button used to send command to raise end effector tool
        raiseEndEffector = (Button) findViewById(R.id.raiseEE);
        //button used to send command for forward translational motion along axis 1
        fw1 = (Button) findViewById(R.id.fw1);
        //button used to send command for forward translational motion along axis 2
        fw2 = (Button) findViewById(R.id.fw2);
        //button used to send command for forward translational motion along axis 3
        fw3 = (Button) findViewById(R.id.fw3);
        //button used to send command for backward translational motion along axis 1
        bw1 = (Button) findViewById(R.id.bw1);
        //button used to send command for backward translational motion along axis 2
        bw2 = (Button) findViewById(R.id.bw2);
        //button used to send command for backward translational motion along axis 3
        bw3 = (Button) findViewById(R.id.bw3);
        //button used to send command for clockwise rotation about axis 1
        clk1 = (FloatingActionButton) findViewById(R.id.clk1);
        //button used to send command for anticlockwise rotation about axis 1
        aclk1 = (FloatingActionButton) findViewById(R.id.aclk1);
        //button used to send command for clockwise rotation about axis 2
        clk2 = (FloatingActionButton) findViewById(R.id.clk2);
        //button used to send command for anticlockwise rotation about axis 2
        aclk2 = (FloatingActionButton) findViewById(R.id.aclk2);
        //button used to send command for clockwise rotation about axis 3
        clk3 = (FloatingActionButton) findViewById(R.id.clk3);
        //button used to send command for anticlockwise rotation about axis 3
        aclk3 = (FloatingActionButton) findViewById(R.id.aclk3);
        //variable used to toggle visibility of buttons for rotation about an axis
        rot=0;

        //set the initial visibility of the buttons for rotational movement to gone
        clk1.setVisibility(View.GONE);
        aclk1.setVisibility(View.GONE);
        clk2.setVisibility(View.GONE);
        aclk2.setVisibility(View.GONE);
        clk3.setVisibility(View.GONE);
        aclk3.setVisibility(View.GONE);

        //when the clk1 button is tapped
        clk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clockwise rotation 1 call command
                String message="7";
                send(message);
            }
        });

        //when the aclk1 button is tapped
        aclk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //counter clockwise rotation 1 call command
                String message="8";
                send(message);
            }
        });

        //when the clk2 button is tapped
        clk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clockwise rotation 2 call command
                String message="9";
                send(message);
            }
        });

        //when the aclk2 button is tapped
        aclk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //counter clockwise rotation 2 call command
                String message="10";
                send(message);
            }
        });

        //when the clk3 button is tapped
        clk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clockwise rotation 3 call command
                String message="11";
                send(message);
            }
        });

        //when the aclk3 button is tapped
        aclk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //counter clockwise rotation 3 call command
                String message="12";
                send(message);
            }
        });

        //when the button to transition to drill mode page is tapped
        drillMode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //transition to page for drill mode manipulation page
                //open MainActivity4
                Intent act4 = new Intent(MainActivity2.this, MainActivity4.class);
                // start the activity connect to the specified class
                startActivity(act4);
            }
        });

        //when the button to transition to suction cup manipulation page is tapped
        suctionCups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //transition to page for suction cup manipulation page
                //open MainActivity3
                Intent act3 = new Intent(MainActivity2.this, MainActivity3.class);
                // start the activity connect to the specified class
                startActivity(act3);
            }
        });

        //when the button to set robot to home orientation is tapped
        goToHomeOrientation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //home orientation call command
                String message="13";
                send(message);
            }
        });

        //when the button to raise the end effector tool is tapped
        raiseEndEffector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //raise end effector call command
                String message="14";
                send(message);
            }
        });

        //when the fw1 button is single or double-tapped
        fw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //variables used to check for single or double tap
                lastTouchTime = currentTouchTime;
                currentTouchTime = System.currentTimeMillis();

                //if the button is double-tapped
                if (currentTouchTime - lastTouchTime < 250) {
                    //check the variable used for toggling visibility
                    if(rot<1) {
                        //set the toggle variable to 1
                        rot=1;
                        //make the rotation buttons visible
                        clk1.setVisibility(View.VISIBLE);
                        aclk1.setVisibility(View.VISIBLE);
                        fw2.setVisibility(View.GONE);
                        fw3.setVisibility(View.GONE);
                        bw2.setVisibility(View.GONE);
                        bw3.setVisibility(View.GONE);
                        bw1.setVisibility(View.GONE);
                    }
                    else{
                        //set the toggle variable to 0
                        rot=0;
                        //make the rotation variables invisible
                        clk1.setVisibility(View.GONE);
                        aclk1.setVisibility(View.GONE);
                        fw2.setVisibility(View.VISIBLE);
                        fw3.setVisibility(View.VISIBLE);
                        bw2.setVisibility(View.VISIBLE);
                        bw3.setVisibility(View.VISIBLE);
                        bw1.setVisibility(View.VISIBLE);
                    }

                    //reset the timer for checking for double tap
                    lastTouchTime = 0;
                    currentTouchTime = 0;

                } else {
                    //forward 1 call command
                    String message="1";
                    send(message);
                }
            }
        });

        //when the fw2 button is single or double-tapped
        fw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //variables used to check for single or double tap
                lastTouchTime = currentTouchTime;
                currentTouchTime = System.currentTimeMillis();

                //if the button is double-tapped
                if (currentTouchTime - lastTouchTime < 250) {
                    //check the variable used for toggling visibility
                    if(rot<1) {
                        //set the toggle variable to 1
                        rot=1;
                        //make the rotation buttons visible
                        clk2.setVisibility(View.VISIBLE);
                        aclk2.setVisibility(View.VISIBLE);
                        fw1.setVisibility(View.GONE);
                        fw3.setVisibility(View.GONE);
                        bw2.setVisibility(View.GONE);
                        bw3.setVisibility(View.GONE);
                        bw1.setVisibility(View.GONE);
                    }
                    else{
                        //set the toggle variable to 0
                        rot=0;
                        //make the rotation variables invisible
                        clk2.setVisibility(View.GONE);
                        aclk2.setVisibility(View.GONE);
                        fw1.setVisibility(View.VISIBLE);
                        fw3.setVisibility(View.VISIBLE);
                        bw2.setVisibility(View.VISIBLE);
                        bw3.setVisibility(View.VISIBLE);
                        bw1.setVisibility(View.VISIBLE);
                    }

                    //reset the timer for checking for double tap
                    lastTouchTime = 0;
                    currentTouchTime = 0;

                } else {
                    //forward 2 call command
                    String message="3";
                    send(message);
                }
            }
        });

        //when the fw3 button is single or double-tapped
        fw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //variables used to check for single or double tap
                lastTouchTime = currentTouchTime;
                currentTouchTime = System.currentTimeMillis();

                //if the button is double-tapped
                if (currentTouchTime - lastTouchTime < 250) {
                    //check the variable used for toggling visibility
                    if(rot<1) {
                        //set the toggle variable to 1
                        rot=1;
                        //make the rotation buttons visible
                        clk3.setVisibility(View.VISIBLE);
                        aclk3.setVisibility(View.VISIBLE);
                        fw2.setVisibility(View.GONE);
                        fw1.setVisibility(View.GONE);
                        bw2.setVisibility(View.GONE);
                        bw3.setVisibility(View.GONE);
                        bw1.setVisibility(View.GONE);
                    }
                    else{
                        //set the toggle variable to 0
                        rot=0;
                        //make the rotation variables invisible
                        clk3.setVisibility(View.GONE);
                        aclk3.setVisibility(View.GONE);
                        fw2.setVisibility(View.VISIBLE);
                        fw1.setVisibility(View.VISIBLE);
                        bw2.setVisibility(View.VISIBLE);
                        bw3.setVisibility(View.VISIBLE);
                        bw1.setVisibility(View.VISIBLE);
                    }

                    //reset the timer for checking for double tap
                    lastTouchTime = 0;
                    currentTouchTime = 0;

                } else {
                    //forward 3 call command
                    String message="5";
                    send(message);
                }
            }
        });

        //when the bw1 button is tapped
        bw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //backward 1 call command
                String message="2";
                send(message);
            }
        });

        //when the bw2 button is tapped
        bw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //backward 2 call command
                String message="4";
                send(message);
            }
        });

        //when the bw3 button is tapped
        bw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //backward 3 call command
                String message="6";
                send(message);
            }
        });

    }

    /**
     * This method is used to send the command to the robot via the bluetooth socket.
     * @param message is the command to be sent
     */
    public void send(String message) {
        //variables defined to use bluetooth socket
        bs=Device_NetworkList.socket;
        sock=MainActivity.s;
        dout=MainActivity.dout;
        out=Device_NetworkList.output;
        //convert message to bytes
        byte[] toSend = message.getBytes();
        try {
            //check if the bluetooth socket is connected
            if(bs.isConnected()){
                //send the message using the socketg
                out.write(toSend);
            }
            else{
                dout.writeUTF(message);
                dout.flush();

                //transition to the main landing page to connect to the robot.
                Intent A1 = new Intent(this, MainActivity.class);
                startActivity(A1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to print the message msg on the screen.
     * @param msg is the message to be printed
     */
    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}

