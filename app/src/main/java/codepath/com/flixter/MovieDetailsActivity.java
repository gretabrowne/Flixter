package codepath.com.flixter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import codepath.com.flixter.models.Config;
import codepath.com.flixter.models.Movie;
import cz.msebera.android.httpclient.Header;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static codepath.com.flixter.MovieListActivity.API_BASE_URL;
import static codepath.com.flixter.MovieTrailerActivity.API_KEY_PARAM;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movie;
    Config config;
    AsyncHttpClient client;
    ImageView im;
    public static final String VIDEO_ID = "id";

    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvOverview) TextView tvOverview;
    @BindView(R.id.rbVoteAverage) RatingBar rbVoteAverage;
    @BindString(R.string.youtube_api_key) public String youtube_api_key;
    @BindString(R.string.api_key) public String api_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        client = new AsyncHttpClient();

        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        config = (Config) Parcels.unwrap(getIntent().getParcelableExtra(Config.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        // set the title and overview
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        final String backdropUrl = config.getImageUrl(config.getBackdropSize(), movie.getBackdropPath());
        im = findViewById(R.id.imageView);

        int radius = 30;
        int margin = 10;
        GlideApp.with(this)
                .load(backdropUrl)
                .transform(new RoundedCornersTransformation(radius, margin))
                .into(im);

        // vote average is 0..10, convert to 0..5 by dividing by 2
        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage = voteAverage > 0 ? voteAverage / 2.0f : voteAverage);
    }

    public void onClick(View view) {
            // create url
            String url = API_BASE_URL + String.format("/movie/%s/videos", movie.getId());

            // set request parameters
            RequestParams params = new RequestParams();
            params.put(API_KEY_PARAM, api_key);

            // execute GET request using client to get JSON response
            client.get(url, params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // load the results into movies
                    try {
                        JSONArray results = response.getJSONArray("results");
                        // iterate through array, create movie objects
                        String videoId = null;
                        videoId = results.getJSONObject(0).getString("key");
                        if (videoId != null) {
                            Intent intent = new Intent(MovieDetailsActivity.this, MovieTrailerActivity.class);
                            intent.putExtra(VIDEO_ID, videoId);
                            startActivity(intent);
                        }
                        // Log.i(TAG, String.format("Loaded %s movies", results.length()));

                     } catch (JSONException e) {
                        Log.i("MovieTrailerActivity","Failed to parse now playing movies");
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.i("MovieDetailActivity", "Failed");
                }
            });
        }
        }

