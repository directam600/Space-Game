package graphics.sprites;


public class Sprite {
	public final int SIZE;
	private int x, y;
	private int width, height;	//sprite width/height
	private int[] pixels;
	protected SpriteSheet sheet;
		
	public Sprite(int size, int xCoord, int yCoord, SpriteSheet sheet){
		this.SIZE = size;
		width = SIZE;
		height = SIZE;
		x = xCoord * SIZE;
		y = yCoord * SIZE;
		pixels = new int[SIZE * SIZE];
		this.sheet = sheet;
		obtainPixels();
	}
	
	protected void obtainPixels(){
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y)
				             						* sheet.width];
			}
		}
	}

	public int[] getPixels(){
		return pixels;
	}
}
