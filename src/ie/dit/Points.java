package ie.dit;

import processing.core.PApplet;

public class Points 
{
	PApplet parent;
	float pointX;
	float pointY;
	float pointH;
	float pointW;
	float speed = 3;
	
	public Points(PApplet p)
	{
		parent = p;
		pointX = parent.random(20,parent.width);
		pointY = parent.random(20,parent.height);
		pointH =  50;
		pointW =  10;
	}
	
	public void move()
	{
		pointY += speed;
	}
	
	public void display()
	{
		parent.fill(0,255,255);
		parent.rect(pointX-20, pointY+20, pointH, pointW);
		parent.rect(pointX,pointY,pointW,pointH);
		
		
		if(pointY > parent.height)
		{
			pointY = parent.random(-300, -100);
			pointX = parent.random(0, parent.width);
		}
	}
}
