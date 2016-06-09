package entities.platforms;

import entities.Entity;
import graphics.sprites.Sprite;

public abstract class Platform extends Entity{
	protected Sprite lSprite, mSprite, rSprite;
	protected boolean movable = false;
	public abstract void update();
	public int getXCoord(){
		return xCoord;
	}
	public int getYCoord(){
		return yCoord;
	}
}
