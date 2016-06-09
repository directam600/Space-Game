package graphics.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private String path;
	int width, height;
	public int[] pixels;
	protected static SpriteSheet individual_sprites = new SpriteSheet("/textures/sheets/tiles.png");
	protected static SpriteSheet player_model = new SpriteSheet("/textures/player/temp2.png");
	public SpriteSheet(String path){
		this.path = path;	
		load();
	}
	
	private void load(){
		try{
			BufferedImage bi = ImageIO.read(SpriteSheet.class.getResource(path));
			width = bi.getWidth();
			height = bi.getHeight();
			pixels = new int[width * height];
			bi.getRGB(0,0,width,height,pixels,0,width);
		} catch(IOException e){
			e.printStackTrace();
			System.out.println("Failed to load spritesheet");
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
}
