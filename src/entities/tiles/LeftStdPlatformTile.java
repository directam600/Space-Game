package entities.tiles;

import entities.mobs.player.Player;
import graphics.sprites.Sprite;

public class LeftStdPlatformTile extends StdPlatformTile{	
	public LeftStdPlatformTile(Sprite sprite, int xPixel, int yPixel) {
		super(sprite);
		xCoord = xPixel;
		yCoord = yPixel;
		solidPointsX = new int[]{xCoord + 32};
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
