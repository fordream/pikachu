package com.example.gamepikachu.utils;

import java.util.HashMap;

public class PikaLevel {
	private HashMap<Integer, GAMEMODE> levels = new HashMap<Integer, GAMEMODE>();

	public PikaLevel() {
		levels.put(1, GAMEMODE.NONE);
		levels.put(2, GAMEMODE.LEN);
		levels.put(3, GAMEMODE.TRAI);
		levels.put(4, GAMEMODE.XUONG);
		levels.put(5, GAMEMODE.PHAI);
		levels.put(6, GAMEMODE.LENTRENXUONGDUOI);
		levels.put(7, GAMEMODE.SANNGTRAISANGPHAI);
		levels.put(8, GAMEMODE.TRENDUOIVAOGIUA);
		levels.put(9, GAMEMODE.TRAIPPHAIVAOGIUA);
	}

	public GAMEMODE getLevelMode(Integer level) {
		GAMEMODE levelMode = null;
		if (levels.containsKey(level)) {
			levels.get(level);
		}
		return levelMode;
	}
}
