package com.andromeda.autismhelper.activities;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.andromeda.autismhelper.R;
import com.andromeda.autismhelper.adapters.FeaturesAdapter;
import com.andromeda.autismhelper.models.Feature;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by fahim on 22/11/17.
 */

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    
    // Controls
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    Toolbar toolbar;
    RecyclerView recyclerViewFeatures;
    TextToSpeech ttsEngine;
    
    // Data
    private ArrayList<Feature> allFeatures = new ArrayList<>();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);


        ttsEngine = new TextToSpeech(this, this);

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
        FeaturesAdapter featuresAdapter = new FeaturesAdapter(this, allFeatures);

        recyclerViewFeatures.setAdapter(featuresAdapter);
        recyclerViewFeatures.setLayoutManager(staggeredGridLayoutManager);
        recyclerViewFeatures.setHasFixedSize(true);

        // RecyclerView on item click listener
        featuresAdapter.setOnItemClickListener(new FeaturesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(@NotNull View itemView, int position) {
                speak(englishToBengali(allFeatures.get(position).getFeatureName()));
            }
        });
    }

    private void speak(String text){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ttsEngine.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }else{
            ttsEngine.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Changing Grid size according to the device orientation
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            staggeredGridLayoutManager.setSpanCount(5);
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            staggeredGridLayoutManager.setSpanCount(3);
        }
    }

    @Override
    public void onInit(int i) {
        if(i == TextToSpeech.SUCCESS)
            ttsEngine.setLanguage(new Locale("bn", "BD"));
    }

    @Override
    public void onDestroy() {
        if (ttsEngine != null) {
            ttsEngine.stop();
            ttsEngine.shutdown();
        }
        super.onDestroy();
    }

    private String englishToBengali(String englishText){
        String bengaliText;
        switch (englishText){
            case "Food":
                bengaliText = "খাবার";
                break;
            default:
                bengaliText = englishText;
        }
        return bengaliText;
    }

}