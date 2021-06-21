package com.challengeone.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.challengeone.R
import com.challengeone.adapter.CountryAdapter
import com.challengeone.databinding.ActivityFirstScreenBinding
import com.challengeone.databinding.ActivitySecondScreenBinding
import com.challengeone.model.ResponseModel

class SecondScreenActivity : AppCompatActivity(),CountryAdapter.onItemClick {

    var selectedlist:ArrayList<ResponseModel>?=null
    var binding: ActivitySecondScreenBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second_screen)

        if(intent!=null)
        {
            selectedlist= intent.getSerializableExtra("data") as ArrayList<ResponseModel>?
            val filteredlist=selectedlist!!.filter { it.isSelected!! }
            val adapter= CountryAdapter(this, filteredlist as ArrayList<ResponseModel>)
            adapter.setOnClickListener(this)
            binding!!.rvCountry.adapter=adapter

        }
    }

    override fun onItemClickListener(list: ArrayList<ResponseModel>) {
        selectedlist=list



    }

    override fun onBackPressed() {

        val intent= Intent()
        intent.putExtra("data",selectedlist)
        setResult(100, intent)
        finish()


    }
}