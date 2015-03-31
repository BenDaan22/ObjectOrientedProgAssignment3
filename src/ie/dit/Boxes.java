package ie.dit;

import processing.core.PApplet;

public class Boxes
{
	PApplet parent;
	float boxX;
	float boxY;
	float boxW;
	float boxH;
	
	public Boxes(PApplet p)
	{
		parent = p;
		boxX = parent.random(0,parent.width- boxW);
		boxY = parent.random(0,parent.height- boxH);
		boxH = 15;
		boxW = 60;
	}
	
	public void move()
	{
		
	}
	
	public void display()
	{
		parent.fill(255);
		parent.rect(boxX, boxY, boxW, boxH);
	}
	

}
