package tile;

import graphics.sprites.LeftStdPlatformSprite;
import graphics.sprites.MiddleStdPlatformSprite;
import graphics.sprites.RightStdPlatformSprite;
import graphics.sprites.Sprite;

public class StandardPlatformTile extends Tile{	
	public static StandardPlatformTile l_tile = new StandardPlatformTile(LeftStdPlatformSprite.l_sprite);
	public static StandardPlatformTile m_tile = new StandardPlatformTile(MiddleStdPlatformSprite.m_sprite);
	public static StandardPlatformTile r_tile = new StandardPlatformTile(RightStdPlatformSprite.r_sprite);

	
	public StandardPlatformTile(Sprite sprite) {
		this.sprite = sprite;
	}
}
