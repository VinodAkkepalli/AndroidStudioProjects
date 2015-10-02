package news.agoda.com.sample;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents a news item
 */
public class NewsEntity {
    private static final String TAG = NewsEntity.class.getSimpleName();
    private String title;
    private String summary;
    private String articleUrl;
    private String byline;
    private String publishedDate;
    private List<MediaEntity> mediaEntityList;

    public NewsEntity(JSONObject jsonObject) {
        try {
            mediaEntityList = new ArrayList<>();

            //Making a special check for each item required by checking if the item is empty
            title = ((jsonObject.getString("title") == null) ? "": (jsonObject.getString("title")));
            summary = ((jsonObject.getString("abstract") == null) ? "": (jsonObject.getString("abstract")));
            articleUrl = ((jsonObject.getString("url") == null) ? "": (jsonObject.getString("url")));
            byline = ((jsonObject.getString("byline") == null) ? "": (jsonObject.getString("byline")));
            publishedDate = ((jsonObject.getString("published_date") == null) ? "": (jsonObject.getString("published_date")));
            if(jsonObject.getJSONArray("multimedia") == null){
                mediaEntityList = null;
            }else {
                JSONArray mediaArray = jsonObject.getJSONArray("multimedia");
                for (int i = 0; i < mediaArray.length(); i++) {
                    JSONObject mediaObject = (JSONObject) mediaArray.getJSONObject(i);
                    MediaEntity mediaEntity = new MediaEntity(mediaObject);
                    mediaEntityList.add(mediaEntity);
                }
            }

        } catch (JSONException exception) {
            Log.e(TAG, exception.getMessage());
        }
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public String getByline() {
        return byline;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public List<MediaEntity> getMediaEntity() {
        return mediaEntityList;
    }
}
