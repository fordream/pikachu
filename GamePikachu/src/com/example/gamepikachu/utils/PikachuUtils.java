package com.example.gamepikachu.utils;

import java.util.ArrayList;
import java.util.List;

import com.example.gamepikachu.R;

public class PikachuUtils {
	// 14
	public static int columns = 12 ;
	// 10
	public static int rows = 8;
	public static int number_pikachu = 30;
	public static int[] ImagePath = { R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6, R.drawable.image7, R.drawable.image8,
			R.drawable.image9, R.drawable.image10, R.drawable.image11, R.drawable.image12, R.drawable.image13, R.drawable.image14, R.drawable.image15 };

	public static boolean qucikCheckLine(Pikachu[][] pikachus, Pikachu p1, Pikachu p2) {
		boolean checkByY = (p1.getPosition().x == p2.getPosition().x);
		int start = Math.min(checkByY ? p1.getPosition().y : p1.getPosition().x, checkByY ? p2.getPosition().y : p2.getPosition().x);
		int end = Math.max(checkByY ? p1.getPosition().y : p1.getPosition().x, checkByY ? p2.getPosition().y : p2.getPosition().x);

		for (int i = start; i < end; i++) {
			Pikachu p = pikachus[checkByY ? p1.getPosition().x : i][checkByY ? i : p1.getPosition().y];
			if (p1.getPosition().equals(p.getPosition())) {
			} else if (p2.getPosition().equals(p.getPosition())) {
			} else if (p.hasPikachu()) {
				return false;
			}
		}
		return true;
	}

	private static int lengthWay(Pikachu... p1) {
		int length = 0;
		length = Math.abs(p1[0].getPosition().x - p1[1].getPosition().x) + Math.abs(p1[0].getPosition().y - p1[1].getPosition().y);
		length = length + Math.abs(p1[2].getPosition().x - p1[1].getPosition().x) + Math.abs(p1[2].getPosition().y - p1[1].getPosition().y);
		length = length + Math.abs(p1[2].getPosition().x - p1[3].getPosition().x) + Math.abs(p1[2].getPosition().y - p1[3].getPosition().y);
		return length;
	}

	public static Pikachu[] findByLines(Pikachu[][] pikachus, Pikachu p1, Pikachu p2) {
		Pikachu[] lines = null;
		for (int i = 0; i < pikachus.length; i++) {
			Pikachu p01 = pikachus[i][p1.getPosition().y];
			Pikachu p02 = pikachus[i][p2.getPosition().y];
			if ((p1.equals(p01) || !p1.equals(p01) && !p01.hasPikachu()) && (p2.equals(p02) || !p2.equals(p02) && !p02.hasPikachu())) {
				if (qucikCheckLine(pikachus, p1, p01) && qucikCheckLine(pikachus, p01, p02) && qucikCheckLine(pikachus, p02, p2)) {
					if (lines == null || (lines != null && lengthWay(lines) >= lengthWay(p1, p01, p02, p2))) {
						lines = new Pikachu[4];
						lines[0] = p1;
						lines[1] = p01;
						lines[2] = p02;
						lines[3] = p2;
					}
				}
			}
		}

		for (int i = 0; i < pikachus[0].length; i++) {
			Pikachu p01 = pikachus[p1.getPosition().x][i];
			Pikachu p02 = pikachus[p2.getPosition().x][i];
			if ((p1.equals(p01) || !p1.equals(p01) && !p01.hasPikachu()) && (p2.equals(p02) || !p2.equals(p02) && !p02.hasPikachu())) {
				if (qucikCheckLine(pikachus, p1, p01) && qucikCheckLine(pikachus, p01, p02) && qucikCheckLine(pikachus, p02, p2)) {
					if (lines == null || (lines != null && lengthWay(lines) >= lengthWay(p1, p01, p02, p2))) {
						lines = new Pikachu[4];
						lines[0] = p1;
						lines[1] = p01;
						lines[2] = p02;
						lines[3] = p2;
					}
				}
			}
		}
		return lines;
	}

	public static boolean isEndGame(Pikachu[][] pikachu) {

		for (int coloum = 0; coloum < PikachuUtils.columns; coloum++) {
			for (int row = 0; row < PikachuUtils.rows; row++) {
				if (pikachu[coloum][row].hasPikachu()) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean canFindWay(Pikachu[][] pikachu) {
		List<Pikachu> pikachus = new ArrayList<Pikachu>();
		for (int coloum = 0; coloum < PikachuUtils.columns; coloum++) {
			for (int row = 0; row < PikachuUtils.rows; row++) {
				pikachus.add(pikachu[coloum][row]);
			}
		}

		for (int coloum = 0; coloum < PikachuUtils.columns; coloum++) {
			for (int row = 0; row < PikachuUtils.rows; row++) {
				Pikachu p1 = pikachu[coloum][row];
				if (p1.hasPikachu()) {
					for (Pikachu p2 : pikachus) {
						if (!p2.getPosition().equals(p1.getPosition()) && p2.hasPikachu()) {
							if (findByLines(pikachu, p1, p2) != null) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
}