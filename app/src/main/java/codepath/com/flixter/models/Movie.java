package codepath.com.flixter.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
    // values coming back from API
    private String title;
    private String overview;
    private String posterPath; // only the path, not full URL
    private String backdropPath;

    // constructor
    public Movie(JSONObject object) throws JSONException {
        title = object.getString("title");
        overview = object.getString("overview");
        posterPath = object.getString("poster_path");
        backdropPath = object.getString("backdrop_path");
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }
}
