	package game;

	import java.awt.Color;
	import java.awt.Graphics;

	
	public class FrutaTipoBombFruit extends FrutasDiversas{

		public FrutaTipoBombFruit(int dX, int dY, int tileSize) {
			
			this.dX = dX;
			this.dY = dY;
			WIDTH = tileSize;
			HEIGHT = tileSize;
			
		}

		public void draw(Graphics g) {
			
			g.setColor(Color.WHITE);
			g.fillRect(dX*WIDTH, dY*HEIGHT, WIDTH, HEIGHT);
			g.fillRect((dX*WIDTH)+5, (dY*HEIGHT)-3, 2, 3);
			g.setColor(Color.BLACK);
			g.fillRect((dX*WIDTH)+1, (dY*HEIGHT)+1, WIDTH-2, HEIGHT-2);
			g.setColor(Color.RED);
			g.fillRect((dX*WIDTH)+5, (dY*HEIGHT)-5, 3, 2);
			
		}
		
	}
