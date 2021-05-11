package com.lucienchu.motionsensors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lucienchu.motionsensors.utils.SensorUtils;

public class AccActivity extends AppCompatActivity implements SensorUtils.OnSensorUpdateListener {

    public int sensorType;

    private TextView titleTxtView, xAxisTextView, yAxisTextView, zAxisTextView;
    private SensorUtils sensorUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc);

        this.sensorType = getIntent().getIntExtra("SENSOR_TYPE", -1);
        initTextViews();
        this.sensorUtils = new SensorUtils(this, this);
    }

    private void initTextViews() {
        this.titleTxtView = findViewById(R.id.sensorType);
        this.xAxisTextView = findViewById(R.id.xAxis);
        this.yAxisTextView = findViewById(R.id.yAxis);
        this.zAxisTextView = findViewById(R.id.zAxis);

        String sensorType = "undefined";
        switch (this.sensorType) {
            case MainActivity.SENSOR_TYPE_ACC:
                sensorType = "ACC";
                break;
            case MainActivity.SENSOR_TYPE_LINEAR_ACC:
                sensorType = "LINEAR_ACC";
                break;
            case MainActivity.SENSOR_TYPE_AMBIENT:
                sensorType = "AMBIENT";
                yAxisTextView.setVisibility(View.GONE);
                zAxisTextView.setVisibility(View.GONE);
                break;
            case MainActivity.SENSOR_TYPE_TURNED:
                sensorType = "MAGNETO & ACC COMBO";
                yAxisTextView.setVisibility(View.GONE);
                zAxisTextView.setVisibility(View.GONE);
                break;
            case MainActivity.SENSOR_TYPE_ROTATION:
                sensorType = "ROTATION VECTOR";
                break;
        }
        titleTxtView.setText(sensorType);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.sensorUtils.registerSensors();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.sensorUtils.unregisterSensors();
    }

    @Override
    public void onAmbientTemperatureChanged(float newTemp) {
        if (sensorType == MainActivity.SENSOR_TYPE_AMBIENT) {
            this.xAxisTextView.setText("" + newTemp);
        }
    }

    @Override
    public void onAccelerometerUpdated(float[] values) {
        if (sensorType == MainActivity.SENSOR_TYPE_ACC) {
            float x = values[0];
            float y = values[1];
            float z = values[2];

            this.xAxisTextView.setText("" + x);
            this.yAxisTextView.setText("" + y);
            this.zAxisTextView.setText("" + z);
        }
    }

    @Override
    public void onLinearAccelerometerUpdated(float[] values) {
        if (sensorType == MainActivity.SENSOR_TYPE_LINEAR_ACC) {
            float x = values[0];
            float y = values[1];
            float z = values[2];

            this.xAxisTextView.setText("" + x);
            this.yAxisTextView.setText("" + y);
            this.zAxisTextView.setText("" + z);
        }
    }

    @Override
    public void onDeviceTurned(float degree) {
        if (sensorType == MainActivity.SENSOR_TYPE_TURNED) {
            this.xAxisTextView.setText("" + degree);
        }
    }

    @Override
    public void onDeviceRotated(float [] values) {
        if (sensorType == MainActivity.SENSOR_TYPE_ROTATION) {
            float x = values[0];
            float y = values[1];
            float z = values[2];

            this.xAxisTextView.setText("" + x);
            this.yAxisTextView.setText("" + y);
            this.zAxisTextView.setText("" + z);
        }

    }
}