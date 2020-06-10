package game;

	import java.awt.Color;
	import java.awt.Graphics;
	
	public class Wall {
		
		private int dX, dY, WIDTH, HEIGHT;
		
		public Wall(int dX, int dY, int tileSize) {
			this.dX = dX;
			this.dY = dY;
			WIDTH = tileSize;
			HEIGHT = tileSize;
		}
		
		public void draw(Graphics g) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(dX*WIDTH, dY*HEIGHT, WIDTH, HEIGHT);
		}
		public int getdX() {
			return dX;
		}

		public void setdX(int dX) {
			this.dX = dX;
		}

		public int getCoordY() {
			return dY;
		}

		public void setdY(int coordY) {
			this.dY = coordY;
		}
	}


