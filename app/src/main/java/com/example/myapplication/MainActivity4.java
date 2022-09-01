package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * This class is used to declare the methods for the page activity_main4.xml.
 * In this class, the commands sent for manipulation of the robot are defined.
 */
public class MainActivity4 extends AppCompatActivity {
    //declare the variables to be used for the buttons
    private ImageButton largeDown; //button used to move the end effector tool down: large
    private ImageButton largeUp; //button used to move the end effector tool up: large
    private ImageButton fineDown; //button used to move the end effector tool down: fine
    private ImageButton fineUp; //button used to move the end effector tool up: fine
    private ImageButton extraFineDown; //button used to move the end effector tool down: extra fine
    private ImageButton extraFineUp; //button used to move the end effector tool up: extra fine
    private Button fw1; //button used to send command for forward translational motion along axis 1
    private Button fw2; //button used to send command for forward translational motion along axis 2
    private Button fw3; //button used to send command for forward translational motion along axis 3
    private Button bw1; //button used to send command for backward translational motion along axis 1
    private Button bw2; //button used to send command for backward translational motion along axis 2
    private Button bw3; //button used to send command for backward translational motion along axis 3
    private Button circular; //button used to send command for circular manipulation
    private Button rectangular; //button used to send command for rectangular manipulation
    private Button addNew; //button used to transition to MainActivity6
    private BluetoothSocket bs; //bluetooth socket
    //variables declared for use of the bluetooth socket
    private OutputStream out;
    private Socket sock;
    private DataOutputStream dout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the page to activity_main4
        setContentView(R.layout.activity_main4);

        largeDown = (ImageButton) findViewById(R.id.largeDown); //button used to move the end effector tool down: large
        largeUp = (ImageButton) findViewById(R.id.largeup); //button used to move the end effector tool up: large
        fineDown = (ImageButton) findViewById(R.id.fineDown); //button used to move the end effector tool down: fine
        fineUp = (ImageButton) findViewById(R.id.fineUp); //button used to move the end effector tool up: fine
        extraFineDown = (ImageButton) findViewById(R.id.extraFineDown); //button used to move the end effector tool down: extra fine
        extraFineUp = (ImageButton) findViewById(R.id.extraFineUp); //button used to move the end effector tool up: extra fine
        fw1 = (Button) findViewById(R.id.fw1); //button used to send command for forward translational motion along axis 1
        fw2 = (Button) findViewById(R.id.fw2); //button used to send command for forward translational motion along axis 2
        fw3 = (Button) findViewById(R.id.fw3); //button used to send command for forward translational motion along axis 3
        bw1 = (Button) findViewById(R.id.bw1); //button used to send command for backward translational motion along axis 1
        bw2 = (Button) findViewById(R.id.bw2); //button used to send command for backward translational motion along axis 2
        bw3 = (Button) findViewById(R.id.bw3); //button used to send command for backward translational motion along axis 3
        circular = (Button) findViewById(R.id.circular); //button used to send command for circular manipulation
        rectangular = (Button) findViewById(R.id.rectangular); //button used to send command for rectangular manipulation
        addNew = (Button) findViewById(R.id.addNew); //button used to transition to MainActivity6

        //when the largeDown button is tapped
        largeDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //large down call command
                String message="27";
                send(message);

            }
        });

        //when the largeUp button is tapped
        largeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //large up call command
                String message="28";
                send(message);

            }
        });

        //when the fineDown button is tapped
        fineDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fine down call command
                String message="29";
                send(message);

            }
        });

        //when the fineUp button is tapped
        fineUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fine up call command
                String message="30";
                send(message);

            }
        });

        //when the extraFineDown button is tapped
        extraFineDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //extra fine down call command
                String message="31";
                send(message);

            }
        });

        //when the extraFineUp button is tapped
        extraFineUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //extra fine up call command
                String message="32";
                send(message);

            }
        });

        //when the fw1 button is tapped
        fw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //forward 1 action command
                String message="21";
                send(message);

            }
        });

        //when the fw2 button is tapped
        fw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //forward 2 action command
                String message="23";
                send(message);

            }
        });

        //when the fw3 button is tapped
        fw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //forward 3 action command
                String message="25";
                send(message);

            }
        });

        //when the bw1 button is tapped
        bw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //backward 1 call command
                String message="22";
                send(message);

            }
        });

        //when the bw2 button is tapped
        bw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //backward 2 call command
                String message="24";
                send(message);

            }
        });

        //when the bw3 button is tapped
        bw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //backward 3 call command
                String message="26";
                send(message);

            }
        });

        //when the circular button is tapped
        circular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //circular manipulation command
                String message="33";
                send(message);

            }
        });

        //when the rectangular button is tapped
        rectangular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //rectangular manipulation command
                String message="34";
                send(message);
            }
        });

        //when the addNew button is tapped
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add new transition
                //open MainActivity6
                Intent act5 = new Intent(MainActivity4.this, MainActivity6.class);
                startActivity(act5);

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