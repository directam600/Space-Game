package graphics.sprites;

public class RightStdPlatformSprite extends Sprite{

	public static RightStdPlatformSprite r_sprite = new RightStdPlatformSprite(64,3,0,SpriteSheet.individual_sprites);
	
	public RightStdPlatformSprite(int size, int xCoord, int yCoord,
			SpriteSheet sheet) {
		super(size, xCoord, yCoord, sheet);
	}
}
