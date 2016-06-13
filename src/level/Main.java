package level;
import entities.mobs.player.Player;
import entities.mobs.player.PlayerSpawnPoint;
import graphics.Screen;
import input.Keyboard;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

/**
 * Class providing the main() method
 * <br>
 * 
 * @author James Du
 *
 */
public class Main extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;

	private static int width = 640;
	private static int height = 960;
	
	private static final String TITLE = "Space Game";
	
	private boolean running = false;
	private Thread thread;
	private JFrame frame;
	private Screen screen;
	private Level level;
	private Keyboard key;
	private Player player;
	private PlayerSpawnPoint spawn;
	private final double XTOPLEFTPIXEL;
	
	private BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)bi.getRaster().getDataBuffer()).getData();
	
	public Main(){
		Dimension d = new Dimension(width,height);
		setPreferredSize(d);
		frame = new JFrame(TITLE);
		screen = new Screen(width,height);
		level = Level.baseLevel;
		key = new Keyboard();
		spawn = new PlayerSpawnPoint(level);
		player = new Player(spawn.getXCoord(), spawn.getYCoord(), key);
		XTOPLEFTPIXEL = player.getXCoord() - screen.getWidth()/2;
		level.add(player);
		addKeyListener(key);
	}
	
	public static void main(String[] args){
		Main m = new Main();
		m.frame.setResizable(false);
		m.frame.add(m);
		m.frame.pack();
		m.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.frame.setLocationRelativeTo(null);
		m.frame.setVisible(true);
		m.start();
	}

	private synchronized void start(){
		if(running){
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop(){
		if(!running){
			return;
		}
		running = false;
		try{
			thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	@Override
	public void run() {
		long previousTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		
		double nanoSecPerTick = 1000000000.0 / 60.0; // 1 billion / 60 ticks
		double threshold = 0.0;
		int ticks = 0;
		int frames = 0;
		requestFocus();
		while(running){
			long currentTime = System.nanoTime();
			threshold += (currentTime - previousTime) / nanoSecPerTick;
			previousTime = currentTime;
			if(threshold >= 1){
				update();
				ticks++;
				threshold--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(ticks + " Ticks, FPS: " + frames);
				frame.setTitle(TITLE + " | " + "FPS: " + frames);
				ticks = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	public void update(){
		key.update();
		level.update();
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		double yTopLeftPixel = player.getYCoord() - (screen.getHeight()*3)/4 + 16;	//-32
		level.getBluePrint((int)XTOPLEFTPIXEL, (int)yTopLeftPixel, screen);
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(bi,0,0,width,height,null);
		g.dispose();
		bs.show();
	}
	
	

}
