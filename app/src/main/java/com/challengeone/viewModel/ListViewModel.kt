package com.challengeone.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challengeone.Repository
import com.challengeone.model.ResponseModel
import com.challengeone.utils.Resource
import com.challengeone.utils.getError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel(var application: Application, var repository: Repository):ViewModel() {
    var resultCountrylist= MutableLiveData<Resource<ArrayList<ResponseModel>>>()

    fun getCountrylist()
    {


        resultCountrylist.value= Resource.loading(data = null)
        viewModelScope.launch (Dispatchers.IO )
        {

            try{
                val response=repository.getCountrylist()
                withContext(Dispatchers.Main)
                {
                    if(response.code()==200 && response.isSuccessful)
                    {
                       if(response.body()!=null)
                       {
                           resultCountrylist.value= Resource.success(response.body()!!,"")
                       }


                    }
                    else if(response.code()==204)
                    {
                        resultCountrylist.value=Resource.error(data = null, message = "")
                    }
                    else
                    {
                        resultCountrylist.value=Resource.error(data = null, message = response.message())
                    }
                }
            }
            catch (throwable:Throwable)
            {
                withContext(Dispatchers.Main)
                {
                    resultCountrylist.value=Resource.error(data = null,message = getError(throwable))
                }
            }

        }
    }


}