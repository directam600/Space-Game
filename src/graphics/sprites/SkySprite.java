package graphics.sprites;

public class SkySprite extends Sprite{

	public static SkySprite sky_sprite = new SkySprite(64,0,0,SpriteSheet.individual_sprites);
	
	public SkySprite(int size, int xCoord, int yCoord, SpriteSheet sheet) {
		super(size, xCoord, yCoord, sheet);
	}

}
