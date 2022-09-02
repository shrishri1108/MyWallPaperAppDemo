package com.example.mywallpaperapp.RetrofitDataFetchingNecessaryClasses;

import com.example.mywallpaperapp.RetrofitDataFetchingNecessaryClasses.ApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {
    private static Retrofit retrofit=null;
    public static final String APIKey="563492ad6f917000010000017e10b97f6ecd4a29ba10a58b3c02685f";

    public static ApiInterface getApiInterface(){
        if (retrofit== null ){
             retrofit= new Retrofit.Builder().baseUrl(ApiInterface.Base_Url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(ApiInterface.class);
    }
}
