import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Button {
	
	public int x;
	public int y;
	public int w;
	public int h;
	public Color c;
	public String txt;
	public boolean isClicked;
	
	public Button(int x, int y, int w, int h, Color c, String txt) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = c;
		this.txt = txt;
		isClicked = false;
	}
	
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, w, h);
		g.setColor(Color.black);
		g.drawRect(x, y, w, h);
		g.setFont(new Font("Arial", Font.PLAIN, 18));
		drawCenteredString(g, g.getFont());
		if (isClicked) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			g2.drawRect(x, y, w, h);
			g2.setStroke(new BasicStroke());
		}
	}
	
	public void setColor(Color c) {
		this.c = c;
	}
	
	public void drawCenteredString(Graphics g, Font font) {
	    FontMetrics metrics = g.getFontMetrics(font);
	   int x1 = x + (w - metrics.stringWidth(txt)) / 2;
	    int y1 = y + ((h - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setFont(font);
	    g.drawString(txt, x1, y1);
	}
	
	public boolean clicked(int xc, int yc) {
		if (xc > x - 5) {
			if (xc < x + w + 5) {
				if (yc > y - 5) {
					if (yc < y + h + 5) {
						isClicked = true;
						return true;
					}
				}
			}
		}
		isClicked = false;
		return false;
	}

}
