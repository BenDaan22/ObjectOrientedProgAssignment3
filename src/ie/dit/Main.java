package ie.dit;

import java.util.ArrayList;

import processing.core.PApplet;

public class Main extends PApplet
{
	ArrayList players;
	ArrayList boxes;
	
	int score;

	
	public void setup()
	{
		size(700,1000);
		score = 0;

		boxes = new ArrayList();
		for(int i=0;i<5;i++)
		{
			boxes.add(new Boxes (this));
		}
		
		players = new ArrayList();
		players.add(new Player(this));

	}
	
	public void draw()
	{
		background(0);
		
		for(int i =0 ; i < boxes.size(); i++)
		{
			Boxes box = (Boxes) boxes.get(i);
			box.move();
			box.display();
		}
		
	    Player player = (Player)players.get(0);
	    player.move();
	    player.display();
	    
	    //check for boxes and player collision
	  	for(int j = 1; j < boxes.size(); j++)
	  	{
	  		Boxes box = (Boxes) boxes.get(j);
	  		if(player.collided(box))
	  		{
	  			score++;
	  			println("Hit!");
	  		}
	  	}
		
		textSize(15);
		text("Score: " + score,50,50);
		
	}//end draw 
	
}
