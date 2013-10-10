package com.badlogic.androidgames.mrnom;

import android.graphics.Rect;

import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Pixmap;

public class Particle 
{
	
        public Pixmap texture;
        public int posX, posY, velX, velY;
        public Rect rect;
        public int age;
        public boolean isAlive;

        public Particle(Pixmap t, int pX, int pY, int vX, int vY, int a)
        {
            texture = t;
            posX = pX;
            posY = pY;
            velX = vX;
            velY = vY;
            rect = new Rect();
            age = a;
            isAlive = true;
        }

        public void update()
        {
        	
        	
            posX += velX;
            posY += velY;
            rect.left = posX;
            rect.top = posY;
            rect.bottom = posY + texture.getHeight();
            rect.right = posX + texture.getWidth();
            
            age--;
            if (age <= 0)
            {
                isAlive = false;
            }
        }

        public void draw(Graphics g)
    	{
    		g.drawPixmap(texture, posX , posY);
    		
    	}
    
}
