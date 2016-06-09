package graphics.sprites;

public class VoidSprite extends Sprite{
	public static VoidSprite void_sprite = new VoidSprite(64,0,1,SpriteSheet.individual_sprites);
	
	public VoidSprite(int size, int xCoord, int yCoord, SpriteSheet sheet) {
		super(size, xCoord, yCoord, sheet);
	}
}
