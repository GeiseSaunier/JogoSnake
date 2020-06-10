package game;
	
import java.awt.Color;
import java.awt.Graphics;
import java.lang.String;
import java.util.Random;

public class SnakeBody {
	
	private int dX, dY, WIDTH, HEIGHT, gx ,gy;
	private String snake;
	private Random ran;

	public SnakeBody(int dX, int dY, int tileSize, String snake) {
		
		ran = new Random();
		this.dX = dX;
		this.dY = dY;
		WIDTH = tileSize;
		HEIGHT = tileSize;
		this.snake = snake;
		
	}
	
	public void draw(Graphics g) {
		
		if(snake == "Snake Comum"){
			g.setColor(Color.CYAN);
			g.fillRect(dX*WIDTH, dY*HEIGHT, WIDTH, HEIGHT);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(dX*WIDTH, dY*HEIGHT, WIDTH/2, HEIGHT/2);
		}
		if(snake == "Kitty"){
			g.setColor(Color.GREEN);
			g.fillRect(dX*WIDTH, dY*HEIGHT, WIDTH, HEIGHT);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(dX*WIDTH, dY*HEIGHT, WIDTH/2, HEIGHT/2);
		}
		if(snake == "Star"){
			g.setColor(Color.YELLOW);
			g.fillRect(dX*WIDTH, dY*HEIGHT, WIDTH, HEIGHT);
			gx = ran.nextInt(5);
			gy = ran.nextInt(5);
			g.setColor(Color.WHITE);
			g.fillRect((dX*WIDTH)+gx, (dY*HEIGHT)+gy, 3, 3);
		}
	}

	public int getdX() {
		return dX;
	}

	public void setdX(int dX) {
		this.dX = dX;
	}

	public int getdY() {
		return dY;
	}

	public void setdY(int dY) {
		this.dY = dY;
	}
}
