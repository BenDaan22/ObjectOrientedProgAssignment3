package ie.dit;

import java.util.ArrayList;

import processing.core.PApplet;

public class Main extends PApplet
{
	ArrayList players;
	ArrayList boxes;
	ArrayList powerUps;
	
	int score;
	int counter=0;
	int jumpCounter;

	
	public void setup()
	{
		frameRate(30);
		
		size(700,1000);
		score = 0;
		jumpCounter = 0;

		boxes = new ArrayList();
		for(int i=0;i< 3;i++)
		{
			boxes.add(new Boxes (this));
		}
		
		players = new ArrayList();
		players.add(new Player(this));
		
		//to make powerUp objects
		powerUps = new ArrayList();
		for(int i=0; i < 5; i++)
		{
			powerUps.add(new PowerUp(this));
		}
		

	}
	
	public void draw()
	{
		println(frameCount);
		background(0);
		
		for(int i =0 ; i < boxes.size(); i++)
		{
			Boxes box = (Boxes) boxes.get(i);
			box.move();
			box.display();
		}
		
		for(int i =0; i < powerUps.size(); i ++)
		{
			PowerUp powerUp = (PowerUp) powerUps.get(i);
			powerUp.move();
			powerUp.display();
		}
		
	    Player player = (Player)players.get(0);
	    player.move();
	    player.display();
	    
	    //check for boxes and player collision
	  	for(int j = 0; j < boxes.size(); j++)
	  	{
	  		Boxes box = (Boxes) boxes.get(j);
	  		if(player.collided(box))
	  		{
	  			//score++;
	  			println("platform Hit!");
	  		}
	  		
	  		counter = (int)player.LeftRight(box);
	  		if(counter==1)
	  		{
	  			player.playerX--;
	  			println("Hit left!!");
	  		}
	  		if(counter==2)
	  		{
	  			player.playerX++;
	  			println("Hit right!!");
	  		}


	  				
	  	}
	  	
	  	//check for powerUp and player collision
	  	for(int i = 0 ; i < powerUps.size(); i++)
	  	{
	  		PowerUp powerUp = (PowerUp) powerUps.get(i);
	  		if(player.collided(powerUp))
	  		{
	  			jumpCounter++;
	  			println("Power Up Hit!");
	  		}
	  		/*
	  		if(keyPressed && key == ' ')//&& jumpCounter !=0 )
  			{
	  			jumpCounter = jumpCounter - 1;
	  			if(player.speed<0)
	  			{
	  				player.speed*=-1;
	  			}
  				player.playerY   -= 8;
  			}
  			*/
  			
	  	}
		
		textSize(15);
		text("Score: " + score,50,50);
		text("JumpCounter: " + jumpCounter,50,70);
		
	}//end draw 
	
	
	public void keyReleased()
	{
		Player player = (Player)players.get(0);
		
		if(key == ' ')//&& jumpCounter !=0 )
			{
  			jumpCounter = jumpCounter - 1;
  			if(player.speed<0)
  			{
  				player.speed*=-1;
  			}
				player.playerY   -= 40;
			}
	}
	
	
}
