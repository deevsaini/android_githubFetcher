package com.example.github_repos;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github_repos.Adapters.RepositoryAdapter;
import com.example.github_repos.Api.ApiClient;
import com.example.github_repos.Api.EndPoints;
import com.example.github_repos.Model.Repository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SearchView searchView;
    List<Repository> data;
    RepositoryAdapter repositoryAdapter;
    TextView NoUsr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        searchView = findViewById(R.id.searchView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NoUsr = findViewById(R.id.textView2);
        data = new ArrayList<>();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.equals("") | query == null){
                    NoUsr.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                else {
                    NoUsr.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    loadRepositories(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("") | newText==null){
                    NoUsr.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);

                }
                else {
                    NoUsr.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    loadRepositories(newText);
                }
                return false;
            }
        });
        repositoryAdapter = new RepositoryAdapter(data, getApplicationContext());
        recyclerView.setAdapter(repositoryAdapter);

    }

    public void loadRepositories(String q) {
        EndPoints apiService =
                ApiClient.getClient().create(EndPoints.class);

        Call<List<Repository>> call = apiService.getRepo(q);
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {

                data.clear();
                if (data!=null) {
                    data.addAll(response.body());
                    repositoryAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                // Log error here since request failed
                Log.e("Repos", t.toString());
            }

        });
   }
}