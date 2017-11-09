package com.andromeda.autismhelper.adapters
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import android.support.v7.widget.CardView
import android.support.v4.content.res.ResourcesCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.andromeda.autismhelper.R
import com.andromeda.autismhelper.models.Feature

class FeaturesAdapter(val context: Context, var mFeatures: ArrayList<Feature>) : RecyclerView.Adapter<FeaturesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val featureView = inflater.inflate(R.layout.feature_layout, parent, false)
        return ViewHolder(featureView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aFeture = mFeatures[position]

        holder.featureName.text = aFeture.featureName
        holder.featureImage.setImageDrawable(ResourcesCompat.getDrawable(context.resources, aFeture.featureImage, null))
    }

    override fun getItemCount(): Int {
        return mFeatures.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var featureImage: ImageView = itemView.findViewById(R.id.feature_image)
        var featureName: TextView = itemView.findViewById(R.id.feature_name)
        private var featureCard: CardView = itemView.findViewById(R.id.feature_card)

        init {
            featureCard.setOnClickListener {
                val position = adapterPosition
                println(position)
                if (position != RecyclerView.NO_POSITION) {
                    val feature = mFeatures[position]
                    //context.startActivity(Intent(context, feature.activityClass))
                    Toast.makeText(context, feature.featureName, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}