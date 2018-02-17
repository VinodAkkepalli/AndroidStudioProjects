package com.shine.vinodakkepalli.fingerprintlogin;

import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Vinod Akkepalli on 2/17/2018.
 */

public final class MainActivity extends AppCompatActivity {

    @BindView(R.id.fp_button)
    Button fpCheckButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.fp_button)
    void onClick() {
        //Check mobile device compatibility for Fingerprint Authentication
        if (isDeviceFingerprintCompatible()) {
            Intent startActivityIntent = new Intent(this, FingerprintAuthenticationActivity.class);
            startActivity(startActivityIntent);
        }
    }

    private boolean isDeviceFingerprintCompatible() {
        boolean compatible = false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            //Fingerprint API only available on from Android 6.0 (M)
            FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

            if (fingerprintManager != null) {
                if (!fingerprintManager.isHardwareDetected()) {
                    Toast.makeText(this,
                            "Can't use Fingerprint!! Fingerprint scanner not available!",
                            Toast.LENGTH_LONG).show();
                } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                    Toast.makeText(this,
                            "Can't use Fingerprint!! No Fingerprints are enrolled on the device yet!",
                            Toast.LENGTH_LONG).show();
                } else {
                    compatible = true;
                }
            }
        } else {
            Toast.makeText(this,
                    "Can't use Fingerprint!! Device is running on lower version than Marshmallow!",
                    Toast.LENGTH_LONG).show();
        }
        return compatible;
    }

}
