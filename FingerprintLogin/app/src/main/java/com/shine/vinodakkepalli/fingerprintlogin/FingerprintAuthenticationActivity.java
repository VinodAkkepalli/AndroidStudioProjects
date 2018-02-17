package com.shine.vinodakkepalli.fingerprintlogin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinod Akkepalli on 2/17/2018.
 */

public class FingerprintAuthenticationActivity extends AppCompatActivity {

    @BindView(R.id.fp_label)
    TextView fpLabel;
    @BindView(R.id.fp_image)
    ImageView fpImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint_authentication);
        ButterKnife.bind(this);
    }
}
