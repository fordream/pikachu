package com.example.gamepikachu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class PikachuService extends Service {
	private MediaPlayer no_;
	private MediaPlayer Click;
	private MediaPlayer pair;
	private MediaPlayer level;
	private MediaPlayer no_move;

	private void initSound(Context c) {
		no_ = MediaPlayer.create(c, R.raw.no);
		Click = MediaPlayer.create(c, R.raw.click);
		pair = MediaPlayer.create(c, R.raw.pair);
		level = MediaPlayer.create(c, R.raw.level);
		no_move = MediaPlayer.create(c, R.raw.no_move);
	}

	@SuppressLint("NewApi")
	@Override
	public void onCreate() {
		super.onCreate();
		initSound(this);
		Log.e("AAAAAAAAAAAAAA", "onCreate");
		this.getApplication().registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

			@Override
			public void onActivityStopped(Activity activity) {
				Log.e("AAAAAAAAAAAAAA", "onActivityStopped");
			}

			@Override
			public void onActivityStarted(Activity activity) {
				Log.e("AAAAAAAAAAAAAA", "onActivityStarted");
			}

			@Override
			public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
				Log.e("AAAAAAAAAAAAAA", "onActivitySaveInstanceState");
			}

			@Override
			public void onActivityResumed(Activity activity) {
				Log.e("AAAAAAAAAAAAAA", "onActivityResumed");
			}

			@Override
			public void onActivityPaused(Activity activity) {
				Log.e("AAAAAAAAAAAAAA", "onActivityPaused");
			}

			@Override
			public void onActivityDestroyed(Activity activity) {
				Log.e("AAAAAAAAAAAAAA", "onActivityDestroyed");
			}

			@Override
			public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
				Log.e("AAAAAAAAAAAAAA", "onActivityCreated");
			}
		});
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}