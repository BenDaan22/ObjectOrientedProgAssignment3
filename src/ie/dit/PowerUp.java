package ie.dit;

import processing.core.PApplet;

public class PowerUp
{
	PApplet parent;
	float powerX;
	float powerY;
	float powerW,powerH;
	float speed = 3;
	
	
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
		powerY += speed;
	}
	public void display()
	{
		parent.fill(255,0,0);
		parent.rect(powerX,powerY,powerW,powerH);
		
		if(powerY > parent.height)
		{
			powerY = parent.random(-300, -100);
			powerX = parent.random(0, parent.width);
		}
	}
}
