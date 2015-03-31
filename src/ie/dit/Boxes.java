package ie.dit;

import processing.core.PApplet;

public class Boxes
{
	PApplet parent;
	float boxX;
	float boxY;
	float boxW;
	float boxH;
	float speed=1;
	
	public Boxes(PApplet p)
	{
		parent = p;
		boxX = parent.random(0,parent.width- boxX);
		boxY = parent.random(-200,-100);
		boxH = 15;
		boxW = 60;
	}
	
	public void move()
	{
		boxY+=speed;
	}
	
	public void display()
	{
		parent.fill(255);
		parent.rect(boxX, boxY, boxW, boxH);
		if(boxY>parent.height)
		{
			boxY=parent.random(-300,-100);
			boxX=parent.random(0,parent.width- boxX);
			//speed=speed+(float)0.5;
		}
	}
	

}
