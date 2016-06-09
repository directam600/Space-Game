package tile;

import graphics.sprites.Sprite;
import graphics.sprites.VoidSprite;

public class VoidTile extends Tile{
	public static VoidTile void_tile = new VoidTile(VoidSprite.void_sprite);
	
	public VoidTile(Sprite sprite) {
		this.sprite = sprite;
	}
	
}
