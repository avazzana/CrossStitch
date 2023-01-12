import java.awt.Color;
import java.awt.Graphics;

public class Colour {
	public int i;
	public int r;
	public int g;
	public int b;
	
	public Colour(int i, int r, int g, int b) {
		this.i = i;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public String toString() {
		return i + "_" + r + "_" + g + "_" + b + "\n";
	}
	
	public Color getColor() {
		return new Color(r, g, b);
	}
	
	public void set(Graphics g2) {
		g2.setColor(new Color(r, g, b));
	}
	
	public void setBlack(Graphics g2) {
		g2.setColor(new Color(0, 0, 0));
	}

}
