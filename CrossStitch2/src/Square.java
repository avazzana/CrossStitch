
import java.awt.Color;
import java.awt.Graphics;

public class Square {
	public Colour col;
	public int x;
	public int y;
	public int s;
	public boolean h;
	public boolean c;
	public int[] lines;
	
	public Square(Colour col, int x, int y, int s, boolean h, int[] lines) {
		this.col = col;
		this.x = x;
		this.y = y;
		this.s = s;
		this.h = h;
		c = false;
		this.lines = lines;
		
	}
	
	public int getLinesToInt() {
		int ans = 0;
		int pwr = 8;
		for (int i = 0; i < 4; i++) {
			if (lines[i] == 1) {
				ans += pwr;
			}
			pwr /= 2;
		}
		return ans;
	}
	
	public void setColor(Colour col1) {
		col = col1;
	}
	
	public void setLine(int i) {
		if (i > 3) {
			return;
		}
			if (lines[i] == 1) {
				lines[i] = 0;
			}
			else {
				lines[i] = 1;
			}
		
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
		if (lines[0] == 1) {
			g2.fillRect(x, y, 4, s);
		}
		if (lines[1] == 1) {
			g2.fillRect(x, y, s, 4);
		}
		if (lines[2] == 1) {
			g2.fillRect(x + s - 4, y, 4, s);
		}
		if (lines[3] == 1) {
			g2.fillRect(x, y + s - 4, s, 4);
		}
	}

	public void negateLines(int outlinedir) {
		if (lines[outlinedir] == 1) {
			lines[outlinedir] = 0;
		}
		else {
			lines[outlinedir] = 1;
		}
		
	}

}
