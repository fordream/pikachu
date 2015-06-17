package com.example.gamepikachu.utils;

import android.graphics.Point;

import com.example.gamepikachu.R;

public class PikachuUtils {
	public static int columns = 14;
	public static int rows = 9;
	public static int number_pikachu = 30;
	public static int[] ImagePath = { R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6, R.drawable.image7, R.drawable.image8,
			R.drawable.image9, R.drawable.image10, R.drawable.image11, R.drawable.image12, R.drawable.image13, R.drawable.image14, R.drawable.image15 };

	public static Pikachu[] findByLines(Pikachu[][] pikachus, Pikachu p1, Pikachu p2) {
		Pikachu[] lines = null;
		Pikachu[] _lines = findByLine(pikachus, p1, p2);
		if (_lines != null) {
			lines = _lines;
		} else {

		}

		return lines;
	}

	private static Pikachu[] findByLine(Pikachu[][] pikachus, Pikachu p1, Pikachu p2) {
		Pikachu[] lines = null;
		boolean hasLine = false;
		if (p1.getPosition().x == p2.getPosition().x) {
			int start = Math.min(p1.getPosition().y, p2.getPosition().y);
			int end = Math.max(p1.getPosition().y, p2.getPosition().y);
			for (int i = start; i <= end; i++) {
				hasLine = true;

				Point p = pikachus[p1.getPosition().x][i].getPosition();
				if (p.equals(p1.getPosition())) {
					continue;
				}

				if (p.equals(p2.getPosition())) {
					continue;
				}

				if (pikachus[p1.getPosition().x][i].hasPikachu()) {
					hasLine = false;
					break;
				}
			}
		} else if (p1.getPosition().y == p2.getPosition().y) {
			int start = Math.min(p1.getPosition().x, p2.getPosition().x);
			int end = Math.max(p1.getPosition().x, p2.getPosition().x);

			for (int i = start; i <= end; i++) {
				hasLine = true;

				Point p = pikachus[i][p1.getPosition().y].getPosition();
				if (p.equals(p1.getPosition())) {
					continue;
				}

				if (p.equals(p2.getPosition())) {
					continue;
				}
				if (pikachus[i][p1.getPosition().y].hasPikachu()) {
					hasLine = false;
					break;
				}
			}
		}

		if (hasLine) {
			lines = new Pikachu[2];
			lines[0] = p1;
			lines[1] = p2;
		}
		return lines;
	}
}