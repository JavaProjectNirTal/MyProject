package viewNwindow;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;

public class WelcomeWindow extends BasicWindow {

	GameWindow gameWindow;
	
	public WelcomeWindow(int width, int height, String title) {
		super(width, height, title);
		GameWindow gameWindow = null;
	}

	public GameWindow getGameWindow() {
		return gameWindow;
	}

	@Override
	void initWidgets() {
		//setting the Window:
		shell.setSize(700, 400);
		shell.setLayout(new GridLayout(2, true));
		
		//Game #1:
		Image gameImage1 = new Image(display,"resources/maze.jpg");
		Button game1maze = new Button(shell, SWT.PUSH);
		game1maze.setImage(gameImage1);
		game1maze.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, true));
		
		game1maze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				 //shell.getDisplay().syncExec(new Runnable() {
				//	public void run() {
						display.close();
						gameWindow =  new MazeGameWindow(500, 500, "MazeGameWindow");
						WelcomeWindow.this.setChanged();
						WelcomeWindow.this.notifyObservers(gameWindow);
						gameWindow.run();
						
				//	}
				//});
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//Game #2:
		Image gameImage2 = new Image(display,"resources/puzzle.jpg");
		Button game2puzzle = new Button(shell, SWT.PUSH);
		game2puzzle.setImage(gameImage2);
		game2puzzle.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, true));
		
		game2puzzle.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				 //shell.getDisplay().asyncExec(new Runnable() {
				//	public void run() {
						display.dispose();
						gameWindow =  new PuzzleGameWindow(500, 500, "PuzzleGameWindow");
						WelcomeWindow.this.setChanged();
						WelcomeWindow.this.notifyObservers(gameWindow);
						gameWindow.run();	
				//	}
			//	});
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	
	
	
	
	
}