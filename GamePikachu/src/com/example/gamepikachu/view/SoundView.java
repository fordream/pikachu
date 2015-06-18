package com.example.gamepikachu.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.gamepikachu.R;
import com.vnp.core.common.CommonAndroid;
//com.example.gamepikachu.view.SoundView
public class SoundView extends LinearLayout implements OnClickListener {
	private xstatus status = xstatus.CLOSE;
	private View sound_main;
	private ImageView sound_bg, sound_click, sound_open;

	public SoundView(Context context) {
		super(context);
		init();
	}

	public SoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SoundView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.sound, this);
		sound_main = CommonAndroid.getView(this, R.id.sound_main);
		sound_bg = CommonAndroid.getView(this, R.id.sound_bg);
		sound_click = CommonAndroid.getView(this, R.id.sound_click);
		sound_open = CommonAndroid.getView(this, R.id.sound_open);

		sound_open.setOnClickListener(this);
		sound_bg.setOnClickListener(this);
		sound_bg.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (status == xstatus.CLOSE || status == xstatus.OPEN) {
			if (v == sound_open) {
				open(status == xstatus.CLOSE);
			}
		}
	}

	private void open(boolean b) {
		status = b ? xstatus.RUNOPEN : xstatus.RUNCLOSE;
		final int min = (int) getResources().getDimension(R.dimen.dimen_30dp);
		final int max = (int) getResources().getDimension(R.dimen.dimen_90dp);
		final int add = (int) getResources().getDimension(R.dimen.dimen_20dp);
		final long time = 50;

		push(min, max, add, time);

	}

	private void push(final int min, final int max, final int add, final long time) {
		ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) sound_main.getLayoutParams();
		params.height = params.height + (xstatus.RUNOPEN == status ? add : -add);

		if (xstatus.RUNOPEN == status) {
			if (params.height > max) {
				params.height = max;
			}
		} else {
			if (params.height < min) {
				params.height = min;
			}
		}

		sound_main.setLayoutParams(params);

		if (xstatus.RUNOPEN == status && params.height < max //
				|| xstatus.RUNCLOSE == status && params.height > min//
		) {
			Runnable r = new Runnable() {
				
				@Override
				public void run() {
					push(min, max, add, time);
				}
			};
			new Handler().postDelayed(r, time);
		} else {
			status = (xstatus.RUNOPEN == status ? xstatus.OPEN : xstatus.CLOSE);
		}
	}

	public enum xstatus {
		NONE, RUNOPEN, RUNCLOSE, OPEN, CLOSE
	}

}
