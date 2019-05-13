package ee.traxnet.plussample.android;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import ee.traxnet.plus.AdRequestCallback;
import ee.traxnet.plus.AdShowListener;
import ee.traxnet.plus.TraxnetPlus;

public class InterstitialActivity extends AppCompatActivity {

    private View btShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rewarded_video);

        init();
    }

    private void init() {
        View btRequest = findViewById(R.id.btRequest);
        btShow = findViewById(R.id.btShow);
        btShow.setEnabled(false);
        btRequest.setOnClickListener(v -> requestAd());
        btShow.setOnClickListener(v -> showAd());
    }

    private void requestAd() {
        TraxnetPlus.requestInterstitial(
                this, BuildConfig.TRAXNET_INTERSTITIAL,
                new AdRequestCallback() {
                    @Override
                    public void response() {
                        if (isDestroyed())
                            return;

                        btShow.setEnabled(true);
                    }

                    @Override
                    public void error(@NonNull String message) {
                        if (isDestroyed())
                            return;

                        Log.e("error", message);
                    }

                });
    }

    private void showAd() {
        TraxnetPlus.showAd(this, BuildConfig.TRAXNET_INTERSTITIAL,
                new AdShowListener() {
                    @Override
                    public void onOpened() {

                    }

                    @Override
                    public void onClosed() {

                    }

                    @Override
                    public void onRewarded() {

                    }

                    @Override
                    public void onError(String a) {

                    }
                });

        btShow.setEnabled(false);
    }
}
