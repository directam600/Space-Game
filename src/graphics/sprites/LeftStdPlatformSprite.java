package graphics.sprites;

public class LeftStdPlatformSprite extends Sprite{
	public static LeftStdPlatformSprite l_sprite = new LeftStdPlatformSprite(64,1,0,SpriteSheet.individual_sprites);
	
	public LeftStdPlatformSprite(int size, int xCoord, int yCoord,
			SpriteSheet sheet) {
		super(size, xCoord, yCoord, sheet);
	}
}
