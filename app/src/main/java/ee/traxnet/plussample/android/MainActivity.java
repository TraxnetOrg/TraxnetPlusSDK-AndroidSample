package ee.traxnet.plussample.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import ee.traxnet.plus.TraxnetPlus;

public class MainActivity extends AppCompatActivity {
    private View btRewardedVideo;
    private View btInterstitial;
    private View btNativeBanner;
    private View btStandardBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TraxnetPlus.initialize(this, BuildConfig.TRAXNET_KEY);

        init();
    }

    private void init() {
        btRewardedVideo = findViewById(R.id.btRewardedVideo);
        btInterstitial = findViewById(R.id.btInterstitial);
        btNativeBanner = findViewById(R.id.btNativeBanner);
        btStandardBanner = findViewById(R.id.btStandardBanner);

        btRewardedVideo.setOnClickListener(v -> startActivity(RewardedVideoActivity.class));
        btInterstitial.setOnClickListener(v -> startActivity(InterstitialActivity.class));
        btNativeBanner.setOnClickListener(v -> startActivity(NativeBannerActivity.class));
        btStandardBanner.setOnClickListener(v -> startActivity(StandardBannerActivity.class));
    }

    private void startActivity(Class cla) {
        Intent intent = new Intent(this, cla);
        startActivity(intent);
    }
}
