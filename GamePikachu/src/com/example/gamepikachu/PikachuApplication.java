package com.example.gamepikachu;

import com.example.gamepikachu.utils.SoundManager;

import android.app.Application;
import android.content.Intent;

public class PikachuApplication extends Application {
	private SoundManager soundManager = new SoundManager();

	@Override
	public void onCreate() {
		super.onCreate();
		soundManager.init(this);
		startService(new Intent(this, PikachuService.class));
	}
	
	public SoundManager getSoundManager() {
		return soundManager;
	}
}
