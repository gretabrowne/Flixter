package codepath.com.flixter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codepath.com.flixter.models.Config;
import codepath.com.flixter.models.Movie;
import cz.msebera.android.httpclient.Header;

public class MovieListActivity extends AppCompatActivity {

    public final static String API_BASE_URL = "https://api.themoviedb.org/3";
    public final static String API_KEY_PARAM = "api_key";
    // logging calls
    public final static String TAG = "MovieListActivity";

    AsyncHttpClient client;

    // list of currently playing movies
    ArrayList<Movie> movies;
    // recycler view
    RecyclerView rvMovies;
    // the adapter wired to the recycler view
    MovieAdapter adapter;

    Config config;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        // init client
        client = new AsyncHttpClient();

        movies = new ArrayList<>();
        // init adapter-- movies array can't be reinstated
        adapter = new MovieAdapter(movies);

        rvMovies = (RecyclerView) findViewById(R.id.rvMovies);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        rvMovies.setAdapter(adapter);

        // get config
        getConfiguration();
    }

    // get list of currently playing movies
    private void getNowPlaying() {
        // create url
        String url = API_BASE_URL + "/movie/now_playing";
        // set request parameters
        RequestParams params = new RequestParams();
        params.put(API_KEY_PARAM, getString(R.string.api_key));
        // execute GET request using client to get JSON response
        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // load the results into movies
                try {
                    JSONArray results = response.getJSONArray("results");
                    // iterate through array, create movie objects
                    for (int i = 0; i < results.length(); i++) {
                        Movie movie = new Movie(results.getJSONObject(i));
                        movies.add(movie);
                        // notify adapter
                        adapter.notifyItemInserted(movies.size() - 1);
                    }
                    Log.i(TAG, String.format("Loaded %s movies", results.length()));

                } catch (JSONException e) {
                    logError("Failed to parse now playing movies", e, true);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                logError("Failed to get data from now playing endpoint", throwable, true);
            }
        });
    }

    // get config
    private void getConfiguration() {
        // create url
        String url = API_BASE_URL + "/configuration";
        // set request parameters
        RequestParams params = new RequestParams();
        params.put(API_KEY_PARAM, getString(R.string.api_key));
        // execute GET request using client to get JSON response
        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    config = new Config(response);
                    Log.i(TAG,
                            String.format("Loaded configuration with imageBaseUrl %s and posterSize %s", config.getImageBaseUrl(), config.getPosterSize()));
                    // pass config object to adapter
                    adapter.setConfig(config);
                    // get now playing movies
                    getNowPlaying();
                } catch (JSONException e) {
                    logError("Failed parsing configuration", e, true);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                logError("Failed getting configuration", throwable, true);
            }
        });
    }

    // handle errors, log and alert user
    private void logError (String message, Throwable error, boolean alertUser) {
        // always log error
        Log.e(TAG, message, error);
        // alert user
        if (alertUser) {
            // show a long toast
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }

}
