package entities.mobs.player;

import input.Keyboard;
import entities.mobs.Mob;
import graphics.Screen;
import graphics.sprites.PlayerSprite;
import graphics.sprites.Sprite;

public class Player extends Mob{
	
	private Keyboard key;
	private int speed;
	
	public Player(int x, int y, Keyboard key) {
		//super(x, y, sprite);
		xCoord = x;	//pixel precision
		yCoord = y;
		this.key = key;
		speed = 8;
		sprite = PlayerSprite.player_sprite;
	}
	//TODO: implement shoot();
	public void update(){
		updateMove();
		//shoot();
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
	
	protected void move(int xDir, int yDir){
		xCoord += xDir;
		yCoord += yDir;
	}
	
	public int getXCoord(){
		return xCoord;
	}
	
	public int getYCoord(){
		return yCoord;
	}
}
