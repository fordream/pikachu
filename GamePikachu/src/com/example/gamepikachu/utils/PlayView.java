package com.example.gamepikachu.utils;

import com.vnp.core.common.CommonAndroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

//com.example.gamepikachu.utils.PlayView
@SuppressLint("WrongCall")
public class PlayView extends View {
	private Pikachu[][] pikachu = null;
	private Bitmap[] CardImages;

	private void init() {
		int width = getWidth();
		int height = getHeight();
		int dwidth = width / (PikachuUtils.columns + 2);
		if (width / (PikachuUtils.columns + 2) > height / (PikachuUtils.rows + 2)) {
			dwidth = height / (PikachuUtils.rows + 2);
		}

		int left = (getWidth() - PikachuUtils.columns * dwidth) / 2;
		int top = (getHeight() - PikachuUtils.rows * dwidth) / 2;
		for (int coloum = 0; coloum < PikachuUtils.columns; coloum++) {
			for (int row = 0; row < PikachuUtils.rows; row++) {
				pikachu[coloum][row] = new Pikachu();
				pikachu[coloum][row].create(dwidth, coloum, row, left, top);
			}
		}

		CardImages = new Bitmap[PikachuUtils.ImagePath.length];

		// tao va nap 15 hinh vao mang bitmap
		for (int i = 0; i < PikachuUtils.ImagePath.length; i++) {
			CardImages[i] = BitmapFactory.decodeResource(getResources(), PikachuUtils.ImagePath[i]);
			CardImages[i] = CommonAndroid.getScaledBitmap(CardImages[i], dwidth -2 , dwidth-2);
		}
	}

	public PlayView(Context context, AttributeSet attrs) {
		super(context, attrs);
		onCreate();
		setWillNotDraw(false);
	}

	public PlayView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		onCreate();
		setWillNotDraw(false);
	}

	private void onCreate() {

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (pikachu == null) {
			pikachu = new Pikachu[PikachuUtils.columns][PikachuUtils.rows];
			init();
		}

		for (int coloum = 0; coloum < PikachuUtils.columns; coloum++) {
			for (int row = 0; row < PikachuUtils.rows; row++) {
				if (pikachu[coloum][row] != null) {
					Bitmap bitmap = CardImages[0];
					pikachu[coloum][row].onMDraw(canvas, bitmap);

				}
			}
		}
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			for (int coloum = 0; coloum < PikachuUtils.columns; coloum++) {
				for (int row = 0; row < PikachuUtils.rows; row++) {
					if (pikachu[coloum][row].isSelected(event.getX(), event.getY())) {
						Toast.makeText(getContext(), String.format("coloum : %s  row : %s", coloum, row), Toast.LENGTH_LONG).show();
					}
				}
			}
		}
		return super.onTouchEvent(event);
	}
}