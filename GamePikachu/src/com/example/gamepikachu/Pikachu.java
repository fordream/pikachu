package com.example.gamepikachu;

import com.example.gamepikachu.DrawView;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class Pikachu extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_pikachu);
		// khoi tao 1 lan duy nhat
		// MediaPlayer bgSound =MediaPlayer.create(this, R.raw.bgm);
		// DrawView drawView = new DrawView(this);
		// setContentView(drawView);
		// drawView.requestFocus();

		findViewById(R.id.btn_newgame).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		((com.example.gamepikachu.utils.PlayView) findViewById(R.id.playview)).onCreateBroad();
	}
}