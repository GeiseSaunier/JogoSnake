package game;

import java.util.concurrent.TimeUnit;

	
	public class TimeGame implements Runnable{

		private Thread thread;
		private boolean running, disappear, delay;
		
		public boolean isdisappear() {
			return disappear;
		}
		public void setdisappear(boolean disappear) {
			this.disappear = disappear;
		}
		public boolean isDelay() {
			return delay;
		}
		public void setDelay(boolean delay) {
			this.delay = delay;
		}
		public TimeGame() {
			disappear = false;
			delay = true;
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
		@Override
		public void run() {
			System.out.println("Running");
			while(running) {
				
				
				try {
					TimeUnit.MILLISECONDS.sleep(70);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println(sumir);
				
				
				if(disappear == true) disappear();
				if(delay == true) Delay();
			}
		}
		public void disappear() {
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			disappear= false;
		}
		public void Delay() {
			try {
				TimeUnit.SECONDS.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			delay = false;
		}
		

	}
