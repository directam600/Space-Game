package entities.tiles;

import entities.Entity;
import entities.mobs.player.Player;
import graphics.sprites.Sprite;

public abstract class Tile extends Entity{
	public Sprite sprite;
	public boolean isSolid = false;
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	public abstract void update(Player p);
}
