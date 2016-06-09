package tile;

import graphics.sprites.SkySprite;
import graphics.sprites.Sprite;

public class SkyTile extends Tile{
	
	public static SkyTile sky_tile = new SkyTile(SkySprite.sky_sprite);
	
	public SkyTile(Sprite sprite) {
		this.sprite = sprite;
	}
	
}
