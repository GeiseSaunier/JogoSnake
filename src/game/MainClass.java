package game;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class MainClass {
	
	public MainClass() {
		
		@SuppressWarnings("unused")
		boolean exited = false;
		
		while (!exited) {
		
		MenuJogo menu = new MenuJogo();
		
		while(!menu.isStarted()) {
			
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		String tipoSnake = menu.getSnake();
		
		menu.getFrame().dispose();
		
		JFrame frame = new JFrame();
		GamePanel painel = new GamePanel(tipoSnake);
		//JButton button = new JButton("Start");
		
		frame.add(painel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Vamos Jogar?");
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		
		frame.setVisible(true);
		
		//Game loop
		while(true) {
			if(!painel.tick()) {
				break;
			}
			
		}
		
		int points = painel.getPoints();
		frame.dispose();
		
		GameOverPanel overpanel = new GameOverPanel(points);
		overpanel.setRestarted(false);
		while(!overpanel.isRestarted()) {
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		exited = overpanel.isExited();
		overpanel.getFrame().dispose();
		
		}
	}
	public static void main(String[] args) {
		new MainClass();
	}

}
