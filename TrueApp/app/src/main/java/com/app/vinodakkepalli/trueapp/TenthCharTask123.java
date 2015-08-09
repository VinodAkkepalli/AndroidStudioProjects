package com.app.vinodakkepalli.trueapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Vinod Akkepalli on 8/8/2015.
 */
public class TenthCharTask123 extends AsyncTask<Void, Void, Character> {

    private static final String LOG_TAG = TenthCharTask123.class.getSimpleName();

    @Override
    protected Character doInBackground(Void... params) {

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        StringBuffer buffer = new StringBuffer();

        try {

            // Construct the URL
            final String FORECAST_BASE_URL = "http://www.truecaller.com/support";

            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon().build();

            URL url = new URL(builtUri.toString());

            Log.v(LOG_TAG, "Built URI " + builtUri.toString());

            // Create the request to truecaller, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();

            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while (((line = reader.readLine()) != null)
                    //&& (buffer.length() <10)
                    ) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line);
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }

            Log.d(LOG_TAG, "##--##"+buffer.toString() + "##--##");

        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        return buffer.charAt(10);
    }

    @Override
    protected void onPostExecute(Character character) {


    }
}
