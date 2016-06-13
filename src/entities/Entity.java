package entities;

import graphics.sprites.Sprite;
import level.Level;

public abstract class Entity {
	public int xCoord, yCoord;
	public Sprite sprite;
	protected boolean isSolid;
}
