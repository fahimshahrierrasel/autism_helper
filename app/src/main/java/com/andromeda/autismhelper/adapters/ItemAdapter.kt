package com.andromeda.autismhelper.adapters
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.andromeda.autismhelper.R
import com.andromeda.autismhelper.models.Item
import com.bumptech.glide.Glide

class ItemAdapter(val context: Context, var mFeatures: ArrayList<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

    var listener: OnItemClickListener? = null

    interface OnItemClickListener{
        fun onItemClick(itemView: View, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val featureView = inflater.inflate(R.layout.item_layout, parent, false)
        return ViewHolder(featureView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aFeature = mFeatures[position]

        holder.featureName.text = aFeature.itemname.capitalize()
//        holder.featureImage.setImageDrawable(ResourcesCompat.getDrawable(context.resources, aFeature.featureImage, null))
        Glide.with(context).load(aFeature.url).into(holder.featureImage);
    }

    override fun getItemCount(): Int {
        return mFeatures.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var featureImage: ImageView = itemView.findViewById(R.id.feature_image)
        var featureName: TextView = itemView.findViewById(R.id.feature_name)
        var featureCard: CardView = itemView.findViewById(R.id.feature_card)

        init {
            super.itemView
            featureCard.setOnClickListener {
                if(listener != null){
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION){
                        listener?.onItemClick(itemView, position)
                    }
                }
            }
        }
    }
}
