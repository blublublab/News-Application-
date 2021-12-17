package com.maxbt.newsapplication.model.entity.news_media

import com.google.gson.annotations.SerializedName
import com.maxbt.newsapplication.model.Constants

class Guid {

    //This is needed becuase starting url of downloading image
    // is not correct and would not work for downloading image,
    // so i changed it to main domain of wordpress site
    @SerializedName("rendered")
    var mainImageUrl : String = ""
        get() {
           val siteDomain =  Constants.SITE_URL
               .split(".")[1]
               .toCharArray()
               .toMutableList()

            if(siteDomain.last() == '/'){
                siteDomain.removeLast()
            }

            return field.replace("wpnet.ninja",
               siteDomain.joinToString("", ""))
        }

}