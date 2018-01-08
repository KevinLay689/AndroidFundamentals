package com.example.kevinlay.androidfundamentalspractice.NetworkAndModels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kevinlay.androidfundamentalspractice.NetworkAndModels.RetrofitMovie.MovieClient;
import com.example.kevinlay.androidfundamentalspractice.NetworkAndModels.RetrofitMovie.MovieModel;
import com.example.kevinlay.androidfundamentalspractice.NetworkAndModels.RetrofitMovie.Result;
import com.example.kevinlay.androidfundamentalspractice.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit Activity
 *
 * Major Takeaways
 * -Needs internet permission
 *
 * Steps to using Retrofit
 *      1. Create interface that is in charge of API endpoints, see GitHubClient class
 *      2. Create model that can hold data being passed back, see GithubRepo class
 *      3. Create builder
 *          3a. Builder needs base URL + Converter Factory
 *      4. Create instance of interface using retrofit.create
 *      5. Call a method on the client that returns a call object
 *      6. Use call.enqueue to handle the response, response is returned in the response.body();
 *
 */

public class RetrofitActivity extends AppCompatActivity {

    private static final String TAG = "RetrofitActivity";

    private static final String movieApiKey = "c414f6748521b1f9e997639b2c7c92d9";

    private ListView lvRetrofitRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        lvRetrofitRepos = (ListView) findViewById(R.id.lvRetrofitRepos);
        //startRetrofit();
        startMovieRetrofit();
    }

    private void startMovieRetrofit() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("apikey", "your-actual-api-key")
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        MovieClient movieClient = retrofit.create(MovieClient.class);
        Call<MovieModel> call = movieClient.getRecentMovies();

        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                MovieModel movieModel = response.body();
//                Log.i(TAG, "onResponse: " +response.body());
//
//                for(int i = 0; i < movieModel.getResults().size(); i++) {
//                    Result result = movieModel.getResults().get(i);
//                    Log.i(TAG, "onResponse: Results movie name " +
//                            result.getTitle() + "Date released: " +
//                            result.getReleaseDate());
//                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }

    private void startRetrofit() {

        //3
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //4
        GithubClient client = retrofit.create(GithubClient.class);

        //5
        Call<List<GithubRepo>> call = client.reposForUser("kevinlay689");
        //6
        call.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                List<GithubRepo> repos = response.body();

                // Creates ArrayAdapter and populates ListView
                ArrayList<String> names = new ArrayList<>();
                for(int i = 0; i < repos.size(); i++) {
                    names.add(repos.get(i).getName());
                }
                ArrayAdapter<String> itemsAdapter =
                        new ArrayAdapter<>(getApplicationContext(), R.layout.simple_single_item, names);
                lvRetrofitRepos.setAdapter(itemsAdapter);
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
