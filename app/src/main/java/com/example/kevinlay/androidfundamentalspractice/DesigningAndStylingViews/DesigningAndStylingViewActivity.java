package com.example.kevinlay.androidfundamentalspractice.DesigningAndStylingViews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kevinlay.androidfundamentalspractice.R;
import com.example.kevinlay.androidfundamentalspractice.Structure.PermissionsActivity;
import com.example.kevinlay.androidfundamentalspractice.Structure.ScreenRotationActivity;

public class DesigningAndStylingViewActivity extends AppCompatActivity {

    private Button bEnterDrawables, bEnterAnimations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designing_and_styling_view);


        bEnterDrawables = (Button) findViewById(R.id.bEnterDrawables);
        bEnterDrawables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DrawableActivity.class);
                startActivity(i);
            }
        });

        bEnterAnimations = (Button) findViewById(R.id.bEnterAnimations);
        bEnterAnimations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AnimationActivity.class);
                startActivity(i);
            }
        });

    }
}
