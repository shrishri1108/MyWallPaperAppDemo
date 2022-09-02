package com.example.mywallpaperapp.RetrofitDataFetchingNecessaryClasses;

import static com.example.mywallpaperapp.RetrofitDataFetchingNecessaryClasses.ApiUtilities.APIKey;

import com.example.mywallpaperapp.FetchedModelClasses.SearchModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {

    static final String Base_Url="https://api.pexels.com/v1/";

    @Headers("Authorization:"+APIKey)
    @GET("curated")
    Call<SearchModel> getImage(
            @Query("page") int page,
            @Query("per_page") int per_page
    );


    @Headers("Authorization:"+APIKey)
    @GET("search")
    Call<SearchModel> getSearchedImages(
            @Query("query") String query,
            @Query("page") int page,
            @Query("per_page") int per_page
    );
}
