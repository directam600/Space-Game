package entities.mobs.player;

import graphics.sprites.PlayerSprite;
import input.Keyboard;
import level.Level;
import entities.mobs.Mob;
import entities.tiles.LeftStdPlatformTile;
import entities.tiles.RightStdPlatformTile;
import entities.tiles.StdPlatformTile;
import entities.tiles.Tile;

public class Player extends Mob{
	
	private Keyboard key;
	private int speed;
	Level level;
	
	public Player(int x, int y, Keyboard key) {
		xCoord = x;	//pixel precision
		yCoord = y;
		this.key = key;
		speed = 8;
		level = Level.baseLevel;
		sprite = PlayerSprite.player_sprite;
	}
	//TODO: implement shoot();
	public void update(){
		updateTile();
		updateMove();
		//shoot();
	}
	
	private void updateTile() {
		
	}
	
	private void updateMove(){
		int xDir = 0;
		int yDir = 0;
		if(key.up){
			yDir -= speed;
		}else if(key.down){
			yDir += speed;
		}if(key.left){
			xDir -= speed;
		}else if(key.right){
			xDir += speed;
		}
		if(xDir != 0 || yDir != 0){
			move(xDir, yDir);
		}
	}
	
	//move 1 pixel at a time to prevent pixel hopping and missing collision
	protected void move(int xDir, int yDir){
		while(xDir != 0 || yDir != 0){
			if(!collision(dirValue(xDir),0)){
				if(xDir != 0){
					xCoord += dirValue(xDir);
					xDir -= dirValue(xDir);
				}
			}else break;
			if(!collision(0, dirValue(yDir))){
				if(yDir != 0){
					yCoord += dirValue(yDir);
					yDir -= dirValue(yDir);
				}
			}else break;
		}
	}
	
	private int dirValue(int dir){
		if(dir > 0) return 1;
		else return -1;
	}
	//TODO: implement possible collision detection in other directions
	private boolean collision(int x, int y){
		double playerTilePosX = xCoord/64.0;	//get player tile position
		double playerTilePosY = yCoord/64.0;
		if(y > 0){
			//get the tile 32 pixels down and right and 32 pixels down and left of the player's position
			Tile bottomLeft =  level.tileList.get((int)(playerTilePosX+0.5)+(int)(playerTilePosY+0.5)*level.getWidth());
			Tile bottomRight =  level.tileList.get((int)(playerTilePosX-0.5)+(int)(playerTilePosY+0.5)*level.getWidth());
			if(bottomLeft.isSolid || bottomRight.isSolid){	//isSolid is true if and only if the player is above the tile
				if(checkContact(bottomLeft) || checkContact(bottomRight)){
					return true;
				}
			}			
		}
		return false;
	}
	//check in pixel precision for platform contact by checking y coordinate and then x coordinate
	public boolean checkContact(Tile t){
		if(t instanceof StdPlatformTile){
			for(int i = 0; i < ((StdPlatformTile) t).solidPointsY.length; i++){
				if(yCoord + 29 == ((StdPlatformTile) t).solidPointsY[i]){
					if(t instanceof LeftStdPlatformTile){
						for(int j = 0; j < ((LeftStdPlatformTile) t).solidPointsX.length; j++){
							if(xCoord + 31 < ((LeftStdPlatformTile) t).solidPointsX[j]){
								return false;
							}
						}
					}
					else if(t instanceof RightStdPlatformTile){
						for(int j = 0; j < ((RightStdPlatformTile) t).solidPointsX.length; j++){
							if(xCoord - 31 > ((RightStdPlatformTile) t).solidPointsX[j]){
								return false;
							}
						}
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public int getXCoord(){
		return xCoord;
	}
	
	public int getYCoord(){
		return yCoord;
	}
}
