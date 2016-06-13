package entities.mobs;

import entities.Entity;
import graphics.sprites.Sprite;

public abstract class Mob extends Entity{
	protected boolean killable = true;
	protected boolean isSolid = true;
	protected int health;
	
	protected abstract void move(int xDir, int yDir);
}
