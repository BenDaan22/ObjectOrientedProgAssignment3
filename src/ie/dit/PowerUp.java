package ie.dit;

import processing.core.PApplet;

public class PowerUp
{
	//to access the Processing links
	PApplet parent;
	
	float powerX;
	float powerY;
	float powerW,powerH;
	float speed = 3; // this shows how fast the powerUp will fall down
	
	
	public PowerUp(PApplet p)
	{
		parent = p;
		powerX = parent.random(0,parent.width);
		powerY = parent.random(-200,-100);
		powerH =  parent.random(20,40);
		powerW =  parent.random(20,40);
	}
	
	public void move()
	{
		powerY += speed;  // pulls the powerUp object down
	}
	public void display()
	{
		parent.fill(255,255,0);
		parent.rect(powerX,powerY,powerW,powerH);
		
		if(powerY > parent.height)
		{
			// if the powerUp hits the maximum length of the screen height then it goes back to the top
			powerY = parent.random(-300, -100);
			powerX = parent.random(0, parent.width);
		}
	}
}
