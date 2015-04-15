package ie.dit;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;

public class Main extends PApplet
{
	AudioPlayer backsound;
	AudioPlayer scoreSound;
	AudioPlayer jumpPoint;
	AudioPlayer jumpSound;
	
	Minim minim; //for microphone
	AudioInput in; // to make the mic pick up sounds the computer is hearing
	
	boolean begin = false; 		// a boolean to check if the player is already in-Game or in Game over screen
	float sampleRate = 44100;
	float frameSize = 2048;
	FFT fft; // to create the signal wave at the bottom of the game
	
	//create ArrayLists of players, boxes powerUps, point objects
	ArrayList players;
	ArrayList boxes;
	ArrayList powerUps; 
	ArrayList point;
	
	
	int score; // a counter to keep track how many plus signs(score) the player has gotten
	int jumpCounter; // a counter to check if the player is able to jump
	int gameOver= 0; // a counter to show the game Over screen

	PImage start_screen;
	PImage game_screen;
	
	public void setup()
	{
		size(2048,600);
		
		frameRate(120);
		
		
		minim = new Minim(this);
		in = minim.getLineIn(Minim.MONO,(int)frameSize, sampleRate,16);
		fft = new FFT(width, sampleRate);
		
		//to add the sounds
		backsound = minim.loadFile("music.mp3", 2048);
		scoreSound = minim.loadFile("Children Yay! - Sound Effect.mp3", 2048);
		jumpPoint = minim.loadFile("ding.mp3", 2048);
		jumpSound = minim.loadFile("Jump6.wav",2048);
		
		//play the sound file
		backsound.play();
		backsound.loop();
		
		score = 0;
		jumpCounter = 10;

		//to input images
		start_screen = loadImage("MainMenu.png");
		game_screen = loadImage("GameScreen.png");
		
		//to create how many boxes object will be shown during game
		boxes = new ArrayList();
		for(int i=0;i< 5;i++)
		{
			boxes.add(new Boxes (this));
		}
		
		//to create how many player object will be shown during game
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
		image(start_screen,0,0,width,height);
		
		if(key == 's')
		{ 
			begin = true; // to Start the game
		}	
		
		fft.window(FFT.HAMMING); // to calculate the Fourier Transformation
		fft.forward(in.left);
		
		//this shows the objects within the game
		if(begin == true)
		{
			image(game_screen,0,0,width,height);
			
			
			//to display the boxes objects
			for(int i =0 ; i < boxes.size(); i++)
			{
				Boxes box = (Boxes) boxes.get(i);
				box.move();
				box.display();
			}
			
			//to display the powerUps objects
			for(int i =0; i < powerUps.size(); i ++)
			{
				PowerUp powerUp = (PowerUp) powerUps.get(i);
				powerUp.move();
				powerUp.display();
			}
			
			//to display the points objects
			for(int i=0; i < point.size(); i++)
			{
				Points points =  (Points)point.get(i);
				points.move();
				points.display();
			}
			
			//to display the player objects
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
					gameOver = 1;
						    
				}
			}
		    
		    //check for boxes and player collision
		  	for(int j = 0; j < boxes.size(); j++)
		  	{
		  		Boxes box = (Boxes) boxes.get(j);
		  		if(player.collided(box))
		  		{
		  			println("platform Hit!");
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
		  			//to play the sound file when hits
		  			jumpPoint.play();
		  			jumpPoint.rewind();
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
		  			
		  			//to play the sound file when hits
		  			scoreSound.play();
		  			scoreSound.rewind();
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
			image(game_screen,0,0,width,height); // to show the game Screen background
			
			textSize(40);
			
			
			text("GAMEOVER!!", width/2- 40,height/2);
			text("Press S to play again and Good Luck" ,400, height/2 + 30);
			text("Your Score is: "+ score , width/2-40, height/2 + 70);
			
			//if the player wants to play again
			if(keyPressed && key == 's')
			{
				//resets everything back to their original state
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
  			/*if(player.speed<0)
  			{
  				player.speed*=-1;
  			}*/
			
  			player.playerY   -= 100;
  			jumpSound.play();
  			jumpSound.rewind();
		}
		
	}
	
	
}
