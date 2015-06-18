package com.example.gamepikachu.utils;

import java.util.Random;

import com.vnp.core.common.CommonAndroid;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class Pikachu {
	private Point point = new Point();
	private String typePokemon = "1";// 0->number_pikachu
	private int dwidth;

	public Pikachu() {
		typePokemon = "";
	}

	public boolean hasPikachu() {
		return !"".equals(typePokemon);
	}

	public Point getPoint() {
		return point;
	}

	public void setTypePokemon(String typePokemon) {
		this.typePokemon = typePokemon;
	}

	public String getTypePokemon() {
		return typePokemon;
	}

	public void create(int dwidth, int coloum, int row, int left, int top) {
		this.dwidth = dwidth;
		point = new Point(coloum * dwidth + left, dwidth * row + top);
		position = new Point(coloum, row);
	}

	private Point position = new Point();

	public Point getPosition() {
		return position;
	}

	public void onMDraw(Canvas canvas, Bitmap[] bit) {
		if (!CommonAndroid.isBlank(typePokemon)) {
			Paint paint = new Paint();
			paint.setColor(Color.LTGRAY);
			int index = Integer.parseInt(typePokemon);
			canvas.drawBitmap(bit[index], point.x + 2, point.y + 2, null);
		}

	}

	public void onDrawBG(Canvas canvas, Bitmap b1, Bitmap b2) {

		// if (!CommonAndroid.isBlank(typePokemon)) {
		if (getPosition().x == 0 || getPosition().y == 0 || getPosition().x == PikachuUtils.columns - 1 || getPosition().y == PikachuUtils.rows - 1) {
			return;
		}
		Bitmap bitmap = b1;
		if (getPosition().x % 2 == 0) {
			if (getPosition().y % 2 == 0) {
				bitmap = b2;
			}
		} else {
			if (getPosition().y % 2 == 1) {
				bitmap = b2;
			}
		}

		canvas.drawBitmap(bitmap, point.x, point.y, null);

	}

	public boolean isSelected(float x, float y) {
		if (point.x < x && x < point.x + dwidth) {
			if (point.y < y && y < point.y + dwidth) {
				return true;
			}
		}

		return false;
	}

	public void onDrawSelected(Canvas canvas) {

		if (!CommonAndroid.isBlank(typePokemon)) {
			Paint paint = new Paint();
			paint.setColor(Color.RED);
			canvas.drawRect(point.x + 0, point.y + 0, point.x + dwidth - 0, point.y + dwidth - 0, paint);
		}
	}

	public void eat() {
		typePokemon = "";
	}

	public void random(int length) {
		typePokemon = new Random().nextInt(length) + "";
	}

	public float getCenterX() {
		return point.x + dwidth / 2;
	}

	public float getCenterY() {
		return point.y + dwidth / 2;
	}
}