package graphics.sprites;

public class MiddleStdPlatformSprite extends Sprite{
	public static MiddleStdPlatformSprite m_sprite = new MiddleStdPlatformSprite(64,2,0,SpriteSheet.individual_sprites);
	
	public MiddleStdPlatformSprite(int size, int xCoord, int yCoord,
			SpriteSheet sheet) {
		super(size, xCoord, yCoord, sheet);
	}
}
