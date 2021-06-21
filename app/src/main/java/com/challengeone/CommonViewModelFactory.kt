package com.challengeone

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.challengeone.viewModel.ListViewModel
import java.lang.IllegalArgumentException

class CommonViewModelFactory(private  val application: Application, private  val repository: Repository):
    ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListViewModel::class.java))
        {
            return  ListViewModel(application,repository) as T
        }
        throw  IllegalArgumentException("Class not found")
    }
}