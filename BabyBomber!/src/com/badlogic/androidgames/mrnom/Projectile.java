package com.badlogic.androidgames.mrnom;

import android.graphics.Rect;
import android.util.Log;

import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Pixmap;



public class Projectile 
{
	public int posX = 0, posY = 0;
	Pixmap projectilePixmap = Assets.poopyDiaper;
	int run, rise, length, unitX, unitY, X, Y, distance, vX, vY, v = 10;
	public Rect projectileRect = new Rect();
	public boolean isDead = false;
	
	
	public Projectile(int x, int y, int BX, int BY)
	{
		X = BX;
		Y = BY;		
		distance = (int) Math.sqrt( (x-BX)*(x-BX) + (y-BY)*(y-BY) );
		vX = v*(x-BX)/distance;
		vY = v*(y-BY)/distance;
	
	}
	
	
	
	public void update()
	{				
		X += vX *2;
		Y += vY * 2;
		
		projectileRect.left = X;
		projectileRect.top = Y;
		projectileRect.bottom = Y + projectilePixmap.getHeight();
		projectileRect.right = X + projectilePixmap.getWidth();
	}
	
	public void draw(Graphics g)
	{
		g.drawPixmap(projectilePixmap, X , Y);
		
	}
	
	
	

}
