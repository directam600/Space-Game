package entities.tiles;

import graphics.sprites.LeftStdPlatformSprite;
import graphics.sprites.MiddleStdPlatformSprite;
import graphics.sprites.RightStdPlatformSprite;

public class StdPlatformTileFactory {
	public StdPlatformTile sptFactory(String name, int xPixel, int yPixel){
		if(name.equals("l_tile")){
			return new LeftStdPlatformTile(LeftStdPlatformSprite.l_sprite, xPixel, yPixel);
		} else if(name.equals("m_tile")){
			return new MiddleStdPlatformTile(MiddleStdPlatformSprite.m_sprite, xPixel, yPixel);
		} else if(name.equals("r_tile")){
			return new RightStdPlatformTile(RightStdPlatformSprite.r_sprite, xPixel, yPixel);
		}
		return null;
	}
}
