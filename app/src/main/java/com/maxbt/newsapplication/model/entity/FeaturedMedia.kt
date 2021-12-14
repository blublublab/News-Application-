package com.maxbt.newsapplication.model.entity

import com.google.gson.annotations.SerializedName
import com.maxbt.newsapplication.model.api.RetrofitClient
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * This class is only needed for getting an title image of news,
 * but can be used later in other cases
 *
 */
data class FeaturedMedia(
    @SerializedName("href")
    val url : String
) {


    //This suspend function is getting url of main image if is not set , or returns imageurl if set
    var imageUrl : String? = null
    private suspend fun getMedia() : String {
        return imageUrl?: suspendCancellableCoroutine {
            val request = Request.Builder()
                .url(url)
                .build()
            RetrofitClient.okHttpClient
                .newCall(request)
                .enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        e.printStackTrace()
                        it.resumeWithException(e)
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val jsonData = JSONObject(response.toString())
                        val link = jsonData.getString("link")
                        val formattedLink = link.replace("\'", "")
                        it.resume(formattedLink)
                    }
                })
        }
    }
}