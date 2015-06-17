package com.example.gamepikachu.utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.gamepikachu.R;

public class SoundManager {
	private MediaPlayer no_;
	private MediaPlayer Click;
	private MediaPlayer pair;
	private MediaPlayer level;
	private MediaPlayer no_move;
	private MediaPlayer bg;

	public SoundManager() {
	}

	public void init(Context c) {
		no_ = MediaPlayer.create(c, R.raw.no);
		Click = MediaPlayer.create(c, R.raw.click);
		pair = MediaPlayer.create(c, R.raw.pair);
		level = MediaPlayer.create(c, R.raw.level);
		no_move = MediaPlayer.create(c, R.raw.no_move);
		bg = MediaPlayer.create(c, R.raw.bgm);
		bg.setLooping(true);
	}

	public void playlevel() {
		new Thread() {
			public void run() {
				level.start();
			};
		}.start();
	}

	public void playno_move() {
		new Thread() {
			public void run() {
				no_move.start();
			};
		}.start();
	}

	public void playpair() {
		new Thread() {
			public void run() {
				pair.start();
			};
		}.start();
	}

	public void playNo() {
		new Thread() {
			public void run() {
				no_.start();
			};
		}.start();
	}

	public void playClick() {
		new Thread() {
			public void run() {
				Click.start();
			};
		}.start();
	}

	public void playBG(boolean b) {
		if (b) {
			bg.start();
		} else {
			bg.pause();
		}
	}

}
