package com.challengeone.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.challengeone.CommonViewModelFactory
import com.challengeone.R
import com.challengeone.Repository
import com.challengeone.adapter.CountryAdapter
import com.challengeone.databinding.ActivityFirstScreenBinding
import com.challengeone.model.ResponseModel
import com.challengeone.utils.Status
import com.challengeone.utils.hideProgress
import com.challengeone.utils.showProgress
import com.challengeone.viewModel.ListViewModel
import com.google.gson.Gson

class FirstScreenActivity : AppCompatActivity(), CountryAdapter.onItemClick {
    var binding: ActivityFirstScreenBinding? = null
    var viewModel: ListViewModel? = null
    var selectedlist: ArrayList<ResponseModel>? = null
    var mainlist: ArrayList<ResponseModel>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_first_screen)
        init()
        setObservers()
    }

    private fun init() {
        val viewModelFactory =
            CommonViewModelFactory(application = application, repository = Repository())
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(ListViewModel::class.java)

        viewModel?.getCountrylist()

        binding!!.btNext.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SecondScreenActivity::class.java)
            intent.putExtra("data", selectedlist)
            startActivityForResult(intent, 100)


        })
    }

    private fun setObservers() {
        viewModel?.resultCountrylist?.observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        hideProgress()

                        setAdapter(resource.data!!)
                        mainlist = resource.data

                    }
                    Status.LOADING -> {
                        showProgress(this)

                    }
                    Status.ERROR -> {
                        hideProgress()


                    }
                }
            }
        })
    }

    private fun setAdapter(list: ArrayList<ResponseModel>) {

        val adapter = CountryAdapter(this, list)
        adapter.setOnClickListener(this)
        binding!!.rvCountry.adapter = adapter
    }

    override fun onItemClickListener(list: ArrayList<ResponseModel>) {
        selectedlist = ArrayList()
        selectedlist = list

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {

            if (data?.extras != null) {
                var list = data?.extras!!.getSerializable("data") as ArrayList<ResponseModel>
                println("Gsonsfsf" + Gson().toJson(list))

                for (i in mainlist!!.indices) {
                    for (j in list.indices) {
                        if (mainlist!![i].name.equals(list[j].name)) {
                            if (list[j].isSelected!!) {
                                mainlist!![i].isSelected = true
                            } else {
                                mainlist!![i].isSelected = false
                            }
                        }
                    }
                }
                setAdapter(mainlist!!)


            }
        }
    }
}