package codepath.com.flixter.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Video {
    String id;

    public Video(JSONObject object) throws JSONException
    {
        id = object.getString("key");
    }
}
