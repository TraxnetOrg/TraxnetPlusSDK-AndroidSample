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
        View btRewardedVideo = findViewById(R.id.btRewardedVideo);
        View btInterstitial = findViewById(R.id.btInterstitial);
        View btNativeBanner = findViewById(R.id.btNativeBanner);
        View btStandardBanner = findViewById(R.id.btStandardBanner);
        View btNativeBannerInList = findViewById(R.id.btNativeBannerInList);

        btRewardedVideo.setOnClickListener(v -> startActivity(RewardedVideoActivity.class));
        btInterstitial.setOnClickListener(v -> startActivity(InterstitialActivity.class));
        btNativeBanner.setOnClickListener(v -> startActivity(NativeBannerActivity.class));
        btStandardBanner.setOnClickListener(v -> startActivity(StandardBannerActivity.class));
        btNativeBannerInList.setOnClickListener(v -> startActivity(NativeBannerInListActivity.class));
    }

    private void startActivity(Class cla) {
        Intent intent = new Intent(this, cla);
        startActivity(intent);
    }
}
