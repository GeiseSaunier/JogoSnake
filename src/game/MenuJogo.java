	package game;



	import java.awt.BorderLayout;
	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;

	public class MenuJogo {
	
		private JLabel statusLabel;
		private String snake = "Snake Comum";
		private JFrame frame;
		private boolean started = false;
		
	
		public JFrame getFrame() {
			return frame;
		}
		public String getSnake() {
			return snake;
		}
		public boolean isStarted() {
			return started;
		}
	
	public MenuJogo () {
		
		frame = new JFrame();
		JPanel panel = new JPanel();
		JButton startButton = new JButton("Start");
		JButton simpleButton = new JButton("Snake Comum");
		JButton kittyButton = new JButton("Kitty Snake");
		JButton starButton = new JButton("Star Snake");
		
		panel.setLayout(new BorderLayout());
		
		statusLabel = new JLabel("Escolha uma das cobrinhas para jogar:", JLabel.CENTER);
		statusLabel.setSize(500, 200);
		
		frame.setSize(500,500);
		
		//frame.setLocation(500, 200);
		
		frame.setLayout(new GridLayout(2,1));
		
		startButton.setActionCommand("Start");
		startButton.addActionListener(new Button ());
		
		simpleButton.setActionCommand("Simple");
		simpleButton.addActionListener(new Button ());
		
		kittyButton.setActionCommand("Kitty");
		kittyButton.addActionListener(new Button ());
		
		starButton.setActionCommand("Star");
		starButton.addActionListener(new Button ());
		
		panel.setSize(600,600);
		panel.add(startButton,BorderLayout.PAGE_START);
		panel.add(simpleButton,BorderLayout.LINE_START);
		panel.add(kittyButton,BorderLayout.CENTER);
		panel.add(starButton,BorderLayout.LINE_END);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Vamos jogar?");
		frame.add(panel);
		frame.add(statusLabel,BorderLayout.PAGE_END);
		frame.pack();
		frame.setSize(400,400);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
	}
	
	private class Button implements ActionListener{
		
		@Override
		
		public void actionPerformed(ActionEvent e) {
			String comando = e.getActionCommand();
			if(comando.equals("Start")) {
				
				started = true;
			}
			else if(comando.equals("Simple")) {
				statusLabel.setText("Você escolheu a Snake Comum");
				snake = "Snake Comum";
			}
			else if(comando.equals("Kitty")) {
				statusLabel.setText("Você escolheu a Kitty Snake");
				snake = "Kitty";
			}
			else if(comando.equals("Star")) {
				statusLabel.setText("Você escolheu a Star Snake");
				snake = "Star";
			}
		}
	}
	

	
}

	
