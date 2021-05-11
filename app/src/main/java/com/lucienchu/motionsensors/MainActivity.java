package com.lucienchu.motionsensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button accBtn, linearBtn, ambientBtn, turnedBtn, rotationBtn;

    public static final int SENSOR_TYPE_ACC = 0;
    public static final int SENSOR_TYPE_LINEAR_ACC = 1;
    public static final int SENSOR_TYPE_AMBIENT = 2;
    public static final int SENSOR_TYPE_TURNED = 3;
    public static final int SENSOR_TYPE_ROTATION =4 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.accBtn = findViewById(R.id.acc_btn);
        this.linearBtn = findViewById(R.id.linear_btn);
        this.ambientBtn = findViewById(R.id.ambient_btn);
        this.turnedBtn = findViewById(R.id.turned_btn);
        this.rotationBtn = findViewById(R.id.rotation_btn);
    }


    public void showActivity(View button) {
        int btnId = button.getId();
        Intent temp = new Intent(this, AccActivity.class);if(btnId == this.accBtn.getId()) {
            temp.putExtra("SENSOR_TYPE", SENSOR_TYPE_ACC);

        }else if(btnId == this.linearBtn.getId()) {
            temp.putExtra("SENSOR_TYPE", SENSOR_TYPE_LINEAR_ACC);

        }else if(btnId == this.ambientBtn.getId()) {
            temp.putExtra("SENSOR_TYPE", SENSOR_TYPE_AMBIENT);

        }else if(btnId == this.turnedBtn.getId()) {
            temp.putExtra("SENSOR_TYPE", SENSOR_TYPE_TURNED);

        }else if (btnId == this.rotationBtn.getId()) {
            temp.putExtra("SENSOR_TYPE", SENSOR_TYPE_ROTATION);
        }
        startActivity(temp);
    }
}