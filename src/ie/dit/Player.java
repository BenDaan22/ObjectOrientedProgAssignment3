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
		
		playerX = 100; //parent.random(0,100);
		playerY = 80; //parent.random(0,80);
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
			playerX += 10;
		}
		if(parent.keyPressed && parent.key == 'a')
		{
			playerX -= 10;
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
		parent.fill(255,255,0);
		
		parent.rect(playerX, playerY,w,h);
		
		//left eye
		parent.fill(0);
		parent.rect(playerX + 10, playerY + 10, 10, 10);
		//right eye
		parent.rect(playerX + 30, playerY + 10, 10, 10);
		//mouth
		parent.ellipse(playerX + 25, playerY + 40, 30, 10);
		
		playerY=playerY+speed;// makes the player to move down
		
		if(playerY>parent.height-h)
		{
			speed=speed*-1;
		}
		if(playerY<0)
		{
			speed=speed*-1;
		}

		
		
	}
	boolean collided(Boxes b)
	{
		if(playerX + w < b.boxX) //(playerX + w < b.boxX) 
		{
			return false; 
			
		}
		if(playerX - b.boxW > b.boxX)//(playerX-w - b.boxW > b.boxX)
		{
			return false;
		}
		if(playerY + h < b.boxY)//(playerY + w < b.boxY)
		{
			return false;
		}
		if(playerY - b.boxH > b.boxY)//(playerY - w - b.boxH > b.boxY)
		{
			return false;
		}
		
		playerY = b.boxY - h; // allows the player to stay on the platform when they do collide
		speed *= b.speed;// allows the player to have the same pace as the platform while going down
		
		
		//if they collided then its true
		return true;
	}
	
	/*
	public int LeftRight(Boxes b)
	{
		int i=0;
		if(playerX+w>b.boxX&&playerY+h>b.boxY)
		{
		    i =1;
			return i;
		}
		//left box side
		else if(playerX+w>b.boxX&&playerY-b.boxH<b.boxY)
		{
			i =1;
			return i;
		}
		else if(playerX-b.boxW<b.boxX&&playerY+h>b.boxY)
		{
			i=2;
			return i;
		}
		else if(playerX-b.boxW<b.boxX&&playerY-b.boxH<b.boxY)
		{
			i=2;
			return i;
		}
		else
		{
			return i;
		}
	}
	*/
	
	//check for powerUp and player collision
	boolean collided(PowerUp pow)
	{
		
		if(playerX + w < pow.powerX) //(playerX + w < b.boxX) 
		{
			return false; 
			
		}
		if(playerX - pow.powerW > pow.powerX)//(playerX-w - b.boxW > b.boxX)
		{
			return false;
		}
		if(playerY + h < pow.powerY)//(playerY + w < b.boxY)
		{
			return false;
		}
		if(playerY - pow.powerH > pow.powerY)//(playerY - w - b.boxH > b.boxY)
		{
			return false;
		}
		
		
		pow.powerY = parent.random(-200,-100);
		
		//if they collided then its true
		return true;
		
	}
	//check for powerUp and player collision
	boolean collided(Points poi)
	{
			
		if(playerX + w < poi.pointX) //(playerX + w < b.boxX) 
		{
			return false; 
				
		}
		if(playerX - poi.pointW > poi.pointX)//(playerX-w - b.boxW > b.boxX)
		{
			return false;
		}
		if(playerY + h < poi.pointY)//(playerY + w < b.boxY)
		{
			return false;
		}
		if(playerY - poi.pointH > poi.pointY)//(playerY - w - b.boxH > b.boxY)
		{
			return false;
		}
			
			
		poi.pointY = parent.random(-200,-100);
			
		//if they collided then its true
		return true;
			
	}
	
}
