package entities.tiles;

import entities.mobs.player.Player;
import graphics.sprites.Sprite;
import graphics.sprites.VoidSprite;

public class VoidTile extends Tile{
	public static VoidTile void_tile = new VoidTile(VoidSprite.void_sprite);
	
	private VoidTile(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void update(Player p) {
		
	}
	
}
