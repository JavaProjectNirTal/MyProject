package viewBoard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class MazeGameBoard extends GameBoard {

	private String mazeString;
	private int[][] maze;
	private int mazeSize;
	private Image mazeBackround, imageStart, imageEnd;
	private GameCharacter character;

	
	
	public MazeGameBoard(Composite parent, int style, String description) {
		super(parent, style, description);
		mazeBackround = new Image(getDisplay(), "resources/wall3.jpg");
		imageEnd = new Image(getDisplay(), "resources/stam.jpg");
		mazeString = description;
		if (mazeString != null) {
			buildMaze();
			createBoardGame();
			//character.setX(0);
			//character.setY(0);
			character  = new GameCharacter(0,0);
			redraw();
		}
	}

	@Override
	public void createBoardGame() {
		
		setLayout(new GridLayout(2, false));									//Layout of Board itself (in side)
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));		//Layout (out Side)
		//setBackground(new Color(null, 255, 255, 255));
		setBackgroundImage(mazeBackround);
		
		addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				Image tmp = new Image(getDisplay(), "resources/wall2.jpg");
				//setSize(300, 300);
				
/*				//e.gc.setForeground(new Color(null, 255, 0, 0));
				e.gc.setBackground(new Color(null, 0, 0, 255));

				//start = new GameCharacter(0, 0);
				//end = new GameCharacter(getSize().x-50, getSize().y-50);
				int width = getSize().x; // changes with the window size
				int height = getSize().y; // changes with the window size

				int w = width / maze[0].length; // Relatively how much columns
				int h = height / maze.length; // Relatively how much rows
				//end = new GameCharacter(maze[10][0], maze[0][10]);
				for (int i = 0; i < maze.length; i++)
					// paint the maze
					for (int j = 0; j < maze[i].length; j++) {
						int x = j*w; // width of every cell
						int y = i*h; // height of every cell
						if (maze[i][j] == 0)
							e.gc.fillRectangle(x, y, w, h);
					}
				
				//start.paint(e, w, h); // paint the red character
				//end.paint(e, w, h);			
			}
		});*/
						if (maze != null) {
							int width = getSize().x;				//x
							int height = getSize().y;				//y
							int w = width / mazeSize;				// x : mazeSize
							int h = height / mazeSize;				// y : mazeSize
							for (int i = 0; i < mazeSize; i++)
								for (int j = 0; j < mazeSize; j++) {		
									int x = j * w;						//colNumber * (x : mazeSize)
									int y = i * h;						//rowNumber * (y : mazeSize)
									if (maze[i][j] == 0) {
									e.gc.drawImage(tmp, 0, 0, tmp.getImageData().width, tmp.getImageData().height, x, y, w, h);
									e.gc.drawRectangle(x, y, w, h);
									}
									if (i == mazeSize-1 && j == mazeSize-1 ) {
										e.gc.drawImage(imageEnd, 0, 0,imageEnd.getImageData().width, imageEnd.getImageData().height, x, y, w, h);
									}
								}
							character.paint(e, w, h);
						}
					}
				});
	}

	public int getRowNumber(String s) {
		int sum=0;
		for (int i=1; i<s.length(); i++) {
			if (s.charAt(i) == ',')
				break;
			sum = sum*10 + Character.getNumericValue(s.charAt(i));
		}
		return sum;
	}
	
	public int getColumnNumber(String s) {
		int sum=0;
		int lastChar = s.length()-1;
		int firstChar = s.indexOf(',');
		for (int i=firstChar+1; i<lastChar; i++) {
			sum = sum*10 + Character.getNumericValue(s.charAt(i));
		}
		return sum;
	}
	
	public void extractSizeOfMaze(String s) {
		//stage #1 - extract size out of the description
		String[] a = s.split("Size ");
		String[] b = a[1].split("X");
		
		//stage #2 set the size on the Maze
		mazeSize = Integer.parseInt(b[0]);
		//System.out.println("size is: "+mazeSize);  //check
	}
	
	public void setWallsOfMaze(String s) {
		//stage #1 - extract walls out of the description
		String[] a = s.split(": ");
		String b = a[1];
		String[] arrWalls = b.split(" ");
		//check
		//System.out.println("walls are:");
	//	for(int i=0; i<arrWalls.length; i++)
	//		System.out.print(arrWalls[i]+" ");
		//System.out.println();
		//stage #2 set the walls on the Maze
		for(int k=0; k<arrWalls.length; k++) {
			int row =getRowNumber(arrWalls[k]); 
			int col =getColumnNumber(arrWalls[k]);
			//System.out.print("row: "+row +" ");			//for check
			//System.out.println("col: "+col);			//for check
			maze[row][col] = 0;
		}
		
	}
	
	public void buildMaze() {
		//MazeGame:Start(0,0) Goal(10,10) Size 11X11 with 30 Walls: (2,4)(5,4)(5,9)(9,5)(9,10)(4,8)(9,4)(7,1)(2,5)
		//Walls 5(2,3)(3,3)(2,1)(1,3)(3,1)
		//because of the Maze Levels, we already know: Start, Goal, numOfWalls
		//the only thing we don't know is the walls indexes
		//System.out.println("description: "+description);
		
		//#1: set size
		extractSizeOfMaze(mazeString);
		
		//#2: building maze
		maze = new int[mazeSize][mazeSize];
		for(int i=0; i<mazeSize; i++)
			for(int j=0; j<mazeSize; j++) {
				maze[i][j] = 1;
			}
		System.out.println();
		
		//#3: setting walls
		setWallsOfMaze(mazeString);
/*		
		//#4: printing for check
		for(int i=0; i<mazeSize; i++) {
			System.out.println();
			for(int j=0; j<mazeSize; j++) {
				System.out.print(maze[i][j]+" ");
			}
		}*/
	}
	
	public String getGameCharacter() {
		return ("("+(character.getX()/(getSize().y/ mazeSize))+","+(character.getY()/(getSize().x/ mazeSize))+")");
	}

	public void setGameCharacter(String cordinate) {
		int x = getRowNumber(cordinate);  
		int y = getColumnNumber(cordinate);
		
		int width = getSize().x;				//canvas width
		int height = getSize().y;               //canvas hight
		int w = width / mazeSize;				// column
		int h = height / mazeSize;	            // rows
		
		/*//colNumber * (x : mazeSize)	//rowNumber * (y : mazeSize)
		int newX = x * h;
		int newY = y * w;
		
		System.out.println("setGameChararcter");
		System.out.println("getSize().x : "+getSize().x);
		System.out.println("getSize().y : "+getSize().y);
		System.out.println("mazeSize is: "+mazeSize);
		System.out.println("x (row) is: "+x+" 	y (col) is: "+y+"\nnewI is: "+newX+" newJ is: "+newY);*/
		
		character.setX(y*w);
		character.setY(x*h);
		redraw();
		
		
	}
}
		
