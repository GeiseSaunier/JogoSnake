package game;

	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;


	public class GameOverPanel { 
		
		private JLabel label;
		private JFrame frame;
		private JPanel panel;
		private boolean restarted = false, exited = false;
		
		public GameOverPanel(int points) {
			
			frame = new JFrame();
			panel = new JPanel();
			panel.setLayout(new GridLayout(3,1));
			label = new JLabel(String.valueOf(points), JLabel.CENTER);
			panel.add(label);
			JButton restart = new JButton("Restart");
			JButton exit = new JButton("Exit");
			
			restart.setActionCommand("Restart");
			restart.addActionListener(new Button());
			
			exit.setActionCommand("Exit");
			exit.addActionListener(new Button());
			
			panel.add(restart);
			panel.add(exit);
			frame.add(panel);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setTitle("GAME OVER");
			frame.add(panel);
			frame.pack();
			frame.setSize(500,500);
			frame.setLocationRelativeTo(null);
			
			frame.setVisible(true);
			
		}
		public JFrame getFrame() {
			return frame;
		}
		
		public boolean isRestarted() {
			return restarted;
		}
		

		public void setRestarted(boolean restarted) {
			this.restarted = restarted;
		}
		public boolean isExited() {
			return exited;
		}


		private class Button implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				String comando = e.getActionCommand();
				if(comando.equals("Restart")) {
					restarted = true;
				}
				else if(comando.equals("Exit")) {
					
					exited = true;
					restarted = true;
				}
			}
		}
	}


