package ie.dit;

import java.util.ArrayList;

import processing.core.PApplet;
import ddf.minim.AudioInput;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;

public class Main extends PApplet
{
	Minim minim; //for microphone
	AudioInput in;
	
	boolean begin = false;
	float sampleRate = 44100;
	float frameSize = 2048;
	FFT fft;
	
	ArrayList players;
	ArrayList boxes;
	ArrayList powerUps;
	ArrayList point;
	
	int score;
	int counter=0;
	int jumpCounter;
	int gameOver= 0;

	
	public void setup()
	{
		frameRate(50);
		
		size(2048,600);
		minim = new Minim(this);
		in = minim.getLineIn(Minim.MONO,(int)frameSize, sampleRate,16);
		fft = new FFT(width, sampleRate);
		
		score = 0;
		jumpCounter = 10;

		boxes = new ArrayList();
		for(int i=0;i< 5;i++)
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
		
		//to make points Objects
		point = new ArrayList();
		for(int i =0; i < 5; i++)
		{
			point.add(new Points(this));
		}
		

	}
	
	public void draw()
	{
		textSize(20);
		
		background(0);
		fill(255,255,0);
		
		//Instructions
		text("Press S to start the game",550,20);
		text("Get Plus signs as much as you can to gain points", 420,40);
		text("To gain more jumping powers get the yellow boxes", 420, 60);
		
		text("Instructions:", 200, 300);
		text("•Movements are A(left) and D(right)",200,330);
		text("•To JUMP(Spacebar)", 200,350);
		text("•Player can stay on platforms",200,370);
		text("Note: If player hits signal waves underneath they loose",200,390);
		text("Note: If jump counter hits Zero, player cant press SPACEBAR to jump unless hit yellow jump counter boxes", 200, 410);
		
		
		if(key == 's')
		{ 
			begin = true;
		}
		println(frameCount);
	
		
		fft.window(FFT.HAMMING);
		fft.forward(in.left);
		
		if(begin == true)
		{
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
			
			for(int i=0; i < point.size(); i++)
			{
				Points points =  (Points)point.get(i);
				points.move();
				points.display();
			}
			
		    Player player = (Player)players.get(0);
		    player.move();
		    player.display();
		    
		   
			//bottom signal wave
			for (int i = 0 ; i < in.bufferSize() ; i ++)
			{
				stroke(255,0,0); // red colour for the signal wave
				float signal = in.left.get(i) *500;
				line(i, height,i, height - signal);
				if(player.playerY+ player.h > height- signal)
				{
				    //player.speed = player.speed * -1;
					gameOver = 1;
						    
				}
			}
		    
		    //check for boxes and player collision
		  	for(int j = 0; j < boxes.size(); j++)
		  	{
		  		Boxes box = (Boxes) boxes.get(j);
		  		if(player.collided(box))
		  		{
		  			//score++;
		  			println("platform Hit!");
		  		}
		  		
		  		//counter = (int)player.LeftRight(box);
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
		  		
	  			
		  	}
			
		  //check for Points and player collision
		  	for(int i = 0 ; i < point.size(); i++)
		  	{
		  		Points points =  (Points)point.get(i);
		  		if(player.collided(points))
		  		{
		  			score++;
		  			println("Score Hit!");
		  		}
		  		
	  			
		  	}
		  	
			textSize(15);
			fill(255,255,0);
			text("Score: " + score,50,50);
			text("JumpCounter: " + jumpCounter,50,70);
		}//end if statement
		
		// to display game over 
		if(gameOver == 1)
		{
			textSize(40);
			background(0);
			
			text("GAMEOVER!!", width/2- 40,height/2);
			text("Press S to play again and Good Luck" ,400, height/2 + 30);
			text("Your Score is: "+ score , width/2-40, height/2 + 70);
			
			//if the player wants to play again
			if(keyPressed && key == 's')
			{
				begin = true;
				gameOver =0;
				score = 0;
				jumpCounter = 10;
				
				Player player = (Player)players.get(0);
				player.playerX = 100;
				player.playerY = 80;
			}
		}
		
	}//end draw 
	
	
	public void keyReleased()
	{
		Player player = (Player)players.get(0);
		
		/* if the player still has jump counter to jump this will run
		 * but if no more counters then the player can no longer jump
		*/
		
		if(key == ' ' && jumpCounter !=0 )
		{
  			jumpCounter = jumpCounter - 1;
  			if(player.speed<0)
  			{
  				player.speed*=-1;
  			}
			
  			player.playerY   -= 100;
		}
		
	}
	
	
}
