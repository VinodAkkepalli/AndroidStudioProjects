package com.shine.vinodakkepalli.fingerprintlogin;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;

/**
 * Created by Vinod Akkepalli on 2/18/2018.
 */

@TargetApi(Build.VERSION_CODES.M)
@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    Context context;

    public FingerprintHandler(Context context) {
        this.context = context;
    }

    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){

        fingerprintManager.authenticate(cryptoObject, new CancellationSignal(), 0, this, null);

    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);

        updateUI(R.drawable.ic_check_circle_black_24dp, context.getString(R.string.fp_auth_success_message));
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();

        updateUI(R.drawable.ic_error_black_24dp, context.getString(R.string.fp_auth_failed_message));
    }

    private void updateUI(int resourceId, String label){
        ((MainActivity)context).setImage(resourceId);
        ((MainActivity)context).setInstructionLabel(label);
    }
}
