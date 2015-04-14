package ie.dit;

import processing.core.PApplet;

public class Boxes
{
	//to access the Processing links
	PApplet parent;
	
	float boxX;
	float boxY;
	float boxW;
	float boxH;
	float speed=1; // this shows how fast the boxes will fall down
	
	public Boxes(PApplet p)
	{
		parent = p;
		boxX = parent.random(0,parent.width);
		boxY = parent.random(0,parent.height);
		boxH = 15;
		boxW = 60;
	}
	
	public void move()
	{
		boxY+=speed; // pulls the boxes object down
	}
	
	public void display()
	{
		parent.fill(255);
		parent.rect(boxX, boxY, boxW, boxH);
		if(boxY>parent.height)
		{
			// if the boxes hits the maximum length of the screen height then it goes back to the top
			boxY=parent.random(-300,-100);
			boxX=parent.random(0,parent.width +200);
			
		}
	}
	

}
