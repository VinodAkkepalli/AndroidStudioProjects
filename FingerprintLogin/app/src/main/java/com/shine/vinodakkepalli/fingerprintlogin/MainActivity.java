package com.shine.vinodakkepalli.fingerprintlogin;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinod Akkepalli on 2/17/2018.
 */

public final class MainActivity extends AppCompatActivity {

    @BindView(R.id.fp_label)
    TextView fpLabel;
    @BindView(R.id.fp_image)
    ImageView fpImage;

    FingerprintManager fingerprintManager;
    private Cipher cipher;
    private KeyStore keyStore;
    private String KEY_NAME =  "AndroidKey";
    private KeyguardManager keyguardManager;

    @TargetApi(Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_fingerprint_authentication);
        ButterKnife.bind(this);

        //Check mobile device compatibility for Fingerprint Authentication
        if (isDeviceFingerprintCompatible()) {

            fpLabel.setVisibility(View.VISIBLE);
            fpImage.setVisibility(View.VISIBLE);

            generateKey();

            if(cipherInit()){

                FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
                FingerprintHandler fingerprintHandler = new FingerprintHandler(this);
                fingerprintHandler.startAuth(fingerprintManager, cryptoObject);
            }

        }
    }

    private boolean isDeviceFingerprintCompatible() {
        boolean compatible = false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            //Fingerprint API only available on from Android 6.0 (M)
            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            if (fingerprintManager != null) {
                if (!fingerprintManager.isHardwareDetected()) {
                    Toast.makeText(this,
                            "Can't use Fingerprint!! Fingerprint scanner not available!",
                            Toast.LENGTH_LONG).show();
                } else if(!keyguardManager.isKeyguardSecure()){
                    Toast.makeText(this,
                            "Can't use Fingerprint!! First setup device lock!",
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

    @TargetApi(Build.VERSION_CODES.M)
    private void generateKey() {

        try {

            keyStore = KeyStore.getInstance("AndroidKeyStore");
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();

        } catch (KeyStoreException | IOException | CertificateException
                | NoSuchAlgorithmException | InvalidAlgorithmParameterException
                | NoSuchProviderException e) {

            e.printStackTrace();

        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }


        try {

            keyStore.load(null);

            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);

            cipher.init(Cipher.ENCRYPT_MODE, key);

            return true;

        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }

    }


    public void setInstructionLabel(String label) {
        this.fpLabel.setText(label);
    }

    public void setImage(int resourceId) {
        this.fpImage.setImageResource(resourceId);
    }
}
