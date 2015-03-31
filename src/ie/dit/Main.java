package ie.dit;

import processing.core.PApplet;

public class Main extends PApplet
{
	Player[] players = new Player[1];
	Boxes[] boxes = new Boxes[5];
	
	
	public void setup()
	{
		size(800,500);
		
		for(int i = 0 ; i < players.length; i++)
		{
			players[i] = new Player(this);
		}
		
		//To draw the box platforms
		for(int i = 0 ; i < boxes.length; i++)
		{
			boxes[i] = new Boxes(this);
		}
	}
	
	public void draw()
	{
		background(0);
		
		for(int i = 0; i < players.length; i++)
		{
			players[i].move();
			players[i].display();
		}
		
		for(int i = 0; i < boxes.length; i++)
		{
			boxes[i].display();
		}
	}
	
}
