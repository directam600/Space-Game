package entities.tiles;

import entities.mobs.player.Player;
import graphics.sprites.Sprite;

public class MiddleStdPlatformTile extends StdPlatformTile{
	public MiddleStdPlatformTile(Sprite sprite, int xPixel, int yPixel) {
		super(sprite);
		xCoord = xPixel;
		yCoord = yPixel;
		solidPointsY = new int[]{yCoord + 52};
	}
	
	public void update(Player p){
		if(p.getYCoord() + 29 <= yCoord + 52){
			isSolid = true;
		}
		else{
			isSolid = false;
		}
	}
}
