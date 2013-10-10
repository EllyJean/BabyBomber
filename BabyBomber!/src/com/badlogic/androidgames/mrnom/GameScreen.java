package com.badlogic.androidgames.mrnom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Object;

import android.util.Log;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;

public class GameScreen extends Screen 
{
    enum GameState 
    {
        Ready,
        Running,
        Paused,
        GameOver
    }
    
    GameState state = GameState.Ready;
    World world;
    int oldScore = 0;
    String score = "0";
    int current;
	int previous;
	boolean isUp = false, onceThrough = false;
	int newX, newY, deltaX, deltaY, oldX = 0, oldY = 0, OrigiinX, OriginY;
	public List<Projectile> bullets = new ArrayList<Projectile>();
	public List<enemy> clowns = new ArrayList<enemy>();
	public List<Particle> hitExplosion = new ArrayList<Particle>();
	public int babyX, babyY;
	public Pixmap headPixmap;
	public Pixmap particle = Assets.poopyDiaper;
	Random generator = new Random();
	int X;
	int Y;
	int tempVelX;
	int tempVelY;
	int tempX;
	int tempY;
	
    
    public GameScreen(Game game) 
    {
        super(game);
        world = new World();
        
    }

    @Override
    public void update(float deltaTime) 
    {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        
        if(state == GameState.Ready)
            updateReady(touchEvents);
        if(state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if(state == GameState.Paused)
            updatePaused(touchEvents);
        if(state == GameState.GameOver)
            updateGameOver(touchEvents);        
    }

    private void updateReady(List<TouchEvent> touchEvents) 
    {
        if(touchEvents.size() > 0)
            state = GameState.Running;
    }
//plYER CONTROLS ARE HERE
    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) 
    {      
        int len = touchEvents.size();
                
        for(int i = 0; i < len; i++) 
        {
            TouchEvent event = touchEvents.get(i);
            
            if (event.type == TouchEvent.TOUCH_UP)
            {
            	isUp = true;
            
            }
            else
            {
            	isUp = false;
            }
            //current = event.type;
            if(event.type == TouchEvent.TOUCH_UP) 
            {
                if(event.x < 64 && event.y < 64) 
                {
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    state = GameState.Paused;
                    return;
                }
            }
            if(event.type == TouchEvent.TOUCH_UP) 
            {   
            	
               
               bullets.add(new Projectile(event.x, event.y, babyX, babyY));
                             
               
            }
            else
            {
            	world.staypuft.canMove = false;
            }
        }
        
        world.update(deltaTime);
        if(world.gameOver) 
        {
            if(Settings.soundEnabled)
                Assets.bitten.play(1);
            state = GameState.GameOver;
        }
        if(oldScore != world.score) 
        {
            oldScore = world.score;
            score = "" + oldScore;
            if(Settings.soundEnabled)
                Assets.eat.play(1);
        }
        
        //oldX = newX;
       // oldY = newY;
        
        for(enemy n : clowns)
        {
        	for(Projectile i : bullets)
            {	
            	
            	if(n.enemyRect.intersect(i.projectileRect))
            	{
            		for(int s = 0; s < 10; s++)
            		{
            		 X = (int)(generator.nextDouble() - 0.5f)*10;
            			 Y = (int)(generator.nextDouble() - 0.5f)*10;
            			 tempVelX = X;
            			 tempVelY = Y;
            			 tempX = n.posX;
            			 tempY = n.posY;
            			 Log.w("my app", String.valueOf(X)); 
            			hitExplosion.add(new Particle(particle, tempX, tempY, tempVelX, tempVelY, generator.nextInt(255)));
            			
            		}
            		n.isDead = true;
            		i.isDead = true;
            	}
            }
        	n.update();
        }
        
        for(Projectile i : bullets)
        {	
        	
        	i.update();
        }
        
        for(Particle i : hitExplosion)
        {	
        	
        	i.update();
        }
        
        for (int i = hitExplosion.size() - 1; i >= 0; i--)
        {
            Particle temp = hitExplosion.get(i);
            if (!temp.isAlive)
            {
                hitExplosion.remove(temp);
            }
        }
        
        for (int i = clowns.size() - 1; i >= 0; i--)
        {
            enemy o = clowns.get(i);
            if (o.isDead == true)
            {
                clowns.remove(i);
            }
        }
        
        for (int i = bullets.size() - 1; i >= 0; i--)
        {
            Projectile o = bullets.get(i);
            if (o.isDead == true)
            {
                bullets.remove(i);
            }
        }

        
    }

