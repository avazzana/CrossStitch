import java.awt.*;

import javax.swing.*;

public class ColorComponent extends JComponent {
	public int r;
	public int g;
	public int b;
	public final int prefW = 300;
	public final int prefH = 700;
	public int[][] colors;
	
	
	public ColorComponent() {
		r = 0;
		g = 0;
		b = 0;
		this.setPreferredSize(new Dimension(prefW, prefH));
	}
	
	public Color getColor() {
		return new Color(r, g, b);
	}
	
	public void setColor(int r1, int g1, int b1) {
		r = r1;
		g = g1;
		b = b1;
	}
	
	
	
	
	public void paintComponent(Graphics g2) {
		//g2.setColor(Color.white);
		//g2.fillRect(0, 0, prefW, prefH);
		g2.setColor(this.getColor());
		g2.fillRect(50, 150, 150, 150);
		g2.setColor(Color.black);
		g2.setFont(new Font("Arial", Font.PLAIN, 15));
		g2.drawString("current:", 100, 140);
		g2.drawRect(50, 120, 150, 30);
		g2.drawRect(50, 150, 150, 150);
		g2.drawString("red = " + r, 50, 320);
		g2.drawString("green = " + g, 50, 340);
		g2.drawString("blue = " + b, 50, 360);
	}

}
