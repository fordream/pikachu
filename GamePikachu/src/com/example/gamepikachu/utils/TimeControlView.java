package com.example.gamepikachu.utils;

import com.example.gamepikachu.R;
import com.vnp.core.common.CommonAndroid;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
//com.example.gamepikachu.utils.TimeControlView
public class TimeControlView extends LinearLayout {
	public TimeControlView(Context context, AttributeSet attrs) {
		super(context, attrs);
		CommonAndroid.getView(getContext(), R.layout.time_socre, this);
	}

	private int time = PikachuUtils.times;

	private GAMESTAUTS gamestauts = GAMESTAUTS.NONE;

	public void setGamestauts(GAMESTAUTS gamestauts) {
		this.gamestauts = gamestauts;
	}
}