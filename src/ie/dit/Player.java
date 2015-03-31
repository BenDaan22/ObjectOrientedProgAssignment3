package ie.dit;

import processing.core.PApplet;

public class Player 
{
	float w,h;
	float playerX, playerY;
	float gravity = 1;
	float speed =5;//0;
	
	PApplet parent;
	
	Player(PApplet p)
	{
		parent = p;
		playerX = parent.random(0,100);
		playerY = parent.random(0,80);
		w= 50;
		h = 50;
		
	}

	
	public void move()
	{
		/*
		if(parent.keyPressed)
		{
			if(parent.key == 'd')
			{
				playerX += 5;
			}
			
			if(parent.key == 'a')
			{
				playerX -= 5;
			}
		}
		*/
		if(parent.keyPressed && parent.key == 'd')
		{
			playerX += 5;
		}
		if(parent.keyPressed && parent.key == 'a')
		{
			playerX -= 5;
		}

	}
	
	public void display()
	{
		/*if(speed==0&&playerY==parent.height-h)
		{
			speed=0;
		}
		else
		{
		  speed=speed+gravity;
		}*/
		parent.fill(255);
		parent.ellipse(playerX, playerY,w,h);
		playerY=playerY+speed;
		if(playerY>parent.height-h)
		{
			speed=speed*-1;
		}
		if(playerY<h)
		{
			speed=speed*-1;
		}
		
		
		//if(y >= rectY-(1.5*playerH) && x >= rectX-1 && x <= rectX+playerW+50 )
		  //{
		    //speed = speed * -1;
		  //}
	}
}
