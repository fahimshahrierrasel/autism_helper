package com.andromeda.autismhelper.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.andromeda.autismhelper.R;
import com.andromeda.autismhelper.adapters.FeaturesAdapter;
import com.andromeda.autismhelper.models.Feature;

import java.util.ArrayList;

/**
 * Created by fahim on 22/11/17.
 */

public class MainActivity extends AppCompatActivity {
    
    // Controls
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    Toolbar toolbar;
    RecyclerView recyclerViewFeatures;
    
    // Data
    private ArrayList<Feature> allFeatures = new ArrayList<>();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        
        // Controls Initialize
        toolbar = findViewById(R.id.toolbar);
        recyclerViewFeatures = findViewById(R.id.featureRecyclerView);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
        
        
        setSupportActionBar(toolbar);
        

        // Add Components for Feature RecyclerView
        allFeatures.add(new Feature("Food", R.drawable.fastfood, null));
        allFeatures.add(new Feature("Drink", R.drawable.beer, null));
        allFeatures.add(new Feature("Dress", R.drawable.shirt, null));
        allFeatures.add(new Feature("Alphabet", R.drawable.alphabet, null));
        allFeatures.add(new Feature("Chocolate", R.drawable.chocolate, null));
        allFeatures.add(new Feature("Dairy", R.drawable.dairy, null));
        allFeatures.add(new Feature("Puzzle", R.drawable.puzzle, null));
        allFeatures.add(new Feature("Pantone", R.drawable.pantone, null));
        allFeatures.add(new Feature("Water", R.drawable.water, null));
        allFeatures.add(new Feature("Settings", R.drawable.settings, null));

        // Set up Feature RecyclerView
        recyclerViewFeatures.setAdapter(new FeaturesAdapter(this, allFeatures));
        recyclerViewFeatures.setLayoutManager(staggeredGridLayoutManager);
        recyclerViewFeatures.setHasFixedSize(true);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Changing Grid size according to the device orientation
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            staggeredGridLayoutManager.setSpanCount(5);
            Toast.makeText(this, String.valueOf(staggeredGridLayoutManager.getSpanCount()), Toast.LENGTH_SHORT).show();
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            staggeredGridLayoutManager.setSpanCount(3);
            Toast.makeText(this, String.valueOf(staggeredGridLayoutManager.getSpanCount()), Toast.LENGTH_SHORT).show();
        }
    }
}