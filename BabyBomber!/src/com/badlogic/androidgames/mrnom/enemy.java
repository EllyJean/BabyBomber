package com.badlogic.androidgames.mrnom;

import android.graphics.Rect;

import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Pixmap;

public class enemy 
{
	public int posX, posY;
	public boolean isDead = false;
	
	Pixmap enemyPixmap = Assets.teddyBear;
	public Rect enemyRect = new Rect();
	
	
	
	
	public enemy(int x, int y)
	{
		posX = x;
		posY = y;
			
	}
	
	
	
	public void update()
	{
		enemyRect.left = posX;
		enemyRect.top = posY;
		enemyRect.bottom = posY + enemyPixmap.getHeight();
		enemyRect.right = posX + enemyPixmap.getWidth();
	}
	
	public void draw(Graphics g)
	{
		g.drawPixmap(enemyPixmap, posX , posY);
		
	}
}
