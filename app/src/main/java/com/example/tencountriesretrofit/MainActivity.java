package com.example.tencountriesretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<Worldpopulation> data;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter Adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView =findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        LoadJson();
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        //Adapter = new MyAdapter(data ,getApplicationContext());
        //recyclerView.setAdapter(Adapter);
    }

    private void LoadJson() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.androidbegin.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface request=retrofit.create(RetrofitInterface.class);
        Call<JsonResponce> call=request.getJson();
        call.enqueue(new Callback<JsonResponce>() {

            @Override
            public void onResponse(Call<JsonResponce> call, Response<JsonResponce> response) {
                data=response.body().getWorldpopulation();
                // specify an adapter (see also next example)
                Adapter = new MyAdapter(data ,getApplicationContext());
                recyclerView.setAdapter(Adapter);
            }

            @Override
            public void onFailure(Call<JsonResponce> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
