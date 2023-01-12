
import java.awt.Color;
import java.awt.Graphics;

public class Square {
	public Colour col;
	public int x;
	public int y;
	public int s;
	public boolean h;
	public boolean c;
	
	public Square(Colour col, int x, int y, int s, boolean h) {
		this.col = col;
		this.x = x;
		this.y = y;
		this.s = s;
		this.h = h;
		c = false;
		
	}
	
	
	public void setColor(Colour col1) {
		col = col1;
	}
	
	
	public void setC(boolean b) {
		c = b && h;
	}
	
	public void draw(Graphics g2) {
		if (c) {
			col.setBlack(g2);
		}
		else {
			col.set(g2);
		}
		g2.fillRect(x, y, s, s);
		g2.setColor(Color.black);
		
	}

}
