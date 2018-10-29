package fr.formation.visionneuse;

import android.content.Loader;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public abstract class MainActivity extends AppCompatActivity implements SensorEventListener {

    ImageView ivPhoto;
    Sensor sensor;
    SensorManager sm;

    int[] images = {R.drawable.manga_girl2, R.drawable.manga_girl3, R.drawable.manga_girl4, R.drawable.manga_girl5, R.drawable.manga_girl6, R.drawable.manga_girl7};
    int noImage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivPhoto = findViewById(R.id.ivPhoto);
        ivPhoto.setImageResource(images[noImage]);

        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        float x;
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = event.values[0];
            if (noImage == images.length-1){
                noImage = 0;
                ivPhoto.setImageResource(images[noImage]);
            } else {
                noImage += 1;
                ivPhoto.setImageResource(images[noImage]);
            }
        }
    }

    public void next(View view) {
        if (noImage == images.length-1){
            noImage = 0;
            ivPhoto.setImageResource(images[noImage]);
        } else {
            noImage += 1;
            ivPhoto.setImageResource(images[noImage]);
        }
    }

    public void foward(View view) {
        if (noImage > 0) {
            noImage --;
            ivPhoto.setImageResource(images[noImage]);
        }
    }
}
