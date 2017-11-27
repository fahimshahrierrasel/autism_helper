package com.andromeda.autismhelper.activities;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.andromeda.autismhelper.R;
import com.andromeda.autismhelper.adapters.ItemAdapter;
import com.andromeda.autismhelper.models.Category;
import com.andromeda.autismhelper.models.Item;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

public class CategoryItems extends AppCompatActivity implements TextToSpeech.OnInitListener{

    Category category;
    Gson gson;
    TextToSpeech ttsEngine;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ttsEngine = new TextToSpeech(this, this);

        gson = new Gson();

        String jsonFileName="Food.json";
        String jsonString = readJSONStringFromFile(jsonFileName);;

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            jsonFileName = extras.getString("JSON_FILE");
            jsonString = readJSONStringFromFile(jsonFileName);
        }

        System.out.println(jsonFileName);
        System.out.println(jsonString);

        category = gson.fromJson(jsonString, Category.class);

//        if(getSupportActionBar() != null){
//            getSupportActionBar().setTitle(category.getCategory());
//        }

        RecyclerView recyclerViewAllItems = findViewById(R.id.recyclerViewAllItem);

        final ArrayList<Item> allFeatures = new ArrayList<Item>(category.getItems());

        ItemAdapter itemAdapter = new ItemAdapter(this, allFeatures);

        recyclerViewAllItems.setAdapter(itemAdapter);
        recyclerViewAllItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAllItems.setHasFixedSize(true);

        // RecyclerView on item click listener
        itemAdapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(@NotNull View itemView, int position) {
                String text = allFeatures.get(position).getItemname();
                speak(text);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void speak(String text){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ttsEngine.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }else{
            ttsEngine.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public String readJSONStringFromFile(String fileName) {
        String json = null;
        try {
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
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
}
