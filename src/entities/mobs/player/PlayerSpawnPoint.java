package entities.mobs.player;

import java.util.Random;

import level.Level;

public class PlayerSpawnPoint {
	private int xCoord, yCoord;
	Random rand = new Random();
	
	public PlayerSpawnPoint(Level level){
		xCoord = (level.getWidth()/2)*64;
		yCoord = level.getHeight()/2*64;// + rand.nextInt(level.getHeight()/8))*64; 
	}
	
	public int getXCoord(){
		return xCoord;
	}
	
	public int getYCoord(){
		return yCoord;
	}
	
}
