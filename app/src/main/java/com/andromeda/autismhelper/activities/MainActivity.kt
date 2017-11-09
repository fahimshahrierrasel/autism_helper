package com.andromeda.autismhelper.activities

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import com.andromeda.autismhelper.R
import com.andromeda.autismhelper.adapters.FeaturesAdapter
import com.andromeda.autismhelper.models.Feature

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var allFeatures: ArrayList<Feature> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        allFeatures.add(Feature("Food", R.drawable.fastfood, null))
        allFeatures.add(Feature("Drink", R.drawable.beer, null))
        allFeatures.add(Feature("Dress", R.drawable.shirt, null))
        allFeatures.add(Feature("Alphabet", R.drawable.alphabet, null))
        allFeatures.add(Feature("Chocolate", R.drawable.chocolate, null))
        allFeatures.add(Feature("Dairy", R.drawable.dairy, null))
        allFeatures.add(Feature("Puzzle", R.drawable.puzzle, null))
        allFeatures.add(Feature("Pantone", R.drawable.pantone, null))
        allFeatures.add(Feature("Water", R.drawable.water, null))
        allFeatures.add(Feature("Settings", R.drawable.settings, null))

        featureRecyclerView.adapter = FeaturesAdapter(this,allFeatures)
        featureRecyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        featureRecyclerView.setHasFixedSize(true)

    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (newConfig?.orientation == Configuration.ORIENTATION_LANDSCAPE){
            featureRecyclerView.layoutManager = StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL)
        }else if(newConfig?.orientation == Configuration.ORIENTATION_PORTRAIT){
            featureRecyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        }
    }
}
