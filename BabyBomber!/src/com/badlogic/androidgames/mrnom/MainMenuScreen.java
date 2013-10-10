package com.badlogic.androidgames.mrnom;

import java.util.List;

import android.R;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

public class MainMenuScreen extends Screen 
{
	public boolean isFirst = false;
	//MediaPlayer mediaPlayer = new MediaPlayer();
	//Context context = getApplicationContext()
	

	
    public MainMenuScreen(Game game) 
    {
    	
        super(game);               
    }   

    public void update(float deltaTime) 
    {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents(); 
        
        if(isFirst!=true)
        {
        	//Uri path = Uri.parse("android.resource://com.badlogic.androidgames.mrnon/raw/bloodySunday");
			//mediaPlayer.create(this, path);
        	//Assets.U2.play(1);
        	//Assets.U2.play();
        	//Assets.U2.isLooping();
        	isFirst = true;
        }
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) 
        {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) 
            {
                if(inBounds(event, 0, g.getHeight() - 64, 64, 64)) 
                {
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                }
                if(inBounds(event, 64, 220, 192, 42) ) 
                {
                    game.setScreen(new GameScreen(game));
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
              
                if(inBounds(event, 64, 220 + 84, 192, 42) ) 
                {
                    game.setScreen(new credits(game));
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
            }
        }
    }
    
    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) 
    {
        if(event.x > x && event.x < x + width - 1 && 
           event.y > y && event.y < y + height - 1) 
            return true;
        else
            return false;
    }

    public void present(float deltaTime) 
    {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.logo, 32, 20);
        g.drawPixmap(Assets.mainMenu, 64, 220);
        if(Settings.soundEnabled)
            g.drawPixmap(Assets.sound, 0, 416);
        else
            g.drawPixmap(Assets.noSound, 0, 416);
    }

    public void pause() 
    {        
        Settings.save(game.getFileIO());
    }

    public void resume() 
    {

    }

    public void dispose() 
    {

    }
}

