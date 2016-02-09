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
public class ProductsEntity {
    private static final String TAG = ProductsEntity.class.getSimpleName();
    private String amount;
    private String sku;
    private String currency;

    public ProductsEntity(JSONObject jsonObject) {
        try {

            //Making a special check for each item required by checking if the item is empty
            amount = ((jsonObject.getString("amount") == null) ? "": (jsonObject.getString("amount")));
            sku = ((jsonObject.getString("sku") == null) ? "": (jsonObject.getString("sku")));
            currency = ((jsonObject.getString("currency") == null) ? "": (jsonObject.getString("currency")));

        } catch (JSONException exception) {
            Log.e(amount, exception.getMessage());
        }
    }

    public String getAmount() {
        return amount;
    }

    public String getSku() {
        return sku;
    }

    public String getCurrency() {
        return currency;
    }
}
