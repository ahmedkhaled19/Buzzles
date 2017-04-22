package com.example.ahmedkhaled.buzzels.Utils;

import java.io.File;

import rx.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AhmedKhaled on 4/22/2017.
 */

public class ProfileRepo {
    private static Retrofit retrofitClient;
    private static OkHttpClient.Builder okHttpClient;

    private static final String BASE_URL = URLs.ROOT_URL;

    public Observable<ResponseBody> uploadProfilePic(String userKey, File file) {
        ProfileApi profileApi = getRetrofitClient()
                .create(ProfileApi.class);

        RequestBody requestPic = RequestBody.create(MediaType.parse("image/*"), file);

       // MultipartBody.Part step = MultipartBody.Part.createFormData("step", "3");
        MultipartBody.Part profilePic = MultipartBody.Part.createFormData("profile_pic", file.getName(), requestPic);
        MultipartBody.Part token = MultipartBody.Part.createFormData("token", userKey);

        return profileApi.uploadProfilePic(token,
                profilePic);
    }

    private static Retrofit getRetrofitClient() {
        if (retrofitClient == null) {
            okHttpClient = new OkHttpClient.Builder();
            retrofitClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient.build())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitClient;
    }
}
