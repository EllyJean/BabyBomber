package com.badlogic.androidgames.mrnom;

import java.util.ArrayList;
import java.util.List;

public class StayPuft
{
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
    
    public boolean canMove;
    
    public List<StayPuftPart> parts = new ArrayList<StayPuftPart>();
    public int direction;  
    
    

    public StayPuft() 
    {        
     
        parts.add(new StayPuftPart(5, 6));
     
    }

    public void turnLeft() 
    {
      
            direction = LEFT;
    }
    
    public void turnRight() 
    {
      
            direction = RIGHT;
    }
    
    public void turnUp() 
    {
        
            direction = UP;
    }
    
    public void turnDown() 
    {
       
            direction = DOWN;
    }

   

    public void advance() 
    {
        StayPuftPart head = parts.get(0);               
        
     
        
        if(direction == UP && canMove == true)
            head.y -= 1;
        if(direction == LEFT && canMove == true)
            head.x -= 1;
        if(direction == DOWN && canMove == true)
            head.y += 1;
        if(direction == RIGHT && canMove == true)
            head.x += 1;
        
        if(head.x < 0)
            head.x = 9;
        if(head.x > 9)
            head.x = 0;
        if(head.y < 0)
            head.y = 12;
        if(head.y > 12)
            head.y = 0;
    }

    public boolean checkBitten() 
    {
        int len = parts.size();
        StayPuftPart head = parts.get(0);
        for(int i = 1; i < len; i++) 
        {
            StayPuftPart part = parts.get(i);
            if(part.x == head.x && part.y == head.y)
                return true;
        }        
        return false;
    }      
}

