package com.example.gamepikachu.utils;

import android.content.Context;
import android.graphics.Canvas;

public class BoardPikachu {
	private int columns = 14;
	private int rows = 9;
	private Pikachu[][] pikachu = new Pikachu[columns][rows];
	private BitmapManager bitmapManager = new BitmapManager();

	public BoardPikachu() {
	}

	public void onCreate(Context context, int widthBoard, int heightBoard) {
		int dwidth = widthBoard / (columns);
		if (widthBoard / (columns) > heightBoard / (rows)) {
			dwidth = heightBoard / (PikachuUtils.rows);
		}

		int left = (widthBoard - columns * dwidth) / 2;
		int top = (heightBoard - rows * dwidth) / 2;

		for (int coloum = 0; coloum < columns; coloum++) {
			for (int row = 0; row < rows; row++) {
				pikachu[coloum][row] = new Pikachu();
				pikachu[coloum][row].create(dwidth, coloum, row, left, top);
			}
		}

		bitmapManager.onCreate(context, dwidth);
	}

	public void onDraw(Canvas canvas) {

	}
}
