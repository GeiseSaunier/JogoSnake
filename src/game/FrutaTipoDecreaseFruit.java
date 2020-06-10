package game;

	import java.awt.Color;
	import java.awt.Graphics;

	public class FrutaTipoDecreaseFruit extends FrutasDiversas {
		
		public FrutaTipoDecreaseFruit(int dX, int dY, int tileSize) {
			
			this.dX = dX;
			this.dY = dY;
			WIDTH = tileSize;
			HEIGHT = tileSize;
			
		}

		public void draw(Graphics g) {
			
			g.setColor(Color.CYAN);
			g.fillRect(dX*WIDTH, dY*HEIGHT, WIDTH, HEIGHT);
			g.setColor(Color.WHITE);
			g.fillRect(dX*WIDTH, dY*HEIGHT, WIDTH/2, HEIGHT/2);
			
		}
	}