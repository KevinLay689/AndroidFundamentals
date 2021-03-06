package com.example.kevinlay.androidfundamentalspractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kevinlay.androidfundamentalspractice.AdapterViews.AdapterViewActivity;
import com.example.kevinlay.androidfundamentalspractice.dagger.Dagger2Activity;
import com.example.kevinlay.androidfundamentalspractice.databinding.BindingActivity;
import com.example.kevinlay.androidfundamentalspractice.DataPersistence.DataPersistenceActivity;
import com.example.kevinlay.androidfundamentalspractice.DesigningAndStylingViews.DesigningAndStylingViewActivity;
import com.example.kevinlay.androidfundamentalspractice.Fragments.FragmentsActvity;
import com.example.kevinlay.androidfundamentalspractice.NetworkAndModels.NetworkAndModelsActivity;
import com.example.kevinlay.androidfundamentalspractice.Services.ServicesActivity;
import com.example.kevinlay.androidfundamentalspractice.Structure.StructureMainActivity;
import com.example.kevinlay.androidfundamentalspractice.ViewsAndLayouts.ViewsAndLayoutsActivity;
import com.example.kevinlay.androidfundamentalspractice.kotlin.KotlinActivity;
import com.example.kevinlay.androidfundamentalspractice.rxjava.RxJavaActivity;

public class MainActivity extends AppCompatActivity {

    private Button bEnterStructureActivity, bEnterVlActivity, bEnterDesignActivity, bEnterAdapterViews,
                    bEnterDataPersistence, bEnterFragments, bEnterServices, bEnterNetworking, bEnterDataBinding,
                    bEnterDagger, bEnterKotlin, bEnterRxJava;

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
        bEnterAdapterViews = (Button) findViewById(R.id.bEnterAdapterViews);
        bEnterAdapterViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AdapterViewActivity.class);
                startActivity(i);
            }
        });

        bEnterDataPersistence = (Button) findViewById(R.id.bEnterDataPersistence);
        bEnterDataPersistence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DataPersistenceActivity.class);
                startActivity(i);
            }
        });

        bEnterFragments = (Button) findViewById(R.id.bEnterFragments);
        bEnterFragments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FragmentsActvity.class);
                startActivity(i);
            }
        });

        bEnterServices = (Button) findViewById(R.id.bEnterServices);
        bEnterServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ServicesActivity.class);
                startActivity(i);
            }
        });

        bEnterNetworking = (Button) findViewById(R.id.bEnterNetworking);
        bEnterNetworking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NetworkAndModelsActivity.class);
                startActivity(i);
            }
        });

        bEnterDataBinding = (Button) findViewById(R.id.bEnterDataBinding);
        bEnterDataBinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), BindingActivity.class);
                startActivity(i);
            }
        });

        bEnterDagger = (Button) findViewById(R.id.bEnterDagger);
        bEnterDagger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Dagger2Activity.class);
                startActivity(i);
            }
        });

        bEnterKotlin = (Button) findViewById(R.id.bEnterKotlin);
        bEnterKotlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), KotlinActivity.class);
                startActivity(i);
            }
        });

        bEnterRxJava = (Button) findViewById(R.id.bEnterRxJava);
        bEnterRxJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RxJavaActivity.class);
                startActivity(i);
            }
        });
    }
}
