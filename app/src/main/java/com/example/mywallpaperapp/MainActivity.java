package com.example.mywallpaperapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mywallpaperapp.Adapter.RecyclerAdapter;
import com.example.mywallpaperapp.FetchedModelClasses.ImageModel;
import com.example.mywallpaperapp.FetchedModelClasses.SearchModel;
import com.example.mywallpaperapp.RetrofitDataFetchingNecessaryClasses.ApiUtilities;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageModel> modelClassList;
    private RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    EditText editText;
    ImageView search;
    CardView mnature, mbus, mcar, mtrending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.recycler_views_bottom_lays);
        mnature = findViewById(R.id.btn_natures);
        mbus = findViewById(R.id.bus_btns);
        mcar = findViewById(R.id.btn_cars);
        mtrending = findViewById(R.id.trending_btns);
        editText = findViewById(R.id.editText);
        search = findViewById(R.id.search_btns);

        modelClassList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter(getApplicationContext(), modelClassList);
        findPhotos();
        recyclerView.setAdapter( adapter);
        mnature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "nature";
                getsearchedImages(query);
            }
        });
        mbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "Bus";
                getsearchedImages(query);
            }
        });
        mcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "car";
                getsearchedImages(query);
            }
        });
        mtrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPhotos();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query=editText.getText().toString().trim().toLowerCase();
                if (!query.isEmpty()){
                    getsearchedImages(query);
                }
            }
        });
        
    }

    private void findPhotos() {
        modelClassList.clear();
        ApiUtilities.getApiInterface().getImage(1,79).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                if(response.isSuccessful()){
                    Log.d("successful Responses ", "onResponse: "+response.code());
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else {
                    Log.d("Error on responses ", "onResponse: "+ response.errorBody());
                    Toast.makeText(MainActivity.this, " Already Added below. "+ response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, " There is some errors . Error as"+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getsearchedImages(String query) {

        ApiUtilities.getApiInterface().getSearchedImages(query,1,79).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                if(response.isSuccessful()){
                    modelClassList.clear();
                    modelClassList.addAll( response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this, "Already Added below. " ,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Sorry for inconvanience. Can't find data . Thorowing Errors is as : "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}