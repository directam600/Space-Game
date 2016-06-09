package graphics.sprites;

public class PlayerSprite extends Sprite{
	
	public static PlayerSprite player_sprite = new PlayerSprite(64,0,0,SpriteSheet.player_model);
	
	public PlayerSprite(int size, int xCoord, int yCoord, SpriteSheet sheet) {
		super(size, xCoord, yCoord, sheet);
	}
}
