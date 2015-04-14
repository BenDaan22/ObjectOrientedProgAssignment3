package ie.dit;

import processing.core.PApplet;

public class Points 
{
	//to access the Processing links
	PApplet parent;
	
	float pointX;
	float pointY;
	float pointH;
	float pointW;
	float speed = 4; // this shows how fast the points will fall down
	
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
		pointY += speed; // pulls the points object down
	}
	
	public void display()
	{
		parent.fill(0,255,255);
		parent.rect(pointX-20, pointY+20, pointH, pointW);
		parent.rect(pointX,pointY,pointW,pointH);
		
		
		if(pointY > parent.height)
		{
			// if the points hits the maximum length of the screen height then it goes back to the top
			pointY = parent.random(-300, -100);
			pointX = parent.random(0, parent.width);
		}
	}
}
