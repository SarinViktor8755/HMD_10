package com.mygdx.game.deathmatch;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.mygdx.game.deathmatch.adMod.AdAds;


public class AndroidLauncher  extends AndroidApplication implements AdAds {
	private InterstitialAd mInterstitialAd;
	private static final int PERMISSION_REQUEST_CODE = 1;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		GdxNativesLoader.load();
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useGyroscope = false;
		// initFlavor(ZombiKiller);
		int tip = 0;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (!isVoicePermissionGranted()) {
				requestRecordVoice();
			}
			if(isVoicePermissionGranted()) tip = 1;
		}

		initialize(new ZombiKiller(tip,this), config);
		////////////////////////////////////////////////////
		MobileAds.initialize(this, "ca-app-pub-3062739183422189~2319030096");
		mInterstitialAd = new InterstitialAd(this);
		//mInterstitialAd.setAdUnitId("ca-app-pub-3062739183422189/6323969964");
		mInterstitialAd.setAdUnitId("ca-app-pub-3062739183422189/1599925199"); //
		//mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/5224354917"); // test
		//ca-app-pub-3940256099942544/6300978111
		mInterstitialAd.loadAd(new AdRequest.Builder().build());
		mInterstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				mInterstitialAd.loadAd(new AdRequest.Builder().build());
			}
		});
		////////////////////////////////////////////////////oplata


	}

	public boolean isVoicePermissionGranted() {
		return getContext().checkCallingOrSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
	}

	@TargetApi(Build.VERSION_CODES.M)
	public void requestRecordVoice() {
		requestPermissions(new String[] { Manifest.permission.RECORD_AUDIO }, PERMISSION_REQUEST_CODE);
	}

	@Override
	public void show() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (mInterstitialAd.isLoaded()) {
					mInterstitialAd.show();
				} else {
					Gdx.app.log("ANDROID", "The interstitial wasn't loaded yet.");
				}
			}
		});

	}
	//implements AdAds
}
