package game;

	
	import java.awt.Graphics;

	public abstract class FrutasDiversas {
		
		protected int dX, dY, WIDTH, HEIGHT, score;
		
		public abstract void draw(Graphics g);
		
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
