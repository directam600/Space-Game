package entities.platforms;

import graphics.sprites.Sprite;

public class StandardPlatform extends Platform{
	public StandardPlatform(int x, int y, Sprite lSprite, Sprite mSprite, Sprite rSprite){
		this.xCoord = x;
		this.yCoord = y;
		this.lSprite = lSprite;
		this.mSprite = mSprite;
		this.rSprite = rSprite;
	}

	@Override
	public void update() {
		return;
	}
}
