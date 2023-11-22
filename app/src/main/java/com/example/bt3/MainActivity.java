package com.example.bt3;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button buttonON;
    Button buttonOFF;
    BluetoothAdapter myBluetoothAdapter;
    Intent btEnablingIntent;
    int requestCodeForEnable;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonON = (Button) findViewById(R.id.btON);
        buttonOFF = (Button) findViewById(R.id.btOFF);
        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        btEnablingIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        requestCodeForEnable =1;


        bluetoothONMethod();
        bluetoothOFFMethod();
    }

    private void bluetoothOFFMethod(){
        buttonOFF.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (myBluetoothAdapter.isEnabled())
                {
                    myBluetoothAdapter.disable();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == requestCodeForEnable){
            if (resultCode ==RESULT_OK){
                Toast.makeText(getApplicationContext(),"bt ok",Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED)
            {
                Toast.makeText(getApplicationContext(),"bt not ok",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void bluetoothONMethod(){
        buttonON.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(myBluetoothAdapter==null){
                    Toast.makeText(getApplicationContext(),"bt marche pas lol",Toast.LENGTH_LONG).show();
                }else
                {
                    if(!myBluetoothAdapter.isEnabled()){
                        startActivityForResult(btEnablingIntent,requestCodeForEnable);
                    }
                }
            }
        }
        ));
    }

}
