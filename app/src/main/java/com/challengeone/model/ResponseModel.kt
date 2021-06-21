package com.challengeone.model

import java.io.Serializable

class ResponseModel :Serializable{

    var name:String?=null
    var population:String?=null
    var flag:String?=null
    var borders:ArrayList<String>?=null
    var isSelected:Boolean?=false


}