package ie.dit;

import processing.core.PApplet;

public class Player 
{
	float w,h;
	float playerX, playerY;
	float gravity = 1;
	float speed =5;//0;
	
	//to access the OOP links
	PApplet parent;

	Player(PApplet p)
	{
		parent = p;
		
		playerX = 100; 
		playerY = 80;
		w= 50;
		h = 50;
		
		
	}

	
	public void move()
	{
		//allows the player to move from left or right
		if(parent.keyPressed && parent.key == 'd')
		{
			//to go right
			playerX += 10;
		}
		if(parent.keyPressed && parent.key == 'a')
		{
			//to go left
			playerX -= 10;
		}
		

	}
	
	public void display()
	{
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
		
		//this acts as the gravity to pull the player down
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
		//If the player doesnt collide with the boxes then it returns false
		if(playerX + w < b.boxX)
		{
			return false; 
			
		}
		if(playerX - b.boxW > b.boxX)
		{
			return false;
		}
		if(playerY + h < b.boxY)
		{
			return false;
		}
		if(playerY - b.boxH > b.boxY)
		{
			return false;
		}
		
		playerY = b.boxY - h; // allows the player to stay on the platform when they do collide
		speed *= b.speed;// allows the player to have the same pace as the platform while going down
		
		
		//if they collided then its true
		return true;
	}
	
	
	
	//check for powerUp and player collision
	boolean collided(PowerUp pow)
	{
		//If the player doesnt collide with the powerUp then it returns false
		if(playerX + w < pow.powerX) 
		{
			return false; 
			
		}
		if(playerX - pow.powerW > pow.powerX)
		{
			return false;
		}
		if(playerY + h < pow.powerY)
		{
			return false;
		}
		if(playerY - pow.powerH > pow.powerY)
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
		//If the player doesnt collide with the points then it returns false
		if(playerX + w < poi.pointX) 
		{
			return false; 
				
		}
		if(playerX - poi.pointW > poi.pointX)
		{
			return false;
		}
		if(playerY + h < poi.pointY)
		{
			return false;
		}
		if(playerY - poi.pointH > poi.pointY)
		{
			return false;
		}
			
			
		poi.pointY = parent.random(-200,-100);
			
		//if they collided then its true
		return true;
			
	}
	
}
