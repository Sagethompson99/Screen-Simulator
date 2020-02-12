package graphicsSimple;

public class simpleScreen {
	
	private int displayHeight;
	private int displayWidth;
	private int numFramesToCall;
	private String pixelOn;
	private String pixelOff;
	
	simpleScreen(int displayHeight, int displayWidth, int numFrames, String pixelOn, String pixelOff){
		this.displayHeight = displayHeight;
		this.displayWidth = displayWidth;
		this.numFramesToCall = numFrames;
		this.pixelOn = pixelOn;
		this.pixelOff = pixelOff;
	}
	
	//takes in current frame and updates pixel values accordingly
	public void updatePixelStates(int frame) {
		int pixelValues[][] = new int[displayHeight][displayWidth];
		
		int rowPos = 0;
        int colPos = 0;
		colPos = frame/displayWidth;
		rowPos = frame;
		if(rowPos >= displayWidth)
			rowPos = frame % displayWidth;
		
		for(int i = 0; i < displayHeight; i++) {
			for(int j = 0; j < displayWidth; j++) {
				if(i == colPos && j == rowPos)
					pixelValues[i][j] = 1;
			}
		}
		redrawImage(pixelValues);
	}
	
	//uses pixelValues[][] array to print the current frame of the screen
	public void redrawImage(int[][] values) {
		String display[][] = new String[displayHeight][displayWidth];
		
		for(int i = 0; i < display.length; i++) {
			for(int j = 0; j < display[i].length; j++) {
				if(values[i][j] == 0) {
					display[i][j] = pixelOff;
					System.out.print(display[i][j]);
				}
				else {
					display[i][j] = pixelOn;
					System.out.print(display[i][j]);
				}
				if(j == display[i].length-1)
					System.out.println();
			}
		}
	}
	
	//clears screen
	public void clearScreen() {
		for(int i = 0; i < 20; i++)
			System.out.println();
	}
	
	public static void main(String args[]) {
		int fps = 15;
		double timePerTick = 1000000000/fps; //1,000,000,000 nano seconds in a second
		double delta = 0; //keeps track of when to call ticks
		long now;
		long lastTime = System.nanoTime();
		String onPixel = "Sage";
		String offPixel = "-";
		
		simpleScreen tv = new simpleScreen(20, 70, 800, onPixel, offPixel);

        boolean running = true;
        int framesCalled = 0;
        
		while(running) {
			if(framesCalled >= tv.numFramesToCall)
	        	running = false;
			
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			if(delta >= 1) {
				if(framesCalled > 0) {
					tv.clearScreen();
				
				}
				tv.updatePixelStates(framesCalled);
				
				if(framesCalled > 0)
					tv.clearScreen();
				
				delta--;
				framesCalled++;
			}
		}
	}
}