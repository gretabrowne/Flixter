package codepath.com.flixter.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Config {
    String imageBaseUrl;
    String posterSize;
    String backdropSize;

    public Config(JSONObject object) throws JSONException {
        JSONObject images = object.getJSONObject("images");
        // get image base url
        imageBaseUrl = images.getString("secure_base_url");
        // get poster size
        JSONArray posterSizeOptions = images.getJSONArray("poster_sizes");
        // use option at index 3
        posterSize = posterSizeOptions.optString(3, "w342");
        // parse backdrop sizes, use option at index 1 or w780
        JSONArray backdropSizeOptions = images.getJSONArray("backdrop_sizes");
        backdropSize = backdropSizeOptions.optString(1, "w780");
    }

    // helper method for constructing url
    public String getImageUrl (String size, String path) {
        return String.format("%s%s%s", imageBaseUrl, size, path); // concatenate
    }

    public String getImageBaseUrl() {
        return imageBaseUrl;
    }

    public String getPosterSize() {
        return posterSize;
    }
}
