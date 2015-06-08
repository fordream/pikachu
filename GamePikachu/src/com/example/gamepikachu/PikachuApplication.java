package com.example.gamepikachu;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

public class PikachuApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		Log.e("AAAAAAAAAAAAAA", "onCreate PikachuApplication");
		startService(new Intent(this, PikachuService.class));
	}
}
