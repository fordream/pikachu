package com.example.gamepikachu.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.example.gamepikachu.PikachuApplication;
import com.example.gamepikachu.R;
import com.vnp.core.common.CommonAndroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

//com.example.gamepikachu.utils.PlayView
@SuppressLint("WrongCall")
public class PlayView extends View {

	private Pikachu[][] pikachu = null;
	private Bitmap[] CardImages;
	private GAMESTAUTS gamestauts = GAMESTAUTS.NONE;

	Bitmap bg_1, bg_2;

	public void setGamestauts(GAMESTAUTS gamestauts) {
		this.gamestauts = gamestauts;
	}

	private void init() {
		int width = getWidth();
		int height = getHeight();
		int dwidth = width / (PikachuUtils.columns);
		if (width / (PikachuUtils.columns) > height / (PikachuUtils.rows)) {
			dwidth = height / (PikachuUtils.rows);
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
			CardImages[i] = CommonAndroid.getScaledBitmap(BitmapFactory.decodeResource(getResources(), PikachuUtils.ImagePath[i]), dwidth - 4, dwidth - 4);
		}

		bg_1 = CommonAndroid.getScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bg_3), dwidth - 2, dwidth - 2);
		bg_2 = CommonAndroid.getScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bg_3), dwidth - 2, dwidth - 2);
	}

	public PlayView(Context context, AttributeSet attrs) {
		super(context, attrs);
		onCreate();
		// setWillNotDraw(false);

		invalidate();
	}

	public PlayView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		onCreate();
		// setWillNotDraw(false);
		invalidate();
	}

	private void onCreate() {
		picked1.x = -1;
		picked2.x = -1;
		picked1.y = -1;
		picked2.y = -1;
	}

	public void onCreateBroad() {
		setGamestauts(GAMESTAUTS.CREATING);
		picked1.x = -1;
		picked2.x = -1;
		picked1.y = -1;
		picked2.y = -1;
		List<Pikachu> pikas = new ArrayList<Pikachu>();

		for (int coloum = 1; coloum < PikachuUtils.columns - 1; coloum++) {
			for (int row = 1; row < PikachuUtils.rows - 1; row++) {
				pikas.add(pikachu[coloum][row]);
			}
		}

		int count = (PikachuUtils.columns - 2) * (PikachuUtils.rows - 2) / PikachuUtils.ImagePath.length;
		if (count % 2 == 1) {
			count++;
		}
		int type = 0;
		while (pikas.size() > 0) {
			int index = new Random().nextInt(pikas.size());
			Pikachu p = pikas.get(index);
			pikas.remove(index);

			count--;
			p.setTypePokemon(type + "");
			if (count == 0) {
				count = (PikachuUtils.columns - 2) * (PikachuUtils.rows - 2) / PikachuUtils.ImagePath.length;
				if (count % 2 == 1) {
					count++;
				}
				type++;
			}
		}

		invalidate();

		setGamestauts(GAMESTAUTS.RUNING);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (pikachu == null) {
			pikachu = new Pikachu[PikachuUtils.columns][PikachuUtils.rows];
			try {
				init();
			} catch (Exception ex) {

			}
		}

		for (int coloum = 0; coloum < PikachuUtils.columns; coloum++) {
			for (int row = 0; row < PikachuUtils.rows; row++) {
				if (pikachu[coloum][row] != null) {
					Bitmap bitmap = CardImages[0];
					pikachu[coloum][row].onDrawBG(canvas, bg_1, bg_2);
					if (coloum == picked1.x && row == picked1.y) {
						pikachu[coloum][row].onDrawSelected(canvas);
					}

					if (coloum == picked2.x && row == picked2.y) {
						pikachu[coloum][row].onDrawSelected(canvas);
					}

					pikachu[coloum][row].onMDraw(canvas, CardImages);
				}
			}
		}

		if (lines != null) {
			Paint paint = new Paint();
			paint.setColor(Color.RED);

			if (lines.length == 2) {
				Pikachu p1 = lines[0];
				Pikachu pi2 = lines[1];

				if (p1 != null && pi2 != null)
					canvas.drawLine(p1.getCenterX(), p1.getCenterY(), pi2.getCenterX(), pi2.getCenterY(), paint);
			}
			if (lines.length == 4) {
				Pikachu pi1 = lines[0];
				Pikachu pi2 = lines[1];
				Pikachu pi3 = lines[2];
				Pikachu pi4 = lines[3];
				canvas.drawLine(pi1.getCenterX(), pi1.getCenterY(), pi2.getCenterX(), pi2.getCenterY(), paint);
				canvas.drawLine(pi3.getCenterX(), pi3.getCenterY(), pi2.getCenterX(), pi2.getCenterY(), paint);
				canvas.drawLine(pi3.getCenterX(), pi3.getCenterY(), pi4.getCenterX(), pi4.getCenterY(), paint);
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (!(GAMESTAUTS.RUNING == gamestauts)) {
			return super.onTouchEvent(event);
		}

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			for (int coloum = 0; coloum < PikachuUtils.columns; coloum++) {
				for (int row = 0; row < PikachuUtils.rows; row++) {
					if (pikachu[coloum][row].isSelected(event.getX(), event.getY()) && pikachu[coloum][row].hasPikachu()) {
						if (picked1.x == -1) {
							picked1.x = coloum;
							picked1.y = row;
							// sound click
							// FIXME
							((PikachuApplication) getContext().getApplicationContext()).getSoundManager().playClick();
							invalidate();
						} else if (picked1.x == coloum && picked1.y == row) {
							picked1.x = -1;
							picked1.y = -1;

							// sound no
							// FIXME
							((PikachuApplication) getContext().getApplicationContext()).getSoundManager().playNo();
							invalidate();
						} else if (picked2.x == -1) {
							picked2.x = coloum;
							picked2.y = row;
							invalidate();
							check();
						}
					}
				}
			}
		}
		return super.onTouchEvent(event);
	}

	private Pikachu[] lines = null;

	private void check() {

		setGamestauts(GAMESTAUTS.CHECKING);
		new Thread(new Runnable() {

			@Override
			public void run() {
				findLine();
				handler.sendEmptyMessage(0);

				if (lines != null) {
					// sound un eat
					// FIXME
					((PikachuApplication) getContext().getApplicationContext()).getSoundManager().playpair();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
					}

					// eat
					pikachu[picked1.x][picked1.y].eat();
					pikachu[picked2.x][picked2.y].eat();
					// clear
					lines = null;
					picked1.x = -1;
					picked1.y = -1;
					picked2.x = -1;
					picked2.y = -1;

					if (PikachuUtils.isEndGame(pikachu)) {
						setGamestauts(GAMESTAUTS.END);
					} else if (!PikachuUtils.canFindWay(pikachu)) {
						setGamestauts(GAMESTAUTS.GAMEOVER);
					} else {
						setGamestauts(GAMESTAUTS.RUNING);
					}
					handler.sendEmptyMessage(0);
				} else {
					picked1.x = -1;
					picked1.y = -1;
					picked2.x = -1;
					picked2.y = -1;
					handler.sendEmptyMessage(0);
					setGamestauts(GAMESTAUTS.RUNING);

					// sound un eat
					// FIXME

					((PikachuApplication) getContext().getApplicationContext()).getSoundManager().playno_move();
				}
			}
		}).start();
	}

	private Handler handler = new Handler() {
		public void dispatchMessage(android.os.Message msg) {
			invalidate();

			if (gamestauts == GAMESTAUTS.END || gamestauts == GAMESTAUTS.GAMEOVER) {
				CommonAndroid.showDialog(getContext(), gamestauts == GAMESTAUTS.END ? "end" : "gameover", null);
			}
		};
	};

	private void findLine() {
		lines = null;
		Pikachu pikachu1 = pikachu[picked1.x][picked1.y];
		Pikachu pikachu2 = pikachu[picked2.x][picked2.y];

		if (pikachu1.getTypePokemon().equals(pikachu2.getTypePokemon()) && !CommonAndroid.isBlank(pikachu1.getTypePokemon())) {
			lines = PikachuUtils.findByLines(pikachu, pikachu1, pikachu2);
		}

		// invalidate();
	}

	private Point picked1 = new Point(), picked2 = new Point();
}