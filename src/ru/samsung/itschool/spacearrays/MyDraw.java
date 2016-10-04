package ru.samsung.itschool.spacearrays;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyDraw extends View {

	public MyDraw(Context context, AttributeSet attrs) {
		super(context, attrs);
		makeSky();
	}

	Paint paint = new Paint();
	Bitmap rocket = BitmapFactory.decodeResource(getResources(), R.drawable.rocket);
	Matrix matrix = new Matrix();
	
	float xRocket = 300, yRocket = 300;
	float vxRocket = 0.5f, vyRocket = -0.5f;
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		drawSky(canvas);

		drawRocket(canvas, xRocket, yRocket, vxRocket, vyRocket);
		
		xRocket += vxRocket;
		yRocket += vyRocket;
		
		// Запрос на перерисовку экрана
		invalidate();
	}
	
	final int numStars = 500;
	
	int xStar[] = new int[numStars];
	int yStar[] = new int[numStars];
	int alphaStar[] = new int[numStars];
	
	void makeSky()
	{
		// Звезды генерируются в зоне maxX на maxY
		int maxX = 2000;
		int maxY = 2000;
		for (int i = 0; i < numStars; i++)
		{	
		   xStar[i] = (int)(Math.random() * maxX);
		   yStar[i] = (int)(Math.random() * maxY);
		   alphaStar[i] = (int)(Math.random() * 256);
		}   
	}
	
	void drawSky(Canvas canvas)
	{
		canvas.drawColor(Color.BLACK);
		paint.setColor(Color.YELLOW);
		paint.setStrokeWidth(2);
		for (int i = 0; i < numStars; i++)
		{	
		   paint.setAlpha(alphaStar[i]);
		   alphaStar[i] += (int)(Math.random() * 11) - 5;
		   if (alphaStar[i] > 255) alphaStar[i] = 255;
		   if (alphaStar[i] < 0) alphaStar[i] = 0;
		   canvas.drawPoint(xStar[i], yStar[i], paint);
		}   
	}
	
	void drawRocket(Canvas canvas, float x, float y, float vx, float vy)
	{
		matrix.setScale(0.2f, 0.2f);
		//Study mathematics, dear young programmer :)  
		matrix.postRotate((float)Math.toDegrees(Math.atan2(vy, vx)) + 45);
		matrix.postTranslate(xRocket, yRocket);
		paint.setAlpha(255);
		canvas.drawBitmap(rocket, matrix, paint);
	}
	
}
