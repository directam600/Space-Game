package entities.tiles;

import graphics.sprites.Sprite;

public abstract class StdPlatformTile extends Tile{
	public int[] solidPointsX;
	public int[] solidPointsY;
	public StdPlatformTile(Sprite sprite){
		super(sprite);
	}
}
