package com.example.gamepikachu.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class Pikachu {
	private Point point = new Point();
	private String typePokemon = "";// 0->number_pikachu
	private int dwidth;

	public Point getPoint() {
		return point;
	}

	public String getTypePokemon() {
		return typePokemon;
	}

	public void create(int dwidth, int coloum, int row, int left, int top) {
		this.dwidth = dwidth;
		point = new Point(coloum * dwidth + left, dwidth * row + top);
	}

	public void onMDraw(Canvas canvas, Bitmap bit) {
		Paint paint = new Paint();
		paint.setColor(Color.LTGRAY);
		canvas.drawRect(point.x + 1, point.y + 1, point.x + dwidth - 1, point.y + dwidth - 1, paint);
		canvas.drawBitmap(bit, point.x + 1, point.y + 1, null);
	}

	public boolean isSelected(float x, float y) {

		if (point.x < x && x < point.x + dwidth) {
			if (point.y < y && y < point.y + dwidth) {
				return true;
			}
		}
		return false;
	}
}