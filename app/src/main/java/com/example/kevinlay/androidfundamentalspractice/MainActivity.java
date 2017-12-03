package com.example.kevinlay.androidfundamentalspractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kevinlay.androidfundamentalspractice.DesigningAndStylingViews.DesigningAndStylingViewActivity;
import com.example.kevinlay.androidfundamentalspractice.Structure.StructureMainActivity;
import com.example.kevinlay.androidfundamentalspractice.ViewsAndLayouts.ViewsAndLayoutsActivity;

public class MainActivity extends AppCompatActivity {

    private Button bEnterStructureActivity, bEnterVlActivity, bEnterDesignActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bEnterStructureActivity = (Button) findViewById(R.id.bEnterStructureActivity);
        bEnterStructureActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), StructureMainActivity.class);
                startActivity(i);
            }
        });
        bEnterVlActivity = (Button) findViewById(R.id.bEnterViewsAndLayoutActivity);
        bEnterVlActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewsAndLayoutsActivity.class);
                startActivity(i);
            }
        });
        bEnterDesignActivity = (Button) findViewById(R.id.bEnterDesignActivity);
        bEnterDesignActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DesigningAndStylingViewActivity.class);
                startActivity(i);
            }
        });
    }
}
