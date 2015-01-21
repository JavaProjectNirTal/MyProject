package viewBoard;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

class GameCharacter {
	private int x, y;
	private Image imageHomer;

	public GameCharacter(int x, int y) {
		this.x = x;
		this.y = y;
		imageHomer = new Image(null, "resources/homer.jpg");
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		System.out.println("GameCharacter setX(): x is now: "+x);
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		System.out.println("GameCharacter setY(): y is now: "+y);
		this.y = y;
	}

	public void paint(PaintEvent e, int w, int h) {
		// e.gc.setForeground(new Color(null, 255, 0, 0));
		e.gc.setBackground(new Color(null, 210, 210, 210));
		// e.gc.fillRectangle(x, y, w, h);
		e.gc.drawImage(imageHomer, 0, 0, imageHomer.getImageData().width, imageHomer.getImageData().height, x, y, w, h);

	}
}
