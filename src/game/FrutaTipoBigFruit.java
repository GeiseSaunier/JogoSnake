package game;

import java.awt.Color;
import java.awt.Graphics;

public class FrutaTipoBigFruit extends FrutasDiversas {

		
		public FrutaTipoBigFruit(int dX, int dY, int tileSize) {
			// TODO Auto-generated constructor stub
			this.dX = dX;
			this.dY = dY;
			WIDTH = tileSize;
			HEIGHT = tileSize;
			score = 20;
		}
		

		@Override
		public void draw(Graphics g) {
			g.setColor(Color.GREEN);
			g.fillRect(dX*WIDTH, dY*HEIGHT, WIDTH, HEIGHT);
			g.setColor(Color.BLUE);
			g.fillRect(dX*WIDTH, dY*HEIGHT, WIDTH/2, HEIGHT/2);
		}
		
	}

	
