package com.andromeda.autismhelper.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.andromeda.autismhelper.R;
import com.andromeda.autismhelper.adapters.ItemAdapter;
import com.andromeda.autismhelper.models.Category;
import com.andromeda.autismhelper.models.Feature;
import com.andromeda.autismhelper.models.Item;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CategoryItems extends AppCompatActivity {

    Category category;
    Gson gson;

    private ArrayList<Item> allFeatures = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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


        ItemAdapter itemAdapter = new ItemAdapter(this, new ArrayList<Item>(category.getItems()));

        recyclerViewAllItems.setAdapter(itemAdapter);
        recyclerViewAllItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAllItems.setHasFixedSize(true);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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


}
