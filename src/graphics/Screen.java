package graphics;

import entities.Entity;
import entities.mobs.Mob;
import entities.tiles.Tile;

public class Screen {
	private int width, height;	//width and height of screen in pixels
	public int[] pixels;
	
	public Screen(int width, int height){
		this.width = width;
		this.height = height;
		pixels = new int[width * height];	
	}
	
	public void clear(){
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = 0;
		}
	}
	public void renderTile(int xCoord, int yCoord, Tile tile){
		for(int y = 0; y < tile.sprite.SIZE; y++){
			int yAdjusted = y + yCoord;
			for(int x = 0; x < tile.sprite.SIZE; x++){
				int xAdjusted = x + xCoord;
				if(xAdjusted < -tile.sprite.SIZE || xAdjusted >= width || yAdjusted < 0 || yAdjusted >= height) break;
				if(xAdjusted < 0) xAdjusted = 0;
				int spritePixel = tile.sprite.getPixels()[x+y*tile.sprite.SIZE];
				if(spritePixel != 0xffff00ff){
					pixels[xAdjusted+yAdjusted*width] = spritePixel;
				}
				else{
					pixels[xAdjusted+yAdjusted*width] = 0xff0096ff;
				}
			}
		}
	}
	
	public void renderEntity(int xCoord, int yCoord, Entity e){
		for(int y = 0; y < e.sprite.SIZE; y++){
			int yAdjusted = y + yCoord;
			for(int x = 0; x < e.sprite.SIZE; x++){
				int xAdjusted = x + xCoord;
				if(xAdjusted < -e.sprite.SIZE || xAdjusted >= width || yAdjusted < 0 || yAdjusted >= height) break;
				int spritePixel = e.sprite.getPixels()[x+y*e.sprite.SIZE];
				if(spritePixel != 0xffff00ff){
					pixels[xAdjusted+yAdjusted*width] = spritePixel;
				}
			}
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

}