    private void updatePaused(List<TouchEvent> touchEvents) 
    {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) 
        {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) 
            {
                if(event.x > 80 && event.x <= 240) 
                {
                    if(event.y > 100 && event.y <= 148) 
                    {
                        if(Settings.soundEnabled)
                            Assets.click.play(1);
                        state = GameState.Running;
                        return;
                    }                    
                    if(event.y > 148 && event.y < 196) 
                    {
                        if(Settings.soundEnabled)
                            Assets.click.play(1);
                        game.setScreen(new MainMenuScreen(game)); 
                        return;
                    }
                }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x >= 128 && event.x <= 192 &&
                   event.y >= 200 && event.y <= 264) {
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.gameBackground, 0, 0);
        drawWorld(world);
        if(state == GameState.Ready) 
            drawReadyUI();
        if(state == GameState.Running)
            drawRunningUI();
        if(state == GameState.Paused)
            drawPausedUI();
        if(state == GameState.GameOver)
            drawGameOverUI();
        
        drawText(g, score, g.getWidth() / 2 - score.length()*20 / 2, g.getHeight() - 42);
    }

    private void drawWorld(World world) 
    {
        Graphics g = game.getGraphics();
        StayPuft staypuft = world.staypuft;
        //StayPuftPart head = staypuft.parts.get(0);
        int screenWidth = g.getWidth();
        int screenHeight = g.getHeight();
               
        headPixmap = Assets.babyCanon;
      
       while(onceThrough!=true)
       {
    	   babyX = screenWidth/2 - (headPixmap.getWidth()/2);
    	   babyY = screenHeight  - headPixmap.getHeight();
    	   
    	   clowns.add(new enemy(50,50));
    	   clowns.add(new enemy(150, 150));
    	   clowns.add(new enemy(150,50));
    	   clowns.add(new enemy(50, 175));
    	   clowns.add(new enemy(75,200));
    	   clowns.add(new enemy(300, 280));
    	   
    	   onceThrough = true;
       }
        g.drawPixmap(headPixmap, babyX, babyY);
        
        for(enemy n : clowns)
        {
        	n.draw(g);
        }
        
        for(Projectile i : bullets)
        {     	
        	i.draw(g);
        }
        
        for(Particle i : hitExplosion)
        {	
        	
        	i.draw(g);
        }
    }

    private void drawReadyUI() 
    {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.ready, 47, 75);
    }
    
    private void drawRunningUI() 
    {
        Graphics g = game.getGraphics();

    }
    
    private void drawPausedUI() 
    {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.pause, 80, 100);
    }

    private void drawGameOverUI() 
    {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.gameOver, 62, 100);
    }
    
    public void drawText(Graphics g, String line, int x, int y) 
    {
        int len = line.length();
        for (int i = 0; i < len; i++) 
        {
            char character = line.charAt(i);

            if (character == ' ') 
            {
                x += 20;
                continue;
            }

            int srcWidth = 0;
            if (character == '.') 
            {
                srcWidth = 10;
            } 
            else 
            {
                srcWidth = 20;
            }


            x += srcWidth;
        }
    }

    @Override
    public void pause() 
    {
        if(state == GameState.Running)
            state = GameState.Paused;
        
        if(world.gameOver) 
        {
            Settings.addScore(world.score);
            Settings.save(game.getFileIO());
        }
    }

    @Override
    public void resume() 
    {
        
    }

    @Override
    public void dispose() 
    {
        
    }
    
    public void rotateSprite()
    {
    	
    }
}

