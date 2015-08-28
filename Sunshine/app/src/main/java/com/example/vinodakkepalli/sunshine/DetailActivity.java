package com.example.vinodakkepalli.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ShareActionProvider;
import android.widget.TextView;


public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A fragment containing a simple view.
     */
    public static class DetailFragment extends Fragment {

        private static final String TAG = DetailFragment.class.getSimpleName();
        private ShareActionProvider mShareActionProvider;
        private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
        private String mForecastStr;
        
        public DetailFragment() {
            //setHasOptionsMenu(true);
        }

        /*@Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            //super.onCreateOptionsMenu(menu, inflater);

            inflater.inflate(R.menu.detail_fragment,menu);

            MenuItem item = menu.findItem(R.id.action_share);

            mShareActionProvider = (ShareActionProvider) item.getActionProvider();

        }*/

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            Intent intent = getActivity().getIntent();

            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.detail_text);

            if(intent != null && intent.hasExtra("EXTRA_TEXT")){
                mForecastStr = intent.getStringExtra("EXTRA_TEXT");
                Log.d(TAG,"mForecastStr = " + mForecastStr);
                textView.setText(mForecastStr);
            }


            return rootView;
        }


        /*// Call to update the share intent
        private void setShareIntent(Intent shareIntent) {
            if (mShareActionProvider != null) {
                mShareActionProvider.setShareIntent(createShareForecastIntent());
            }else{
                Log.d(TAG,"share action provider is null?");
            }
        }

        private Intent createShareForecastIntent(){
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, mForecastStr + FORECAST_SHARE_HASHTAG);

            return shareIntent;
        }*/
    }
}
