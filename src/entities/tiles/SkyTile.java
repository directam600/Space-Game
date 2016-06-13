package entities.tiles;

import entities.mobs.player.Player;
import graphics.sprites.SkySprite;
import graphics.sprites.Sprite;

public class SkyTile extends Tile{
	
	public static SkyTile sky_tile = new SkyTile(SkySprite.sky_sprite);
	
	private SkyTile(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void update(Player p) {
				
	}
	
}
