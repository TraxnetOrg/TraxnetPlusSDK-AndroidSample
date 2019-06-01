package ee.traxnet.plussample.android;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import ee.traxnet.plus.AdRequestCallback;
import ee.traxnet.plus.AdShowListener;
import ee.traxnet.plus.TraxnetPlus;

public class RewardedVideoActivity extends AppCompatActivity {

    private static final String TAG = "RewardActivity";

    private View btShow;
    private View btRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rewarded_video);
        init();
    }

    private void init() {
        btRequest = findViewById(R.id.btRequest);
        btShow = findViewById(R.id.btShow);
        btShow.setEnabled(false);
        btRequest.setOnClickListener(v -> requestAd());
        btShow.setOnClickListener(v -> showAd());
    }

    private void requestAd() {
        TraxnetPlus.requestRewardedVideo(
                this, BuildConfig.TRAXNET_REWARDED_VIDEO,
                new AdRequestCallback() {
                    @Override
                    public void response() {
                        if (isDestroyed())
                            return;

                        btShow.setEnabled(true);
                        Log.d(TAG, "Ad Response");
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
        TraxnetPlus.showAd(this, BuildConfig.TRAXNET_REWARDED_VIDEO,
                new AdShowListener() {
                    @Override
                    public void onOpened() {
                        Log.d(TAG, "Ad Opened");

                    }

                    @Override
                    public void onClosed() {
                        Log.d(TAG, "Ad Closed");

                    }

                    @Override
                    public void onRewarded() {
                        Log.d(TAG, "Reward");

                    }

                    @Override
                    public void onError(String s) {
                        Log.e(TAG, "Reward");

                    }
                });
        btShow.setEnabled(false);
    }
}
