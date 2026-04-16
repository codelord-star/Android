package com.example.firstapp.network

import com.cloudinary.android.ResponsiveUrl
import com.example.firstapp.models.CloudinaryResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.Response

interface CloudinaryAPI {
    @Multipart
    @POST("v1_1/dosbf5vye/image/upload")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part,
        @Part("upload_preset")uploadPreset: RequestBody
    ): Response<CloudinaryResponse>                 //ensure the "Response" imported is from retrofit2 and not okhttp3
}