package ee.traxnet.plussample.android;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import ee.traxnet.plus.TraxnetPlus;
import ee.traxnet.plus.AdRequestCallback;
import ee.traxnet.plus.TraxnetPlusBannerType;

public class StandardBannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_standard_banner);

        TraxnetPlus.showBannerAd(
                this, findViewById(R.id.standardBanner),
                BuildConfig.TRAXNET_STANDARD_BANNER,
                TraxnetPlusBannerType.BANNER_320x100,
                new AdRequestCallback() {
                    @Override
                    public void response() {
                        if (isDestroyed())
                            return;

                        Log.d("StandardBanner", "response");

                    }

                    @Override
                    public void error(@NonNull String message) {
                        if (isDestroyed())
                            return;

                        Log.e("StandardBanner", message);
                    }

                });
    }
}
