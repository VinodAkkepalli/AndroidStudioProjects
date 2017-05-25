package com.akkepalli.vinod.backgroundtask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {
    @BindView(R.id.progress_bar) ProgressBar mProgress;
    @BindView(R.id.percentage) TextView percentage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind ButterKnife
        ButterKnife.bind(this);

        new CountProgress().execute();
    }


    class CountProgress extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            mProgress.setProgress(0);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... unused) {
            for (int i=0; i<101;i++ ) {
                if (isCancelled())
                    break;

                publishProgress(i);
                SystemClock.sleep(200);
            }

            return(null);
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            if (!isCancelled()) {

                mProgress.setVisibility(View.VISIBLE);

                // updating progress bar value
                mProgress.setProgress(progress[0]);
                // updating progess percentage text
                percentage.setText(progress[0].toString() + "%");
            }
        }

        @Override
        protected void onPostExecute(Void unused) {
            Toast.makeText(getApplicationContext(), R.string.done, Toast.LENGTH_SHORT).show();
        }
    }

}
