package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

@SuppressWarnings("unused")
public class GamePanel extends JPanel implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	
	private Thread thread;
	private boolean running;
	private TimeGame timegame;
	
	private boolean SpecialFruit = false;
	private FrutaTipoFrutaComum fruta;
	private ArrayList<FrutaTipoFrutaComum> frutas;
	private FrutaTipoBigFruit bigFruit;
	private ArrayList<FrutaTipoBigFruit> bigFruits;
	private FrutaTipoBombFruit bombFruit;
	private ArrayList<FrutaTipoBombFruit> bombFruits;
	private FrutaTipoDecreaseFruit decreaseFruit;
	private ArrayList<FrutaTipoDecreaseFruit> decreaseFruits;
	
	private Wall wall;
	private ArrayList<Wall> wall1, wall2, wall3, wall4;
	
	
	private Random ran, gamble;
	
	private String tipoSnake;
	
	private SnakeBody c;
	private ArrayList<SnakeBody> snake;
	private int dX = 10, dY = 7, size = 5, points = 0 , multiplier = 1;
	private boolean right = true, left = false, up = false, down = false;
	
	public static final int WIDTH = 500, HEIGHT = 500;
	
	public GamePanel(String tipoSnake) {
		
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		this.tipoSnake = tipoSnake;
		if(tipoSnake == "Star") multiplier = 2;
		wall1 = new ArrayList<Wall>();
		wall2 = new ArrayList<Wall>();
		wall3 = new ArrayList<Wall>();
		wall4 = new ArrayList<Wall>();
		snake = new ArrayList<SnakeBody>();
		frutas = new ArrayList<FrutaTipoFrutaComum>();
		bigFruits = new ArrayList<FrutaTipoBigFruit>();
		bombFruits = new ArrayList<FrutaTipoBombFruit>();
		decreaseFruits = new ArrayList<FrutaTipoDecreaseFruit>();
		timegame = new TimeGame();
		ran = new Random();
		gamble = new Random();
		
		for(int i=0;i<16;i++) {
			wall = new Wall(10,17+i,10);
			wall1.add(wall);
		}
		for(int i=0;i<16;i++) {
			wall = new Wall(40,17+i,10);
			wall2.add(wall);
		}
		for(int i=0;i<16;i++) {
			wall = new Wall(17+i,10,10);
			wall3.add(wall);
		}
		for(int i=0;i<16;i++) {
			wall = new Wall(17+i,40,10);
			wall4.add(wall);
		}
		
		start();
	}
	
	public void start() {
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public void stop() {
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean tick() {
		
		if(snake.size() == 0) {
			c = new SnakeBody(dX, dY, 10, tipoSnake);
			snake.add(c);
		}
		try {
			TimeUnit.MILLISECONDS.sleep(70);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			if(right) dX ++;
			if(left) dX--;
			if(down) dY++;
			if(up) dY--;
			
			c = new SnakeBody(dX, dY, 10, tipoSnake);
			snake.add(c);
			
			if(snake.size() > size) {
				snake.remove(0);
			}
			

		//Criação da fruta comum
		if(frutas.size() == 0) {
			int dX = ran.nextInt(48)+1;
			int dY = ran.nextInt(48)+1;
			if (WallCollide(dX, dY) == 1) dX++;
			if (WallCollide(dX, dY) == 2) dX--;
			if (WallCollide(dX, dY) == 3) dY++;
			if (WallCollide(dX, dY) == 4) dY--;
			fruta = new FrutaTipoFrutaComum(dX, dY, 10);
			frutas.add(fruta);
		}
		
		//Colisão da fruta comum
		for(int i=0;i<frutas.size();i++) {
			if(dX == frutas.get(i).getdX() && dY == frutas.get(i).getdY()) {
				size++;
				frutas.remove(i);
				i++;
				points+=10*multiplier;
			}
		}
		
		//Randomizador frutas diversas
		int gambled = gamble.nextInt(10);
		
		//Geração de frutas diversas
		if (SpecialFruit == false && timegame.isDelay() == false) {
			int dX = ran.nextInt(40);
			int dY = ran.nextInt(40);
			if (WallCollide(dX, dY) == 1) dX++;
			if (WallCollide(dX, dY) == 2) dX--;
			if (WallCollide(dX, dY) == 3) dY++;
			if (WallCollide(dX, dY) == 4) dY--;
			
			//Gerando fruta do tipo BigFruit
			
			if(gambled >= 0 && gambled <= 4) {
				
				bigFruit = new FrutaTipoBigFruit(dX, dY, 10);
				bigFruits.add(bigFruit);
				SpecialFruit = true;
				timegame.setdisappear(true);
			}
			//Gerando fruta do tipo BombFruit 
			
			if(gambled >= 5 && gambled <= 8) {
				bombFruit = new FrutaTipoBombFruit(dX, dY, 10);
				bombFruits.add(bombFruit);
				SpecialFruit = true;
				timegame.setdisappear(true);
			}
			
			//Gerando fruta do tipo DecreaseFruit
			
			if(gambled >= 9 && gambled <= 10) {
				decreaseFruit = new FrutaTipoDecreaseFruit(dX, dY, 10);
				decreaseFruits.add(decreaseFruit);
				SpecialFruit = true;
				timegame.setdisappear(true);
			}
		}
		
		//Colisão da fruta BigFruit
		for(int i=0;i<bigFruits.size();i++) {
			if((dX == bigFruits.get(i).getdX() && dY == bigFruits.get(i).getdY())) {
				size++;
				bigFruits.remove(i);
				i++;
				SpecialFruit = false;
				timegame.setDelay(true);
				timegame.setdisappear(false);
				points+=20*multiplier;
				break;
			}
			if(!timegame.isdisappear()) {
				bigFruits.remove(i);
				i++;
				SpecialFruit = false;
				timegame.setDelay(true);
			}
		}
		
		//Colisão da fruta BombFruit
		for(int i=0;i<bombFruits.size();i++) {
			if((dX == bombFruits.get(i).getdX() && dY == bombFruits.get(i).getdY())) {
				bombFruits.remove(i);
				i++;
				SpecialFruit = false;
				timegame.setDelay(true);
				timegame.setdisappear(false);
				System.out.println("GAME OVER");
				stop();
				return false;
				}
			if(!timegame.isdisappear()) {
				bombFruits.remove(i);
				i++;
				SpecialFruit = false;
				timegame.setDelay(true);
			}
		}
		
		//Colisão da fruta DecreaseFruit
		for(int i=0;i<decreaseFruits.size();i++) {
			if((dX == decreaseFruits.get(i).getdX() && dY == decreaseFruits.get(i).getdY())) {
				size = 5;
				decreaseFruits.remove(i);
				i++;
				SpecialFruit = false;
				timegame.setDelay(true);
				timegame.setdisappear(false);
				break;
			}
			if(!timegame.isdisappear()) {
				decreaseFruits.remove(i);
				i++;
				SpecialFruit = false;
				timegame.setDelay(true);
			}
		}
		if(snake.size() > size) {
			snake.remove(0);
		}
		
		//Colisão do tipo snakebody
		for(int i=0;i<snake.size();i++) {
			if(dX == snake.get(i).getdX() && dY == snake.get(i).getdY()) {
				if(i != snake.size() -1) {
					stop();
					return false;
					
				}
			}
		}
		
		//Colisão Wall
		if (WallCollide(dX, dY) != 0 && tipoSnake == "Kitty") {
			stop();
			return false;
		}
		
		if (dX < -1) dX = 50;
		if (dX > 50) dX = -1;
		if (dY < -1) dY = 50;
		if (dY > 50) dY = -1;
			
		return true;
	
	}
	
	public void paint(Graphics g2) {
		Graphics2D g;
		g = (Graphics2D) g2;
		
		//Draw Grid
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		for(int i=0;i<WIDTH/10;i++) {
			g.drawLine(i*10, 0, i*10, HEIGHT);
		}
		for(int i=0;i<HEIGHT/10;i++) {
			g.drawLine(0, i*10, HEIGHT, i*10);
		}
		
		//Draw Walls
				for(int i=0;i<wall1.size();i++) {
					wall1.get(i).draw(g);
				}
				for(int i=0;i<wall2.size();i++) {
					wall2.get(i).draw(g);
				}
				for(int i=0;i<wall3.size();i++) {
					wall3.get(i).draw(g);
				}
				for(int i=0;i<wall4.size();i++) {
					wall4.get(i).draw(g);
				}
		//Draw Snake
		for(int i=0;i<snake.size();i++) {
			snake.get(i).draw(g);
		}
		
		//Draw SimpleFruit
		for(int i=0;i<frutas.size();i++) {
			frutas.get(i).draw(g);
		}
		
		//Draw BigFruit
		for(int i=0;i<bigFruits.size();i++) {
			bigFruits.get(i).draw(g);
		}
		
		//Draw BombFruit
		for(int i=0;i<bombFruits.size();i++) {
			bombFruits.get(i).draw(g);
		}
		
		//Draw DecreaseFruit
		for(int i=0;i<decreaseFruits.size();i++) {
			decreaseFruits.get(i).draw(g);
		}
		
		g.setColor(Color.ORANGE);
		g.drawString(String.valueOf(points), 0, 10);
		
	}
	public int WallCollide(int dX, int dY) {
		for(int i=0;i<wall1.size();i++) {
			if((dX == wall1.get(i).getdX() && dY == wall1.get(i).getdX())){
				return 1;
			}
		}
		for(int i=0;i<wall2.size();i++) {
			if((dX == wall2.get(i).getdX() && dY == wall2.get(i).getdX())) {
				return 2;
			}
		}
		for(int i=0;i<wall3.size();i++) {
			if((dX == wall3.get(i).getdX() && dY == wall3.get(i).getdX())) {
				return 3;
			}
		}
		for(int i=0;i<wall4.size();i++) {
			if((dX == wall4.get(i).getdX() && dY == wall4.get(i).getdX())) {
				return 4;
			}
		}
		return 0;
	}
	
	@Override
	public void run() {
		while(running) {
			repaint();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent event) {

		int key = event.getKeyCode();
		if(key == KeyEvent.VK_RIGHT && !left) {
			right = true;
			up = false;
			down = false;
		}
		if(key == KeyEvent.VK_LEFT && !right) {
			left = true;
			up = false;
			down = false;
		}
		if(key == KeyEvent.VK_UP && !down) {
			right = false;
			left = false;
			up = true;
		}
		if(key == KeyEvent.VK_DOWN && !up) {
			right = false;
			left = false;
			down = true;
		}
		try {
			TimeUnit.MILLISECONDS.sleep(70);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public int getPoints() {
		// TODO Auto-generated method stub
		return points;
	}


}
