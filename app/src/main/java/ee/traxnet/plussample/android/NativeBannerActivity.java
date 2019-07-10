package ee.traxnet.plussample.android;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import ee.traxnet.plus.AdHolder;
import ee.traxnet.plus.AdRequestCallback;
import ee.traxnet.plus.TraxnetPlus;

public class NativeBannerActivity extends AppCompatActivity {
    private FrameLayout adContainer;
    private AdHolder adHolder;

    private static final String TAG = "NativeBannerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_native_banner);

        init();
    }

    private void init() {
        adContainer = findViewById(R.id.adContainer);

        adHolder = TraxnetPlus.createAdHolder(
                this, adContainer, R.layout.native_banner);

        requestAd();
    }

    private void requestAd() {
        TraxnetPlus.requestNativeBanner(
                this,
                BuildConfig.TRAXNET_NATIVE_BANNER,
                new AdRequestCallback() {
                    @Override
                    public void response() {
                        if (isDestroyed())
                            return;

                        Log.d(TAG, "Ad Response");
                        showAd();
                    }

                    @Override
                    public void error(@NonNull String message) {
                        if (isDestroyed())
                            return;

                        Log.e(TAG, "error: " + message);
                    }
                });
    }

    private void showAd() {
        TraxnetPlus.showAd(this, adHolder, BuildConfig.TRAXNET_NATIVE_BANNER);
    }
}
