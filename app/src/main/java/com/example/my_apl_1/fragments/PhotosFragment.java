package com.example.my_apl_1.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.my_apl_1.PhotoAdapter;
import com.example.my_apl_1.R;
import com.example.my_apl_1.model.Photo;
import com.example.my_apl_1.services.ApiInterface;
import com.example.my_apl_1.services.AppClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosFragment extends Fragment {

    private static final String TAG = "PhotosFragment";

    final String API_KEY = "gzZuJ4u49cxEO7YTSnxR0tlidPCoE5nnxKfmI8VX_k4";

    private RecyclerView recyclerView;
    private PhotoAdapter photoAdapter;
    private List<Photo> photoList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefresh;



    private ApiInterface api;
    private AppClient app;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app =(AppClient) getActivity().getApplication();
        api = app.getApi();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_rec,container,false);

        swipeRefresh = view.findViewById(R.id.id_swipe);
        recyclerView = view.findViewById(R.id.item_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        photoAdapter = new PhotoAdapter(getActivity(),photoList);
        recyclerView.setAdapter(photoAdapter);

        swipeRefresh.setColorSchemeColors(Color.BLACK);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPhoto(API_KEY);
            }
        });

        getPhoto(API_KEY);

        return view;
    }


    public void getPhoto(String api_key){

      swipeRefresh.setRefreshing(true);

        Call<List<Photo>> call = api.getPhotos(API_KEY);

     call.enqueue(new Callback<List<Photo>>() {
         @Override
         public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
             if(response.isSuccessful()){
                swipeRefresh.setRefreshing(false);
                 photoList.addAll(response.body());
                 photoAdapter.notifyDataSetChanged();
                 Log.i(TAG, "onResponse: " + response.body().size());
             }else {
                 Log.e(TAG,"fail " + response.message());
             }
         }

         @Override
         public void onFailure(Call<List<Photo>> call, Throwable t) {
             swipeRefresh.setRefreshing(false);
             Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
          Log.e(TAG,"fail " + t.getMessage());
         }
     });

    }

    public void getDetailedPhoto(String id, String api_key){

          Call<Photo> call = api.getDetailPhoto(id, api_key);

          call.enqueue(new Callback<Photo>() {
              @Override
              public void onResponse(Call<Photo> call, Response<Photo> response) {

              }

              @Override
              public void onFailure(Call<Photo> call, Throwable t) {

              }
          });
    }
}
