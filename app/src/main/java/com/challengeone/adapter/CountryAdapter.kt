package com.challengeone.adapter

import android.content.Context
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.challengeone.R
import com.challengeone.model.ResponseModel
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener


class CountryAdapter(var context: Context, var list: ArrayList<ResponseModel>):RecyclerView.Adapter<CountryAdapter.Viewholder>() {

    var onClick:CountryAdapter.onItemClick?=null

    fun setOnClickListener(onClick: CountryAdapter.onItemClick)
    {
        this.onClick=onClick
    }


    inner  class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var ivFlag=itemView.findViewById<ImageView>(R.id.ivFlag)
        var tvName=itemView.findViewById<TextView>(R.id.tvName)
        var tvPopulation=itemView.findViewById<TextView>(R.id.tvPopulation)
        var tvBorders=itemView.findViewById<TextView>(R.id.tvBorders)
        var checkBox=itemView.findViewById<CheckBox>(R.id.checkbox)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(
            LayoutInflater.from(context).inflate(
                R.layout.item_country,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        holder.tvName.text=list[position].name
        holder.tvBorders.text=list[position].borders?.joinToString()
        holder.tvPopulation.text=list[position].population


        GlideToVectorYou.init().with(context).withListener(object :GlideToVectorYouListener{
            override fun onLoadFailed() {
                Toast.makeText(context, "Load failed", Toast.LENGTH_SHORT).show()
            }

            override fun onResourceReady() {
            }

        }).load(Uri.parse(list[position].flag),holder.ivFlag)

        if(list[position].isSelected!!)
        {
            holder.checkBox.isChecked=true
        }
        else
        {
            holder.checkBox.isChecked=false
        }
        holder.checkBox.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    list[position].isSelected = true
                    onClick!!.onItemClickListener(list)

                } else {
                    list[position].isSelected = false
                    onClick!!.onItemClickListener(list)
                }
            }

        })




    }

    override fun getItemCount(): Int {
       return  list.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    interface  onItemClick{
        fun onItemClickListener(list: ArrayList<ResponseModel>)
    }




}