package com.andromeda.autismhelper.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.andromeda.autismhelper.R;
import com.andromeda.autismhelper.adapters.FeaturesAdapter;
import com.andromeda.autismhelper.image_recognition.ClassifierActivity;
import com.andromeda.autismhelper.image_recognition.DetectorActivity;
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

        ImageButton imageButtonClassifier = findViewById(R.id.classifier);
        ImageButton imageButtonDetection = findViewById(R.id.detection);

        imageButtonClassifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent classifier = new Intent(MainActivity.this, ClassifierActivity.class);
                startActivity(classifier);
            }
        });

        imageButtonDetection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detector = new Intent(MainActivity.this, DetectorActivity.class);
                startActivity(detector);
            }
        });
        
        
        setSupportActionBar(toolbar);


        // Add Components for Feature RecyclerView
        String string_alphabet = getResources().getString(R.string.Alphabet);
        allFeatures.add(new Feature(string_alphabet, R.drawable.alphabet));
        String string_chocolate = getResources().getString(R.string.Chocolate);
        allFeatures.add(new Feature(string_chocolate, R.drawable.chocolate));
        String string_color = getResources().getString(R.string.Color);
        allFeatures.add(new Feature(string_color, R.drawable.pantone));
        String string_food = getResources().getString(R.string.Food);
        allFeatures.add(new Feature(string_food, R.drawable.doughnut));
        String string_drink = getResources().getString(R.string.Drink);
        allFeatures.add(new Feature(string_drink, R.drawable.water));
        String string_dress = getResources().getString(R.string.Dress);
        allFeatures.add(new Feature(string_dress, R.drawable.shirt));
        String string_emotion = getResources().getString(R.string.Emotion);
        allFeatures.add(new Feature(string_emotion, R.drawable.love));
        String string_fastfood = getResources().getString(R.string.FastFood);
        allFeatures.add(new Feature(string_fastfood, R.drawable.stand));
        String string_puzzle = getResources().getString(R.string.Puzzle);
        allFeatures.add(new Feature(string_puzzle, R.drawable.puzzle));
        String string_shape = getResources().getString(R.string.Shape);
        allFeatures.add(new Feature(string_shape, R.drawable.share));
        String string_toy = getResources().getString(R.string.Toy);
        allFeatures.add(new Feature(string_toy, R.drawable.teddybear));
        String string_water = getResources().getString(R.string.Water);
        allFeatures.add(new Feature(string_water, R.drawable.water));

        // Set up Feature RecyclerView
        FeaturesAdapter featuresAdapter = new FeaturesAdapter(this, allFeatures);

        recyclerViewFeatures.setAdapter(featuresAdapter);
        recyclerViewFeatures.setLayoutManager(staggeredGridLayoutManager);
        recyclerViewFeatures.setHasFixedSize(true);

        // RecyclerView on item click listener
        featuresAdapter.setOnItemClickListener(new FeaturesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(@NotNull View itemView, int position) {
                String text = allFeatures.get(position).getFeatureName();
                speak(englishToBengali(text));
                Intent detector = new Intent(MainActivity.this, CategoryItems.class);
                detector.putExtra("JSON_FILE", "Food.json");
                startActivity(detector);
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
        if(i == TextToSpeech.SUCCESS) {
            ttsEngine.setLanguage(new Locale("bn", "BD"));
            ttsEngine.setSpeechRate(0.5f);
        }

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