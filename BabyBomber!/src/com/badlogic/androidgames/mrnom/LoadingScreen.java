package com.badlogic.androidgames.mrnom;

import com.badlogic.androidgames.framework.Audio;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;
import com.badlogic.androidgames.framework.Sound;

public class LoadingScreen extends Screen 
{
    public LoadingScreen(Game game) 
    {
        super(game);
    }

    public void update(float deltaTime) 
    {
        Graphics g = game.getGraphics();
        Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565);
        Assets.gameBackground = g.newPixmap("gameBackground.png", PixmapFormat.RGB565);
        Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
        Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
 
        Assets.help1 = g.newPixmap("help1.png", PixmapFormat.ARGB4444);
        Assets.help2 = g.newPixmap("help2.png", PixmapFormat.ARGB4444);
        Assets.help3 = g.newPixmap("help3.png", PixmapFormat.ARGB4444);
 
        Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB4444);
        Assets.pause = g.newPixmap("pausemenu.png", PixmapFormat.ARGB4444);
        Assets.pause1 = g.newPixmap("pause.png", PixmapFormat.ARGB4444);
        Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
       
        Assets.left= g.newPixmap("left_button.png", PixmapFormat.ARGB4444);
        Assets.babyCanon= g.newPixmap("babyCanon.png", PixmapFormat.ARGB4444);
        Assets.poopyDiaper= g.newPixmap("poopyDiaper.png", PixmapFormat.ARGB4444);
        Assets.teddyBear= g.newPixmap("teddyBear.png", PixmapFormat.ARGB4444);
        Assets.credits= g.newPixmap("credits.png", PixmapFormat.ARGB4444);
        Assets.exit= g.newPixmap("exit_button.png", PixmapFormat.ARGB4444);
      
        Assets.right = g.newPixmap("right_button.png", PixmapFormat.ARGB4444);
        Assets.up = g.newPixmap("up_button.png", PixmapFormat.ARGB4444);
        Assets.down = g.newPixmap("down_button.png", PixmapFormat.ARGB4444);

        Assets.sound = g.newPixmap("sound.png", PixmapFormat.ARGB4444);
        Assets.noSound = g.newPixmap("no_sound.png", PixmapFormat.ARGB4444);
        Assets.click = game.getAudio().newSound("click.ogg");
        Assets.eat = game.getAudio().newSound("eat.ogg");
        Assets.bitten = game.getAudio().newSound("bitten.ogg");
        //Assets.U2 = game.getAudio().newSound("bloodySunday.ogg");
        //Assets.U2 =   game.getAudio().newMusic("bloodySunday.mp3");
        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
    }
    
    public void present(float deltaTime) 
    {

    }

    public void pause() 
    {

    }

    public void resume() 
    {

    }

    public void dispose() 
    {

    }
}
