package com.app.vinodakkepalli.trueapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static volatile Character textView1 = null;
    private static volatile String textView2;
    private static volatile Map<String, Integer> wordsMap = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //invoke the async task
        new TenthCharTask().execute();
        new EveryTenthCharTask().execute();
        new WordCounterTask().execute();

        //initialization of all the UI elements of this activity
        Button clickMeButton = (Button) findViewById(R.id.clickmebutton);
        final TextView tv1 = (TextView) findViewById(R.id.tenthChar);
        final TextView tv2 = (TextView) findViewById(R.id.everyTenthChar);
        final TextView tv3 = (TextView) findViewById(R.id.wordCount);

        clickMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //display the results on the UI
                Log.d(LOG_TAG, "##--textView1 is " + textView1);
                if(textView1 != null)   // to avoid null values initially
                    tv1.setText("10th Character is: " + textView1.toString());

                Log.d(LOG_TAG, "##--textView2 is " + textView2);
                tv2.setText("String with every 10th character is: " + textView2);

                if(wordsMap != null){   // to avoid null values initially
                    Log.d(LOG_TAG, "##--wordsMap is " + wordsMap.toString());
                    tv3.setText("Number of distinct words are: " + wordsMap.size());
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        //Refresh button executes all the Async tasks
        if (id == R.id.action_refresh) {
            new TenthCharTask().execute();
            new EveryTenthCharTask().execute();
            new WordCounterTask().execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * TenthCharTask
     * Purpose: to get the network data in background thread and update the UI
     * Return: a character which is at 10th location
     */
    public class TenthCharTask extends AsyncTask<Void, Void, Character> {

        private final String LOG_TAG = TenthCharTask.class.getSimpleName();

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
                while (((line = reader.readLine()) != null)) {
                    buffer.append(line);
                }

                if (buffer.length() == 0) {
                    // Stream was empty.
                    return null;
                }

            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
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

            textView1 = (character.equals(' ')) ? ' ' : character;
            Log.d(LOG_TAG, "##--Char is " + textView1);
        }
    }

    /**
     *
     * EveryTenthCharTask
     * Purpose: to get the network data in background thread and update the UI
     * Return: a string containing every 10th character of the given url text
     *
     */
    public class EveryTenthCharTask extends AsyncTask<Void, Void, String> {

        private final String LOG_TAG = EveryTenthCharTask.class.getSimpleName();

        @Override
        protected String doInBackground(Void... params) {

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
                while (((line = reader.readLine()) != null)) {
                    buffer.append(line);
                }

                if (buffer.length() == 0) {
                    // Stream was empty.
                    return null;
                }

            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
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

            int n = 10;
            int length = buffer.length();
            String str = new String();
            while(n < length ){
                str = str + buffer.charAt(n);
                n+=10;
            }
            Log.d(LOG_TAG, "##--sb is " + str);
            return str;
        }

        @Override
        protected void onPostExecute(String str) {

            Log.d(LOG_TAG, "##--str is " + str);
            textView2 = str;
        }
    }

    /**
     *
     * WordCounterTask
     * Purpose: to get the network data in background thread and update the UI
     * Return: a map which contains every distinct word and the number of times it occurred
     *
     */

    public class WordCounterTask extends AsyncTask<Void, Void, Map<String, Integer>> {

        private final String LOG_TAG = WordCounterTask.class.getSimpleName();
        Map<String, Integer> uniqueWords = new HashMap<String, Integer>();

        @Override
        protected Map<String, Integer> doInBackground(Void... params) {

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

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
                String lowerStr;
                while (((line = reader.readLine()) != null)) {

                    for (String string : line.split("[ !,?.]+")) {
                        lowerStr = string.toLowerCase();
                        if(uniqueWords.get(lowerStr) == null)
                            uniqueWords.put(lowerStr, 1);
                        else
                            uniqueWords.put(lowerStr, uniqueWords.get(lowerStr) + 1);
                    }
                }

                Log.d(LOG_TAG, uniqueWords.toString());


            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
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

            return uniqueWords;
        }

        @Override
        protected void onPostExecute(Map<String, Integer> map) {

            Log.d(LOG_TAG, "##--Char is " + map.toString());
            wordsMap = new HashMap<String, Integer>(map);

        }
    }
}
