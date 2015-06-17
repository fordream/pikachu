package com.example.gamepikachu.utils;

import android.graphics.Point;

import com.example.gamepikachu.R;
import com.vnp.core.common.LogUtils;

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
			Pikachu[] lineNgang = findByLineNgang(pikachus, p1, p2);
			Pikachu[] lineDoc = findByLineDoc(pikachus, p1, p2);

			if (lineNgang != null) {
				lines = lineNgang;
			}

			if (lines == null && lineDoc != null) {
				lines = lineDoc;
			}
		}

		return lines;
	}

	private static Pikachu[] findByLineNgang(Pikachu[][] pikachus, Pikachu p1, Pikachu p2) {
		Pikachu[] lines = null;

		int p1X = p1.getPosition().x;
		int p2X = p2.getPosition().x;
		int p1XMin = p1X, p1XMax = p1X;
		int p2XMin = p1X, p2XMax = p1X;
		for (int i = p1X - 1; i >= 0; i--) {
			if (pikachus[i][p1.getPosition().y].hasPikachu()) {
				break;
			}
			p1XMin = i;
		}

		for (int i = p1X + 1; i < pikachus.length; i++) {
			if (pikachus[i][p1.getPosition().y].hasPikachu()) {
				break;
			}
			p1XMax = i;
		}

		for (int i = p2X - 1; i >= 0; i--) {
			if (pikachus[i][p2.getPosition().y].hasPikachu()) {
				break;
			}
			p2XMin = i;
		}

		for (int i = p2X + 1; i < pikachus.length; i++) {
			if (pikachus[i][p2.getPosition().y].hasPikachu()) {
				break;
			}
			p2XMax = i;
		}

		for (int i = Math.max(p1XMin, p2XMin); i <= Math.min(p1XMax, p2XMax); i++) {
			Pikachu nP1 = pikachus[i][p1.getPosition().y];
			Pikachu nP2 = pikachus[i][p2.getPosition().y];
			Pikachu[] mLines = findByLine(pikachus, nP1, nP2);
			if (mLines != null) {
				lines = new Pikachu[4];
				lines[0] = p1;
				lines[1] = nP1;
				lines[2] = nP2;
				lines[3] = p2;
			}
		}
		return lines;
	}

	private static Pikachu[] findByLineDoc(Pikachu[][] pikachus, Pikachu p1, Pikachu p2) {
		Pikachu[] lines = null;

		int p1Y = p1.getPosition().y;
		int p2Y = p2.getPosition().y;
		int p1YMin = p1Y, p1YMax = p1Y;
		int p2YMin = p1Y, p2YMax = p1Y;
		for (int i = p1Y - 1; i >= 0; i--) {
			if (pikachus[p1.getPosition().x][i].hasPikachu()) {
				break;
			}
			p1YMin = i;
		}

		for (int i = p1Y + 1; i < pikachus[0].length; i++) {
			if (pikachus[p1.getPosition().x][i].hasPikachu()) {
				break;
			}
			p1YMax = i;
		}

		for (int i = p2Y - 1; i >= 0; i--) {
			if (pikachus[p2.getPosition().x][i].hasPikachu()) {
				break;
			}
			p2YMin = i;
		}

		for (int i = p2Y + 1; i < pikachus[0].length; i++) {
			if (pikachus[p2.getPosition().x][i].hasPikachu()) {
				break;
			}
			p2YMax = i;
		}

		for (int i = Math.max(p1YMin, p2YMin); i <= Math.min(p1YMax, p2YMax); i++) {
			Pikachu nP1 = pikachus[p1.getPosition().x][i];
			Pikachu nP2 = pikachus[p1.getPosition().x][i];
			Pikachu[] mLines = findByLine(pikachus, nP1, nP2);
			if (mLines != null) {
				lines = new Pikachu[4];
				lines[0] = p1;
				lines[1] = nP1;
				lines[2] = nP2;
				lines[3] = p2;
			}
		}
		return lines;
	}

	private static Pikachu[] findByLine(Pikachu[][] pikachus, Pikachu p1, Pikachu p2) {
		Pikachu[] lines = null;

		if (p1.getPosition().equals(p2.getPosition())) {
			return lines;
		}
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