package com.example.aa.customviewdemo;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConcentricCircles concentricCircles = new ConcentricCircles(this);
        concentricCircles.setCenter(new Pair<>(400, 500));
        concentricCircles.setRadius(new ArrayList<>(Arrays.asList(50, 150, 250, 350)));
        concentricCircles.setPoints(new ArrayList<>(Arrays.asList(
                new Pair<>(450, 500),
                new Pair<>(400, 350),
                new Pair<>(150, 500),
                new Pair<>(400, 850)
        )));

        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.container);
        constraintLayout.addView(concentricCircles);
    }
}
