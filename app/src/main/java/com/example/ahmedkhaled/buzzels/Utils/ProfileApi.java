package com.example.ahmedkhaled.buzzels.Utils;


import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by AhmedKhaled on 4/22/2017.
 */

interface ProfileApi {
    @POST("profile/addProfilePic")

    @Multipart
    Observable<ResponseBody> uploadProfilePic(@Part MultipartBody.Part userKey,
                                              @Part MultipartBody.Part file);
}
