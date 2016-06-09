package level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import tile.SkyTile;
import tile.StandardPlatformTile;
import tile.Tile;
import tile.VoidTile;
import entities.Entity;
import entities.mobs.player.Player;
import entities.platforms.Platform;
import graphics.Screen;

public class Level{
	protected int width, height;
	protected int[] tiles;
	private int maxDistance = 3;
	public int skyColor = 0xff0096ff;
	
	public static Level baseLevel = new Level("/levels/BaseLevel.png");
	List<Player> player = new ArrayList<Player>();
	List<Platform> platform = new ArrayList<Platform>();
	
	public Level(String path){
		load(path);
	}

	protected void load(String path) {
		try{
			BufferedImage image = ImageIO.read(Level.class.getResource(path));
			width = image.getWidth();	//40 (converted to game size = 2560)
			height = image.getHeight();	//60 (converted to game size = 3840)
			tiles = new int[width * height];
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
				screen.renderTile((x << 6) - topLeftPixel_x,(y << 6) - topLeftPixel_y,t);	//convert to pixel precision and obtain the relevant screen pixel
			}
		}
		for(int i = 0; i < player.size(); i++){
			screen.renderEntity(player.get(i).getXCoord() - 32 - topLeftPixel_x, player.get(i).getYCoord() - 32 - topLeftPixel_y + 180, player.get(i));
		}
		for(int i = 0; i < platform.size(); i++){
			screen.renderEntity(platform.get(i).getXCoord() - topLeftPixel_x, player.get(i).getYCoord() - topLeftPixel_y + 180, platform.get(i));
		}
	}
	
	void add(Entity e){
		if(e instanceof Player){
			player.add((Player)e);
		}
		else if(e instanceof Platform){
			platform.add((Platform)e);
		}
	}
	
	void update(){
		for(int i = 0; i < player.size(); i++){
			player.get(i).update();
		}
		for(int i = 0; i < platform.size(); i++){
			platform.get(i).update();
		}
	}	

	protected Tile generateTile(int x, int y){
		if(x < 0 || x >= width || y < 0 || y >= height) return VoidTile.void_tile; 	//will be changed to skytile later
		if(tiles[x+y*width] == -1) return StandardPlatformTile.l_tile;
		else if(tiles[x+y*width] == -2) return StandardPlatformTile.m_tile;
		else if(tiles[x+y*width] == -3) return StandardPlatformTile.r_tile;
		else return SkyTile.sky_tile;
	}

	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
}
