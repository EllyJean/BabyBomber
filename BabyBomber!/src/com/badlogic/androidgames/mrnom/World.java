package com.badlogic.androidgames.mrnom;



public class World 
{
    static final int WORLD_WIDTH = 10;
    static final int WORLD_HEIGHT = 13;
    static final int SCORE_INCREMENT = 10;

    public StayPuft staypuft;
    public boolean gameOver = false;;
    public int score = 0;

    boolean fields[][] = new boolean[WORLD_WIDTH][WORLD_HEIGHT];

    public World() 
    {
        staypuft = new StayPuft();
       
    }

   

    public void update(float deltaTime) 
    {
        if (gameOver)
            return;

            staypuft.advance();
      
    }
}
