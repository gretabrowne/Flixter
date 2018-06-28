package codepath.com.flixter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.parceler.Parcels;

import codepath.com.flixter.models.Movie;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));
    }
}
