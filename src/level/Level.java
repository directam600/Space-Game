package level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import entities.Entity;
import entities.mobs.player.Player;
import entities.tiles.SkyTile;
import entities.tiles.StdPlatformTileFactory;
import entities.tiles.Tile;
import entities.tiles.VoidTile;
import graphics.Screen;

public class Level{
	protected int width, height;	//width and height of level in number of tiles
	protected int[] tiles;	//size = tile format
	private int maxDistance = 3;
	public int skyColor = 0xff0096ff;
	public StdPlatformTileFactory spt;
	private Player player;
	
	public static Level baseLevel = new Level("/levels/BaseLevel.png");
	public List<Tile> tileList = new ArrayList<Tile>();
	
	public Level(String path){
		load(path);
		spt = new StdPlatformTileFactory();
	}

	protected void load(String path) {
		try{
			BufferedImage image = ImageIO.read(Level.class.getResource(path));
			width = image.getWidth();	//10 (converted to game size = 640)
			height = image.getHeight();	//15 (converted to game size = 960)
			tiles = new int[width * height];	//
			image.getRGB(0,0,width,height,tiles,0,width);
			generateRandPlatforms();
		} catch(IOException e){
			e.printStackTrace();
			System.out.println("Could not load level.");
		}
	}
	
	protected void generateRandPlatforms(){
		Random rand = new Random();
		for(int i = 0; i < tiles.length; i++){
			if(rand.nextInt(10) == 0){			
				tiles[i] = -1;
				for(int k = 0; k < rand.nextInt(4); k++){
					if(i < tiles.length - 1) i++;
					else return;
					tiles[i] = -2;
				}
				if(i < tiles.length - 1){
					tiles[++i] = -3;
				}
				else{
					tiles[i] = -3;
				}
			}
		}	
	}
	
	protected void getBluePrint(int topLeftPixel_x, int topLeftPixel_y, Screen screen) {
		//these variables capture the screen relative to the player's position on the levelmap
		int screenTopLeft_x = topLeftPixel_x >> 6;	//tile precision
		int screenBottomRight_x = (topLeftPixel_x + screen.getWidth()) >> 6;
		int screenTopLeft_y = topLeftPixel_y >> 6;
		int screenBottomRight_y = (topLeftPixel_y + screen.getHeight() + 64) >> 6;
		for(int y = screenTopLeft_y; y < screenBottomRight_y; y++){	//x and y represent tile counters
			for(int x = screenTopLeft_x; x < screenBottomRight_x; x++){
				Tile t = generateTile(x,y);
				if(t != VoidTile.void_tile){
					if(x+y*width >= tileList.size()){
						tileList.add(t);
					}
					else{						
						tileList.set(x+y*width,t);
					}
				}
				screen.renderTile((x << 6) - topLeftPixel_x,(y << 6) - topLeftPixel_y,t);	//convert to pixel precision and obtain the relevant screen pixel
			}
		}
		screen.renderEntity(player.getXCoord() - 32 - topLeftPixel_x, player.getYCoord() - 32 - topLeftPixel_y, player);
	}
	
	void add(Entity e){
		if(e instanceof Player){
			this.player = (Player)e;
		}
	}
	
	void update(){
		for(int i = 0; i < tileList.size(); i++){
			tileList.get(i).update(player);
		}
		player.update();
	}	

	protected Tile generateTile(int x, int y){
		int xPixel = x * 64;
		int yPixel = y * 64;
		if(x < 0 || x >= width || y < 0 || y >= height) return VoidTile.void_tile; 	//will be changed to skytile later
		if(tiles[x+y*width] == -1){
			return spt.sptFactory("l_tile", xPixel, yPixel);
		}
		else if(tiles[x+y*width] == -2){
			return spt.sptFactory("m_tile", xPixel, yPixel);
		}
		else if(tiles[x+y*width] == -3){
			return spt.sptFactory("r_tile", xPixel, yPixel);
		}
		else{
			return SkyTile.sky_tile;
		}
	}

	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
}
