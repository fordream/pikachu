package com.example.gamepikachu.utils;

import com.example.gamepikachu.R;
import com.vnp.core.common.CommonAndroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapManager {
	public int[] ImagePath = { R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6, R.drawable.image7, R.drawable.image8,
			R.drawable.image9, R.drawable.image10, R.drawable.image11, R.drawable.image12, R.drawable.image13, R.drawable.image14, R.drawable.image15 };

	private Bitmap[] CardImages = new Bitmap[ImagePath.length];

	public BitmapManager() {
	}

	public void onCreate(Context context, int dwidth) {
		for (int i = 0; i < PikachuUtils.ImagePath.length; i++) {
			CardImages[i] = CommonAndroid.getScaledBitmap(BitmapFactory.decodeResource(context.getResources(), PikachuUtils.ImagePath[i]), dwidth - 4, dwidth - 4);
		}
	}
}
